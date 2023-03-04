package magazzino;

import java.util.HashMap;
import java.util.Map;

public class Magazzino {
    private Map<String, Integer> prodotti;

    public Magazzino() {
        this.prodotti = new HashMap<>();
    }

    public void aggiornaProdotto(String prodotto, int quantita) {
        int nuovaQuantita = this.prodotti.getOrDefault(prodotto, 0) + quantita;
        this.prodotti.put(prodotto, nuovaQuantita);
    }

    public boolean rimuoviProdotto(String prodotto, int quantita) {
        if (!this.prodotti.containsKey(prodotto)) {
            return false;
        }

        int quantitaAttuale = this.prodotti.get(prodotto);

        if (quantita > quantitaAttuale) {
            return false;
        }

        int nuovaQuantita = quantitaAttuale - quantita;
        this.prodotti.put(prodotto, nuovaQuantita);

        return true;
    }

    public boolean esisteProdotto(String prodotto) {
        return this.prodotti.containsKey(prodotto);
    }

    public int getQuantitaProdotto(String prodotto) {
        return this.prodotti.getOrDefault(prodotto, 0);
    }

    public String[] getProdottiDisponibili() {
        return this.prodotti.keySet().toArray(new String[0]);
    }
}

