package pirates;

import java.util.*;

public abstract class PirateManager {

    static Treasure[] treasures;
    /**
     * Generate a list of pirates by assigning treasures to the pirate
     * using the name of the pirate in the pirate-to-treasure mapping
     * @param treasures the sequence of treasures, is not empty
     * @param pirateToTreasureMap pirateToTreasureMap[i] is the name of the pirate
     *                            that is given treasures[i], and is not empty
     * @return a list of pirates with each pirate being assigned the appropriate treasures
     * @throws IllegalArgumentException if treasures.length != pirateToTreasureMap.length
     */
    public static List<Pirate> buildPiratesWithTreasure(Treasure[] treasures, String[] pirateToTreasureMap) {
        PirateManager.treasures =treasures;
        if(treasures.length!= pirateToTreasureMap.length){
            throw new IllegalArgumentException("precondition not met");
        }
        List<Pirate> pirateList = new ArrayList<>();

        for (int i = 0; i < pirateToTreasureMap.length; i++) {
            Pirate pirate = new Pirate(pirateToTreasureMap[i]);
            int pirateIdx = pirateList.indexOf(pirate);
            if (pirateIdx != -1) {
                pirate = pirateList.get(pirateIdx);
            }
            else {
                pirateList.add(pirate);
            }
            pirate.addTreasure(treasures[i]);
        }

        return pirateList;
    }

    /**
     * Is the allocation of treasure to pirates balanced?
     *
     * @param treasures the original list of treasures, is not null
     * @param pirates the list of pirates after the treasures have been allocated
     * @param deviationPercentage the allowed variation from the mean
     *                            for the per-pirate treasure value,
     *                            is between 0 and 200
     * @return true if the allocation is balanced and false otherwise
     */
    public static boolean isBalanced(Treasure[] treasures, List<Pirate> pirates, int deviationPercentage) {
        // TODO: Implement this method
        if(!PirateManager.treasures.equals(treasures)){
            return false;
        }
        int N=pirates.size();
        int T=0;
        for(Pirate s:pirates) {
            T+= s.getTreasureValue();
        }
        int P=deviationPercentage;
        int low=T/N * (100-P)/100;
        int high=T/N *(100+P)/100;
        for(Pirate s:pirates){
            if(s.getTreasureValue()<low||s.getTreasureValue()>high){
                return false;
            }
        }

        return true;
    }

}