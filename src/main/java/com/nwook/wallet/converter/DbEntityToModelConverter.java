package com.nwook.wallet.converter;

import com.nwook.wallet.db.model.TransactionDbEntity;
import com.nwook.wallet.db.model.TransactionDescriptionDbEntity;
import com.nwook.wallet.service.model.Money;
import com.nwook.wallet.service.model.Transaction;
import com.nwook.wallet.service.model.TransactionDescription;
import com.nwook.wallet.service.model.TransactionStatus;
import org.springframework.stereotype.Service;

import java.util.Currency;

@Service
public class DbEntityToModelConverter {

    public Transaction toTransaction(final TransactionDbEntity transactionDbEntity) {
        final Transaction transaction = new Transaction();
        transaction.setMoney(new Money(transactionDbEntity.getAmount(), Currency.getInstance("IN")));
        transaction.setTransactionTime(transactionDbEntity.getTransactionTime());

        transaction.setTransactionDescription(
                toTransactionDescription(transactionDbEntity.getTransactionDescriptionDbEntity()));

        transaction.setTransactionStatus(TransactionStatus.valueOf(transactionDbEntity.getTransactionStatus().name()));
        return transaction;
    }

    private TransactionDescription toTransactionDescription(final TransactionDescriptionDbEntity transactionDescriptionDbEntity) {
        final TransactionDescription transactionDescription = new TransactionDescription();
        transactionDescription.setComments(transactionDescriptionDbEntity.getComments());
        return transactionDescription;
    }
}
