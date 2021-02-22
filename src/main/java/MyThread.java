import org.apache.log4j.Logger;

public class MyThread extends Thread {
    private static final Logger logger = Logger.getLogger(MyThread.class);
    private Counter counter;

    @Override
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
