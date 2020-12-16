package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class SimpleStrictXCarRoundabout implements Intersection {
    public static int n, t;
    public static int maximumCars;
    public static ArrayList<Semaphore> semaphores;
    public static CyclicBarrier barrierForCarsFromRoundabout;
    public static CyclicBarrier barrierForAllCars;

    public SimpleStrictXCarRoundabout(int nSimpleStrictXCarRoundabout, int tSimpleStrictXCarRoundabout, int max) {
        n = nSimpleStrictXCarRoundabout;
        t = tSimpleStrictXCarRoundabout;
        maximumCars = max;
        barrierForAllCars = new CyclicBarrier(Main.carsNo);
        barrierForCarsFromRoundabout = new CyclicBarrier(maximumCars * n);
        semaphores = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            semaphores.add(new Semaphore(maximumCars));
        }
    }
}
