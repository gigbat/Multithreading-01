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
        Integer actual1 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1, n -> n + 1).limit(1_000_000)
                .collect(Collectors.toList())));
        Integer actual2 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1, n -> n + 1).limit(700_000)
                .collect(Collectors.toList())));
        Integer actual3 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1, n -> n + 1).limit(600_000)
                .collect(Collectors.toList())));
        Integer actual4 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1, n -> n + 1).limit(500_000)
                .collect(Collectors.toList())));
        Integer actual5 = forkJoinPool.invoke(new MyForkJoinPool(Stream.iterate(1, n -> n + 1).limit(400_000)
                .collect(Collectors.toList())));
        Assertions.assertEquals(1784293664, actual1);
        Assertions.assertEquals(187214128, actual2);
        Assertions.assertEquals(-388326432, actual3);
        Assertions.assertEquals(446198416, actual4);
        Assertions.assertEquals(-1604178624, actual5);
    }
}
