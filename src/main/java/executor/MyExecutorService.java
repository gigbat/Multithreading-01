package executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;

public class MyExecutorService {
    private static final Logger logger = Logger.getLogger(MyExecutorService.class);
    private static final int THREAD_POOL = 4;

    public void sum() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL);
        Future<Long> future = executorService.submit(new MyCallable());
        executorService.shutdown();
        long startTime = System.currentTimeMillis();
        logger.info("Start -> Results of ExecutorService.");
        logger.info("Sum of 1_000_000 elements are: " + future.get());
        long endTime = System.currentTimeMillis();
        logger.info("End -> Results of ExecutorService. Time: "
                + (endTime - startTime) + " ms.");
    }
}
