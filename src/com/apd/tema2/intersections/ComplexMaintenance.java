package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

public class ComplexMaintenance implements Intersection {
    public static class StartAndEnd {
        public int start;
        public int end;

        public StartAndEnd(int s, int e) {
            start = s;
            end = e;
        }
    }

    public static ArrayList<LinkedBlockingQueue<Integer>> coupledLanes;
    public static List<LinkedBlockingQueue<Car>> oldLanes;
    public static CyclicBarrier barrier;
    public static final Object myLock = new Object();
    public static List<Car> newLanes;
    public static List<StartAndEnd> startAndEnds;
    public static int numberOfNewLanes, numberOfOldLanes, maximumCars;

    public ComplexMaintenance(int m, int n, int x) {
        numberOfNewLanes = m;
        numberOfOldLanes = n;
        maximumCars = x;
        barrier = new CyclicBarrier(Main.carsNo);
        coupledLanes = new ArrayList<>();
        oldLanes = Collections.synchronizedList(new ArrayList<>());
        newLanes = Collections.synchronizedList(new ArrayList<>());
        startAndEnds = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < numberOfOldLanes; ++i) {
            oldLanes.add(new LinkedBlockingQueue<>());
        }

        for (int i = 0; i < numberOfNewLanes; ++i) {
            int start = i * numberOfOldLanes / numberOfNewLanes;
            int end = (i + 1) * numberOfOldLanes / numberOfNewLanes;
            startAndEnds.add(new StartAndEnd(start, end));

            LinkedBlockingQueue<Integer> aux = new LinkedBlockingQueue<>();
            for (int j = start; j < end; ++j) {
                aux.add(j);
            }
            coupledLanes.add(aux);
        }
    }
}
