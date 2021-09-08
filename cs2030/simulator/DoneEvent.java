package cs2030.simulator;

/**
 * The DoneEvent happens when a customer is done and server becomes free. It
 * returns a string with
 * the customer id and the time the customer is done being served.
 */

public class DoneEvent extends Event {

    /**
     * Constructor that creates a DoneEvent.
     * 
     * @param customer - a new customer
     * @param time     - time when the customer is done
     * @param server   - server serving the customer
     */

    public DoneEvent(Customer customer, double time, Server server) {
        super(customer, x -> {
            Server s = server;
            if (server instanceof HumanServers) {
                s = new HumanServers(server.getID(), true, 
                                    server.hasCust(), time,  server.getList(),
                                    server.getSupplier(), false);
            } else {
                s = new SelfCheckServers(server.getID(), true, 
                                        server.hasCust(), time, 
                                        server.getList(), server.getSupplier(), false);
            }

            return new Pair<Shop, Event>(x.replace(s), null);
        }, time, server);

    }

    @Override
    public String toString() {
        String s = "";
        if (getCust().getGreedy() == 1) {
            s = "(greedy)";
        }
        if (getServer().getID() <= Manager.numOfServers) {
            return String.format("%.3f", this.getTime()) + " " 
                    + this.getCust().getID() + s + " done serving by server "
                    + this.getServer().getID();
        } else {
            return String.format("%.3f", this.getTime()) 
                    + " " + this.getCust().getID() + s
                    + " done serving by self-check " + this.getServer().getID();
        }

    }
}
