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
        rowTextField = new JTextField("3");
        rowTextField.setBounds(INTERVAL+((CHAR_LENGTH*ROW_TEXT.getText().length())), INTERVAL, INTERVAL, INTERVAL);
        panel.add(rowTextField);

        //field for column
        columnTextField = new JTextField("3");
        columnTextField.setBounds(INTERVAL+((CHAR_LENGTH*COLUMN_TEXT.getText().length())), INTERVAL*2, INTERVAL, INTERVAL);
        panel.add(columnTextField);

        //where u type in the rgb
        JTextField hexField = new JTextField("111");
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
        panel.removeAll();
        int column = Integer.parseInt(columnTextField.getText());
        int row = Integer.parseInt(rowTextField.getText());
        if(row<=0){
            row = 1;
        }
        if(column<=0){
            column = 1;
        }

        panel.setLayout(new GridLayout(row,column));
        Random rand = new Random();

        for(int rowCount=1;rowCount<=row;rowCount++){
            for(int columnCount=1;columnCount<=column;columnCount++){
                int R = rand.nextInt(255);
                int G = rand.nextInt(255);
                int B = rand.nextInt(255);
                Color color = new Color(R,G,B);

                JTextField colorPane = new JTextField();
                colorPane.setBackground(color);
                panel.add(colorPane);
                panel.repaint();
                panel.revalidate();
            }
        }
    }

    public static ArrayList<Integer> parseHex(int hex){
        int length = Integer.toString(hex).length();
        String hexCode = Character.toString(Integer.toString(hex).charAt(0));
        int count = 0;
        ArrayList<Integer> output = new ArrayList<Integer>();
        for(int i=1;i<length;i++){
            char current = Integer.toString(hex).charAt(i);
            hexCode += current;
            if((i+1)%3==0){
                output.add(Integer.parseInt(hexCode));
                count += 3;

                if((i+1)<length){
                    i++;
                }                  
                hexCode = Character.toString(Integer.toString(hex).charAt(i));
            }
        }
        System.out.println(count);
        if(count<length){
            output.add(Integer.parseInt(hexCode));
        }

        System.out.println(output);
        return output;
    }
}