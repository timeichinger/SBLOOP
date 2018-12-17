package adventure.game;

import adventure.location.Facility;
import adventure.location.FunRide;
import adventure.location.Location;

import java.util.Random;

public class Player {

    private Location currentLocation;
    public static Random randomGenerator = new Random(10);
    private double money;
    private int energy;
    private int funPoints;

    private static final int MAX_ENERGY = 200;
    private static final int MIN_ENERGY = 60;
    private static final int ENERGY_POINTS_COST_RIDE = 5;
    private static final double MAX_MONEY = 50.00;
    private static final int WALK_ENERGY_COST = 10;

    public Player(Location startLocation) {
        this.currentLocation = startLocation;
        funPoints = 0;
        energy = randomGenerator.nextInt((MAX_ENERGY-MIN_ENERGY) + 1) + MIN_ENERGY;
        money = randomGenerator.nextDouble()*MAX_MONEY;
    }

    public double getMoney() {
        return money;
    }

    public int getEnergy() {
        return energy;
    }

    public int getFunPoints() {
        return funPoints;
    }

    @Override
    public String toString() {

        return currentLocation.toString() + "\n" + String.format("You have %d energy and %.2f \u20ac. You already earned %d fun points", getEnergy(), getMoney(), getFunPoints());
    }

    public void walk(String direction) {
        energy -= WALK_ENERGY_COST;
        currentLocation = currentLocation.getNeighboringLocation(direction);
    }

    //This method handles when the player wants to stay at the current location
    public void stay() {
        if (currentLocation.getClass().equals(FunRide.class)) {
            FunRide funRide = (FunRide) currentLocation;
            handleRide(funRide);
        } else {
            Facility facility = (Facility) currentLocation;
            handleRest(facility);
        }
    }

    //This method handles the case when the player wants to rest at the passed location
    private void handleRest(Facility facility) {
        if (facility.getCOST() <= getMoney()) {
            money -= facility.getCOST();
            energy += facility.getENERGY_POINTS();
        }
    }

    //This method handles the case when the player wants to ride at the passed location
    private void handleRide(FunRide funRide) {
        if (funRide.getCOST() <= getMoney()) {
            money -= funRide.getCOST();
            energy -= ENERGY_POINTS_COST_RIDE;
            funPoints += funRide.getFUN_POINTS();
        }
    }

    //This method handles the case, when the game ends
    public void gameOver(Location location) {
        currentLocation = location;
        funPoints = 0;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
