package org.innowise.internship.javacore.skynet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Fraction implements Runnable{
    String name;
    int countRobots;
    List<Part> parts;
    Factory factory;
    CyclicBarrier barrier;

    public Fraction(String name, Factory factory) {
        this.name = name;
        this.countRobots = 0;
        this.parts = new ArrayList<Part>();
        this.factory = factory;
    }

    public String getName() {
        return name;
    }

    public void setBarrier(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void tryToGetNewParts() {
        List<Part> newParts = new ArrayList<>();
        while (newParts.size()<5){
            Part part = factory.parts.poll();
            if (part==null)
            {
                break;
            }
            else {
            newParts.add(part);
            }
        }
        parts.addAll(newParts);
        tryToCreateRobot();
    }

    private void tryToCreateRobot() {
        while (canCreateRobot()) {
                createRobot();
        }
    }

    private boolean canCreateRobot() {
        int head = 0, torso = 0, hand = 0, feet = 0;
        for (Part part : parts) {
            switch (part) {
                case HEAD:
                    head++;
                    break;
                case TORSO:
                    torso++;
                    break;
                case HAND:
                    hand++;
                    break;
                case FEET:
                    feet++;
                    break;
            }
            if (head >= 1 && torso >= 1 && hand >= 2 && feet >= 2) {
                return true;
            }
        }
        return false;
    }

    private void createRobot() {
        parts.remove(Part.HEAD);
        parts.remove(Part.TORSO);
        parts.remove(Part.HAND);
        parts.remove(Part.HAND);
        parts.remove(Part.FEET);
        parts.remove(Part.FEET);
        ++countRobots;
    }

    public int getCountRobots() {
        return countRobots;
    }

    public void setCountRobots(int countRobots) {
        this.countRobots = countRobots;
    }

    public List<Part> getParts() {
        return parts;
    }


    @Override
    public void run() {
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
        }
        tryToGetNewParts();
    }
}
