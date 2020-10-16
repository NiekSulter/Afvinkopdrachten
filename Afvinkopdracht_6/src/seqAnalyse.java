import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class seqAnalyse {

    gui gui;
    File file;

    static ArrayList<String> letters = new ArrayList<String>();

    public seqAnalyse(gui parentGUI){
        gui = parentGUI;
    }

    public File openFile(File inputFile) {
        file = inputFile;
        return file;
    }

    public float[] analyseerBestand(int length) {
        int aantalPolair = 0;
        int aantalApolair = 0;
        String[] polair = {"R", "N", "D", "C", "Q", "E", "H", "K", "S", "T", "Y"};
        String[] apolair = {"A", "G", "I", "L", "M", "F", "P", "W", "V"};
        List<String> plist = Arrays.asList(polair);
        List<String> apList = Arrays.asList(apolair);
        //index[0] polair; index[1] apolair;

        for (int i=0; i < letters.size(); i++) {
            if (plist.contains(letters.get(i))) {
                aantalPolair++;
            } else if (apList.contains(letters.get(i))) {
                aantalApolair++;
            }
        }
        float o1 = (float) aantalApolair / length * 100;
        float o2 = (float) aantalPolair / length * 100;


        float[] resArray = {o1, o2};

        return resArray;
    }

    public void readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine();
        String rij = null;
        while ((rij = reader.readLine()) != null) {
            int r=0;
            String[] zin = rij.split("\n");
            for (int i = 0; i < zin.length; i++) {
                    for (int j = 0; j < zin[i].length(); j++) {
                        char ch = zin[i].charAt(j);
                        letters.add(Character.toString(ch));
                }
            }

        }
    }

    public int seqGetLength() {
        int length = letters.size();
        return length;
    }
}
