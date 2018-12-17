package adventure.location;


//This class includes the special location Facility, where the player can get EnergyPoints if he rests.
public class Facility extends Location {

    private final double COST;
    private final int ENERGY_POINTS;

    public Facility(String name, double cost, int energyPoints) {
        super(name);
        this.COST = cost;
        this.ENERGY_POINTS = energyPoints;
    }

    public double getCOST() {
        return COST;
    }

    public int getENERGY_POINTS() {
        return ENERGY_POINTS;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("or rest\nA rest cost %.2f \u20ac and you will receive %d energy points", getCOST(), getENERGY_POINTS());
    }

}
