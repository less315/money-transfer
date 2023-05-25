package org.revolut.account.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    public void testWithdraw(){
        assertEquals(BigDecimal.valueOf(40.0), new Account(100.0).withdraw(BigDecimal.valueOf(60.0)));
    }

    @Test
    public void testWithdrawSameAmount(){
        assertEquals(BigDecimal.valueOf(0.0), new Account(100.0).withdraw(BigDecimal.valueOf(100.0)));
    }

    @Test
    public void testWithdrawNotEnoughMoney(){
        assertThrows(InsufficientFundsException.class,
                () -> new Account(100.0).withdraw(BigDecimal.valueOf(100.5)));
    }

    @Test
    public void testDeposit(){
        assertEquals(BigDecimal.valueOf(160.0), new Account(100.0).deposit(BigDecimal.valueOf(60.0)));
    }

    @Test
    void testWithdrawNullSum() {
        assertThrows(IllegalArgumentException.class,
                () -> new Account(1).withdraw(null));
    }

    @Test
    void testTransferZeroSum() {
        assertThrows(IllegalArgumentException.class,
                () -> new Account(1).withdraw(BigDecimal.ZERO));
    }
}