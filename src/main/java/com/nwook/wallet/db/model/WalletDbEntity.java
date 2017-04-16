package com.nwook.wallet.db.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "Wallet")
public class WalletDbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wallet_id")
    private Long walletId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "currency")
    private String currency;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "walletDbEntity")
    private Set<TransactionDbEntity> transactionDbEntities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserDbEntity userDbEntity;

    public WalletDbEntity() {
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public UserDbEntity getUserDbEntity() {
        return userDbEntity;
    }

    public void setUserDbEntity(UserDbEntity userDbEntity) {
        this.userDbEntity = userDbEntity;
    }

    public Set<TransactionDbEntity> getTransactionDbEntities() {
        return transactionDbEntities;
    }

    public void setTransactionDbEntities(Set<TransactionDbEntity> transactionDbEntities) {
        this.transactionDbEntities = transactionDbEntities;
    }
}
