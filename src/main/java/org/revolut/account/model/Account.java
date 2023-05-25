package org.revolut.account.model;

import java.math.BigDecimal;

public class Account {
    private final AtomicBigDecimal balance;

    public Account(double balance) {
        this.balance = new AtomicBigDecimal(balance);
    }

    public BigDecimal getBalance() {
        return balance.get();
    }

    public BigDecimal withdraw(BigDecimal amount) {
        checkAmountValue(amount);

        BigDecimal negativeAmount = amount.multiply(BigDecimal.valueOf(-1));
        BigDecimal currentValue = balance.get();
        BigDecimal result = balance.updateAndGet(v -> v.compareTo(amount) >= 0 ? v.add(negativeAmount) : v);
        if (result.compareTo(currentValue) == 0) {
            throw new InsufficientFundsException(amount);
        }
        return result;
    }

    private static void checkAmountValue(BigDecimal amount) {
        if(amount == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if(amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public BigDecimal deposit(BigDecimal amount) {
        checkAmountValue(amount);
        return balance.addAndGet(amount);
    }
}
