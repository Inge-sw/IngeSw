package ristorante;

import java.util.ArrayList;

public class Menu implements Prenotabile {
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

    @Override
    public String toString() {
        return piatti.toString();
    }

}
