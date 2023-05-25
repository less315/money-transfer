package org.revolut.account.model;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class AtomicBigDecimal {

    private final AtomicReference<BigDecimal> value;

    public AtomicBigDecimal(double value) {
        this.value = new AtomicReference<>(BigDecimal.valueOf(value));
    }

    public BigDecimal get() {
        return value.get();
    }

    public BigDecimal addAndGet(BigDecimal delta) {
        return value.accumulateAndGet(delta, BigDecimal::add);
    }

    public BigDecimal updateAndGet(UnaryOperator<BigDecimal> updateFunction) {
        return value.updateAndGet(updateFunction);
    }
}
