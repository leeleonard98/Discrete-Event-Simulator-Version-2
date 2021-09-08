package cs2030.simulator;

/**
 * The WaitEvent class happens when the servers are busy. It returns a string of
 * the customer id and time where customer waits and also the server that serves
 * him 
 */

public class WaitEvent extends Event {

    /**
     * Constructor that creates a WaitEvent.
     * 
     * @param customer - a new customer
     * @param time     - time of the customer waiting
     * @param server   - server serving the customer
     */

    public WaitEvent(Customer customer, double time, Server server) {
        super(customer, x -> {
            Server tempServer = server;
            if (server instanceof HumanServers) {
                tempServer = new HumanServers(server.getID(), false, false, server.getAvailTime(), 
                        server.getList(), server.getSupplier(), false);
            } else {
                tempServer = new SelfCheckServers(server.getID(), 
                        false, false, server.getAvailTime(),
                        server.getList(), server.getSupplier(), false);
            }
            tempServer.getList().add(customer);

            return new Pair<Shop, Event>(x.replace(tempServer), null);
        }, time, server);

    }

    @Override
    public String toString() {
        String s = "";
        if (getCust().getGreedy() == 1) {
            s = "(greedy)";
        }
        if (getServer().getID() <= Manager.numOfServers) {
            return String.format("%.3f", this.getTime()) + " " + this.getCust().getID() + s
                    + " waits to be served by server " + this.getServer().getID();
        } else {
            return String.format("%.3f", this.getTime()) + " " + this.getCust().getID() + s
                    + " waits to be served by self-check " + this.getServer().getID();
        }

    }
}
