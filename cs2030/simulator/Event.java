package cs2030.simulator;

import java.util.function.Function;

/**
 * The Event class is an abstract class. It takes in a customer, function to return a pair,
 * time of event and a server for the event.
 */

public abstract class Event {
    private final Customer customer;
    private final Function<Shop, Pair<Shop, Event>> function;
    private final double time;
    private final Server server;

    /**
     * Constructor that creates a customer.
     * 
     * @param customer - a new customer
     * @param function - function that takes in a shop, returns a pair of shop and event
     * @param time - time of event
     * @param server  - the server for the event
     */

    public Event(Customer customer, Function<Shop, 
                Pair<Shop, Event>> function, double time, Server server) {
        this.customer = customer;
        this.function = function;
        this.time = time;
        this.server = server;

    }

    public Server getServer() {
        return this.server;
    }

    public Customer getCust() {
        return this.customer;
    }

    public double getTime() {
        return this.time;
    }

    public final Pair<Shop, Event> execute(Shop shop) {
        return this.function.apply(shop);
    }

}