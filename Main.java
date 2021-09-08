import cs2030.simulator.ArriveEvent;
import cs2030.simulator.Customer;
import cs2030.simulator.ServeEvent;
import cs2030.simulator.Server;
import cs2030.simulator.ServerBack;
import cs2030.simulator.ServerRest;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.Event;
import cs2030.simulator.EventComparator;
import cs2030.simulator.HumanServers;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.Manager;
import cs2030.simulator.Pair;
import cs2030.simulator.SelfCheckServers;
import cs2030.simulator.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Supplier;

/**
 * Does the main computation of the events happening to each Customer Scanner to
 * scan inputs base on the state of the server, different event classes will be
 * added and also printed in sequence after being sorted by the priorty queue.
 * The waiting time, number of customers served and number of customers who left
 * will be updated after each event compute the statistics required
 * 
 * @author Lee Leonard A0199526M
 */
class Main {
    public static void main(String[] args) {
        int size = args.length;
        int seed = 0;
        int numOfServers = 1;
        int numCounters = 0;
        int maxQ = 1;
        int numOfCust = 0;
        double lamda = 0;
        double mu = 0;
        double restingRate = 0;
        double probRR = 0;
        double probGreedy = 0;

        if (size == 5) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numOfCust = Integer.parseInt(args[2]);
            lamda = Double.parseDouble(args[3]);
            mu = Double.parseDouble(args[4]);
        }

