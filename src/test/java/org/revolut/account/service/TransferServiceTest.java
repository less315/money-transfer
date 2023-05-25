package org.revolut.account.service;

import org.junit.jupiter.api.Test;
import org.revolut.account.model.Account;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TransferServiceTest {

    TransferService service = new TransferService();

    @Test
    void testTransferSameAccount() {
        Account account = new Account(1);

        assertThrows(IllegalArgumentException.class,
                () -> service.transfer(account, account, null));
    }

    @Test
    void testTransfer() {
        Account account1 = new Account(1);
        Account account2 = new Account(0);

        service.transfer(account1, account2, BigDecimal.ONE);

        assertEquals(0, BigDecimal.ZERO.compareTo(account1.getBalance()));
        assertEquals(0, BigDecimal.ONE.compareTo(account2.getBalance()));
    }
}