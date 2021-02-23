import executor.MyExecutorService;
import fork.MyForkJoinPool;
import thread.Counter;
import thread.MyRunnable;
import thread.MyThread;

public class Main {
    public static void main(String[] args) throws Exception {
        Counter counter = new Counter();
        Thread myRunnable = new Thread(new MyRunnable(counter));
        myRunnable.start();

        MyThread myThread = new MyThread(counter);
        myThread.start();

        MyExecutorService myExecutorService = new MyExecutorService();
        myExecutorService.sum();

        MyForkJoinPool myForkJoinPool = new MyForkJoinPool();
    }
}
