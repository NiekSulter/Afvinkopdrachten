import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class seqVis extends JFrame {
    private JPanel p1, p2;
    private JLabel l1, l2;
    private JButton b1, b2;
    private JTextField tf1;
    private JTextArea ta1;
    String total = "";
    File file;

    public static void main(String[] args) {
        seqVis frame = new seqVis();
        frame.setSize(1100, 400);
        frame.GUI();
        frame.setVisible(true);
        frame.setTitle("SeqVis");
    }

    private void GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());



        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(1100, 300));
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(1100, 100));

        l1 = new JLabel("File:");
        tf1 = new JTextField(70);
        tf1.setEditable(false);
        b1 = new JButton("search");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    final JFileChooser fw = new JFileChooser();
                    FileNameExtensionFilter filter =
                    new FileNameExtensionFilter("Fasta files (.fasta, .fa, .txt)",
                            "fasta", "fa", "txt");
                    fw.setFileFilter(filter);
                    int retval = fw.showOpenDialog(seqVis.this);
                    if (retval == JFileChooser.APPROVE_OPTION) {
                        file = fw.getSelectedFile();
                        tf1.setText(file.getPath());
                    }
                    try {
                        readFile(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        readFile(false);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        b2 = new JButton("analyse");
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    makeObject();
                } catch (NoValidSeq noValidSeq) {
                    noValidSeq.printStackTrace();
                }
            }
        });
        ta1 = new JTextArea(10, 90);
        l2 = new JLabel("Sequence:");


        p1.add(l1);
        p1.add(tf1);
        p1.add(b1);
        p1.add(b2);
        p1.add(l2);
        p1.add(ta1);
        window.add(p1);

    }

    public void readFile(Boolean x) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String rij = null;

        if (x) {
            while ((rij = reader.readLine()) != null) {
                ta1.append("\n" + rij);
            }
        } else {
            reader.readLine();
            while ((rij = reader.readLine()) != null) {
                total += rij;
            }
        }
    }

    public void makeObject() throws NoValidSeq {
        if (total.matches("^[ACTG]+$")) {
            DNA d1 = new DNA(total);
        } else if (total.matches("^[ACUG]+$")) {
            RNA r1 = new RNA(total);
        } else if (total.matches("^[DERKHSTNQCUGPAVILMFYW]+$"));
        protein pro1 = new protein(total);
    }
}
