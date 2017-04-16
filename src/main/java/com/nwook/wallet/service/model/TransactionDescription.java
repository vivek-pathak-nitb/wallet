package com.nwook.wallet.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class TransactionDescription {

    @JsonProperty("comments")
    private String comments;

    public TransactionDescription() {
    }

    public TransactionDescription(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
