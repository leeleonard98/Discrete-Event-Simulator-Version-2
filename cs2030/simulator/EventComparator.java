package cs2030.simulator;

import java.util.Comparator;

/**
 * The EventComparator class compares 2 events.
 */

public class EventComparator implements Comparator<Event> {

    /**
     * The compare method compares 2 events. It is done first in terms of the time
     * of the event, followed by the customer id. This will then return the earlier
     * event and sort the events accordingly in the priority queue of events.
     * 
     * @return the earlier event.
     */
    public int compare(Event e1, Event e2) {
        if (e1.getTime() == e2.getTime()) {
            return e1.getCust().getID() - e2.getCust().getID();
        } else if (e1.getTime() < e2.getTime()) {
            return -1;
        } else {
            return 1;
        }
    }
}