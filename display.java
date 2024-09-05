import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class display{
    //interval for ui placements
    final static int INTERVAL = 25;
    //how many px each character needs
    final static int CHAR_LENGTH = 8;

    static JTextField rowTextField;
    static JTextField columnTextField;
    final static int WIDTH = 600;
    final static int HEIGHT = 400;
    static JTextField hexField;

    public static void main(String[] args){

        // create a window
        JFrame frame = new JFrame("RGB painting");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(null);

        //panel that holds all the stuff on the side
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 0, WIDTH/2, HEIGHT);
        controlPanel.setLayout(null);
        frame.add(controlPanel);


        //panel that holds all the stuff on the side
        JPanel colorGrid = new JPanel();
        colorGrid.setBounds((WIDTH/2)-(INTERVAL*2),INTERVAL,WIDTH/2,WIDTH/2);
        frame.add(colorGrid);

        controlPanelButtons(controlPanel, colorGrid);
        gridButtons(colorGrid);

        frame.setResizable(false);
        frame.setVisible(true);

    }

    //adds buttons for sidepanel
    public static void controlPanelButtons(JPanel panel, JPanel grid){

        //labels for rows
        JLabel ROW_TEXT = new JLabel("Rows");
        ROW_TEXT.setBounds(INTERVAL, INTERVAL, INTERVAL*((CHAR_LENGTH*ROW_TEXT.getText().length())), INTERVAL);
        panel.add(ROW_TEXT);

        //labels for columns
        JLabel COLUMN_TEXT = new JLabel("Columns");
        COLUMN_TEXT.setBounds(INTERVAL, INTERVAL*2, INTERVAL*(CHAR_LENGTH*COLUMN_TEXT.getText().length()), INTERVAL);
        panel.add(COLUMN_TEXT);

        //fields for column
        rowTextField = new JTextField("1");
        rowTextField.setBounds(INTERVAL+((CHAR_LENGTH*ROW_TEXT.getText().length())), INTERVAL, INTERVAL, INTERVAL);
        panel.add(rowTextField);

        //field for column
        columnTextField = new JTextField("1");
        columnTextField.setBounds(INTERVAL+((CHAR_LENGTH*COLUMN_TEXT.getText().length())), INTERVAL*2, INTERVAL, INTERVAL);
        panel.add(columnTextField);

        //where u type in the rgb
        hexField = new JTextField("123");
        hexField.setBounds(INTERVAL, HEIGHT/3, (WIDTH/2)-(INTERVAL*4), HEIGHT/2);
        panel.add(hexField);

        //button
        JButton refresh = new JButton("Test");
        refresh.setBounds(WIDTH/4,0,CHAR_LENGTH*refresh.getText().length(),INTERVAL);
        panel.add(refresh);

        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gridButtons(grid);
                parseHex(Integer.parseInt(hexField.getText()));
            }
        });


    }

    public static void gridButtons(JPanel panel){
        //init var
        panel.removeAll();
        int column = Integer.parseInt(columnTextField.getText());
        int row = Integer.parseInt(rowTextField.getText());
        int iterations = column*row;

        //error handler
        if(row<=0){
            row = 1;
        }
        if(column<=0){
            column = 1;
        }

        panel.setLayout(new GridLayout(row,column));
        int i = 0;
        Color color = new Color(0,0,0);
        while(i<=iterations){

            ArrayList<Integer> RGB = parseHex(Integer.parseInt(hexField.getText()));
            int RIndex = 0;
            int GIndex = 1;
            int BIndex = 2;

            //prevents negative nums

            if(i>0){
            RIndex = 0+(3*i);
            GIndex = 1+(3*i);
            BIndex = 2+(3*i);
            }

            int RColor = 0;
            int GColor = 0;
            int BColor = 0;

            //System.out.println(RGB.get(RIndex));

            if(RIndex>=(RGB.size()-1)==false){
                RColor = RGB.get(RIndex);
            }
            if(GIndex>=(RGB.size()-1)==false){
                GColor = RGB.get(GIndex);
            }
            if(BIndex>=(RGB.size()-1)==false){
                BColor = RGB.get(BIndex);
            }
                color = new Color(RColor,GColor,BColor);
            }

        JTextField colorPane = new JTextField();
        colorPane.setBackground(color);
        panel.add(colorPane);
        panel.repaint();
        panel.revalidate();

        i++;
        }

    public static ArrayList<Integer> parseHex(int hex){
        //init var
        String hexString = Integer.toString(hex);
        int length = hexString.length();
        String hexCode = Character.toString(hexString.charAt(0));
        int count = 0;
        final int[] DECIMALS = {0,1,2,3,4,5,6,7,8,9};
        ArrayList<Integer> output = new ArrayList<Integer>();


        for(int i=1;i<length;i++){
            char current = hexString.charAt(i);
            //error handler
            if(Arrays.asList(DECIMALS).contains((int)current)==false){
                current = '0';
            }
            hexCode += current;
            //after 3 values, add to list of rgb values
            if((i+1)%3==0){
                int hexInt = Integer.parseInt(hexCode);
                output.add(hexInt);
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

        return output;
    }
}