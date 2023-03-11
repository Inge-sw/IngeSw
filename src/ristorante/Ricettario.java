package ristorante;

import resto.Costante;
import resto.Xml;

import java.util.ArrayList;

public class Ricettario {

    private ArrayList<Ricetta> ricette;

    public Ricettario(){
        ricette = Xml.leggiRicettario(Costante.XML_RICETTARIO);
    }

    public ArrayList<Ricetta> getRicette() {
        return ricette;
    }

}
