package executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyCallableTest {
    private static Callable<Long> callable1;
    private static Callable<Long> callable2;
    private static Callable<Long> callable3;
    private static Callable<Long> callable4;
    private static Callable<Long> callable5;

    private static FutureTask<Long> futureTask1;
    private static FutureTask<Long> futureTask2;
    private static FutureTask<Long> futureTask3;
    private static FutureTask<Long> futureTask4;
    private static FutureTask<Long> futureTask5;

    @BeforeAll
    private static void createCallable() {
        callable1 = new MyCallable(Stream.iterate(1L, n -> n + 1L).limit(1_000_000)
                .collect(Collectors.toList()));
        callable2 = new MyCallable(Stream.iterate(1L, n -> n + 1L).limit(700_000)
                .collect(Collectors.toList()));
        callable3 = new MyCallable(Stream.iterate(1L, n -> n + 1L).limit(600_000)
                .collect(Collectors.toList()));
        callable4 = new MyCallable(Stream.iterate(1L, n -> n + 1L).limit(500_000)
                .collect(Collectors.toList()));
        callable5 = new MyCallable(Stream.iterate(1L, n -> n + 1L).limit(400_000)
                .collect(Collectors.toList()));
    }

    @BeforeAll
    private static void startThreadFutureTask() {
        futureTask1 = new FutureTask<>(callable1);
        futureTask2 = new FutureTask<>(callable2);
        futureTask3 = new FutureTask<>(callable3);
        futureTask4 = new FutureTask<>(callable4);
        futureTask5 = new FutureTask<>(callable5);

        new Thread(futureTask1).start();
        new Thread(futureTask2).start();
        new Thread(futureTask3).start();
        new Thread(futureTask4).start();
        new Thread(futureTask5).start();
    }

    @Test
    void getSum_Ok() throws Exception {
        Assertions.assertEquals(500000500000L, futureTask1.get());
        Assertions.assertEquals(245000350000L, futureTask2.get());
        Assertions.assertEquals(180000300000L, futureTask3.get());
        Assertions.assertEquals(125000250000L, futureTask4.get());
        Assertions.assertEquals(80000200000L, futureTask5.get());
    }
}
