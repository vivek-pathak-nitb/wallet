package com.nwook.wallet.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Currency;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Money {

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("currency")
    @JsonDeserialize(using = CurrencyDeserializer.class)
    private Currency currency;

    public Money(final BigDecimal amount,
                 final Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Currency getCurrency() {
        return currency;
    }
}
