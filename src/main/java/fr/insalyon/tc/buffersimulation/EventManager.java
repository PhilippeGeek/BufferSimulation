package fr.insalyon.tc.buffersimulation;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Manage an Event List
 */
public class EventManager {

    private PriorityQueue<Event> events = new PriorityQueue<Event>(10, (event1, event2)->{
        if (event1.getTime() < event2.getTime()) return -1;
        if (event1.getTime() > event2.getTime()) return 1;
        return 0;
    });

    public void addEvent(Event.Type type, double time){
        events.add(new Event(type, time));
    }

    public Event getNext(){
        return events.poll();
    }

    /**
     * Compute next arrival time based on exponential law.
     * @param lambda The lambda coeficiant of exponential law
     * @param start Offset to the time given
     * @param seed Seed used to the {@link Random} generator
     * @return The next usable time
     */
    public static double nextArrivalTime(double lambda, double start, long seed){
        final double u = new Random(seed).nextDouble();
        return start + Math.log(1- u)/(-lambda);
    }

}
