package executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyCallableTest {
    private static Callable<Integer> callable1;
    private static Callable<Integer> callable2;
    private static Callable<Integer> callable3;
    private static Callable<Integer> callable4;
    private static Callable<Integer> callable5;

    private static FutureTask<Integer> futureTask1;
    private static FutureTask<Integer> futureTask2;
    private static FutureTask<Integer> futureTask3;
    private static FutureTask<Integer> futureTask4;
    private static FutureTask<Integer> futureTask5;

    @BeforeAll
    private static void createCallable() {
        callable1 = new MyCallable(Stream.iterate(1, n -> n + 1).limit(1_000_000)
                .collect(Collectors.toList()));
        callable2 = new MyCallable(Stream.iterate(1, n -> n + 1).limit(700_000)
                .collect(Collectors.toList()));
        callable3 = new MyCallable(Stream.iterate(1, n -> n + 1).limit(600_000)
                .collect(Collectors.toList()));
        callable4 = new MyCallable(Stream.iterate(1, n -> n + 1).limit(500_000)
                .collect(Collectors.toList()));
        callable5 = new MyCallable(Stream.iterate(1, n -> n + 1).limit(400_000)
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
        Assertions.assertEquals(1784293664, futureTask1.get());
        Assertions.assertEquals(187214128, futureTask2.get());
        Assertions.assertEquals(-388326432, futureTask3.get());
        Assertions.assertEquals(446198416, futureTask4.get());
        Assertions.assertEquals(-1604178624, futureTask5.get());
    }
}