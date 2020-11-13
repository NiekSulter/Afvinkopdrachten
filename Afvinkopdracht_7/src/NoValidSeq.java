import javax.swing.*;

/**
 * Exception gemaakt om verkeerde sequenties af te vangen.
 * @author Niek Sülter
 */
public class NoValidSeq extends Exception {
    public NoValidSeq(String error) {
        super(error);
        JOptionPane.showMessageDialog(null, error, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
}
