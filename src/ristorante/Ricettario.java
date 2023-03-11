package ristorante;

import resto.Costante;
import resto.Xml;

import java.util.ArrayList;

public class Ricettario {

    private ArrayList<Ricetta> ricette;

    public Ricettario(ArrayList<Ricetta> ricette){
        this.ricette = ricette;
    }

    public ArrayList<Ricetta> getRicette() {
        return ricette;
    }

    @Override
    public String toString() {
        return "Ricettario{" +
                "ricette=" + ricette +
                '}';
    }
}
