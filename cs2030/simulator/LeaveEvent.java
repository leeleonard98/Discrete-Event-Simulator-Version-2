
package cs2030.simulator;

/**
 * LeaveEvent happens when a customer leaves. This class returns a string with
 * the customer id and the time the customer leaves.
 */

public class LeaveEvent extends Event {

    /**
     * Constructor that creates a LeaveEvent.
     * 
     * @param customer - a customer
     * @param time     - the time when customer leaves
     * @param server  - the server that served the customer
     * 
     */

    public LeaveEvent(Customer customer, double time, Server server) {
        super(customer, x -> new Pair<Shop, Event>(x, null), time, server);

    }

    @Override
    public String toString() {
        String s = "";
        if (getCust().getGreedy() == 1) {
            s = "(greedy)";
        }
        return String.format("%.3f", this.getTime()) + " " + this.getCust().getID() + s + " leaves";
    }

}
