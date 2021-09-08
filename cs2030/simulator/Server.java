package cs2030.simulator;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * The Server class defines a server. It takes in the server id, 2 variables to
 * check if a server is busy and has a waiting customer respectively, and also
 * the next time when the server is free.
 * It also takes in a list of customers waiting under the server, supplier of a manager
 * and a server state of whether the server is resting
 */

public class Server {
    private final int serverID;
    private final boolean isAvailable;
    private final boolean hasWaitingCustomer;
    private final double nextAvailTime;
    private final ArrayList<Customer> list;
    private final Supplier<Manager> supplier;
    private final boolean resting;

    /**
     * Constructor that creates a server.
     * 
     * @param serverID           - the id of the server
     * @param isAvailable        - the availability of the server
     * @param hasWaitingCustomer - the status of whether a server has a waiting
     *                           customer
     * @param nextAvailableTime  - the time when the server finish serving all
     *                           customers he has
     */

    public Server(int serverID, boolean isAvailable, 
                    boolean hasWaitingCustomer, double nextAvailableTime) {
        this.serverID = serverID;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailTime = nextAvailableTime;
        this.list = new ArrayList<>();
        this.supplier = () -> null;
        this.resting = false;

    }

    /**
     * Overloaded Constructor that creates a server.
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

    public Server(int serverID, boolean isAvailable, 
                boolean hasWaitingCustomer, double nextAvailableTime,
                ArrayList<Customer> list, Supplier<Manager> supplier, boolean resting) {
        this.serverID = serverID;
        this.isAvailable = isAvailable;
        this.hasWaitingCustomer = hasWaitingCustomer;
        this.nextAvailTime = nextAvailableTime;
        this.list = list;
        this.supplier = supplier;
        this.resting = resting;

    }

    public boolean getServerState() {
        return this.resting;
    }

    public Supplier<Manager> getSupplier() {
        return this.supplier;
    }

    public ArrayList<Customer> getList() {
        return this.list;
    }

    public int getID() {
        return this.serverID;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public boolean hasCust() {
        return this.hasWaitingCustomer;
    }

    public double getAvailTime() {
        return this.nextAvailTime;
    }

    @Override
    public String toString() {
        if (this.isAvailable == true) {
            return this.serverID + " is available";
        } else {
            if (!this.hasWaitingCustomer) {
                return this.serverID + " is busy; available at "
                    + String.format("%.3f", this.nextAvailTime);
            } else {
                return this.serverID + " is busy; waiting customer to be served at "
                        + String.format("%.3f", this.nextAvailTime);
            }
        }
    }
}