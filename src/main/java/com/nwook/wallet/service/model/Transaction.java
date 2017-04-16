package com.nwook.wallet.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ToString
@EqualsAndHashCode
public class Transaction {

    @JsonProperty("transactionTime")
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date transactionTime;

    @JsonProperty("money")
    private Money money;

    @JsonProperty("transactionStatus")
    private TransactionStatus transactionStatus;

    @JsonProperty("transactionDescription")
    private TransactionDescription transactionDescription;

    public Transaction() {
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionDescription getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(TransactionDescription transactionDescription) {
        this.transactionDescription = transactionDescription;
    }
}
