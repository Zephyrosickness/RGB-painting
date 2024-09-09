import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;


public class display{
    //interval for ui placements
    final static int INTERVAL = 25;
    //how many px each character needs
    final static int CHAR_LENGTH = 8;

    final static int WIDTH = 600;
    final static int HEIGHT = 400;

    static int panelCount = 0;

    //panels where the elements go
    //controlpanel: row/column data / colorgrid: panel for the colors / inputpanel: panel for user rgb input
    static JPanel controlPanel = new JPanel();
    static JPanel colorGrid = new JPanel();
    static JPanel inputPanel = new JPanel();
    public static void main(String[] args){

        // create a window
        JFrame frame = new JFrame("RGB painting");
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(null);

        //panel that holds all the row/column data
        controlPanel.setBounds(0, 0, WIDTH/2, HEIGHT);
        controlPanel.setLayout(null);
        frame.add(controlPanel);

        //panel that holds all color
        colorGrid.setBounds((WIDTH/2)-(INTERVAL*2),INTERVAL,WIDTH/2,WIDTH/2);
        frame.add(colorGrid);

        controlPanelButtons(controlPanel);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    //adds buttons for sidepanel
    public static void controlPanelButtons(JPanel panel){

        //labels for rows
        JLabel ROW_TEXT = new JLabel("Rows");
        ROW_TEXT.setBounds(INTERVAL, INTERVAL, INTERVAL*((CHAR_LENGTH*ROW_TEXT.getText().length())), INTERVAL);
        panel.add(ROW_TEXT);

        //labels for columns
        JLabel COLUMN_TEXT = new JLabel("Columns");
        COLUMN_TEXT.setBounds(INTERVAL, INTERVAL*2, INTERVAL*(CHAR_LENGTH*COLUMN_TEXT.getText().length()), INTERVAL);
        panel.add(COLUMN_TEXT);

        //fields for rows
        JTextField rowTextField = new JTextField("4");
        rowTextField.setBounds(INTERVAL+((CHAR_LENGTH*ROW_TEXT.getText().length())), INTERVAL, INTERVAL, INTERVAL);
        panel.add(rowTextField);

        //field for column
        JTextField columnTextField = new JTextField("2");
        columnTextField.setBounds(INTERVAL+((CHAR_LENGTH*COLUMN_TEXT.getText().length())), INTERVAL*2, INTERVAL, INTERVAL);
        panel.add(columnTextField);

        //where u type in the rgb
        JTextField hexField = new JTextField("0");
        hexField.setBounds(INTERVAL, INTERVAL*4, (WIDTH/2)-INTERVAL*4, (HEIGHT/2)+INTERVAL);
        panel.add(hexField);

         //button
        JButton refresh = new JButton("Run");
        refresh.setBounds(WIDTH/4,(INTERVAL*3)-(INTERVAL/5),(CHAR_LENGTH*3)*refresh.getText().length(),INTERVAL);
        panel.add(refresh);

        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gridButtons(colorGrid, Integer.parseInt(rowTextField.getText()), Integer.parseInt(columnTextField.getText()),hexField.getText());
                //misc.parseHex(Integer.parseInt(hexField.getText()));
            }
        });

        gridButtons(colorGrid, Integer.parseInt(rowTextField.getText()), Integer.parseInt(columnTextField.getText()), hexField.getText());

    }

    public static void gridButtons(JPanel panel, int row, int column, String hex){

        //init var
        panel.removeAll();

        int iterations = column*row;
        System.out.println("test");

        //error handler
        if(row<=0){
            row = 1;
        }
        if(column<=0){
            column = 1;
        }

        panel.setLayout(new GridLayout(row,column));
        Color color = new Color(0,0,0);
        ArrayList<Integer> RGB = misc.parseHex(hex);

        for(int i = 0;i<iterations;i++){

            //init var
            int RIndex = 0 + (3*i);
            int GIndex = 1 + (3*i);
            int BIndex = 2 + (3*i);

            int RColor = 0;
            int GColor = 0;
            int BColor = 0;

            int[] indexList = {RIndex,GIndex,BIndex};
            int[] colorList = {RColor,GColor,BColor};


            /*ik ur not supposed to do scalars whenever possible to keep code versitile but trying to do Arrays.aslist(indexList).size() just always returns 0 and i dont think
            they're gonna invent RGB2 and add a fourth value any time soon or whatever so itll always just be 3 inputs of R,G,B anyway*/
            for(int j = 0; j<=2;j++){
                if(indexList[j]>=RGB.size()){
                    colorList[j] = 0;
                }else{
                    colorList[j] = RGB.get(indexList[j]);
                    if(colorList[j]>255||colorList[j]<0){
                        colorList[j] = 255;
                    }
                    
                }
            }
              
            RColor = colorList[0];
            GColor = colorList[1];
            BColor = colorList[2];

            color = new Color(RColor,GColor,BColor);
            System.out.println(color);

            JTextField colorPane = new JTextField();
            colorPane.setBackground(color);
            panel.add(colorPane);

        }
        panel.repaint();
        panel.revalidate();
    }
}