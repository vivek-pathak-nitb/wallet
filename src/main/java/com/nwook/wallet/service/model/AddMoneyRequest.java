package com.nwook.wallet.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.math.BigInteger;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AddMoneyRequest {

    @JsonProperty("walletId")
    private Long walletId;

    @JsonProperty("amount")
    private BigDecimal amount;

    public AddMoneyRequest(final Long walletId,
                           final BigDecimal amount) {
        this.walletId = walletId;
        this.amount = amount;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
