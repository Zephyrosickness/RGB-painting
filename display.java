import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class display{
    //interval for ui placements
    final static int INTERVAL = 25;
    //how many px each character needs
    final static int CHAR_LENGTH = 8;

    public static void main(String[] args){

        // create a window
        JFrame frame = new JFrame("RGB painting");
        frame.setSize(600, 400);

        //panel that holds all the stuff on the side
        JPanel controlPanel = new JPanel();
        controlPanel.setBounds(0, 0, 280, 400);
        controlPanel.setLayout(null);
        frame.add(controlPanel);

        controlPanelButtons(controlPanel);

        //panel that holds all the stuff on the side
        JPanel colorGrid = new JPanel();
        colorGrid.setBounds(300, 0, 300, 400);
        colorGrid.setLayout(null);
        gridButtons(colorGrid);
        frame.add(colorGrid);

        //controlPanelButtons(controlPanel);

        frame.setResizable(false);
        frame.setVisible(true);

    }

    //adds buttons for sidepanel
    public static void controlPanelButtons(JPanel panel){
        final String[] LABEL_OBJECT_NAMES = {"Rows", "Columns"};
        int bound = 1;

        for (String i:LABEL_OBJECT_NAMES){
            addVar("label", bound, panel, i);
            bound++;
        }

        //fields; interval*5 because rows is 4 chars
        JTextField ROW_TEXT_FIELD = new JTextField("0");
        ROW_TEXT_FIELD.setBounds(INTERVAL*5*CHAR_LENGTH, INTERVAL, INTERVAL, INTERVAL);
        panel.add(ROW_TEXT_FIELD);

        //fields; interval*9 because columns is 8 chars, interval*2 because column is the 2nd one down
        JTextField COLUMN_TEXT_FIELD = new JTextField("0");
        COLUMN_TEXT_FIELD.setBounds(INTERVAL*9*CHAR_LENGTH, INTERVAL*2, INTERVAL, INTERVAL);
        panel.add(COLUMN_TEXT_FIELD);
    }


    public static void gridButtons(JPanel panel){
        panel.setLayout(new GridLayout(3,2));
        panel.setBounds(300, 0, 300, 400);


    }

    public static void addVar(String dataType, int bound, JPanel panel, String additions){

        switch(dataType){

            case "label":
                int length = additions.length();
                int varLength = 8*length;

                JLabel LABEL = new JLabel(additions);
                LABEL.setBounds(INTERVAL,INTERVAL*bound,varLength,INTERVAL);
                panel.add(LABEL);
                break;

        }
    }
}