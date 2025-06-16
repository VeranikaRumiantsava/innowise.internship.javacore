package org.innowise.internship.javacore.skynet;

import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Factory implements Runnable {
    Queue<Part> parts = new ConcurrentLinkedQueue<>();

    public Queue<Part> getParts() {
        return parts;
    }

    public void setParts(Queue<Part> parts) {
        this.parts = parts;
    }

    public void createParts(){
        List<Part> forCreating = List.of(Part.HEAD, Part.TORSO, Part.HAND, Part.FEET);
        int todayCount = 1 + new Random().nextInt(10);
        for (int i = 0; i < todayCount; i++){
           parts.add(forCreating.get(new Random().nextInt(4)));
        }
    }


    @Override
    public void run() {
        createParts();
    }
}
