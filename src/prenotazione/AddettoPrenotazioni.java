package prenotazione;

import resto.RuoloUtente;
import resto.Utente;
import resto.Xml;
import ristorante.Ristorante;

import java.time.LocalDate;
import java.util.ArrayList;


public class AddettoPrenotazioni {

    private ArrayList<Prenotazione> lista_prenotazioni;
/*
    public AddettoPrenotazioni(String username, String password, ArrayList<Prenotazione> l) {
        super(username, password, RuoloUtente.ADDETTO_PRENOTAZIONI);
        this.lista_prenotazioni = l;
    }

    public AddettoPrenotazioni(String username, String password) {
        super(username, password, RuoloUtente.ADDETTO_PRENOTAZIONI);
    }

*/

    public AddettoPrenotazioni(ArrayList<Prenotazione> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
        eliminaPrenotazioniScadute();
    }

    public ArrayList<Prenotazione> getLista_prenotazioni() {
        return lista_prenotazioni;
    }

    public void setLista_prenotazioni(ArrayList<Prenotazione> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
    }

    public boolean checkPosti(Ristorante ristorante, int num_coperti) {
        int posti = num_coperti;
        for (Prenotazione prenotazione : lista_prenotazioni) {
            posti += prenotazione.getNum_coperti();
        }

        if (posti <= ristorante.getNum_posti()) return true;
        return false;
    }

    public void eliminaPrenotazioniScadute() {
        ArrayList<Prenotazione> da_eliminare = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Prenotazione p : lista_prenotazioni) {
            LocalDate data_prenotazione = p.getData();
            if (data_prenotazione.isBefore(today)) da_eliminare.add(p);
        }

        for (Prenotazione p : da_eliminare) {
            lista_prenotazioni.remove(p);
        }
    }

    public void stampa() {
        for (Prenotazione p : lista_prenotazioni) {
            System.out.println(p.getData());
        }
    }

    @Override
    public String toString() {
        return "AddettoPrenotazioni{" +
                "lista_prenotazioni=" + lista_prenotazioni +
                '}';
    }

    public static void checkCarico(Ristorante r) {

    }
}
