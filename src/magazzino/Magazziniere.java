package magazzino;

import java.util.ArrayList;
import java.util.HashMap;

public class Magazziniere extends Utente {
    private Magazzino magazzino;

    public Magazziniere(String nome, String cognome, String username, String password, Magazzino magazzino) {
        super(nome, cognome, username, password);
        this.magazzino = magazzino;
    }

    public void aggiornaGiacenza(Prodotto prodotto, int quantita) {
        magazzino.aggiornaGiacenza(prodotto, quantita);
    }

    public HashMap<Prodotto, Integer> getScorte() {
        return magazzino.getGiacenze();
    }

    public ArrayList<String> generaListaSpesa(ArrayList<Prenotazione> prenotazioni, Ristorante ristorante) {
        HashMap<Prodotto, Integer> listaSpesa = new HashMap<>();

        // Aggiungi alla lista della spesa i prodotti necessari per soddisfare le prenotazioni del giorno
        for (Prenotazione prenotazione : prenotazioni) {
            for (Piatto piatto : prenotazione.getPrenotazione().keySet()) {
                // Aggiungi gli ingredienti necessari per ogni piatto prenotato
                Ricetta ricetta = ristorante.getRicettaByPiatto(piatto);
                for (Ingrediente ingrediente : ricetta.getIngredienti()) {
                    Prodotto prodotto = ingrediente.getProdotto();
                    int quantita = ingrediente.getQuantita() * prenotazione.getNumeroPersone();
                    listaSpesa.put(prodotto, listaSpesa.getOrDefault(prodotto, 0) + quantita);
                }
            }

            // Aggiungi le bevande e i generi alimentari extra necessari per la prenotazione
            int numeroPersone = prenotazione.getNumeroPersone();
            for (Bevanda bevanda : prenotazione.getBevande()) {
                int quantita = (int) Math.ceil(bevanda.getQuantita() * numeroPersone);
                listaSpesa.put(bevanda, listaSpesa.getOrDefault(bevanda, 0) + quantita);
            }
            for (ProdottoExtra extra : prenotazione.getProdottiExtra()) {
                int quantita = (int) Math.ceil(extra.getQuantita() * numeroPersone);
                listaSpesa.put(extra, listaSpesa.getOrDefault(extra, 0) + quantita);
            }
        }

        // Aggiorna la lista della spesa in base alle giacenze attuali in magazzino
        for (Prodotto prodotto : listaSpesa.keySet()) {
            int quantitaRichiesta = listaSpesa.get(prodotto);
            int quantitaGiacenza = magazzino.getQuantita(prodotto);
            if (quantitaRichiesta > quantitaGiacenza) {
                int quantitaDaAcquistare = quantitaRichiesta - quantitaGiacenza;
                listaSpesa.put(prodotto, quantitaDaAcquistare);
            } else {
                listaSpesa.remove(prodotto);
            }
        }

        // Genera la lista della spesa in formato testuale
        ArrayList<String> listaSpesaTestuale = new ArrayList<>();
        for (Prodotto prodotto : listaSpesa.keySet()) {
            int quantita = listaSpesa.get(prodotto);
            String rigaLista = prodotto.getNome()

