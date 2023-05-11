package flips;

import java.util.*;

public class Flipper {

    /**
     * Checks whether or not a list of flips will bring src to dest.
     * Returns false if there is some swap that is an invalid flip.
     *
     * A swap is said to be a flip iff its swapping indices are adjacent
     *     i.e. they differ by one.
     * A swap (and by extension a flip) is said to be valid on a string s
     *     iff its swapping indices are within bounds for s.
     *
     * @param src -- the string to start from
     * @param dest -- the target string
     * @param flips -- a sequence of swaps to be performed on src
     * @return true iff flips is a sequence of valid flips that when applied
     *     to src results in dest
     */
    public static boolean flipsMatches(String src, String dest, List<Swap> flips) {
       char[] start= src.toCharArray();
       char[] end =dest.toCharArray();
       char[] s=Arrays.copyOf(start, start.length);
       for( Swap sw: flips){
           int left=sw.getLeft();
           int right=sw.getRight();
           if(left<0||left>s.length){
               return false;
           }
           if(right<0||right>s.length){
               return false;
           }
           if(Math.abs(left-right)==1) {
               char left_char = s[left];
               char right_char = s[right];
               s[left] = right_char;
               s[right] = left_char;
           }
       }
        return Arrays.equals(s,end);
    }

    /**
     * Finds a list of valid flips on src that when applied to src gives dest.
     * Throws a NoFlipListException if no such list of flips exists.
     *
     * @param src source strng
     * @param dest destination string
     * @return A list of flips, if any exist, that results in dest when applied
     *     to src
     * @throws NoFlipListException if there does not exist a sequence of flips
     *     that results in dest when applied to src
     */
    public static List<Swap> flipsSequence(String src, String dest) throws NoFlipListException {
        List<Swap> seq = new ArrayList<>();
        char[] start = src.toCharArray();
        char[] end = dest.toCharArray();
        char[] transition = Arrays.copyOf(start, start.length);
        char[] end1 = dest.toCharArray();
        if (src.length() != dest.length()) {
            throw new NoFlipListException();
        }
        Arrays.sort(start);
        Arrays.sort(end);
        if (!Arrays.equals(start, end)) {
            throw new NoFlipListException();
        }
        for (int i = 0; i < dest.length(); i++) {
            char i_th_char = end1[i];
            int place = 0;
            for (int j = i; j < transition.length; j++) {
                if (transition[j] == (i_th_char)) {
                    place = j;
                    break;
                }
            }

            if (place > i) {
                while (place != i) {
                    Swap s = new Swap(place - 1, place);
                    seq.add(s);
                    char before_i = transition[place - 1];
                    transition[place - 1] = i_th_char;
                    transition[place] = before_i;
                    place -= 1;
                }
            }
        }
        return seq;
    }

    /**
     * Determines the number of pairs of distinct substrings of s that are
     *     a distance of maxDist or less.
     * The distance of a pair of strings is defined to be the distance between
     *     its two constituent strings.
     * The distance between two strings is defined to be the length of the
     *     shortest list of (valid on either string) flips that brings one
     *     string to the other. If no such list exists, the distance is
     *     taken to be infinite.
     *
     * Note that the distance between two strings as we have defined is
     *     a metric.
     *
     * Further note that a substring may appear multiple times in a string.
     *
     * @param s given string
     * @param maxDist -- is nonnegative
     * @return the number of pairs of substrings of s whose distance is at
     *     most maxDist.
     */
    public static int similarPairsCount(String s, int maxDist) {
        int count = 0;
        //ArrayList<ArrayList<String>> all=new ArrayList<>();
        for (int i = 2; i < s.length(); i++) {
            Set<String> o = new HashSet<>();
            // all.add(o);
            for (int a = 0; a < s.length() - i + 1; a++) {
                String x = s.substring(a, a + i);
                o.add(x);
            }
            ArrayList<String> arr = new ArrayList<>(o);
            for (int i1 = 0; i1 < arr.size() - 1; i1++) {
                //map.put(arr.get(i), new ArrayList<>());
                for (int j = i1 + 1; j < arr.size(); j++) {
                    try {
                        List<Swap> se = new ArrayList<>(flipsSequence(arr.get(i1), arr.get(j)));
                        if (se.size() <= maxDist) {
                            count++;
                            //map.get(arr.get(i1)).add(arr.get(j));
                        }
                    } catch (NoFlipListException ignored) {
                    }
                }
            }
        }

        return count;
    }

}
