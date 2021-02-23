package executor;

import java.util.concurrent.Callable;
import util.SumUtil;

public class MyCallable implements Callable<Long> {
    private static final int BOUND = 12;
    private static final long NUMBER = 1_000_000;

    @Override
    public Long call() throws Exception {
        return new SumUtil().randomSum(NUMBER, BOUND);
    }
}
