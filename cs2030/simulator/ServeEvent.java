package cs2030.simulator;

/**
 * The ServeEvent class happens when a customer is being served. It returns a
 * string that consists of the time the customer is being served and also the
 * server id and customer id. 
 */

public class ServeEvent extends Event {

    /**
     * Constructor that creates a ServeEvent.
     * 
     * @param customer - a new customer
     * @param time - time of the serve event
     * @param server   - the server who serves the customer
     */

    public ServeEvent(Customer customer, double time, Server server) {

        super(customer, x -> {
            double serviceTime = customer.getServiceTime();
            Server tempServer = server;
            if (server instanceof HumanServers) {
                tempServer = new HumanServers(server.getID(), 
                            false, server.hasCust(), time + serviceTime,
                         server.getList(), server.getSupplier(), false);
            } else {
                tempServer = new SelfCheckServers(server.getID(), 
                        false, server.hasCust(), time + serviceTime,
                        server.getList(), server.getSupplier(), false);
            }

            if (tempServer.getList().size() > 0) {
                tempServer.getList().remove(0);
            }
            return new Pair<Shop, Event>(x.replace(tempServer), 
                        new DoneEvent(customer, time + serviceTime, server));
        }, time, server);

    }

    @Override
    public String toString() {
        String s = "";
        if (getCust().getGreedy() == 1) {
            s = "(greedy)";
        }
        if (getServer().getID() <= Manager.numOfServers) {
            return String.format("%.3f", this.getTime()) + " " + 
                    this.getCust().getID() + s + " served by server "
                    + this.getServer().getID();
        } else {
            return String.format("%.3f", this.getTime()) + " " + 
                    this.getCust().getID() + s + " served by self-check "
                    + this.getServer().getID();
        }

    }
}
