import org.apache.log4j.Logger;

public class MyRunnable implements Runnable {
    private static final Logger logger = Logger.getLogger(MyRunnable.class);
    private Counter counter;

    public void run() {
        counter = new Counter();
        synchronized (this.counter) {
            for (int i = 0; i <= 100; i++) {
                logger.info(Thread.currentThread().getName() + " value = "
                        + counter.calculate());
            }
        }
    }
}
