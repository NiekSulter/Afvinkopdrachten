import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class secondGUI extends JFrame implements ActionListener {

    private JTextField textFieldx, textFieldy, textFields; //declare
    private JLabel label, label2, label3;
    private JButton button, buttonc;
    private JPanel panel;

    int xInput;
    int yInput;
    int Hx, H1y, H2y, L1x, L1y, L1x2, L1y2, L2x, L2y, L2x2, L2y2;

    public static void main(String[] args) {
        secondGUI frame = new secondGUI();
        frame.setSize(1280, 720);
        frame.setTitle("H2O - Niek Sulter");
        frame.createGUI();
        frame.setVisible(true);

    }

    private void createGUI() {
        //settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        //init
        panel = new JPanel();
        textFieldx = new JTextField(4);
        textFieldy = new JTextField(4);
        label = new JLabel("Coordinaten:");
        label2 = new JLabel("x");
        label3 = new JLabel("px");
        button = new JButton("H2O");
        buttonc = new JButton("Clear");
        panel.setPreferredSize(new Dimension(1280, 700));

        //placement
        window.setBackground(Color.WHITE);
        window.add(label);
        window.add(textFieldx);
        window.add(label2);
        window.add(textFieldy);
        window.add(label3);
        window.add(button);
        window.add(buttonc);
        window.add(panel);

        //actions
        button.addActionListener(this);
        buttonc.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource()==button) {
            xInput = Integer.parseInt(textFieldx.getText());
            yInput = Integer.parseInt(textFieldy.getText());

            calcCords();

            Graphics paper = panel.getGraphics();
            paper.drawLine(L1x, L1y, L1x2, L1y2);
            paper.drawLine(L2x, L2y, L2x2, L2y2);
            paper.setColor(Color.RED);
            paper.fillOval(xInput, yInput, 100, 100);
            paper.setColor(Color.BLUE);
            paper.fillOval(Hx, H1y, 50, 50);
            paper.fillOval(Hx, H2y, 50, 50);
        } else {
            panel.repaint();
        }
    }

    public void calcCords() {
        System.out.println(xInput);
        System.out.println(yInput);
        Hx = (xInput - 130);
        H1y = (yInput - 100);
        H2y = (yInput + 140);
        L1x = (xInput + 30);
        L1y = (yInput + 40);
        L1x2 = (xInput - 110);
        L1y2 = (yInput - 80);
        L2x = (xInput + 60);
        L2y = (yInput + 40);
        L2x2 = (xInput - 100);
        L2y2 = (yInput + 160);

    }

}
