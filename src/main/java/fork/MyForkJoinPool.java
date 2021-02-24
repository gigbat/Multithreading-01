package fork;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class MyForkJoinPool extends RecursiveTask<Long> {
    private static final int THRESHOLD = 500_000;
    private List<Long> numbers;

    public MyForkJoinPool(List<Long> numbers) {
        this.numbers = numbers;
    }

    @Override
    protected Long compute() {
        if (numbers.size() > THRESHOLD) {
            return ForkJoinTask.invokeAll(createSubtasks())
                    .stream()
                    .mapToLong(ForkJoinTask::join)
                    .sum();
        }
        return processing();
    }

    private Collection<MyForkJoinPool> createSubtasks() {
        List<MyForkJoinPool> dividedTasks = new ArrayList<>();
        dividedTasks.add(new MyForkJoinPool(numbers.subList(0, numbers.size() / 2)));
        dividedTasks.add(new MyForkJoinPool(numbers.subList(numbers.size() / 2, numbers.size())));
        return dividedTasks;
    }

    private Long processing() {
        return numbers.stream().mapToLong(Long::valueOf).sum();
    }
}
