package org.revolut.account.model;

import java.math.BigDecimal;

import static java.lang.String.format;

public class InsufficientFundsException extends RuntimeException {

    private static final String MESSAGE_PATTERN = "Insufficient funds for withdrawal %s";
    private final BigDecimal amount;
    public InsufficientFundsException(BigDecimal amount) {
        super(format(MESSAGE_PATTERN, amount));
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
