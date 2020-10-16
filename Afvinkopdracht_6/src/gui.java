import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class gui extends JFrame {

    private static seqAnalyse seqA;
    private JPanel p1, p2, p3, p4;
    private JLabel l1, l2, l3, l4;
    private JTextField t1;
    private JButton b1, b2;
    private JTextArea te1;
    private JProgressBar pbar1, pbar2;
    File file;

    public static void main(String[] args) {
        gui frame = new gui();
        frame.setSize(700, 470);
        frame.createGUI();
        frame.setVisible(true);
        frame.setTitle("YANARA");
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        seqA = new seqAnalyse(this);


        p1 = new JPanel();
        p1.setLayout(new GridLayout(1,4));
        p1.setPreferredSize(new Dimension(700, 25));

        l1 = new JLabel("      Kies een bestand:");
        t1 = new JTextField(40);
        b1 = new JButton("bestand");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    final JFileChooser chooser = new JFileChooser();
                    FileNameExtensionFilter filter =
                            new FileNameExtensionFilter
                                    ("Fasta files (.fa, .fasta, .txt)", "fa", "fasta", "txt");
                    chooser.setFileFilter(filter);
                    int returnVal = chooser.showOpenDialog(gui.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file = seqA.openFile(chooser.getSelectedFile());
                        t1.setText(file.getAbsolutePath());
                    }



                }
            }
        });
        b2 = new JButton("analyseer");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b2) {
                    try {
                        seqA.readFile(file);
                        float[] resArray = seqA.analyseerBestand(seqA.seqGetLength());
                        te1.setText("Lengte van de sequentie: " + seqA.seqGetLength() + "\n" +
                                    "Het percentage polaire aminozuren is: " + (int) resArray[0] + "\n" +
                                    "Het percentage apolaire aminozuren is: " + (int) resArray[1]);
                        pbar1.setValue((int) resArray[0]);
                        pbar2.setValue((int) resArray[1]);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        p1.add(l1);
        p1.add(t1);
        p1.add(b1);
        p1.add(b2);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 2));
        p2.setPreferredSize(new Dimension(700, 200));

        l2 = new JLabel("       Informatie:");
        te1 = new JTextArea();
        te1.setEditable(false);

        p2.add(l2);
        p2.add(te1);

        p3 = new JPanel();
        p3.setPreferredSize(new Dimension(700, 100));
        p3.setLayout(new GridLayout(1, 2));
        l3 = new JLabel("      Percentage hydrofiel:");
        pbar1 = new JProgressBar();
        pbar1.setStringPainted(true);

        p3.add(l3);
        p3.add(pbar1);

        p4 = new JPanel();
        p4.setPreferredSize(new Dimension(700, 100));
        p4.setLayout(new GridLayout(1, 2));
        l4 = new JLabel("      Percentage hydrofoob:");
        pbar2 = new JProgressBar();
        pbar2.setStringPainted(true);

        p4.add(l4);
        p4.add(pbar2);

        window.add(p1);
        window.add(p2);
        window.add(p3);
        window.add(p4);

    }
}
