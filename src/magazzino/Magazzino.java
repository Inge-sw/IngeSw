package magazzino;

import letturaFile.Xml;

import java.util.HashMap;
import java.util.Map;

public class Magazzino {

    HashMap<String, Double> scorta;

    public Magazzino() {
        scorta = Xml.leggiMerci();
    }

    public void setScorta(HashMap<String, Double> scorta) {
        this.scorta = scorta;
    }

    public HashMap<String, Double> getScorta() {
        return scorta;
    }

    @Override
    public String toString() {
        String da_concatenare = "";
        for (Map.Entry<String, Double> entry : scorta.entrySet()) {
            da_concatenare += "\n\t" + entry.getKey() + " = " + entry.getValue() + "Kg";
        }
        return "Merci rimanenti nel magazzino: " +
                da_concatenare;
    }
}
