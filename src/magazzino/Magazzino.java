package magazzino;

import resto.Xml;

import java.util.HashMap;
import java.util.Map;

public class Magazzino {

    HashMap<String, Double> scorta;

    public Magazzino() {
        scorta = Xml.leggiMerci();
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
