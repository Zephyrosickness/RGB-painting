import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
        colorGrid.setBounds(0, 0, 300, 400);
        frame.add(colorGrid);

        //controlPanelButtons(controlPanel);

        frame.setResizable(false);
        frame.setVisible(true);

    }

    //adds buttons for sidepanel
    public static void controlPanelButtons(JPanel panel){

        //set to null for absolute positioning (eg being able to directly specify what x/y value items go on)

        JLabel debug = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------");
        debug.setBounds(0,0,280,25);
        panel.add(debug);
    }

}