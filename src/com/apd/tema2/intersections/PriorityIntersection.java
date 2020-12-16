package com.apd.tema2.intersections;

import com.apd.tema2.entities.Car;
import com.apd.tema2.entities.Intersection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PriorityIntersection implements Intersection {
    public static int numberOfCarsWithHighPriority;
    public static int numberOfCarsWithLowPriority;
    public final static Object obj = new Object();
    public static List<Car> highPriorityCars = Collections.synchronizedList(new ArrayList<>());
    public static List<Car> lowPriorityCars = Collections.synchronizedList(new ArrayList<>());

    public PriorityIntersection(int carsWithHighPriority, int carsWithLowPriority) {
        numberOfCarsWithHighPriority = carsWithHighPriority;
        numberOfCarsWithLowPriority = carsWithLowPriority;
    }
}
