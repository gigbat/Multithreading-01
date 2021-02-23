import org.apache.log4j.Logger;

public class MyRunnable implements Runnable {
    private static final Logger logger = Logger.getLogger(MyRunnable.class);
    private Counter counter;

    public MyRunnable(Counter counter) {
        this.counter = counter;
    }

    public void run() {
        synchronized (this.counter) {
            for (int i = 0; i <= 100; i++) {
                logger.info(Thread.currentThread().getName() + " value = "
                        + counter.calculate());
            }
        }
    }
}
