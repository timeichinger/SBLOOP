package adventure.location;

public class Location {

    private String name;
    private Location leftLocation;
    private Location rightLocation;
    private Location upLocation;
    private Location downLocation;

    public Location(String name) {
        this.name = name;
        leftLocation = null;
        rightLocation = null;
        upLocation = null;
        downLocation = null;
    }

    public String getName() {
        return name;
    }

    //This method returns the Neighboring Location in the given direction
    public Location getNeighboringLocation(String direction) {
        switch (direction) {
            case "up":
                return upLocation;
            case "down":
                return downLocation;
            case "left":
                return leftLocation;
            case "right":
                return rightLocation;
            default:
                return null;

        }

    }

    @Override
    public String toString() {
        String whereCanHeGo = getName() + ". You can go: ";

        //These if conditions check in which directions the player can go and then add them to the string whereCanHeGo.

        if (leftLocation!=null) {
            whereCanHeGo += "left ";
        }
        if (rightLocation!=null) {
            whereCanHeGo += "right ";
        }
        if (upLocation!=null) {
            whereCanHeGo += "up ";
        }
        if (downLocation!=null) {
            whereCanHeGo += "down ";
        }
        return whereCanHeGo;
    }

    //This method returns true, when the name of the locations are equal.
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this){
            return true;
        }
        if (! obj.getClass().equals(this.getClass())){
            return false;
        }
        Location location = (Location) obj;
        return this.name.equals(location.name);
    }

    //In the createPath method, a path is created between this location and the passed location.
    //The path is created in both passed direction and opposite direction
    public void createPath(String direction, Location location) {
        switch (direction) {
            case "up":
                upLocation = location;
                location.downLocation = this;
                break;
            case "down":
                downLocation = location;
                location.upLocation = this;
                break;
            case "left":
                leftLocation = location;
                location.rightLocation = this;
                break;
            case "right":
                rightLocation = location;
                location.leftLocation = this;
        }
    }
}
