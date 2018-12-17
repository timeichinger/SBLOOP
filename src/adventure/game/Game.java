package adventure.game;
/*
 * OOP - WS1819 - SBL 1
 * Tim Eichinger (2049889)
 * Jakob Kessler (2110006)
 */

import adventure.location.Facility;
import adventure.location.FunRide;
import adventure.location.Location;

import java.util.Scanner;

//This class executes the program and handles inputs from the user
public class Game {

    public static Scanner scanner;
    private static Player player;

    public static void main(String[] argv){

       // Create Locations
        FunRide rollerCoaster = new FunRide("Roller Coaster", 2.5, 5);
        Facility restroom = new Facility("Restroom", 0.5, 1);
        FunRide bumperCar = new FunRide("Bumper Car", 1, 2);
        Facility kiosk = new Facility("Kiosk", 2, 5);
        FunRide wildWaterChannel = new FunRide("Wild-Water Channel", 2.5, 5);
        Facility restaurant = new Facility("Restaurant", 10, 20);
        FunRide carousel = new FunRide("Carousel", 1, 2);
        FunRide freefallTower = new FunRide("Freefall Tower", 1, 2);
        Location entrance = new Location("Entrance");
        Location parkingLot = new Location("Parking Lot");

        // Create Paths
        rollerCoaster.createPath("up", restroom);
        restroom.createPath("left", bumperCar);
        bumperCar.createPath("down", kiosk);
        bumperCar.createPath("left", wildWaterChannel);
        wildWaterChannel.createPath("down", restaurant);
        restaurant.createPath("down", carousel);
        kiosk.createPath("left", carousel);
        restaurant.createPath("left", freefallTower);
        freefallTower.createPath("down", entrance);
        carousel.createPath("left", entrance);
        entrance.createPath("down", parkingLot);

        scanner = new Scanner(System.in);
        player = new Player(rollerCoaster);

        System.out.println("You’re in a theme park, it’s getting dark.");
        System.out.println("You want to go to your car.");
        System.out.println("On the way there you want to have as much fun as possible.");
        System.out.println("But you have only limited energy and money left.\n");

        play(parkingLot);

    }

    //This Method includes the whole game play
    private static void play(Location location) {

        //This while-loop runs as long as the player doesn't reach the parking lot or runs out of energy.
        while (!player.getCurrentLocation().equals(location) && player.getEnergy() >= 0) {
            System.out.println("You are here: " + player.toString());
            System.out.print("> ");
            String directionInput = scanner.nextLine().toLowerCase();

            //This while-loop checks if the input word is allowed and runs as long as the word isn't allowed
            while (!isAllowedWord(directionInput)) {
                System.out.print("> ");
                directionInput = scanner.nextLine().toLowerCase();
            }

            //This if-Statement checks if the player wants to stay or walk
            if (directionInput.equals("rest") || directionInput.equals("ride")) {
                player.stay();
            } else {
                player.walk(directionInput);
            }
        }

        handleGameExit(location);
    }

    //This method handles when the player either reaches the parking lot or runs out of energy
    private static void handleGameExit(Location location) {
        if (player.getEnergy() >= 0) {
            System.out.println("You are here now: Parking Lot");
            System.out.println(String.format("Congratulations, you made it. You have collected %d fun points and have %.2f \u20ac.", player.getFunPoints(), player.getMoney()));
        } else {
            player.gameOver(location);
            System.out.println("Game over. You collapse exhausted and the park inspector must carry you out of the park.");
            System.out.println(String.format("You lose all your fun points! You have %.2f \u20ac.", player.getMoney()));
        }
    }

    //This method checks if the entered word is an allowed word
    private static boolean isAllowedWord(String word) {

        //the if condition returns true, the neighboring location is not null.
        //In the last two conditions, the program checks, if the user input ride or rest is at the current location a valid word.
        if (word.equals("up") && player.getCurrentLocation().getNeighboringLocation("up") != null) {
            return true;
        } else if (word.equals("down") && player.getCurrentLocation().getNeighboringLocation("down") != null) {
            return true;
        } else if (word.equals("left") && player.getCurrentLocation().getNeighboringLocation("left") != null) {
            return true;
        } else if (word.equals("right") && player.getCurrentLocation().getNeighboringLocation("right") != null) {
            return true;
        } else if (word.equals("ride") && player.getCurrentLocation().getClass().equals(FunRide.class)) {
            return true;
        } else return word.equals("rest") && player.getCurrentLocation().getClass().equals(Facility.class);

    }

}
