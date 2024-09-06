import java.util.*;
public class misc {
    public static ArrayList<Integer> parseHex(int hex){
        //init var
        String hexString = Integer.toString(hex);
        int length = hexString.length();
        String hexCode = Character.toString(hexString.charAt(0));
        int count = 0;
        ArrayList<Integer> output = new ArrayList<Integer>();


        for(int i=1;i<length;i++){
            //init var
            char current = hexString.charAt(i);
            hexCode += current;

            //after 3 values, add to list of rgb values
            if((i+1)%3==0){
                if(Integer.parseInt(hexCode)>255){
                    hexCode = "000";
                }
                output.add(Integer.parseInt(hexCode));
                count += 3;

                if((i+1)<length){
                    i++;
                }                  
                hexCode = Character.toString(hexString.charAt(i));
            }
        }

        //add the rest
        if(count<length){
            output.add(Integer.parseInt(hexCode));
        }
        System.out.println("OUTPUT: "+output);
        return output;
    }
}
