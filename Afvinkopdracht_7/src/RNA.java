import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class om een RNA sequentie object aan te maken.
 */
public class RNA extends sequentie {

    public RNA(String seq, String header) throws NoValidSeq {
        super(seq, header);
    }

    /**
     * Methode om nog een keer te kijken of de sequentie daadwerkelijk RNA is.
     *
     * @param seq de sequentie
     * @throws NoValidSeq custom exception als de sequentie onbekende letters bevat
     */
    public void setSeq(String seq) throws NoValidSeq {
        Pattern pattern = Pattern.compile("^[ACUG]+$", Pattern.CASE_INSENSITIVE);
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
        AU(Color.BLUE), GC(Color.RED);

        private Color col;

        Kleuren(Color col) {
            this.col = col;
        }

        public Color getColor() {
            return col;
        }
    }

}
