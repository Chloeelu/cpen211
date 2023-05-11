package evolution;

import java.util.*;

public class Evolution {
    public static ArrayList<ArrayList<Integer>> cell_copy;
    public static ArrayList<ArrayList<Integer>> cell;
    //public static ArrayList<ArrayList<Integer>> all_path;

    /**
     * Compute the number of cells that survive the evolutionary process as
     * described in the problem statement assuming that a given cell dies during
     * the process.
     *
     * @param evolutionaryTree array that represents the normal evolution of the organism
     * @param deadCell         the index in evolutionaryTree of the cell that dies
     * @return the number of surviving cells in the mature organism
     * @requires - evolutionaryTree will contain exactly N elements, where N is
     * an odd integer between 1 and 50, inclusive.
     * - There will be exactly one "-1" element in evolutionaryTree.
     * - Every element of evolutionaryTree will be between -1 and N-1, inclusive.
     * - evolutionaryTree will form a binary tree.
     * - deadCell will be between 0 and N-1, inclusive.
     */
    public static int numSurvivingCells(int[] evolutionaryTree, int deadCell) {
        set(evolutionaryTree);
        ArrayList<ArrayList<Integer>> all_path=new ArrayList<>(getallpaths(cell.get(0),new ArrayList<>(), new ArrayList<>()));
        int dead_path = 0;
        for (ArrayList<Integer> a : all_path) {
            if (a.contains(deadCell)) {
                dead_path++;
            }
        }
        return all_path.size() - dead_path;
    }

    /**
     * Compute the number of cells that survive the evolutionary process as
     * described in the problem statement assuming that a given cell dies during
     * the process.
     *
     * @param evolutionaryTree array that represents the normal evolution of the organism
     * @param deadCell         the index in evolutionaryTree of the cell that dies
     * @return the set of surviving cells in the mature organism
     * @requires - evolutionaryTree will contain exactly N elements, where N is
     * an odd integer between 1 and 50, inclusive.
     * - There will be exactly one "-1" element in evolutionaryTree.
     * - Every element of evolutionaryTree will be between -1 and N-1, inclusive.
     * - evolutionaryTree will form a binary tree.
     * - deadCell will be between 0 and N-1, inclusive.
     */
    public static Set<Integer> getSurvivingCells(int[] evolutionaryTree, int deadCell) {
        set(evolutionaryTree);
        ArrayList<ArrayList<Integer>> all_path=new ArrayList<>(getallpaths(cell.get(0),new ArrayList<>(), new ArrayList<>()));
        ArrayList<ArrayList<Integer>> all_path_copy=new ArrayList<>(all_path);
        for (ArrayList<Integer> a : all_path) {
            if (a.contains(deadCell)) {
                all_path_copy.remove(a);
            }
        }
        Set<Integer> survivor=new HashSet<>();
        for(ArrayList<Integer> arr:all_path_copy){
            survivor.addAll(arr);
        }


        return survivor;
    }

    public static ArrayList<ArrayList<Integer>> getallpaths(ArrayList<Integer> node, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> all_path) {
        //ArrayList<Integer> new_path=new ArrayList<>(path);
        path.add(node.get(0));
        if (node.size() == 1) {
            /*ArrayList<ArrayList<Integer>> all_path_copy=new ArrayList<>();
            if(all_path!=null) {
                for (ArrayList<Integer> c : all_path) {
                    ArrayList<Integer> c_copy = new ArrayList<>(c);
                    all_path_copy.add(c_copy);
                }
            }*/
            all_path.add(path);
            //Evolution.all_path=all_path_copy;
        } else {
            ArrayList<Integer> new_node1;
            int lookfor = node.get(1);
            for (ArrayList<Integer> connection : cell_copy) {
                if (connection.get(0) == lookfor) {
                    ArrayList<Integer> new_path1 = new ArrayList<>(path);
                    new_node1 = new ArrayList<>(connection);
                    getallpaths(new_node1, new_path1, all_path);
                }

            }

            if (node.size() == 3) {
                ArrayList<Integer> new_path = new ArrayList<>(path);
                ArrayList<Integer> new_node2;
                lookfor = node.get(2);
                for (ArrayList<Integer> connection : cell_copy) {
                    if (connection.get(0) == lookfor) {
                        new_node2 = new ArrayList<>(connection);
                        getallpaths(new_node2, new_path, all_path);
                    }
                }


            }
        }
        return  all_path;
    }

    public static void set(int[] evolutionaryTree) {
        ArrayList<ArrayList<Integer>> cell1 = new ArrayList<>();
        for (int i = 0; i < evolutionaryTree.length; i++) {
            ArrayList<Integer> link = new ArrayList<>();
            if (evolutionaryTree[i] == -1) {
                link.add(evolutionaryTree[i]);
                link.add(i);
                cell1.add(0, link);
            } else {
                link.add(evolutionaryTree[i]);
                link.add(i);
                cell1.add(link);
            }
        }//get all connections
        ArrayList<ArrayList<Integer>> cell_copy = new ArrayList<>(cell1);
        for (int i = 0; i < cell1.size(); i++) {
            ArrayList<Integer> a = new ArrayList<>(cell1.get(i));
            int start = cell1.get(i).get(0);
            for (int j = i + 1; j < cell1.size(); j++) {
                if (cell1.get(j).get(0) == start) {
                    a.add(cell1.get(j).get(1));
                    cell_copy.remove(cell1.get(i));
                    cell_copy.remove(cell1.get(j));
                    cell_copy.add(a);
                }
            }
        }
        for (int i = 0; i < cell1.size(); i++) {
            int terminal = 1;
            int lookfor = cell1.get(i).get(1);
            for (ArrayList<Integer> integers : cell1) {
                if (integers.get(0) == lookfor) {
                    terminal = 0;
                }
            }
            if (terminal == 1) {
                ArrayList<Integer> terminal_node = new ArrayList<>();
                terminal_node.add(lookfor);
                cell_copy.add(terminal_node);
            }
        }
        Evolution.cell = cell1;
        Evolution.cell_copy = cell_copy;
    }
}
