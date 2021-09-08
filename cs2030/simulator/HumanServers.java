package cs2030.simulator;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * The HumanServers class defines a human server.
 */

public class HumanServers extends Server {

    /**
     * Constructor that creates a Human server.
     * 
     * @param serverID           - the id of the server
     * @param isAvailable        - the availability of the server
     * @param hasWaitingCustomer - the status of whether a server has a waiting
     *                           customer
     * @param nextAvailableTime  - the time when the server finish serving all
     *                           customers he has
     * @param list               - list of customers waiting under the server
     * @param supplier           - supplier that takes in a Manager
     * @param resting            - status of whether the server is resting
     */

    public HumanServers(int serverID, boolean isAvailable, 
                        boolean hasWaitingCustomer, double nextAvailableTime,
            ArrayList<Customer> list, Supplier<Manager> supplier, boolean resting) {
        super(serverID, isAvailable, hasWaitingCustomer, 
            nextAvailableTime, list, supplier, resting);

    }

}
