import java.util.ArrayList;

/**
 * <p>De class waar de overlap tussen de twee ingevoerde bestanden wordt bepaald.
 * Er worden twee arraylists aan de class meegegeven en deze worden vervolgens vergeleken op duplicaten.
 * Voor elke accessiecode die in beide bestanden voorkomt wordt er 1 toegevoegd aan de overlapcounter.</p>
 * @author Niek Sülter
 */
public class OverlapCalc {
    protected ArrayList<String> aList1, aList2;
    protected int overlapCounter = 0;

    /**
     * <p>Constructor van de OverlapCalc class.</p>
     * @param a1 de eerste arraylist.
     * @param a2 de tweede arraylist.
     */
    public OverlapCalc(ArrayList<String> a1, ArrayList<String> a2) {
        aList1 = a1;
        aList2 = a2;
    }

    /**
     * <p>Methode waar de overlap tussen de 2 arraylist wordt bepaald, elke keer dat er overlap wordt gevonden
     * krijgt de overlapCounter variabele er 1 bij.</p>
     */
    public void checkOverlap() {
        for (String s : aList1) {
            if (aList2.contains(s)) {
                overlapCounter += 1;
            }
        }
    }

    /**
     * Methode waar de overlapCounter variable gereturnt kan worden.
     * @return int met het aantal overlaps.
     */
    public int getOverlapCount() {
        return overlapCounter;
    }
}
