package ca.ubc.ece.cpen221.worlds.items.animals;

import ca.ubc.ece.cpen221.worlds.*;
import ca.ubc.ece.cpen221.worlds.ai.AI;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.MoveCommand;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.LivingItem;

import javax.swing.ImageIcon;

/**
 * The {@link Tiger} is an {@link ArenaAnimal} that tries to eat {@link Rabbit} and {@link Fox}s.
 */
public class Tiger implements ArenaAnimal {
    private static final int INITIAL_ENERGY = 150;
    private static final int MAX_ENERGY = 200;
    private static final int STRENGTH = 200;
    private static final int VIEW_RANGE = 8;
    private static final int MIN_BREEDING_ENERGY = 15;
    private static final int COOLDOWN = 4;
    private static final ImageIcon tigerImage = Util.loadImage("tiger.gif");

    private final AI ai;

    private Location location;
    private int energy;

    /**
     * Create a new {@link Tiger} with an {@link AI} at
     * <code>initialLocation</code>. The <code> initialLocation </code> must be
     * valid and empty
     *
     * @param TigerAI           the AI designed for Tigers
     * @param initialLocation the location where this Tiger will be created
     */
    public Tiger(AI TigerAI, Location initialLocation) {
        this.ai = TigerAI;
        this.location = initialLocation;

        this.energy = INITIAL_ENERGY;
    }

    @Override
    public LivingItem breed() {
        Tiger child = new Tiger(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }

    @Override
    public void eat(Food food) {
        // Note that energy does not exceed energy limit.
        energy = Math.min(MAX_ENERGY, energy + food.getMeatCalories());
    }

    @Override
    public int getCoolDownPeriod() {
        return COOLDOWN;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public ImageIcon getImage() {
        return tigerImage;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public int getMaxEnergy() {
        return MAX_ENERGY;
    }

    @Override
    public int getMeatCalories() {
        // The amount of meat calories it provides is equal to its current
        // energy.
        return energy;
    }

    @Override
    public int getMinimumBreedingEnergy() {
        return MIN_BREEDING_ENERGY;
    }

    @Override
    public int getMovingRange() {
        return 1; // Can only move to adjacent locations.
    }

    @Override
    public String getName() {
        return "Tiger";
    }

    @Override
    public Command getNextAction(World world) {
        // The Gnat selects a random direction and check if the next location at
        // the direction is valid and empty. If yes, then it moves to the
        // location, otherwise it waits.
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(this.getLocation(), dir);
        if (Util.isValidLocation(world, targetLocation) &&
                Util.isLocationEmpty(world, targetLocation)) {
            return new MoveCommand(this, targetLocation);
        }

        return new WaitCommand();
    }

    @Override
    public int getPlantCalories() {
        // This Tiger is not a plant.
        return 0;
    }

    @Override
    public int getStrength() {
        return STRENGTH;
    }

    @Override
    public int getViewRange() {
        return VIEW_RANGE;
    }

    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    @Override
    public void loseEnergy(int energyLoss) {
        this.energy = this.energy - energyLoss;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
    }
}
