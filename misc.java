import java.util.*;
import javax.swing.*;

public class misc extends display{
    public static ArrayList<Integer> parseHex(String hex){
        //init var
        int length = hex.length();
        String hexCode = Character.toString(hex.charAt(0));
        int count = 0;
        ArrayList<Integer> output = new ArrayList<Integer>();


        for(int i=1;i<length;i++){
            //init var
            char current = hex.charAt(i);
            hexCode += current;
            //after 3 values, add to list of rgb values
            if((i+1)%3==0){
                output.add(Integer.parseInt(hexCode));
                count += 3;

                if((i+1)<length){
                    i++;
                }                  
                hexCode = Character.toString(hex.charAt(i));
            }
        }

        //add the rest
        if(count<length){
            output.add(Integer.parseInt(hexCode));
        }
        System.out.println("OUTPUT: "+output);
        return output;
    }

    public static void initHexPanel(int rows, JPanel panel){

    }
}
