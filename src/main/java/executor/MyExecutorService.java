package executor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import org.apache.commons.collections4.ListUtils;
import org.apache.log4j.Logger;
import thread.MyThread;

public class MyExecutorService {
    private static final int THREADS = Runtime.getRuntime().availableProcessors();
    private static final Logger logger = Logger.getLogger(MyThread.class);

    private List<Long> numbers;

    public MyExecutorService(List<Long> numbers) {
        this.numbers = numbers;
    }

    public Integer calculate() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);

        List<List<Long>> partOfTheLists = ListUtils.partition(numbers, numbers.size() / THREADS);
        List<MyCallable> callables = partOfTheLists.stream()
                .map(x -> new MyCallable(x))
                .collect(Collectors.toList());
        List<Future<Long>> futures = null;

        try {
            futures = executor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return getSum(futures);
    }

    private int getSum(List<Future<Long>> futures) {
        int sum = 0;
        for (Future<Long> future : futures) {
            try {
                sum += future.get();
            } catch (InterruptedException | ExecutionException e) {
                logger.info("Can't calculate executor service", e);
            }
        }
        return sum;
    }
}
