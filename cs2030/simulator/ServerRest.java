package cs2030.simulator;

/**
 * The Server_Rest class happens when a server takes a break.
 * His resting state becomes true and he will rest according 
 * to the random rest period generated
 */

public class ServerRest extends Event {

    /**
     * Constructor that creates a Server_Rest event.
     * 
     * @param customer - a new customer
     * @param time - time of the serve event
     * @param server   - the server who serves the customer
     */

    public ServerRest(Customer customer, double time, Server server) {
        super(customer, x -> {
            double restPeriod = server.getSupplier().get().getRestPeriod();

            Server tempServer = new HumanServers(server.getID(), 
                    server.isAvailable(), server.hasCust(),
                    time + restPeriod, server.getList(), server.getSupplier(), true);
            return new Pair<Shop, Event>(x.replace(tempServer),
                    new ServerBack(customer, time + restPeriod, tempServer));
        }, time, server);

    }

}