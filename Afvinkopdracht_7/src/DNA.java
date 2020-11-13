import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class om een DNA sequentie object aan te maken.
 */
public class DNA extends sequentie {

    public DNA(String seq, String header) throws NoValidSeq {
        super(seq, header);

    }

    /**
     * Methode om nog een keer te kijken of de sequentie daadwerkelijk DNA is.
     *
     * @param seq de sequentie
     * @throws NoValidSeq custom exception als de sequentie onbekende letters bevat
     */
    public void setSeq(String seq) throws NoValidSeq {
        Pattern pattern = Pattern.compile("^[ACTG]+$", Pattern.CASE_INSENSITIVE);
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
        AT(Color.YELLOW), GC(Color.RED);

        private Color col;

        Kleuren(Color col) {
            this.col = col;
        }

        public Color getColor() {
            return col;
        }
    }

}
