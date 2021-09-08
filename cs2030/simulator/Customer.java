package cs2030.simulator;

import java.util.function.Supplier;

/**
 * Defines a customer. The Customer class takes in the customer id, the time
 * of his arrival, a supplier that generates service time and also the state
 * of the customer (greedy).
 */

public class Customer {
    private final int custID;
    private final double time;
    private final Supplier<Double> supplier;
    private final int greedyState;

    /**
     * Constructor that creates a customer.
     * 
     * @param custID - the id of the customer
     * @param time   - the time which the customer arrives
     * @param supplier - the supplier that generates service time
     * @param greedyState - the state of the customer
     */

    public Customer(int custID, double time, Supplier<Double> supplier, int greedyState) {
        this.custID = custID;
        this.time = time;
        this.supplier = supplier;
        this.greedyState = greedyState;
    }

    /**
     * Constructor that creates a customer.
     * 
     * @param custID - the id of the customer
     * @param time   - the time which the customer arrives
     */

    public Customer(int custID, double time) {
        this.custID = custID;
        this.time = time;
        this.supplier = null;
        this.greedyState = 0;
    }

    public int getID() {
        return this.custID;
    }

    public int getGreedy() {
        return this.greedyState;
    }

    public Supplier<Double> getSupplier() {
        return this.supplier;
    }

    public double getServiceTime() {
        return this.supplier.get();
    }

    public double getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return this.custID + " arrives at " + this.time;
    }
}