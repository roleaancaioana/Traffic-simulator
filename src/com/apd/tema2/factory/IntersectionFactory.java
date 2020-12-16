package com.apd.tema2.factory;

import com.apd.tema2.entities.Intersection;
import com.apd.tema2.intersections.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * Prototype Factory: va puteti crea cate o instanta din fiecare tip de implementare de Intersection.
 */
public class IntersectionFactory {
    private static Map<String, Intersection> cache = new HashMap<>();

    public static Intersection getIntersection(String handlerType) {
        return cache.get(handlerType);
    }

    public static Intersection putIntersection(String handlerType, Intersection intersection) {
        cache.put(handlerType, intersection);
        return intersection;
    }
}
