import thread.Counter;
import thread.MyRunnable;
import thread.MyThread;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread myRunnable = new Thread(new MyRunnable(counter));
        myRunnable.start();

        MyThread myThread = new MyThread(counter);
        myThread.start();

        int sum = 0;
        for (int i = 1; i <= 1_000_000; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
