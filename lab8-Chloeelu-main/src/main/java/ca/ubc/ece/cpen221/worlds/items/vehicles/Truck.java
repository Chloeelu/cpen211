package ca.ubc.ece.cpen221.worlds.items.vehicles;

import ca.ubc.ece.cpen221.worlds.*;
import ca.ubc.ece.cpen221.worlds.ai.AI;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.MoveCommand;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.Grass;
import ca.ubc.ece.cpen221.worlds.items.MoveableItem;
import ca.ubc.ece.cpen221.worlds.items.animals.ArenaAnimal;
import ca.ubc.ece.cpen221.worlds.items.animals.Gnat;


import javax.swing.*;

public class Truck implements MoveableItem, Actor {
    private static final ImageIcon truckImage = Util.loadImage("trucks.gif");
    private static final int STRENGTH = 500;
    private static final int INITIAL_ENERGY = 100;
    private final AI ai;

    private Location location;
    private int energy;

    /**
     * Create a new {@link Truck} with an {@link AI} at
     * <code> initialLocation </code>. The <code> initialLocation
     * </code> must be valid and empty.
     *
     * @param truckAI         : The AI designed for trucks
     * @param initialLocation : the location where this truck will be created
     */
    public Truck(AI truckAI, Location initialLocation) {
        ai = truckAI;
        location = initialLocation;
        energy = INITIAL_ENERGY;
    }

    /**
     * The energy that this food contains as a plant
     *
     * @return plant energy of this food
     */
    @Override
    public int getPlantCalories() {
        return 0;
    }

    /**
     * The energy that this food contains as an animal
     *
     * @return meat energy of this food
     */
    @Override
    public int getMeatCalories() {
        return 0;
    }

    /**
     * The visualization of this Item in the world.
     *
     * @return the image of this Item
     */
    @Override
    public ImageIcon getImage() {
        return truckImage;
    }

    /**
     * Gets a String that serves as a unique identifier for this type of Item.
     *
     * @return the name of this item
     */
    @Override
    public String getName() {
        return "Truck";
    }

    /**
     * Gets the location of this Item in the World.
     *
     * @return the location in the world
     */
    @Override
    public Location getLocation() {
        return location;
    }

    /**
     * Returns the strength of this Item. Generally, if an item possesses
     * greater strength than another, then it can eliminate the other. For
     * example, a {@link Vehicle} can run over everything that has a lower
     * strength.
     *
     * @return the strength of this Item
     */
    @Override
    public int getStrength() {
        return STRENGTH;
    }

    /**
     * Causes this Item to lose energy. The consequences of this varies for
     * different types of Items.
     * <ul>
     * <li>{@link Grass} and {@link Gnat} die when they lose energy.</li>
     * <li>{@link ArenaAnimal} reduces its energy level and it dies if its
     * energy level drops below or equal to 0</li>
     * </ul>
     *
     * @param energy the amount of energy lost
     */
    @Override
    public void loseEnergy(int energy) {
        this.energy -= energy;
    }

    /**
     * Returns whether or not this Item is dead. If this Item is dead, it will
     * be removed from the World. An item is dead if it is eaten, run over by a
     * Vehicle, loses all its energy and energy level drops below or equal 0,
     * etc.
     *
     * @return true if this Item is dead, false if alive
     */
    @Override
    public boolean isDead() {
        return energy <= 0;
    }

    /**
     * Move to the target location. The <code> targetLocation </code> must be
     * valid and empty.
     *
     * @param targetLocation the location that this item is moving to
     */
    @Override
    public void moveTo(Location targetLocation) {
        location = targetLocation;
        int x_energy = Math.abs(targetLocation.getX()-this.location.getX());
        int y_energy = Math.abs(targetLocation.getY()-this.location.getY());
        loseEnergy(x_energy+y_energy);
    }

    /**
     * Returns the maximum distance that this item can move in one step. For
     * example, a {@link MoveableItem} with moving range 1 can only move to
     * adjacent locations.
     *
     * @return the maximum moving distance
     */
    @Override
    public int getMovingRange() {
        return 1;
    }

    @Override
    public int getCoolDownPeriod() {
        // Each Gnat acts every 1-10 steps randomly.
        return Util.RAND.nextInt(10) + 1;
    }

    @Override
    public Command getNextAction(World world) {
        Direction dir = Util.getRandomDirection();
        Location targetLocation = new Location(this.getLocation(), dir);
        if (Util.isValidLocation(world, targetLocation) &&
                Util.isLocationEmpty(world, targetLocation)) {
            return new MoveCommand(this, targetLocation);
        }

        return new WaitCommand();
    }
}
