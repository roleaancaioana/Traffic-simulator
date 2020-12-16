package com.apd.tema2.factory;

import com.apd.tema2.entities.Car;

public class MessagesFactory {
    public static String getMessage(Car car, int type, int temp) {
        switch(type) {
            case 1:
                return "Car " + car.getId() + " has reached the roundabout, now waiting...";
            case 2:
                return "Car " + car.getId() + " was selected to enter the roundabout from lane " + car.getStartDirection();
            case 3:
                return "Car " + car.getId() + " has entered the roundabout from lane " + car.getStartDirection();
            case 4:
                return "Car " + car.getId() + " has exited the roundabout after " + temp + " seconds";
            case 5:
                return "Car " + car.getId() + " has reached the semaphore, now waiting...";
            case 6:
                return "Car " + car.getId() + " has waited enough, now driving...";
            case 7:
                return "Car " + car.getId() + " has entered the roundabout";
            case 8:
                return "Car " + car.getId() + " has reached the roundabout";
            case 9:
                return "Car " + car.getId() + " with high priority has entered the intersection";
            case 10:
                return "Car " + car.getId() + " with high priority has exited the intersection";
            case 11:
                return "Car " + car.getId() + " with low priority is trying to enter the intersection...";
            case 12:
                return "Car " + car.getId() + " with low priority has entered the intersection";
            case 13:
                return "Car " + car.getId() + " has now green light";
            case 14:
                return "Car " + car.getId() + " has now red light";
            case 15:
                return "Car " + car.getId() + " from side number " + car.getStartDirection() + " has reached the bottleneck";
            case 16:
                return "Car " + car.getId() + " from side number " + car.getStartDirection() + " has passed the bottleneck";
            case 17:
                return "Car " + car.getId() + " has come from the lane number " + car.getStartDirection();
            case 18:
                return "The initial lane " + temp + " has no permits and is moved to the back of the new lane queue";
            case 19:
                return "The initial lane " + temp + " has been emptied and removed from the new lane queue";
            case 20:
                return "Car " + car.getId() + " has reached the roundabout from lane " + car.getStartDirection();
            case 21:
                return "Car " + car.getId() + " from side number " + car.getStartDirection() + " has stopped by the railroad";
            case 22:
                return "Car " + car.getId() + " from side number " + car.getStartDirection() + " has started driving";
            case 23:
                return "The train has passed, cars can now proceed";
            default:
                return null;
        }
    }
}
