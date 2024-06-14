package rgo.wm.spring.jdbc;

import org.springframework.transaction.support.TransactionOperations;

import java.util.function.Supplier;

public class TxWrapper {

    private final TransactionOperations tx;

    public TxWrapper(TransactionOperations tx) {
        this.tx = tx;
    }

    public void tx(Runnable runnable) {
        tx.execute(status -> {
            runnable.run();
            return null;
        });
    }

    public <T> T tx(Supplier<T> supplier) {
        return tx.execute(status -> supplier.get());
    }
}
