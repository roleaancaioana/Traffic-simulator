package com.apd.tema2.intersections;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

public class Railroad implements Intersection {
    public static CyclicBarrier barrier = new CyclicBarrier(Main.carsNo);
    public static LinkedBlockingQueue<Car> railroadCars = new LinkedBlockingQueue<>();
    public static final Object myLock = new Object();
}
