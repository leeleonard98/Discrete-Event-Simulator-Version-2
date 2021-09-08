package cs2030.simulator;

/**
 * The Server_Back class happens when a server returns from his break.
 * His resting state becomes false and is ready to serve
 * He will serve a customer if there are customers waiting under him
 */

public class ServerBack extends Event {

    /**
     * Constructor that creates a Server_Back Event.
     * 
     * @param customer - a new customer
     * @param time - time of the serve event
     * @param server   - the server who serves the customer
     */

    public ServerBack(Customer customer, double time, Server server) {
        super(customer, x -> {

            Server tempServer = new HumanServers(server.getID(), 
                    server.isAvailable(), server.hasCust(), time,
                     server.getList(), server.getSupplier(), false);

            if (server.getList().size() > 0) {
                return new Pair<Shop, Event>(x.replace(tempServer),
                        new ServeEvent(tempServer.getList().get(0), time, server));
            }
            return new Pair<Shop, Event>(x.replace(tempServer), null);
        }, time, server);

    }
}
