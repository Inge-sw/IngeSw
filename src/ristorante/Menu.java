package ristorante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class Menu implements Prenotabile{
    private ArrayList<Piatto> piatti;

    public Menu() {
        this.piatti = new ArrayList<>();
    }

    public Menu(ArrayList<Piatto> piatti) {
        this.piatti = piatti;
    }

    public ArrayList<Piatto> getPiatti() {
        return piatti;
    }

    public void addPiatto(Piatto piatto) {
        piatti.add(piatto);
    }

    @Override
    public String toString() {
        return piatti.toString();
    }

    public void stampa (){
        System.out.println(piatti.toString());
    }
}
