package rgo.wm.spring.jdbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.support.TransactionOperations;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TxWrapperTest {

    private TxWrapper txWrapper;
    private TransactionOperations tx;

    @BeforeEach
    void setUp() {
        tx = mock(TransactionOperations.class);
        txWrapper = new TxWrapper(tx);
    }

    @Test
    void tx_runnable() {
        Runnable runnable = mock(Runnable.class);
        doAnswer(invocation -> {
            runnable.run();
            return null;
        }).when(tx).execute(any());

        txWrapper.tx(runnable);

        verify(tx).execute(any());
        verify(runnable).run();
    }

    @Test
    @SuppressWarnings("unchecked")
    void tx_supplier() {
        Supplier<Object> supplier = mock(Supplier.class);
        Object expected = new Object();
        when(supplier.get()).thenReturn(expected);
        doAnswer(invocation -> supplier.get())
                .when(tx).execute(any());

        Object actual = txWrapper.tx(supplier);

        verify(tx).execute(any());
        verify(supplier).get();
        assertThat(actual).isEqualTo(expected);
    }
}