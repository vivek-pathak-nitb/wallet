package com.nwook.wallet.service;

import com.nwook.wallet.converter.DbEntityToModelConverter;
import com.nwook.wallet.converter.ModelToDbEntityConverter;
import com.nwook.wallet.db.dao.TransactionDao;
import com.nwook.wallet.db.dao.UserDao;
import com.nwook.wallet.db.dao.WalletDao;
import com.nwook.wallet.db.model.TransactionDbEntity;
import com.nwook.wallet.db.model.TransactionDescriptionDbEntity;
import com.nwook.wallet.db.model.TransactionStatus;
import com.nwook.wallet.db.model.WalletDbEntity;
import com.nwook.wallet.service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/v1/wallet")
public class WalletService {

    private final TransactionDao transactionDao;
    private final WalletDao walletDao;
    private final UserDao userDao;

    private final ModelToDbEntityConverter modelToDbEntityConverter;
    private final DbEntityToModelConverter dbEntityToModelConverter;

    @Autowired
    public WalletService(final TransactionDao transactionDao,
                         final WalletDao walletDao,
                         final UserDao userDao,
                         final ModelToDbEntityConverter modelToDbEntityConverter,
                         final DbEntityToModelConverter dbEntityToModelConverter) {

        this.transactionDao = transactionDao;
        this.walletDao = walletDao;
        this.userDao = userDao;
        this.modelToDbEntityConverter = modelToDbEntityConverter;
        this.dbEntityToModelConverter = dbEntityToModelConverter;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create/")
    @ResponseBody
    public GenericResponse createWallet(@RequestBody final CreateWalletRequest createWalletRequest) {
        final User user = createWalletRequest.getUser();
        final Wallet wallet = createWalletRequest.getWallet();
        final WalletDbEntity walletDbEntity = modelToDbEntityConverter.toWalletDbEntity(wallet, user);
        walletDao.save(walletDbEntity);
        return new GenericResponse(200, "Wallet created");
    }


    @RequestMapping(method = RequestMethod.POST, path = "/add")
    @ResponseBody
    public GenericResponse add(@RequestBody final AddMoneyRequest addMoneyRequest) {
        // Updating wallet
        final Long id = addMoneyRequest.getWalletId();
        final BigDecimal amount = addMoneyRequest.getAmount();
        final WalletDbEntity walletDbEntity = walletDao.findByWalletId(id);
        final BigDecimal newBalance = walletDbEntity.getBalance().add(amount);
        walletDbEntity.setBalance(newBalance);
        walletDao.save(walletDbEntity);

        // Updating transaction
        final TransactionDbEntity transactionDbEntity = new TransactionDbEntity();
        transactionDbEntity.setAmount(amount);
        transactionDbEntity.setTransactionTime(new Date());
        transactionDbEntity.setTransactionStatus(TransactionStatus.SUCCESSFUL);
        transactionDbEntity.setWalletDbEntity(walletDbEntity);

        final TransactionDescriptionDbEntity transactionDescriptionDbEntity = new TransactionDescriptionDbEntity();
        transactionDescriptionDbEntity.setComments("Comments");
        transactionDbEntity.setTransactionDescriptionDbEntity(transactionDescriptionDbEntity);

        transactionDao.save(transactionDbEntity);

        return new GenericResponse(200, "Money added successfully");
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/withDraw/{walletId}")
    @ResponseBody
    public GenericResponse withDraw(@PathVariable(value = "walletId") final Long walletId,
                                    @RequestBody final Money money) {
        final WalletDbEntity walletDbEntity = walletDao.findByWalletId(walletId);
        final BigDecimal amountInWallet = walletDbEntity.getBalance();
        final BigDecimal moneyToWithdraw = money.getAmount();
        if (amountInWallet.compareTo(moneyToWithdraw) >= 0) {
            final BigDecimal amountRemaining = amountInWallet.subtract(moneyToWithdraw);
            walletDbEntity.setBalance(amountRemaining);
            walletDao.save(walletDbEntity);

            // Updating transaction table
            final TransactionDbEntity transactionDbEntity = new TransactionDbEntity();
            transactionDbEntity.setAmount(moneyToWithdraw);
            transactionDbEntity.setTransactionTime(new Date());
            transactionDbEntity.setTransactionStatus(TransactionStatus.SUCCESSFUL);
            transactionDbEntity.setWalletDbEntity(walletDbEntity);

            final TransactionDescriptionDbEntity transactionDescriptionDbEntity = new TransactionDescriptionDbEntity();
            transactionDescriptionDbEntity.setComments("Comments");
            transactionDbEntity.setTransactionDescriptionDbEntity(transactionDescriptionDbEntity);

            transactionDao.save(transactionDbEntity);
        }

        return new GenericResponse(200, "Money withdrawn successfully");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/balance/{userAccountId}")
    @ResponseBody
    public Money getBalance(@PathVariable(value = "userAccountId") final Long accountId) {
        final WalletDbEntity walletDbEntity = walletDao.findByUserDbEntity(userDao.findByUserId(accountId));
        return new Money(walletDbEntity.getBalance(), Currency.getInstance(walletDbEntity.getCurrency()));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/transaction/{walletId}")
    @ResponseBody
    public List<Transaction> listTransaction(@PathVariable(value = "walletId") final Long walletId) {
        final List<TransactionDbEntity> transactionDbEntities = transactionDao.
                findByWalletDbEntity(walletDao.findByWalletId(walletId));
        final List<Transaction> transactions = new ArrayList<>();
        for (final TransactionDbEntity transactionDbEntity : transactionDbEntities) {
            transactions.add(dbEntityToModelConverter.toTransaction(transactionDbEntity));
        }
        return transactions;
    }

}
