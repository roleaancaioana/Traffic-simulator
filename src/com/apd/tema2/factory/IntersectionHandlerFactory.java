package com.apd.tema2.factory;

import com.apd.tema2.Main;
import com.apd.tema2.entities.*;
import com.apd.tema2.intersections.*;

import java.util.concurrent.BrokenBarrierException;

import static java.lang.Thread.sleep;

/**
 * Clasa Factory ce returneaza implementari ale InterfaceHandler sub forma unor
 * clase anonime.
 */
public class IntersectionHandlerFactory {
    public static IntersectionHandler getHandler(String handlerType) {
        // simple semaphore intersection
        // max random N cars roundabout (s time to exit each of them)
        // roundabout with exactly one car from each lane simultaneously
        // roundabout with exactly X cars from each lane simultaneously
        // roundabout with at most X cars from each lane simultaneously
        // entering a road without any priority
        // crosswalk activated on at least a number of people (s time to finish all of
        // them)
        // road in maintenance - 2 ways 1 lane each, X cars at a time
        // road in maintenance - 1 way, M out of N lanes are blocked, X cars at a time
        // railroad blockage for s seconds for all the cars
        // unmarked intersection
        // cars racing

        return switch (handlerType) {
            case "simple_semaphore" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    try {
                        String message1 = MessagesFactory.getMessage(car, 5, 0);
                        System.out.println(message1);
                        sleep(car.getWaitingTime());
                        String message2 = MessagesFactory.getMessage(car, 6, 0);
                        System.out.println(message2);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                }
            };
            case "simple_n_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    String message1 = MessagesFactory.getMessage(car,1,0);
                    System.out.println(message1);
                    try {
                        SimpleNRoundabout.mySemaphore.acquire();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    String message2 = MessagesFactory.getMessage(car, 7, 0);
                    System.out.println(message2);

                    try {
                        sleep(SimpleNRoundabout.t);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    int seconds = SimpleNRoundabout.t;
                    String message3 = MessagesFactory.getMessage(car, 4, seconds);
                    System.out.println(message3);
                    SimpleNRoundabout.mySemaphore.release();
                }
            };
            case "simple_strict_1_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    String message1 = MessagesFactory.getMessage(car, 8, 0);
                    System.out.println(message1);
                    String message2 = MessagesFactory.getMessage(car, 3, 0);
                    synchronized (SimpleStrictOneCarRoundabout.startDirectionsCars.get(car.getStartDirection())) {
                        System.out.println(message2);
                        try {
                            sleep(SimpleStrictOneCarRoundabout.t);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }
                    }
                    int seconds = SimpleStrictOneCarRoundabout.t;
                    String message3 = MessagesFactory.getMessage(car, 4, seconds);
                    System.out.println(message3);
                }
            };
            case "simple_strict_x_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    String message1 = MessagesFactory.getMessage(car, 1, 0 );
                    System.out.println(message1);

                    try {
                        SimpleStrictXCarRoundabout.barrierForAllCars.await();
                    } catch (BrokenBarrierException | InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    String message2 = MessagesFactory.getMessage(car, 2, 0 );

                    try {
                        SimpleStrictXCarRoundabout.semaphores.get(car.getStartDirection()).acquire();
                        System.out.println(message2);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    try {
                        SimpleStrictXCarRoundabout.barrierForCarsFromRoundabout.await();
                    } catch (BrokenBarrierException | InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    String message3 = MessagesFactory.getMessage(car, 3, 0);
                    System.out.println(message3);

                    try {
                        sleep(SimpleStrictXCarRoundabout.t);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    try {
                        SimpleStrictXCarRoundabout.barrierForCarsFromRoundabout.await();
                    } catch (BrokenBarrierException | InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    int seconds = SimpleStrictXCarRoundabout.t / 1000;
                    String message4 = MessagesFactory.getMessage(car, 4, seconds);
                    System.out.println(message4);
                    SimpleStrictXCarRoundabout.semaphores.get(car.getStartDirection()).release();
                }
            };
            case "simple_max_x_car_roundabout" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    // Get your Intersection instance

                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

                    // Continuati de aici
                    String message1 = MessagesFactory.getMessage(car, 20, 0);
                    System.out.println(message1);
                    try {
                        SimpleStrictXCarRoundabout.semaphores.get(car.getStartDirection()).acquire();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    String message2 = MessagesFactory.getMessage(car, 3, 0);
                    System.out.println(message2);

                    try {
                        sleep(SimpleStrictXCarRoundabout.t);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    int seconds = SimpleStrictXCarRoundabout.t / 1000;
                    String message3 = MessagesFactory.getMessage(car, 4, seconds);
                    System.out.println(message3);
                    SimpleStrictXCarRoundabout.semaphores.get(car.getStartDirection()).release();
                }
            };
            case "priority_intersection" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    // Get your Intersection instance
                    try {
                        sleep(car.getWaitingTime());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } // NU MODIFICATI

                    // Continuati de aici
                    if (car.getPriority() == 1) {
                        String message3 = MessagesFactory.getMessage(car, 11, 0);
                        System.out.println(message3);
                        PriorityIntersection.lowPriorityCars.add(car);
                        while (!PriorityIntersection.highPriorityCars.isEmpty()) {
                        }
                        synchronized (PriorityIntersection.obj) {
                            Car c = PriorityIntersection.lowPriorityCars.get(0);
                            PriorityIntersection.lowPriorityCars.remove(c);
                            String message4 = MessagesFactory.getMessage(c, 12, 0);
                            System.out.println(message4);
                        }
                    } else if (car.getPriority() > 1) {
                        PriorityIntersection.highPriorityCars.add(car);
                        String message1 = MessagesFactory.getMessage(car, 9, 0);
                        System.out.println(message1);
                        try {
                            sleep(2000);
                        } catch (InterruptedException exception) {
                            exception.printStackTrace();
                        }

                        String message2 = MessagesFactory.getMessage(car, 10, 0);
                        System.out.println(message2);
                        PriorityIntersection.highPriorityCars.remove(car);
                    }

                }
            };
            case "crosswalk" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    String message1 = MessagesFactory.getMessage(car, 13, 0);
                    String message2 = MessagesFactory.getMessage(car, 14, 0);
                    while (!Main.pedestrians.isFinished()) {
                        if (!Main.pedestrians.isPass()) {
                            // Daca pietonii nu pasesc pe trecere, asta inseamna ca masinile au culoarea verde
                            if (car.diff.get() == 0) {
                                car.diff.set(1);
                                System.out.println(message1);
                            }

                        } else if (car.diff.get() == 1){
                            // Daca pietonii pasesc pe trecere, asta inseamna ca masinile au culoarea rosie
                            car.diff.set(0);
                            System.out.println(message2);
                        }
                    }

                    /*
                    Trebuie sa verificam si la final culoarea pe care o au masinile la semafor,
                    dupa ce nu mai vin pietoni la trecere.
                    */
                    if (!Main.pedestrians.isPass()) {
                        if (car.diff.get() == 0){
                            car.diff.set(1);
                            System.out.println(message1);
                        }
                    } else if (car.diff.get() == 1) {
                        car.diff.set(0);
                        System.out.println(message2);
                    }
                }
            };
            case "simple_maintenance" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    String message1 = MessagesFactory.getMessage(car, 15, 0);
                    System.out.println(message1);

                    try {
                        SimpleMaintenance.semaphores.get(car.getStartDirection()).acquire();
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    String message2 = MessagesFactory.getMessage(car, 16, 0);
                    System.out.println(message2);

                    try {
                        SimpleMaintenance.barrier.await();
                    } catch(BrokenBarrierException | InterruptedException exception) {
                        exception.printStackTrace();
                    }

                    SimpleMaintenance.semaphores.get(1 - car.getStartDirection()).release();
                }
            };
            case "complex_maintenance" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    synchronized (ComplexMaintenance.myLock) {
                        String message1 = MessagesFactory.getMessage(car, 17, 0);
                        System.out.println(message1);
                        /*
                        Formez pe benzile vechi cozile de masini, tinand cont de ordinea in care au venit acestea
                        */
                        ComplexMaintenance.oldLanes.get(car.getStartDirection()).add(car);
                        for (int i = 0; i < ComplexMaintenance.numberOfNewLanes; ++i){
                            if (car.getStartDirection() < ComplexMaintenance.startAndEnds.get(i).end
                                    && car.getStartDirection() >= ComplexMaintenance.startAndEnds.get(i).start){
                                car.newLaneForCar = i;
                                break;
                            }
                        }
                    }

                    /*
                    Repartitia pe noile benzi va incepe atunci cand toate masinile s-au incolonat, de aceea am folosit
                    aceasta bariera (ajuta la asteptarea incolonarii masinilor de catre toate thread-urile).
                     */
                    try {
                        ComplexMaintenance.barrier.await();
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (ComplexMaintenance.myLock) {
                        /*
                         Cat timp am in coupledLanes benzi pe care inca se mai afla masini
                        */
                        while (ComplexMaintenance.coupledLanes.get(car.newLaneForCar).size() > 0) {
                            int numberOfCars;
                            int firstLane;

                            /*
                               Masinile care vor circula sunt extrase de pe prima banda,
                               iar apoi vom permuta banda la finalul listei mai tarziu
                             */
                            firstLane = ComplexMaintenance.coupledLanes.get(car.newLaneForCar).remove();

                            /*
                            Trebuie sa circule maxim X masini pe noua banda
                            */
                            numberOfCars = Math.min(ComplexMaintenance.oldLanes.get(firstLane).size(), ComplexMaintenance.maximumCars);

                            /*
                            Introduc masinile pe noua banda, pe rand, tinand cont de ordinea in care au ajuns pe benzile vechi
                             */
                            while (numberOfCars >= 1) {
                                --numberOfCars;
                                Car auxCar = ComplexMaintenance.oldLanes.get(firstLane).remove();
                                System.out.println("Car " + auxCar.getId() + " from the lane " + firstLane + " has entered lane number " + car.newLaneForCar);
                            }

                            /*
                              Daca banda veche este goala, atunci nu ne mai intereseaza, dar daca aceasta nu este goala
                              o vom adauga din nou in coupledLanes, la finalul acesteia.
                            */
                            if (ComplexMaintenance.oldLanes.get(firstLane).size() == 0) {
                                String message2 = MessagesFactory.getMessage(car, 19, firstLane);
                                System.out.println(message2);
                            } else {
                                String message3 = MessagesFactory.getMessage(car, 18, firstLane);
                                System.out.println(message3);
                                ComplexMaintenance.coupledLanes.get(car.newLaneForCar).add(firstLane);
                            }
                        }
                    }
                }
            };
            case "railroad" -> new IntersectionHandler() {
                @Override
                public void handle(Car car) {
                    synchronized (Railroad.myLock) {
                        String message1 = MessagesFactory.getMessage(car, 21, 0);
                        System.out.println(message1);
                        Railroad.railroadCars.add(car);
                    }
                    try {
                        Railroad.barrier.await();
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (car.getId() == 0) {
                        String message3 = MessagesFactory.getMessage(car, 23, 0);
                        System.out.println(message3);
                    }

                    try {
                        Railroad.barrier.await();
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (Railroad.myLock) {
                        Car auxCar = Railroad.railroadCars.remove();
                        String message2 = MessagesFactory.getMessage(auxCar, 22, 0);
                        System.out.println(message2);
                    }
                }
            };
            default -> null;
        };
    }
}
