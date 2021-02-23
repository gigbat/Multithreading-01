package fork;

import executor.MyCallable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import org.apache.log4j.Logger;
import util.SumUtil;

public class MyForkJoinPool extends RecursiveAction {
    private static final Logger logger = Logger.getLogger(MyForkJoinPool.class);
    private static final long NUMBER = 1_000_000;
    private static final int BOUND = 15;

    public MyForkJoinPool() {
        compute();
    }

    @Override
    protected void compute() {
        logger.info("Start -> Results of ForkJoinPool.");
        long startTime = System.currentTimeMillis();
        logger.info("Sum of 1_000_000 elements are: " + createSubtasks());
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(ForkJoinTask.adapt(new MyCallable()));
        long endTime = System.currentTimeMillis();
        logger.info("End -> Results of ExecutorService. Time: "
                + (endTime - startTime) + " ms.");
    }

    private Long createSubtasks() {
        return new SumUtil().randomSum(NUMBER, BOUND);
    }
}
