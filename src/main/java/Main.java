public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread myRunnable = new Thread(new MyRunnable(counter));
        myRunnable.start();

        MyThread myThread = new MyThread(counter);
        myThread.start();
    }
}
