public class Counter {
    private int count = 0;

    public int increment() {
        return count++;
    }

    public int getValue() {
        return count;
    }
}
