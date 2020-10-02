import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;

public class bke_gui extends JFrame implements ActionListener {

    //var
    private JButton button1, button2, button3, button4, button5, button6, button7, button8, button9;
    private JLabel tf1, tf2, tf3;
    //ImageIcon iconX = new ImageIcon(bke_gui.class.getResource("x.png"));
    //ImageIcon iconO = new ImageIcon(bke_gui.class.getResource("o.png"));
    String cPlayer = "X";
    static ArrayList<Integer> zettenSpelerX = new ArrayList<Integer>();
    static ArrayList<Integer> zettenSpelerY = new ArrayList<Integer>();
    static ArrayList<Integer> playedMoves = new ArrayList<Integer>();
    static ArrayList<Integer> dList = new ArrayList<Integer>();

    int clickedButton;


    public static void main(String[] args) {
        bke_gui frame = new bke_gui();
        frame.setSize(400, 550);
        frame.createGUI();
        frame.setVisible(true);
        frame.setTitle("Tic Tac Toe");
        drawCheck();

    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new GridLayout(4, 3));

        //init
        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button5 = new JButton();
        button6 = new JButton();
        button7 = new JButton();
        button8 = new JButton();
        button9 = new JButton();

        tf1 = new JLabel();
        tf2 = new JLabel("X is aan de beurt");
        tf3 = new JLabel();

        //action
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);

        //placement
        window.add(button1);
        window.add(button2);
        window.add(button3);
        window.add(button4);
        window.add(button5);
        window.add(button6);
        window.add(button7);
        window.add(button8);
        window.add(button9);
        window.add(tf1);
        window.add(tf2);
        window.add(tf3);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == button1) {
            button1.setText(cPlayer);
            button1.setEnabled(false);
            clickedButton = 1;
        } else if (event.getSource() == button2) {
            button2.setText(cPlayer);
            button2.setEnabled(false);
            clickedButton = 2;
        } else if (event.getSource() == button3) {
            button3.setText(cPlayer);
            button3.setEnabled(false);
            clickedButton = 3;
        } else if (event.getSource() == button4) {
            button4.setText(cPlayer);
            button4.setEnabled(false);
            clickedButton = 4;
        } else if (event.getSource() == button5) {
            button5.setText(cPlayer);
            button5.setEnabled(false);
            clickedButton = 5;
        } else if (event.getSource() == button6) {
            button6.setText(cPlayer);
            button6.setEnabled(false);
            clickedButton = 6;
        } else if (event.getSource() == button7) {
            button7.setText(cPlayer);
            button7.setEnabled(false);
            clickedButton = 7;
        } else if (event.getSource() == button8) {
            button8.setText(cPlayer);
            button8.setEnabled(false);
            clickedButton = 8;
        } else if (event.getSource() == button9) {
            button9.setText(cPlayer);
            button9.setEnabled(false);
            clickedButton = 9;
        }

        if (cPlayer.equals("X")) {
            zettenSpelerX.add(clickedButton);
        } else {
            zettenSpelerY.add(clickedButton);
        }

        playedMoves.add(clickedButton);

        winCheck();
        switchPlayer();
    }

    public void switchPlayer() {
        if (cPlayer.equals("X")) {
            cPlayer = "O";
            tf2.setText("O is aan de beurt");
        } else {
            cPlayer = "X";
            tf2.setText("X is aan de beurt");
        }
    }

    public void winCheck() {
        List Rij13 = Arrays.asList(1, 2, 3);
        List Rij46 = Arrays.asList(4, 5, 6);
        List Rij79 = Arrays.asList(7, 8, 9);
        List Kolom17 = Arrays.asList(1, 4, 7);
        List Kolom28 = Arrays.asList(2, 5, 8);
        List Kolom39 = Arrays.asList(3, 6, 9);
        List Dwars19 = Arrays.asList(1, 5, 9);
        List bRij37 = Arrays.asList(3, 5, 7);

        List<List> winCondities = new ArrayList<List>();

        winCondities.add(Rij13);
        winCondities.add(Rij46);
        winCondities.add(Rij79);
        winCondities.add(Kolom17);
        winCondities.add(Kolom28);
        winCondities.add(Kolom39);
        winCondities.add(Dwars19);
        winCondities.add(bRij37);

        for (List l : winCondities) {
            if (zettenSpelerX.containsAll(l)) {
                JOptionPane.showMessageDialog(null, "Speler X heeft gewonnen!");
                System.exit(0);
            } else if (zettenSpelerY.containsAll(l)) {
                JOptionPane.showMessageDialog(null, "Speler O heeft gewonnen!");
                System.exit(0);
            } else if (playedMoves.containsAll(dList)) {
                JOptionPane.showMessageDialog(null, "Gelijkspel");
                System.exit(0);
            }
        }
    }

    public static void drawCheck() {
        for (int k = 1; k < 10; k++) {
            dList.add(k);
        }
    }
}
