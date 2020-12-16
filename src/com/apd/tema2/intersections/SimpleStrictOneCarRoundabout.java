package com.apd.tema2.intersections;

import com.apd.tema2.entities.Intersection;
import com.apd.tema2.entities.StartDirectionCar;

import java.util.ArrayList;

public class SimpleStrictOneCarRoundabout implements Intersection {
    public static int n, t;
    public final static ArrayList<StartDirectionCar> startDirectionsCars = new ArrayList<>();

    public SimpleStrictOneCarRoundabout(int nSimpleStrictOneCarRoundabout, int tSimpleStrictOneCarRoundabout) {
        n = nSimpleStrictOneCarRoundabout;
        t = tSimpleStrictOneCarRoundabout;
        for (int i = 0; i < n; ++i) {
            startDirectionsCars.add(new StartDirectionCar(i));
        }
    }
}
