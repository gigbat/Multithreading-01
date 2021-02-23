package executor;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;
import thread.MyThread;

public class MyExecutorService {
    private static final int THREADS = Runtime.getRuntime().availableProcessors();
    private static final Logger logger = Logger.getLogger(MyThread.class);

    private List<Integer> numbers;

    public MyExecutorService(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public Integer calculate() {
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        Future<Integer> future = executor.submit(new MyCallable(numbers));
        executor.shutdown();
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            logger.info("Can't calculate executor service", e);
        }
        throw new RuntimeException("Can't calculate executor service");
    }
}
