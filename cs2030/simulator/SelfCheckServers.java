package cs2030.simulator;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * The SelfCheckServers class is a self check counter. It
 * extends the server class and has the serverID starts after
 * the last human server.
 */

public class SelfCheckServers extends Server {

    /**
     * Overloaded Constructor that creates a Self check counter server.
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


    public SelfCheckServers(int serverID, boolean isAvailable, 
                            boolean hasWaitingCustomer, double nextAvailableTime,
                            ArrayList<Customer> list, Supplier<Manager> supplier, boolean resting) {
        super(serverID, isAvailable, hasWaitingCustomer, 
                nextAvailableTime, list, supplier, resting);

    }

}
