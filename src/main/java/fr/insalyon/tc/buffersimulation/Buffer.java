package fr.insalyon.tc.buffersimulation;

import java.util.LinkedList;

/**
 * Represent the FIFO buffer which process packets.
 */
@SuppressWarnings("WeakerAccess")
public class Buffer {

    private LinkedList<Packet> buffer = new LinkedList<>();
    private int size;
    private final Simulation simulation;

    public Buffer(int size, Simulation simulation){
        this.size = size;
        this.simulation = simulation;
    }

    public boolean addPacket(Packet p){
        if(buffer.size() < size){
            buffer.add(p);
            p.setBufferArrival(simulation.getNow());
            return true;
        } else {
            return false;
        }
    }

    public Packet processPacket(){
        final Packet packet = buffer.pop();
        packet.setBufferDeparture(simulation.getNow());
        return packet;
    }

    public int getSize(){
        return buffer.size();
    }

    public int getMaxSize(){
        return size;
    }

    public float getFill(){
        return (float) getSize()/(float) getMaxSize();
    }

}
