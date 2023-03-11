package ristorante;

import resto.Costante;
import resto.Stagioni;
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

    public void aggiungiRicetta(String nome, Stagioni stagione, int porzioni, int tempo_preparazione, ArrayList<Ingrediente> ingredienti){
        ricette.add(new Ricetta(nome, stagione, porzioni, tempo_preparazione, ingredienti));
        Xml.aggiungiRicetta(new Ricetta(nome, stagione, porzioni, tempo_preparazione, ingredienti));
    }

    public String toString() {
        return ricette.toString();
    }
}
