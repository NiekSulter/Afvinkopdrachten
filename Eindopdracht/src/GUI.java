import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * <p>In de GUI class wordt de user interface gemaakt.
 * Met de ActionListeners wordt er functionaliteit aan de knoppen toegevoegd.
 * In de GUI worden de resultaten van de ProteinFile en OverlapCalc classes weergegeven.
 * Deze classes worden aangeroepen vanuit de ActionListeners.
 * </p>
 * @author Niek Sülter
 */


public class GUI extends JFrame {

    //Aanmaken van de benodigde UI elements en class objects
    private JLabel inputLabel;
    private JButton inputButton1, inputButton2, submitButton;
    private JTextField inputTextfield1, inputTextfield2;
    private JPanel grafPanel;
    private JTextArea outputTextarea;
    public File f1, f2;
    ProteinFile p1, p2;
    OverlapCalc c;

    /**
     * <p>Met de main methode wordt de GUI aangeroepen en geinitialiseerd.</p>
     */
    public static void main(String[] args) {
        GUI frame = new GUI();
        frame.setSize(600, 600);
        frame.createGUI();
        frame.setVisible(true);
        frame.setTitle("Consensus Proteoom Analyse Tool");
    }

    /**
     * <p>In de createGUI methode worden de verschillende elementen van de GUI geinitialiseerd,
     * ook worden de elementen van hun eigenschappen voorzien. Na het het aanmaken van elk element wordt
     * het toegevoegd aan de window container.</p>
     */
    public void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        inputLabel = new JLabel("Selecteer de bestanden die je wilt vergelijken:");
        window.add(inputLabel);

        inputTextfield1 = new JTextField(33);
        inputTextfield1.setEditable(false);
        window.add(inputTextfield1);

        inputButton1 = new JButton("blader");
        inputButton1.addActionListener(e -> {
            if (e.getSource() == inputButton1) {
                final JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(GUI.this);
                f1 = fc.getSelectedFile();
                inputTextfield1.setText(f1.getName());
            }
        });
        window.add(inputButton1);

        inputTextfield2 = new JTextField(33);
        inputTextfield2.setEditable(false);
        window.add(inputTextfield2);

        inputButton2 = new JButton("blader");
        inputButton2.addActionListener(e -> {
            if (e.getSource() == inputButton2) {
                final JFileChooser fc = new JFileChooser();
                fc.showOpenDialog(GUI.this);
                f2 = fc.getSelectedFile();
                inputTextfield2.setText(f2.getName());
            }
        });
        window.add(inputButton2);

        submitButton = new JButton("analyseer bestanden");
        submitButton.setPreferredSize(new Dimension(500, 30));
        submitButton.addActionListener(e -> {
            if (e.getSource() == submitButton) {
                try {
                    p1 = new ProteinFile(f1);
                    p2 = new ProteinFile(f2);
                } catch (IOException | NotValidProteinCode ioException) {
                    ioException.printStackTrace();
                }

                c = new OverlapCalc(p1.getArraylist(), p2.getArraylist());
                c.checkOverlap();

                drawGraph();
                printOutput();
            }
        });
        window.add(submitButton);

        grafPanel = new JPanel();
        grafPanel.setPreferredSize(new Dimension(550, 250));
        grafPanel.setBackground(Color.WHITE);
        window.add(grafPanel);

        outputTextarea = new JTextArea();
        outputTextarea.setPreferredSize(new Dimension(550, 150));
        outputTextarea.setEditable(false);
        window.add(outputTextarea);

    }

    /**
     * <p>In de drawGraph methode worden de elementen in voor de visualisatie van de 2 bestanden getekend.
     * De methode is opgedeeld in 3 delen. Elk deel zorgt voor de elementen van een bepaalde kleur.</p>
     */
    public void drawGraph() {
        Graphics paper = grafPanel.getGraphics();

        //Gegevens voor de rode ovaal
        paper.setColor(Color.RED);
        paper.drawOval(50, 30, 290, 200);
        paper.drawString("#uniek bestand 1", 20, 20);
        paper.drawString(String.valueOf((p1.getRegels() - c.getOverlapCount())), 140, 130);

        //Gegevens voor de overlap
        paper.setColor(Color.BLACK);
        paper.drawString("#overlap", 255, 20);
        paper.drawString(String.valueOf(c.getOverlapCount()), 265, 130);

        //Gegevens voor de blauwe ovaal
        paper.setColor(Color.BLUE);
        paper.drawOval(210, 30, 290, 200);
        paper.drawString("#uniek bestand 2", 420, 20);
        paper.drawString(String.valueOf((p2.getRegels() - c.getOverlapCount())), 390, 130);

    }

    /**
     * In de printOutput methode worden de resultaten van de ProteinFile en OverlapCalc class geprint in de GUI.
     */
    public void printOutput() {
        outputTextarea.append("Regels bestand 1: " + p1.getRegels() + "\n");
        outputTextarea.append("Regels bestand 2: " + p2.getRegels() + "\n");
        outputTextarea.append("Unieke regels bestand 1: " + (p1.getRegels() - c.getOverlapCount()) + "\n");
        outputTextarea.append("Unieke regels bestand 2: " + (p2.getRegels() - c.getOverlapCount()) + "\n");
        outputTextarea.append("Overlappende regels: " + c.getOverlapCount());
    }
}
