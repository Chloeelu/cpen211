package colors;

import java.util.*;

public class ColorMixer {

    /**
     * Given a list of colors, combine all colors to form a new color.
     * Recall that colors can be combined if their component-wise sum is divisible by
     * the number of colors being combined.
     * @param colors A list of color objects.
     * @return The result of the color combination as a Color object
     * @throws CannotCombineColorsException if the colors cannot be combined
     */
    public Color combine(List<Color> colors) throws CannotCombineColorsException {
        int x_sum=0;
        int y_sum=0;
        int z_sum=0;
        int x = 0,y=0,z=0;
        int n=colors.size();
        for(Color c: colors){
            x_sum+=c.red;
            y_sum+=c.green;
            z_sum+=c.blue;
        }
        if(x_sum % n != 0||y_sum % n != 0 || z_sum % n !=0){
            throw new CannotCombineColorsException("Cannot Combine Colors");
        }
        if(x_sum%n==0){
            x=x_sum/n;
        }
        if(y_sum%n==0) {
            y=y_sum/n;
        }
        if(z_sum%n==0){
            z=z_sum/n;
        }
        return new Color(x,y,z);
    }

    /**
     * Given a list of colors and a target color, determine if there is some combination of
     * colors that can be combined to get a target color.
     * @param colors a list of colors.
     * @param target a target color.
     * @return True if there is some combination of colors that can be combined to get target. False otherwise.
     */
    public boolean createColor(List<Color> colors, Color target) {

        int red_target = target.red;
        int green_target = target.green;
        int blue_target = target.blue;
        ArrayList<ArrayList<Color>> allSubsets = getSubset(colors, colors.size() - 1);
        for (ArrayList<Color> combination : allSubsets) {
            int r_sum=0, g_sum=0, b_sum=0;
            if (combination.size()> 0) {
                for (Color c : combination) {
                    r_sum+=c.red;
                    g_sum+=c.green;
                    b_sum+=c.blue;
                }
                if (r_sum % red_target == 0 && g_sum % green_target == 0 && b_sum % blue_target == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * return all power sets with a given list
     * @param list the list contains the elements of Color type
     * @param n equals list.size-1
     * @return an arraylists contains all combination of elements in list
     */
    public ArrayList<ArrayList<Color>> getSubset(List<Color> list, int n) {
        ArrayList<ArrayList<Color>> allSubsets ;
        if (n < 0) {
            allSubsets = new ArrayList<>();
            allSubsets.add(new ArrayList<>());
        } else {
            allSubsets=getSubset(list, n - 1);
            Color item = list.get(n);
            ArrayList<ArrayList<Color>> moresubset = new ArrayList<>();
            for (ArrayList<Color> subset : allSubsets) {
                ArrayList<Color> newsubset = new ArrayList<>(subset);
                newsubset.add(item);
                moresubset.add(newsubset);
            }
            allSubsets.addAll(moresubset);
        }
        return allSubsets;
    }

}
