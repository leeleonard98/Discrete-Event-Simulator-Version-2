package cs2030.simulator;

/**
 * The Pair class takes in 2 Objects of type T and U, and returns a pair.
 */

public class Pair<T, U> {
    private final T t;
    private final U u;

    /**
     * Constructor that creates a Pair.
     * @param t - Object of type T
     * @param u - Object of type U
     */

    public Pair(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public static <T, U> Pair<T, U> of(T t, U u) {
        return new Pair<T, U>(t, u);
    }

    public T first() {
        return this.t;
    }

    public U second() {
        return this.u;
    }
}