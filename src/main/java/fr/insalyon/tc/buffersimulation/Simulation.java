package fr.insalyon.tc.buffersimulation;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Represent a simulation of a buffer.
 */
public class Simulation implements Runnable{

    private static final long SEED = 1342432532732389L;
    private static final long MU_SEED = 134243253278321089L;
    private final int maxTime;
    private final int packetRate;
    private final int packetProcessingRate;
    private final Buffer packetBuffer;
    private PrintStream outputStream;
    private final EventManager eventManager = new EventManager();
    private double now;

    public Simulation(File output, int maxTime, int packetRate, int packetProcessingRate, int bufferSize){

        this.maxTime = maxTime;
        this.packetRate = packetRate;
        this.packetProcessingRate = packetProcessingRate;
        this.packetBuffer = new Buffer(bufferSize, this);

        if(output!=null){
            try{
                outputStream = new PrintStream(new FileOutputStream(output));
            } catch (IOException e){
                output = null;
            }
        }

        if(output == null){
            outputStream = System.out;
        }

    }

    ArrayList<Packet> packets;
    ArrayList<SimulationState> states;
    int accepted, rejected, processed;

    public void run(){
        now = 0;
        packets = new ArrayList<>(packetRate*maxTime);
        states = new ArrayList<>(packetRate*maxTime);
        eventManager.addEvent(Event.Type.ARRIVAL, EventManager.nextArrivalTime(packetRate, 0, SEED));
        while (now <= maxTime) {
            Event event = eventManager.getNext();
            now = event.getTime();
            switch (event.getType()) {

                case RELEASE:
                    break;
                case ARRIVAL:
                    eventManager.addEvent(Event.Type.ARRIVAL, EventManager.nextArrivalTime(packetRate, now, SEED));
                    final Packet p = new Packet();
                    if(packetBuffer.addPacket(p)){
                        // Buffer has accepted the packet
                        accepted++;
                        packets.add(p);
                        if(packetBuffer.getSize() == 1){
                            // Schedule departure of this packet
                            eventManager.addEvent(Event.Type.DEPARTURE, EventManager.nextArrivalTime(packetProcessingRate, now, MU_SEED));
                        }
                    } else {
                        // Buffer has rejected the packet
                        rejected++;
                    }
                    break;
                case DEPARTURE:
                    final Packet packet = packetBuffer.processPacket();
                    processed++;
                    if(packetBuffer.getSize() > 0)
                        // Schedule next packet departure if we have one
                        eventManager.addEvent(Event.Type.DEPARTURE, EventManager.nextArrivalTime(packetProcessingRate, now, MU_SEED));
                    break;
            }
            float packetTime = 0.0f;
            for(Packet packet:packets) {
                if(packet.isProcessed())
                    packetTime += packet.getProcessTime();
            }
            packetTime /= packets.size();
            final SimulationState state = new SimulationState(
                    now,
                    accepted,
                    processed,
                    rejected,
                    packetBuffer.getFill(),
                    packetTime
            );
            states.add(state);
            outputStream.println(state);
        }
    }

    public static void main(String... args){
        new Simulation(null, 100, 120, 100, 79000).run();
    }

    public double getNow() {
        return now;
    }

    public ArrayList<SimulationState> getStates() {
        return states;
    }
}
