package fork;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyForkJoinPoolTest {
    private static ForkJoinPool forkJoinPool;

    @BeforeAll
    private static void create() {
        forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    }

    @Test
    void getSum_Ok() {
        Long actual1 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1L, n -> n + 1L).limit(1_000_000)
                .collect(Collectors.toList())));
        Long actual2 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1L, n -> n + 1L).limit(700_000)
                .collect(Collectors.toList())));
        Long actual3 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1L, n -> n + 1L).limit(600_000)
                .collect(Collectors.toList())));
        Long actual4 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1L, n -> n + 1L).limit(500_000)
                .collect(Collectors.toList())));
        Long actual5 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1L, n -> n + 1L).limit(400_000)
                .collect(Collectors.toList())));
        Assertions.assertEquals(500000500000L, actual1);
        Assertions.assertEquals(245000350000L, actual2);
        Assertions.assertEquals(180000300000L, actual3);
        Assertions.assertEquals(125000250000L, actual4);
        Assertions.assertEquals(80000200000L, actual5);
    }
}
