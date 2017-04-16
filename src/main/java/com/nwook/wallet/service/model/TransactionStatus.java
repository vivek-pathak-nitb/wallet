package com.nwook.wallet.service.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionStatus {
    PENDING,
    SUCCESSFUL,
    FAILED;

    @JsonCreator
    public TransactionStatus forValue(final String value) {
        return TransactionStatus.valueOf(value);
    }

    @JsonValue
    public String toValue() {
        return this.toString();
    }
}
