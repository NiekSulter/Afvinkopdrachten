import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Class om de GUI te maken en figuren te tekenen.
 */
public class seqVis extends JFrame implements ActionListener {
    private JPanel p1, p2;
    private JLabel l1, l2, l3;
    private JButton b1, b2;
    private JTextField tf1, tf2;
    private JTextArea ta1;
    private ArrayList<Color> color;
    String total = "";
    String header;
    readFile f1;


    public static void main(String[] args) {
        seqVis frame = new seqVis();
        frame.setSize(1100, 400);
        frame.GUI();
        frame.setVisible(true);
        frame.setTitle("SeqVis");
    }

    /**
     * Methode om de elementen aan te maken.
     */
    private void GUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        //Panels aanmaken
        p1 = new JPanel();
        p1.setPreferredSize(new Dimension(1100, 250));
        p2 = new JPanel();
        p2.setPreferredSize(new Dimension(1000, 50));

        //Labels aanmaken
        l1 = new JLabel("File:");
        l2 = new JLabel("Sequence:");
        l3 = new JLabel("Visualisatie:");

        //Textfiels en area's
        tf1 = new JTextField(70);
        tf1.setEditable(false);
        ta1 = new JTextArea(10, 90);
        ta1.setPreferredSize(new Dimension(1100, 150));

        //Knoppen en actionlisteners
        b1 = new JButton("search");
        b1.addActionListener(this);
        b2 = new JButton("analyse");
        b2.addActionListener(this);

        p2.setBackground(Color.WHITE);

        //Toevoegen van elementen aan de panels
        p1.add(l1);
        p1.add(tf1);
        p1.add(b1);
        p1.add(b2);
        p1.add(l2);
        p1.add(ta1);
        p1.add(l3);
        window.add(p1);
        window.add(p2);

    }

    /**
     * Actionlisiner om de knoppen van functionaliteit te voorzien.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b2) {
            try {
                parseFile(f1.getFile());
                makeObject();

            } catch (NoValidSeq | IOException noValidSeq) {
                noValidSeq.printStackTrace();
            }

        } else if (e.getSource() == b1) {
            final JFileChooser fw = new JFileChooser();
            FileNameExtensionFilter filter =
                    new FileNameExtensionFilter("Fasta files (.fasta, .fa, .txt)",
                            "fasta", "fa", "txt");
            fw.setFileFilter(filter);
            int retval = fw.showOpenDialog(seqVis.this);
            if (retval == JFileChooser.APPROVE_OPTION) {
                f1 = new readFile(fw.getSelectedFile());
                tf1.setText(f1.getFileName());
            }
        }
    }

    /**
     * Methode om de opgegven file te parsen en een string te maken van de sequentie.
     *
     * @param file bestand dat ingelezen is in de readFile class
     * @throws IOException standaard input exception
     */
    public void parseFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String rij = null;

        while ((rij = reader.readLine()) != null) {
            if (rij.startsWith(">")) {
                header = rij;
                ta1.append(rij + "\n");
            } else {
                total += rij;
                ta1.append(rij + "\n");
            }
        }
    }

    /**
     * Methode om het sequentie aan te maken
     *
     * @throws NoValidSeq custom exception als de sequentie onbekende letters bevat
     */
    public void makeObject() throws NoValidSeq {
        if (total.matches("^[ACTG]+$")) {
            DNA object = new DNA(total, header);
            selectColor(object);
        } else if (total.matches("^[ACUG]+$")) {
            RNA object = new RNA(total, header);
            selectColor(object);
        } else if (total.matches("^[DERKHSTNQCUGPAVILMFYWX]+$")) {
            protein object = new protein(total, header);
            selectColor(object);
        } else {
            throw new NoValidSeq("Dit is geen geldige sequentie!");
        }


    }

    /**
     * Functie om de kleuren aan een lijst toe te voegen om ze te verwerken.
     * Vervolgens worden de lijnen in de visualisatie getekend.
     */
    public void selectColor(sequentie object) {
        char[] polar = {'S', 'T', 'C', 'Y', 'N', 'Q'};
        char[] nonpolar = {'G', 'A', 'V', 'L', 'I', 'M', 'F', 'W', 'P'};
        char[] neutral = {'D', 'E', 'R', 'K', 'H'};
        char[] letters = total.toCharArray();
        Graphics paper = p2.getGraphics();
        int xpos = 0;

        for (char i : letters) {
            if (object instanceof DNA) {
                if (i == 'A' || i == 'T') {
                    paper.setColor(DNA.Kleuren.AT.getColor());
                    paper.drawLine(xpos, 1, xpos + 1, 50);
                } else if (i == 'G' || i == 'C') {
                    paper.setColor(DNA.Kleuren.GC.getColor());
                    paper.drawLine(xpos, 1, xpos + 1, 50);
                }
            } else if (object instanceof RNA) {
                if (i == 'U' || i == 'A') {
                    paper.setColor(RNA.Kleuren.AU.getColor());
                    paper.drawLine(xpos, 1, xpos + 1, 50);
                } else {
                    paper.setColor(RNA.Kleuren.GC.getColor());
                    paper.drawLine(xpos, 1, xpos + 1, 50);
                }
            } else if (object instanceof protein) {
                if (Arrays.asList(polar).contains(i)) {
                    paper.setColor(protein.Kleuren.polair.getColor());
                    paper.drawLine(xpos, 1, xpos + 1, 50);
                } else if (Arrays.asList(nonpolar).contains(i)) {
                    paper.setColor(protein.Kleuren.apolair.getColor());
                    paper.drawLine(xpos, 1, xpos + 1, 50);
                } else {
                    paper.setColor(protein.Kleuren.neutaal.getColor());
                    paper.drawLine(xpos, 1, xpos + 1, 50);
                }
            }
            xpos++;
        }
    }
}
