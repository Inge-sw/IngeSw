package prenotazione;

import ristorante.Prenotabile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Prenotazione {
    private int num_coperti;
    private LocalDate data;
    HashMap<Prenotabile, Integer> lista_prenotazioni_piatti;

    public Prenotazione(int num_coperti, LocalDate data) {
        this.num_coperti = num_coperti;
        this.data = data;
        this.lista_prenotazioni_piatti = new HashMap<>();
    }

    public Prenotazione(int num_coperti, LocalDate data, HashMap<Prenotabile, Integer> l) {
        this.num_coperti = num_coperti;
        this.data = data;
        this.lista_prenotazioni_piatti = l;
    }

    public int getNum_coperti() {
        return num_coperti;
    }

    public LocalDate getData() {
        return data;
    }

    public HashMap<Prenotabile, Integer> getLista_prenotazioni_piatti() {
        return lista_prenotazioni_piatti;
    }

    public void add_piatti(Prenotabile elem, int qnt) {
        this.lista_prenotazioni_piatti.put(elem, Integer.valueOf(qnt));
    }

    /*Precondizioni:
    La variabile lista_prenotazioni_piatti dell'oggetto this deve essere una mappa non nulla.
    Il valore num_coperti dell'oggetto this deve essere un intero non negativo.

    Postcondizioni:
    Viene calcolato il numero totale di piatti prenotati sommando le quantità di ciascun piatto nella mappa lista_prenotazioni_piatti.
    Viene verificato se il numero totale di piatti prenotati è maggiore o uguale al numero di coperti specificato nell'oggetto this.
    Restituisce true se il numero di piatti è sufficiente per coprire i coperti, altrimenti restituisce false.*/

    public boolean check_numero_piatti(Prenotazione this) {
        int check = 0;
        for (Prenotabile elem : this.lista_prenotazioni_piatti.keySet()) {
            check += this.lista_prenotazioni_piatti.get(elem);
        }

        if (check >= this.num_coperti) return true;

        return false;
    }

    @Override
    public String toString() {
        String da_concatenare = "";
        for (Map.Entry<Prenotabile, Integer> entry : lista_prenotazioni_piatti.entrySet()) {
            da_concatenare += "\n\t" + entry.getKey() + " " + entry.getValue() + " porzioni";
        }
        return "\n------------Prenotazione------------" +
                "\n-Numero coperti = " + num_coperti +
                "\n-Data = " + data +
                "\n-Lista piatti = " + da_concatenare;
    }
}
