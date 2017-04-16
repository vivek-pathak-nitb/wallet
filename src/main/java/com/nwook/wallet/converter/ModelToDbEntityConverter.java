package com.nwook.wallet.converter;

import com.nwook.wallet.db.model.*;
import com.nwook.wallet.service.model.Transaction;
import com.nwook.wallet.service.model.TransactionDescription;
import com.nwook.wallet.service.model.User;
import com.nwook.wallet.service.model.Wallet;
import org.springframework.stereotype.Service;

@Service
public class ModelToDbEntityConverter {

    private UserDbEntity toUserDbEntity(final User user) {
        final UserDbEntity userDbEntity = new UserDbEntity();
        userDbEntity.setEmail(user.getEmail());
        userDbEntity.setMobileNo(user.getMobileNo());
        userDbEntity.setName(user.getName());
        return userDbEntity;
    }

    public WalletDbEntity toWalletDbEntity(final Wallet wallet,
                                           final User user) {
        final WalletDbEntity walletDbEntity = new WalletDbEntity();
        walletDbEntity.setBalance(wallet.getBalance().getAmount());
        walletDbEntity.setCurrency(wallet.getBalance().getCurrency().getCurrencyCode());
        walletDbEntity.setUserDbEntity(toUserDbEntity(user));
        return walletDbEntity;
    }

    public TransactionDbEntity toTransactionDbEntity(final Transaction transaction) {
        final TransactionDbEntity transactionDbEntity = new TransactionDbEntity();
        transactionDbEntity.setAmount(transaction.getMoney().getAmount());
        transactionDbEntity.setTransactionTime(transaction.getTransactionTime());
        transactionDbEntity.setTransactionDescriptionDbEntity(
                toTransactionDescriptionDbEntity(transaction.getTransactionDescription()));
        transactionDbEntity.setTransactionStatus(TransactionStatus.valueOf(transaction.getTransactionStatus().name()));
        return transactionDbEntity;
    }

    private TransactionDescriptionDbEntity toTransactionDescriptionDbEntity(final TransactionDescription transactionDescription) {
        final TransactionDescriptionDbEntity transactionDescriptionDbEntity = new TransactionDescriptionDbEntity();
        transactionDescriptionDbEntity.setComments(transactionDescription.getComments());
        return transactionDescriptionDbEntity;
    }

}
