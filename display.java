import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class display{
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


    }


    public static void gridButtons(JPanel panel){
        panel.setLayout(new GridLayout(3,2));
        panel.setBounds(300, 0, 300, 400);


    }

    public static void addVar(String dataType, int bound, JPanel panel, String additions){
        final int INTERVAL = 25;

        switch(dataType){

            case "label":
                int length = additions.length();
                int varLength = 8*length;
                JLabel label = new JLabel(additions);
                label.setBounds(INTERVAL,INTERVAL*bound,varLength,INTERVAL);
                panel.add(label);
                break;

        }
    }
}