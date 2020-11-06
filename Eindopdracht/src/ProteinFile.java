import java.io.*;
import java.util.ArrayList;

/**
 * <p>In de ProteinFile class wordt er een bestand geparst, hier worden vervolgens de accessiecodes uitgehaald
 * en aan een arraylist toegevoegd. De class kan het aantal regels van het bestand en de arraylist
 * met de accessiecodes returnen. </p>
 * @author Niek Sülter
 */
public class ProteinFile {

    protected File file;
    protected ArrayList<String> codes = new ArrayList<>();

    /**
     * <p>≈Constructor van de ProteinFile class </p>
     * @param file het bestand verkregen uit de filechooser in de GUI class.
     * @throws IOException de exception die opkomt als er geen bestand gevonden is.
     * @throws NotValidProteinCode een custom exception die opkomt als er een accessiecode in het
     * ingevoerde bestand staat die niet begint met AT.
     */
    public ProteinFile(File file) throws IOException, NotValidProteinCode {
        setFile(file);
        parseFile();
    }

    /**
     * <p>Methode om de file variable toe te wijzen.</p>
     * @param file bestand verkegen uit de constructor.
     */
    public void setFile(File file) {
        this.file = file;
    }

    /**
     * <p> Methode waar het ingevoerde bestand geparst wordt. </p>
     * @throws IOException de exception die opkomt als er geen bestand gevonden is.
     * @throws NotValidProteinCode een custom exception die opkomt als er een accessiecode in het
     * ingevoerde bestand staat die niet begint met AT.
     */
    public void parseFile() throws IOException, NotValidProteinCode {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        String rij;
        reader.readLine();
        while ((rij = reader.readLine()) != null) {
            String[] splitrij = rij.split("\t");
            if (splitrij[0].startsWith("AT")) {
                codes.add(splitrij[0]);
            } else {
                throw new NotValidProteinCode("Het bestand " + file.getName() +
                        " bevat een onjuiste proteinecode: " + splitrij[0]);
            }
        }
    }

    /**
     * <p>Methode die het aantal regels van het bestand returnt. </p>
     * @return integer met het aantal regels.
     */
    public int getRegels() {
        return codes.size();
    }

    /**
     * <p>Methode die de arraylist met de accessiecodes returnt.</p>
     * @return arraylist van strings.
     */
    public ArrayList<String> getArraylist() {
        return codes;
    }
}
