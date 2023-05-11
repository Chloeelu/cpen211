package ca.ubc.ece.cpen221.worlds.ai;

import ca.ubc.ece.cpen221.worlds.ArenaWorld;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.animals.ArenaAnimal;

/**
 * Your Bird AI.
 */
public class BirdAI extends AbstractAI {

    private int closest = 11; // max number; greater than bird's view range
    private int temp;
    private boolean foxFound;

    public BirdAI() {
    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        // TODO: Change this. Implement your own AI rules.
        return new WaitCommand();
    }
}
