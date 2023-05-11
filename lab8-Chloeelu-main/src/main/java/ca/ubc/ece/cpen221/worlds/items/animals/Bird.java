package ca.ubc.ece.cpen221.worlds.items.animals;

import ca.ubc.ece.cpen221.worlds.*;
import ca.ubc.ece.cpen221.worlds.ai.AI;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.MoveCommand;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.Grass;
import ca.ubc.ece.cpen221.worlds.items.LivingItem;

import javax.swing.ImageIcon;

/**
 * The {@link Bird} is an {@link ArenaAnimal} that eats {@link Grass} and can
 */
public class Bird implements ArenaAnimal {

    private static final int INITIAL_ENERGY = 100;
    private static final int MAX_ENERGY = 50;
    private static final int STRENGTH = 50;
    private static final int MIN_BREEDING_ENERGY = 6;
    private static final int VIEW_RANGE = 3;
    private static final int COOLDOWN = 2;
    private static final ImageIcon BirdImage = Util.loadImage("bird.gif");

    private final AI ai;

    private Location location;
    private int energy;

    /**
     * Create a new {@link Bird} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLoation
     * </code> must be valid and empty.
     *
     * @param BirdAI        : The AI designed for Bird
     * @param initialLocation : the location where this bird will be created
     */
    public Bird(AI BirdAI, Location initialLocation) {
        ai = BirdAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    @Override
    public LivingItem breed() {
        Bird child = new Bird(ai, location);
        child.energy = energy / 2;
        this.energy = energy / 2;
        return child;
    }

    @Override
    public void eat(Food food) {
        // Note that energy does not exceed energy limit.
        energy = Math.min(MAX_ENERGY, energy + food.getPlantCalories());
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
        return BirdImage;
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
        return "Bird";
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
        // This Bird is not a plant.
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
        this.energy -= energyLoss;
    }

    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;

    }
}
