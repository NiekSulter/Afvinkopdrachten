import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DNA extends sequentie{

    public DNA(String seq) throws NoValidSeq {
        super(seq);
    }

    public void setSeq(String seq) throws NoValidSeq{
        Pattern pattern = Pattern.compile("^[ACTG]+$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(seq);
        boolean matchHit = matcher.find();
        if (matchHit) {
            super.setSeq(seq);
        } else {
            throw new NoValidSeq(
                    "No valid sequence format found."
            );
        }
    }
};
