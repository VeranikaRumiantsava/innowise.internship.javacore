package org.innowise.internship.javacore;

import org.innowise.internship.javacore.skynet.Fraction;
import org.innowise.internship.javacore.skynet.Factory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Factory factory = new Factory();

        List<Fraction> fractionsList = new ArrayList<Fraction>();

        fractionsList.add(new Fraction("world", factory));
        fractionsList.add(new Fraction("wednesday", factory));

        CyclicBarrier barrier = new CyclicBarrier(fractionsList.size());
        for (Fraction fraction : fractionsList) {
            fraction.setBarrier(barrier);
        }

        try (ExecutorService fractions = Executors.newFixedThreadPool(fractionsList.size());
             ExecutorService factoryExecutor = Executors.newSingleThreadExecutor()) {

            for (int i = 0; i < 100; ++i) {

                Future<?> factoryWork = factoryExecutor.submit(factory);
                factoryWork.get();

                List<Future<?>> futureList = new ArrayList<>();

                for (Fraction fraction : fractionsList) {
                    futureList.add(fractions.submit(fraction));
                }

                for (Future<?> future : futureList) {
                    future.get();
                }
            }
        }

        System.out.println(fractionsList.stream()
                .max(Comparator.comparing(Fraction::getCountRobots))
                .map(Fraction::getName)
                .get());
    }
}