        if (size == 6) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            maxQ = Integer.parseInt(args[2]);
            numOfCust = Integer.parseInt(args[3]);
            lamda = Double.parseDouble(args[4]);
            mu = Double.parseDouble(args[5]);
        }

        if (size == 8) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            maxQ = Integer.parseInt(args[2]);
            numOfCust = Integer.parseInt(args[3]);
            lamda = Double.parseDouble(args[4]);
            mu = Double.parseDouble(args[5]);
            restingRate = Double.parseDouble(args[6]);
            probRR = Double.parseDouble(args[7]);
        }

        if (size == 9) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numCounters = Integer.parseInt(args[2]);
            maxQ = Integer.parseInt(args[3]);
            numOfCust = Integer.parseInt(args[4]);
            lamda = Double.parseDouble(args[5]);
            mu = Double.parseDouble(args[6]);
            restingRate = Double.parseDouble(args[7]);
            probRR = Double.parseDouble(args[8]);
        }

        if (size == 10) {
            seed = Integer.parseInt(args[0]);
            numOfServers = Integer.parseInt(args[1]);
            numCounters = Integer.parseInt(args[2]);
            maxQ = Integer.parseInt(args[3]);
            numOfCust = Integer.parseInt(args[4]);
            lamda = Double.parseDouble(args[5]);
            mu = Double.parseDouble(args[6]);
            restingRate = Double.parseDouble(args[7]);
            probRR = Double.parseDouble(args[8]);
            probGreedy = Double.parseDouble(args[9]);
        }

        final ArrayList<Customer> selfCheckQ = new ArrayList<>();

        List<Server> servers = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        Manager rgen = new Manager(seed, lamda, mu, restingRate, probRR, maxQ, numOfServers);

        Supplier<Manager> rest = () -> rgen;
        for (int i = 0; i < numOfServers; i++) {
            HumanServers s = new HumanServers(i + 1, true, false, 0, 
                                            new ArrayList<Customer>(), rest, false);
            servers.add(s);
        }
        for (int i = numOfServers; i < numOfServers + numCounters; i++) {
            SelfCheckServers s = new SelfCheckServers(i + 1, true, false,
                                                     0, selfCheckQ, rest, false);
            servers.add(s);
        }

        Shop shop = new Shop(servers);
        double initialTime = 0;
        double arrivalTime = 0;

        Supplier<Double> supplier = () -> rgen.getServiceTime();

        for (int i = 0; i < numOfCust; i++) {
            if (i > 0) {
                arrivalTime = rgen.getArrivalTime() + initialTime;
                initialTime = arrivalTime;
            }
            boolean greedy = rgen.getGreedyCust() < probGreedy;
            if (greedy == true) {
                Customer c = new Customer(i + 1, arrivalTime, supplier, 1);
                customers.add(c);
            } else {
                Customer c = new Customer(i + 1, arrivalTime, supplier, 0);
                customers.add(c);
            }

        }

        double waitingTime = 0;
        int numServed = 0;
        int numLeft = 0;

        PriorityQueue<Event> pqueue = new PriorityQueue<Event>(new EventComparator());
        for (int i = 0; i < customers.size(); i++) {
            Event arrive = new ArriveEvent(customers.get(i));
            pqueue.add(arrive);
        }

        while (pqueue.size() > 0) {
            Event nextEvent = pqueue.poll();
            if (!(nextEvent instanceof ServerBack || nextEvent instanceof ServerRest)) {
                System.out.println(nextEvent);
            }

            Pair<Shop, Event> pair = nextEvent.execute(shop);
            shop = pair.first();
            if (pair.second() != null) {
                pqueue.add(pair.second());
            }
            if (nextEvent instanceof ServeEvent) {

                waitingTime += nextEvent.getTime() - nextEvent.getCust().getTime();
                numServed++;

            } else if (nextEvent instanceof LeaveEvent) {
                numLeft++;
                /**
                 * If the next event is a done event, check if probability of rest is smaller
                 * than smaller than probRR. If true, add a server rest event to queue. Else,
                 * check if there are customers waiting, if true, add a serve event to queue.
                 */
            } else if (nextEvent instanceof DoneEvent) {
                Server s = nextEvent.getServer();

                if (nextEvent.getServer().getID() <= numOfServers) {
                    s = new HumanServers(nextEvent.getServer().getID(), 
                                        true, nextEvent.getServer().hasCust(),
                                        nextEvent.getTime(), nextEvent.getServer().getList(), 
                                        s.getSupplier(), false);
                } else {
                    s = new SelfCheckServers(nextEvent.getServer().getID(), 
                                            true, nextEvent.getServer().hasCust(),
                                            nextEvent.getTime(), nextEvent.getServer().getList(), 
                                            nextEvent.getServer().getSupplier(),
                                            false);
                }

                if (nextEvent.getServer().getID() <= numOfServers 
                    && rgen.getRandomRest() < rgen.getRR()) {

                    pair = new Pair<Shop, Event>(shop.replace(s),
                            new ServerRest(nextEvent.getCust(), 
                                            nextEvent.getTime(), nextEvent.getServer()));

                    shop = pair.first();
                    pqueue.add(pair.second());
                } else if (nextEvent.getServer().getList().size() > 0) {
                    if (nextEvent.getServer().getID() <= numOfServers) {
                        s = new HumanServers(nextEvent.getServer().getID(),
                                             false, nextEvent.getServer().hasCust(),
                                            nextEvent.getTime(), nextEvent.getServer().getList(),
                                            nextEvent.getServer().getSupplier(), false);
                    } else {
                        s = new SelfCheckServers(nextEvent.getServer().getID(), 
                                                false, nextEvent.getServer().hasCust(),
                                                nextEvent.getTime(), 
                                                nextEvent.getServer().getList(),
                                                nextEvent.getServer().getSupplier(), false);
                    }

                    pair = new Pair<Shop, Event>(shop.replace(s),
                                                new ServeEvent(s.getList().get(0), 
                                                s.getAvailTime(), nextEvent.getServer()));
                    pqueue.add(pair.second());
                    shop = pair.first();
                }
            }

        }
        double avgWaiting = 0;

        if (numServed == 0) {
            avgWaiting = 0;
        } else {
            avgWaiting = waitingTime / (double) numServed;
        }

        System.out.println("[" + String.format("%.3f", avgWaiting) +
                         " " + numServed + " " + numLeft + "]");

    }
}
