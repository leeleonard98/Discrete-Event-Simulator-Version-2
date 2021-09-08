package cs2030.simulator;

/**
 * The ArriveEvent is an event that occurs when a customer arrive. It checks if
 * a server is able to serve the customer, and will return a ServeEvent if the
 * server is free, return a WaitEvent if the server is busy but has no waiting
 * customers, and returns a LeaveEvent if the server is busy and has a waiting
 * customer.
 */

public class ArriveEvent extends Event {
    private static Pair<Shop, Event> pair;

    /**
     * Constructor that creates an ArriveEvent.
     * 
     * @param customer - a customer
     */

    public ArriveEvent(Customer customer) {
        super(customer, x -> {
            pair = new Pair<Shop, Event>(x, new LeaveEvent(customer, customer.getTime(), null));
            x.find(i -> i.isAvailable() && i.getServerState() == false).ifPresentOrElse(y -> {
                pair = new Pair<Shop, Event>(x, new ServeEvent(customer, customer.getTime(), y));
            }, () -> {
                    if (customer.getGreedy() == 0) {
                        x.find(a -> a.getList().size() < Manager.maxQ 
                            && !a.hasCust()).ifPresent(y -> {
                                pair = new Pair<Shop, Event>(x, 
                                            new WaitEvent(customer, customer.getTime(), y));
                            });
                    } else {
                        x.findSmallest().ifPresent(y -> {
                            pair = new Pair<Shop, Event>(x, 
                                    new WaitEvent(customer, customer.getTime(), y));
                        });
                    }
                });
            return pair;
        }, customer.getTime(), null);

    }

    @Override
    public String toString() {
        String s = "";
        if (getCust().getGreedy() == 1) {
            s = "(greedy)";
        }
        return String.format("%.3f", this.getCust().getTime()) +
                 " " + this.getCust().getID() + s + " arrives";
    }

}