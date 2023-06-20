package magazzino;

import resto.Xml;

import java.util.HashMap;

public class Magazzino {

    HashMap<String, Integer> scorta;

    public Magazzino(){
        scorta = Xml.leggiMerci();
    }

    @Override
    public String toString() {
        return "Magazzino{" +
                "scorta=" + scorta +
                '}';
    }
}
