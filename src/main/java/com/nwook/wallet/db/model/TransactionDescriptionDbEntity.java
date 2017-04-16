package com.nwook.wallet.db.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "TransactionDescription")
public class TransactionDescriptionDbEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_description_id")
    private Long transactionDescriptionId;

    @Column(name = "comments")
    private String comments;

    @OneToOne(mappedBy = "transactionDescriptionDbEntity")
    private TransactionDbEntity transactionDbEntity;

    public TransactionDescriptionDbEntity() {
    }

    public Long getTransactionDescriptionId() {
        return transactionDescriptionId;
    }

    public void setTransactionDescriptionId(Long transactionDescriptionId) {
        this.transactionDescriptionId = transactionDescriptionId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public TransactionDbEntity getTransactionDbEntity() {
        return transactionDbEntity;
    }

    public void setTransactionDbEntity(TransactionDbEntity transactionDbEntity) {
        this.transactionDbEntity = transactionDbEntity;
    }
}
