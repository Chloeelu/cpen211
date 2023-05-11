package ca.ubc.ece.cpen221.worlds.ai;

import ca.ubc.ece.cpen221.worlds.ArenaWorld;
import ca.ubc.ece.cpen221.worlds.commands.Command;
import ca.ubc.ece.cpen221.worlds.commands.WaitCommand;
import ca.ubc.ece.cpen221.worlds.items.animals.ArenaAnimal;

/**
 * Your Tiger AI.
 */
public class TigerAI extends AbstractAI {
    private int closest = 3; // max number; greater than Tiger's view range

    public TigerAI() {

    }

    @Override
    public Command getNextAction(ArenaWorld world, ArenaAnimal animal) {
        // TODO: Change this. Implement your own AI to make decisions regarding
        // the next action.
        return new WaitCommand();
    }

}
