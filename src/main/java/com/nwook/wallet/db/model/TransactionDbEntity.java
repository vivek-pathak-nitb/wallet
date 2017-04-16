package com.nwook.wallet.db.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Transaction")
public class TransactionDbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private Long transactionId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_description_id")
    private TransactionDescriptionDbEntity transactionDescriptionDbEntity;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionTime;

    @Column(name = "transaction_status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletDbEntity walletDbEntity;

    public TransactionDbEntity() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionDescriptionDbEntity getTransactionDescriptionDbEntity() {
        return transactionDescriptionDbEntity;
    }

    public void setTransactionDescriptionDbEntity(TransactionDescriptionDbEntity transactionDescriptionDbEntity) {
        this.transactionDescriptionDbEntity = transactionDescriptionDbEntity;
    }

    public WalletDbEntity getWalletDbEntity() {
        return walletDbEntity;
    }

    public void setWalletDbEntity(WalletDbEntity walletDbEntity) {
        this.walletDbEntity = walletDbEntity;
    }
}
