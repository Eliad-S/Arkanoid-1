package game;

/**
 * The type Counter.
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     *
     * @param number the number
     */
    public Counter(int number) {
        this.count = number;
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease, subtract number from current count.
     *
     * @param number how much to decrease.
     */
    public void decrease(int number) {
        this.count -= number;
        validTheInput();
    }


    /**
     * Valid the input.
     */
    void validTheInput() {
        if (this.count < 0) {
            this.count = 0;
        }
    }

    /**
     * Gets value, get current count.
     *
     * @return the value
     */
    int getValue() {
        return this.count;
    }
}