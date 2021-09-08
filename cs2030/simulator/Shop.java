package cs2030.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/**
 * The Shop class has an arraylist of servers and a number.
 * The number is the number of servers in the shop. 
 * It has 3 methods, replace, find and
 * findSmallest.
 */

public class Shop {
    private final ArrayList<Server> servers;
    private final int num;

    /**
     * Constructor that creates a shop.
     *  
     * @param num - num of servers in the shop
     */

    public Shop(int num) {
        this.num = num;
        ArrayList<Server> temp = new ArrayList<>();
        IntStream.range(0, num).forEach(i -> {
            Server server = new Server(i + 1, true, false, 0);
            temp.add(server);
        });
        this.servers = temp;
    }

    /**
     * Overloaded Constructor that creates a shop.
     * 
     * @param servers - list of servers in the shop
     */

    public Shop(List<Server> servers) {
        ArrayList<Server> temp = new ArrayList<>();
        IntStream.range(0, servers.size()).forEach(i -> {
            Server server = servers.get(i);
            temp.add(server);
        });

        this.servers = temp;
        this.num = servers.size();
    }

    /**
     * Replaces the server in the arraylist with a new server.
     * @param server - new server Takes in a server, replace the current server in
     *               the arraylist
     * @return a new shop with the new list
     */

    public Shop replace(Server server) {
        ArrayList<Server> temp = new ArrayList<>();
        temp.addAll(servers);
        temp.set(server.getID() - 1, server);
        return new Shop(temp);
    }

    public ArrayList<Server> getServers() {
        return this.servers;
    }

    /**
     * Finds the server with the smallest queue.
     * if size of queue is the same return
     * the one with a smaller id
     * 
     * @return server that has smallest queue
     */
    public Optional<Server> findSmallest() {
        int size = servers.stream().map(x -> x.getList().size()).min(Integer::compare).orElse(-1);
        return servers.stream().filter(a -> a.getList().size() < Manager.maxQ && !a.hasCust())
                .filter((x -> x.getList().size() == size)).findFirst();
    }

    /**
     * Finds the server that meets the predicate.
     * @param predicate - predicate to be tested
     * @return server that first hits the predicate
     */
    public Optional<Server> find(Predicate<? super Server> predicate) {

        Optional<Server> server = servers.stream().filter(predicate).findFirst();
        return server;
    }

    public String toString() {
        return servers.toString();
    }
}
