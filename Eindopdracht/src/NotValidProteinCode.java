import javax.swing.*;

/**
 * Exception gemaakt om verkeerde accessiecodes af te vangen.
 * @author Niek Sülter
 */
public class NotValidProteinCode extends Exception {
    public NotValidProteinCode(String error) {
        super(error);
        JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
