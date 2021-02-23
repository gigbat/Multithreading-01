package executor;

import java.util.List;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private List<Integer> numbers;

    public MyCallable(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public Integer call() throws Exception {
        return numbers.stream().mapToInt(Integer::valueOf).sum();
    }
}
