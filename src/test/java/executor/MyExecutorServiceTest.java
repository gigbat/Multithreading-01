package executor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyExecutorServiceTest {
    private static MyExecutorService executor1;
    private static MyExecutorService executor2;
    private static MyExecutorService executor3;
    private static MyExecutorService executor4;
    private static MyExecutorService executor5;

    @BeforeAll
    private static void createExecutorService() {
        executor1 = new MyExecutorService(Stream.iterate(1L, n -> n + 1L).limit(1_000_000)
                .collect(Collectors.toList()));
        executor2 = new MyExecutorService(Stream.iterate(1L, n -> n + 1L).limit(700_000)
                .collect(Collectors.toList()));
        executor3 = new MyExecutorService(Stream.iterate(1L, n -> n + 1L).limit(600_000)
                .collect(Collectors.toList()));
        executor4 = new MyExecutorService(Stream.iterate(1L, n -> n + 1L).limit(500_000)
                .collect(Collectors.toList()));
        executor5 = new MyExecutorService(Stream.iterate(1L, n -> n + 1L).limit(400_000)
                .collect(Collectors.toList()));
    }

    @Test
    void getSum_Ok() throws Exception {
        Assertions.assertEquals(1784293664, executor1.calculate());
        Assertions.assertEquals(187214128, executor2.calculate());
        Assertions.assertEquals(-388326432, executor3.calculate());
        Assertions.assertEquals(446198416, executor4.calculate());
        Assertions.assertEquals(-1604178624, executor5.calculate());
    }
}
