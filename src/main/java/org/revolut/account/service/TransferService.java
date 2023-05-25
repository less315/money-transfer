package org.revolut.account.service;

import org.revolut.account.model.Account;

import java.math.BigDecimal;

public class TransferService {

    public void transfer(Account from, Account to, BigDecimal amount) {
        if(from.equals(to)) {
            throw new IllegalArgumentException("Cannot transfer to the same account");
        }

        from.withdraw(amount);
        to.deposit(amount);
    }
}
