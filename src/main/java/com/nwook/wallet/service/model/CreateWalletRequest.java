package com.nwook.wallet.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CreateWalletRequest {

    @JsonProperty("wallet")
    private Wallet wallet;

    @JsonProperty("user")
    private User user;

    public CreateWalletRequest(final Wallet wallet,
                               final User user) {
        this.wallet = wallet;
        this.user = user;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
