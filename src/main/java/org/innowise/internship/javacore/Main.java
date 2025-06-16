package org.innowise.internship.javacore;

import org.innowise.internship.javacore.skynet.Fraction;
import org.innowise.internship.javacore.skynet.Factory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Factory factory = new Factory();
        Fraction world = new Fraction("world", factory);
        Fraction wednesday = new Fraction("wednesday", factory);

        try (ExecutorService fractions = Executors.newFixedThreadPool(2);
             ExecutorService factoryExecutor = Executors.newSingleThreadExecutor()) {

            for (int i = 0; i < 100; ++i) {

                Future<?> factoryWork = factoryExecutor.submit(factory);
                factoryWork.get();

                Future<?> firstFraction;
                Future<?> secondFraction;

                if (i % 2 == 0) {
                    firstFraction = fractions.submit(world);
                    secondFraction = fractions.submit(wednesday);
                } else {
                    firstFraction = fractions.submit(wednesday);
                    secondFraction = fractions.submit(world);
                }
                firstFraction.get();
                secondFraction.get();
            }
        }

        System.out.println(world.getCountRobots() > wednesday.getCountRobots() ? "world won!" : "wednesday won!");
    }
}