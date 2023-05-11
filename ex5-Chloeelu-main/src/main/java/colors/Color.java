package colors;

import java.util.ArrayList;
import java.util.List;

public class Color {

    String color;
    int red, green, blue;



    /**
     * First constructor for color
     * @param color of the form '#uvwxyz'
     */
    public Color(String color){
        this.color=color;
        List<Integer> colorlist=toDecimal(color);
        this.red=colorlist.get(0);
        this.green=colorlist.get(1);
        this.blue=colorlist.get(2);
    }

    /**
     * Second constructor for color
     * @param red the units of red (0 <= red <= 255>
     * @param green the units of green (0 <= green <= 255)
     * @param blue the units of blue (0 <= blue <= 255)
     */
    public Color(int red, int green, int blue){
        if((red>=0&&red<=256)&&(green>=0&&green<=256)&&(blue>=0&&blue<=256)){
            this.red=red;
            this.green=green;
            this.blue=blue;
        }
        this.color=toHex(red, green, blue);
    }

    /**
     * Create a list of components for the current color.
     * @return A list of integers L where L[0], L[1] and L[2] are the red, green and blue
     * components of the color respectively.
     */
    public List<Integer> components(){
        List<Integer> current_color=new ArrayList<>();
        current_color.add(red);
        current_color.add(green);
        current_color.add(blue);
        return current_color;
    }

    //override equals() to compare two Color objects
    @Override
    public boolean equals(Object obj) {
        //if the object compares with itself return true
        if(obj==this){
            return true;
        }

        //check if obj an instance of Color
        if(!(obj instanceof Color)){
            return false;
        }
        //typecast obj to Color and then it can be compared
        Color c=(Color) obj;
        //compare the data members
        return CharSequence.compare(color, c.color)==0
                    && red == c.red
                    && green == c.green
                    && blue == c.blue;
    }

    /**
     * return a list of number that converted from the color string
     * @param color string of color name
     * @return list of decimal number that represents the color
     */
    public List<Integer> toDecimal(String color){
        int red_h=Integer.parseInt(color.substring(1,3),16);
        int green_h=Integer.parseInt(color.substring(3,5),16);
        int blue_h=Integer.parseInt(color.substring(5),16);
        List<Integer>color_h=new ArrayList<>(3);
        color_h.add(red_h);
        color_h.add(green_h);
        color_h.add(blue_h);

        return color_h;
    }

    public String toHex(int x, int y, int z){
        StringBuilder c= new StringBuilder("#");
        String r=Integer.toHexString(x);
        String g=Integer.toHexString(y);
        String b=Integer.toHexString(z);
        ArrayList<String> alist=new ArrayList<>(){{ add(r); add(g); add(b);}};
        for(String colorstr:alist){
            if(colorstr.length()<2) {
                String str="0"+colorstr;
                c.append(str);
            }else{
                c.append(colorstr);
            }
        }
        return c.toString().toUpperCase();
    }
}
