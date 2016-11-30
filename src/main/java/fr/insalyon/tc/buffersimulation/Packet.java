package fr.insalyon.tc.buffersimulation;

/**
 * Represent a Packet into the buffer
 */
public class Packet {

    private static int NEXT_ID = 1;

    private final int id;
    private double bufferArrival = 0;
    private double bufferDeparture = 0;

    public Packet(){
        id = ++NEXT_ID;
    }

    public int getId() {
        return id;
    }

    public void setBufferArrival(double bufferArrival) {
        this.bufferArrival = bufferArrival;
    }

    public void setBufferDeparture(double bufferDeparture) {
        this.bufferDeparture = bufferDeparture;
    }

    public double getProcessTime(){
        return bufferDeparture - bufferArrival;
    }

    public boolean isProcessed(){
        return getProcessTime() > 0;
    }
}
