package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;

import java.util.concurrent.Semaphore;

public class SimpleNRoundabout implements Intersection {
    public static int n, t;
    public static Semaphore mySemaphore;

    public SimpleNRoundabout(int nSimpleRoundAbout, int tSimpleRoundAbout) {
        n = nSimpleRoundAbout;
        t = tSimpleRoundAbout;
        mySemaphore = new Semaphore(n);
    }
}
