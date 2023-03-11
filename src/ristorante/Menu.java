package ristorante;

import java.util.ArrayList;

public class Menu {
    private ArrayList<Piatto> piatti;

    public Menu() {
        this.piatti = new ArrayList<Piatto>();
    }

    public ArrayList<Piatto> getPiatti() {
        return piatti;
    }

    public void addPiatto(Piatto piatto) {
        piatti.add(piatto);
    }

}
