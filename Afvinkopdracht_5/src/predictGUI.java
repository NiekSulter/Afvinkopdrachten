import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class predictGUI extends JFrame {
    static ArrayList<String> comboOptionsLeeg = new ArrayList<String>();
    static ArrayList<String> zinnen = new ArrayList<String>();
    private JComboBox<String> text1;
    private JTextField input1;
    private JLabel label1, label2;


    public static void main(String[] args) throws IOException {
        predictGUI frame = new predictGUI();
        frame.setSize(500, 160);
        frame.createGUI();
        frame.setVisible(true);
        frame.setTitle("Word prediction");


    }

    private void createGUI() throws IOException {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        label1 = new JLabel("Voer hier je tekst in:");
        label2 = new JLabel("Suggesties:");

        input1 = new JTextField(35);
        JComboBox text1 = new JComboBox(comboOptionsLeeg.toArray());
        text1.setPrototypeDisplayValue("Dit is een voorbeeld zin voor de JCombobox die best lang is.");
        text1.setEditable(false);
        //text1.setSelectedIndex(0);

        window.add(label1);
        window.add(input1);
        window.add(label2);
        window.add(text1);

        openFile();

        input1.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                run();
                DefaultComboBoxModel model = new DefaultComboBoxModel( comboOptionsLeeg.toArray() );
                text1.setModel(model);

            }
        });
    }

    private void openFile() throws IOException {
        String row;
        BufferedReader reader = new BufferedReader(new FileReader("/Users/niek/Downloads/zin.txt"));
        while ((row = reader.readLine()) != null) {
            String[] sen = row.split("\n");
            zinnen.add(sen[0]);
        }
    }

    private void run() {
        SwingWorker<ArrayList<String>, ArrayList<String>> worker = new SwingWorker<ArrayList<String>, ArrayList<String>>() {
            @Override
            protected ArrayList<String> doInBackground() throws Exception {
                comboOptionsLeeg.clear();
                Thread.sleep(100);
                String input = input1.getText();
                Pattern pattern = Pattern.compile(input, Pattern.LITERAL);
                for (String i : zinnen) {
                    System.out.println(i);
                    Matcher matcher = pattern.matcher("^" + i + "g");
                    boolean match = matcher.find();
                    if (match) {
                        comboOptionsLeeg.add(i);
                    } else {
                        comboOptionsLeeg.remove(i);
                    }

                }

                return comboOptionsLeeg;
            }


        };

        worker.execute();
    }
}
