package adventure.location;

//This class includes the special location FunRide, where the player can get FunPoints if he rides.
public class FunRide extends Location {

    private final double COST;
    private final int FUN_POINTS;

    public FunRide(String name, double cost, int funPoints) {
        super(name);
        this.COST = cost;
        this.FUN_POINTS = funPoints;
    }

    public double getCOST() {
        return COST;
    }

    public int getFUN_POINTS() {
        return FUN_POINTS;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("or ride\nA ride cost %.2f \u20ac and you will receive %d fun points", getCOST(), getFUN_POINTS());
    }
}
