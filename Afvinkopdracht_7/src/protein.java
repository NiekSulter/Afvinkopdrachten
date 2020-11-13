import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class om een eiwit sequentie object aan te maken.
 */
public class protein extends sequentie {

    public protein(String seq, String header) throws NoValidSeq {
        super(seq, header);
    }

    /**
     * Methode om nog een keer te kijken of de sequentie daadwerkelijk een eiwit is.
     *
     * @param seq de sequentie
     * @throws NoValidSeq custom exception als de sequentie onbekende letters bevat
     */
    public void setSeq(String seq) throws NoValidSeq {
        Pattern pattern = Pattern.compile("^[DERKHSTNQCUGPAVILMFYW]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(seq);
        boolean matchHit = matcher.find();
        if (matchHit) {
            super.setSeq(seq);
        } else {
            throw new NoValidSeq("No valid sequence format found.");
        }
    }

    /**
     * Enum om de kleuren te selecteren.
     */
    public enum Kleuren {
        polair(Color.BLUE), neutaal(Color.GRAY), apolair(Color.RED);

        private Color col;

        Kleuren(Color col) {
            this.col = col;
        }

        public Color getColor() {
            return col;
        }
    }
}
