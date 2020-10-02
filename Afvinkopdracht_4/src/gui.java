import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class gui extends JFrame implements ActionListener {
    private JLabel text1, paard1, status, paard2, af1, af2;
    private JPanel panel;
    private JButton button1;
    private JProgressBar pro1, pro2;

    public static void main(String[] args) {
        gui frame = new gui();
        frame.setSize(700, 250);
        frame.createGUI();
        frame.setVisible(true);
        frame.setTitle("Paardenrace");
    }

    private void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());

        text1 = new JLabel("Klik om de race te starten:");
        status = new JLabel("Nog niet begonnen");
        button1 = new JButton("Start!");


        paard1 = new JLabel();
        paard1.setBounds(10,10,50,30);
        paard2 = new JLabel();
        paard2.setBounds(10,40, 50,30);
        af1 = new JLabel();
        af1.setBounds(600,10,50,30);
        af2 = new JLabel();
        af2.setBounds(600,40,50,30);


        pro1 = new JProgressBar(0, 100);
        pro1.setBounds(50,10,500,30);
        pro2 = new JProgressBar(0,100);
        pro2.setBounds(50,40,500, 30);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(700, 250));
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLayout(null);

        window.add(text1);
        window.add(button1);
        window.add(status);
        window.add(panel);
        panel.add(paard1);
        panel.add(pro1);
        panel.add(paard2);
        panel.add(pro2);
        panel.add(af1);
        panel.add(af2);

        button1.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            Paard p1 = new Paard("Kees");
            Paard p2 = new Paard("Jan");
            paard1.setText(p1.getNaam());
            paard2.setText(p2.getNaam());
            status.setText("Race begonnen!");
            start(p1, p2);
        }
    }
    private void start(Paard p1,Paard p2) {
        SwingWorker<Boolean, Integer> worker = new SwingWorker<Boolean, Integer>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                boolean win = false;
                while (p1.getAfstand()<100 | p2.getAfstand()<100) {
                    p1.loop();
                    p2.loop();
                    publish(p1.getAfstand(), p2.getAfstand());
                    Thread.sleep(300);

                    if (p1.getAfstand() >= 100) {
                        win = true;
                        break;
                    } else if (p2.getAfstand() >= 100){
                        win = false;
                        break;
                    }
                }
                return win;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int afstand1 = chunks.get(chunks.size() - 2);
                int afstand2 = chunks.get(chunks.size() -1);
                pro1.setValue(afstand1);
                pro2.setValue(afstand2);
                af1.setText(p1.getAfstand() + "/" + "100");
                af2.setText(p2.getAfstand() + "/" + "100");
            }

            @Override
            protected void done() {
                try {
                    Boolean winnaar = get();
                    if(winnaar) {
                        status.setText("Race afgelopen, " + p1.getNaam() + " heeft gewonnen!");
                    } else {
                        status.setText("Race afgelopen, " + p2.getNaam() + " heeft gewonnen!");
                    }

                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }

            }
        };

        worker.execute();
    }
}
