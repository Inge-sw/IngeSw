package magazzino;

import resto.Xml;

import java.util.HashMap;

public class Magazzino {

    HashMap<String, Double> scorta;

    public Magazzino(){
        scorta = Xml.leggiMerci();
    }

    public HashMap<String, Double> getScorta() {
        return scorta;
    }

    public void setScorta(HashMap<String, Double> scorta) {
        this.scorta = scorta;
    }

    @Override
    public String toString() {
        return "Magazzino: " +
                "\nscorta = " + scorta +
                '}';
    }
}
