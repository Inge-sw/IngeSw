package ristorante;

import resto.Stagioni;

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

    @Override
    public String toString() {
        return "Menu{" +
                "piatti=" + piatti +
                '}';
    }
}
