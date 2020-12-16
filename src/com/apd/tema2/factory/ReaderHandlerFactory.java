package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.Pedestrians;
import com.apd.tema2.entities.ReaderHandler;
import com.apd.tema2.intersections.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Returneaza sub forma unor clase anonime implementari pentru metoda de citire din fisier.
 */
public class ReaderHandlerFactory {

    public static ReaderHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of them)
        // road in maintenance - 1 lane 2 ways, X cars at a time
        // road in maintenance - N lanes 2 ways, X cars at a time
        // railroad blockage for T seconds for all the cars
        // unmarked intersection
        // cars racing
        return switch (handlerType) {
            case "simple_semaphore" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) {
                    // Exemplu de utilizare:
                    // Main.intersection = IntersectionFactory.getIntersection("simpleIntersection");
                }
            };
            case "simple_n_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    // To parse input line use:
                    String[] line = br.readLine().split(" ");
                    int n = Integer.parseInt(line[0]);
                    int t = Integer.parseInt(line[1]);
                    IntersectionFactory.putIntersection("simple_n_roundabout", new SimpleNRoundabout(n, t));
                    Main.intersection = IntersectionFactory.getIntersection("simple_n_roundabout");
                }
            };
            case "simple_strict_1_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    int n = Integer.parseInt(line[0]);
                    int t = Integer.parseInt(line[1]);
                    IntersectionFactory.putIntersection("simple_strict_1_car_roundabout", new SimpleStrictOneCarRoundabout(n, t));
                    Main.intersection = IntersectionFactory.getIntersection("simple_strict_1_car_roundabout");
                }
            };
            case "simple_strict_x_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    int n = Integer.parseInt(line[0]);
                    int t = Integer.parseInt(line[1]);
                    int maximumCars = Integer.parseInt(line[2]);
                    IntersectionFactory.putIntersection("simple_strict_x_car_roundabout", new SimpleStrictXCarRoundabout(n, t, maximumCars));
                    Main.intersection = IntersectionFactory.getIntersection("simple_strict_x_car_roundabout");
                }
            };
            case "simple_max_x_car_roundabout" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    int maximumCars = Integer.parseInt(line[2]);
                    int n = Integer.parseInt(line[0]);
                    int t = Integer.parseInt(line[1]);
                    // Pot utiliza clasa de la task-ul anterior pentru ca cerintele sunt foarte asemanatoare
                    IntersectionFactory.putIntersection("simple_max_x_car_roundabout", new SimpleStrictXCarRoundabout(n, t, maximumCars));
                    Main.intersection = IntersectionFactory.getIntersection("simple_max_x_car_roundabout");
                }
            };
            case "priority_intersection" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    int numberOfCarsWithHighPriority = Integer.parseInt(line[0]);
                    int numberOfCarsWithLowPriority = Integer.parseInt(line[1]);
                    IntersectionFactory.putIntersection("priority_intersection", new PriorityIntersection(numberOfCarsWithHighPriority, numberOfCarsWithLowPriority));
                    Main.intersection = IntersectionFactory.getIntersection("priority_intersection");
                }
            };
            case "crosswalk" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    Main.pedestrians = new Pedestrians(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
                }
            };
            case "simple_maintenance" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    int maximumCars = Integer.parseInt(line[0]);
                    IntersectionFactory.putIntersection("simple_maintenance", new SimpleMaintenance(maximumCars));
                    Main.intersection = IntersectionFactory.getIntersection("simple_maintenance");
                }
            };
            case "complex_maintenance" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {
                    String[] line = br.readLine().split(" ");
                    int m = Integer.parseInt(line[0]);
                    int n = Integer.parseInt(line[1]);
                    int x = Integer.parseInt(line[2]);
                    IntersectionFactory.putIntersection("complex_maintenance", new ComplexMaintenance(m, n, x));
                    Main.intersection = IntersectionFactory.getIntersection("complex_maintenance");
                }
            };
            case "railroad" -> new ReaderHandler() {
                @Override
                public void handle(final String handlerType, final BufferedReader br) throws IOException {

                }
            };
            default -> null;
        };
    }

}
