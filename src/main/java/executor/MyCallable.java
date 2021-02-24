package executor;

import java.util.List;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Long> {
    private List<Long> numbers;

    public MyCallable(List<Long> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Long call() throws Exception {
        return numbers.stream().mapToLong(Long::valueOf).sum();
    }
}
