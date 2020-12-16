package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SimpleMaintenance implements Intersection {
    public static ArrayList<Semaphore> semaphores;
    public static CyclicBarrier barrier;
    public static int maximumCars;

    public SimpleMaintenance(int max) {
        semaphores = new ArrayList<>();
        semaphores.add(new Semaphore(max));
        semaphores.add(new Semaphore(0));
        barrier = new CyclicBarrier(max);
        maximumCars = max;
    }
}
