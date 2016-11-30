package fr.insalyon.tc.buffersimulation;

/**
 * Event to process into the simultation
 */
@SuppressWarnings("WeakerAccess")
public class Event {
    private final Type type;
    private final double time;

    public Event(Type type, double time) {
        this.type = type;
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        RELEASE, ARRIVAL, DEPARTURE
    }
}
