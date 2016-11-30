package fr.insalyon.tc.buffersimulation;

/**
 * Result of the simulation on a given state
 */
public class SimulationState {

    private final double time;
    private final int arrivalCount;
    private final int rejectedCount;
    private final int departureCount;
    private final double bufferFill;
    private final double packetProcessingTime;

    public SimulationState(double time, int arrivalCount, int rejectedCount, int departureCount, double bufferFill, double packetProcessingTime) {
        this.time = time;
        this.arrivalCount = arrivalCount;
        this.rejectedCount = rejectedCount;
        this.departureCount = departureCount;
        this.bufferFill = bufferFill;
        this.packetProcessingTime = packetProcessingTime;
    }

    public double getTime() {
        return time;
    }

    public int getArrivalCount() {
        return arrivalCount;
    }

    public int getRejectedCount() {
        return rejectedCount;
    }

    public int getDepartureCount() {
        return departureCount;
    }

    public double getBufferFill() {
        return bufferFill;
    }

    public double getPacketProcessingTime() {
        return packetProcessingTime;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getTime());
        builder.append(' ');
        builder.append(getArrivalCount());
        builder.append(' ');
        builder.append(getDepartureCount());
        builder.append(' ');
        builder.append(getRejectedCount());
        builder.append(' ');
        builder.append(getBufferFill());
        builder.append(' ');
        builder.append(getPacketProcessingTime());
        return builder.toString();
    }
}
