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

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce l'elenco dei piatti come un'istanza di ArrayList<Piatto>.
     */

    public ArrayList<Piatto> getPiatti() {
        return piatti;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce una rappresentazione testuale dell'oggetto Menu, che corrisponde alla rappresentazione testuale dell'elenco dei piatti come una stringa.
     */

    @Override
    public String toString() {
        return piatti.toString();
    }

}
