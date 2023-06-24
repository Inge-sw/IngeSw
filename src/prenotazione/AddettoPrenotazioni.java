package prenotazione;

import letturaFile.Xml;
import ristorante.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddettoPrenotazioni {

    private ArrayList<Prenotazione> lista_prenotazioni;

    public AddettoPrenotazioni(ArrayList<Prenotazione> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
        eliminaPrenotazioniScadute();
    }

    public ArrayList<Prenotazione> getLista_prenotazioni() {
        return lista_prenotazioni;
    }

    /*Precondizioni:
    Il parametro ristorante non deve essere nullo.
    Il parametro p non deve essere nullo.
    La variabile lista_prenotazioni deve essere un elenco di oggetti Prenotazione non nullo.

    Postcondizioni:
    Viene calcolato il numero totale di posti occupati considerando tutte le prenotazioni presenti nella lista per la stessa data di prenotazione di p.
    Viene verificato se il numero totale di posti occupati, incluso il numero di posti richiesti da p, è minore o uguale al numero di posti disponibili nel ristorante.
    Restituisce true se ci sono posti disponibili, altrimenti restituisce false.*/

    public boolean checkPosti(Ristorante ristorante, Prenotazione p) {
        int posti = p.getNum_coperti();
        for (Prenotazione prenotati : lista_prenotazioni) {
            if (prenotati.getData().equals(p.getData()))
                posti += prenotati.getNum_coperti();
        }

        if (posti <= ristorante.getNum_posti()) return true;
        return false;
    }
    /*Precondizioni:
    Il parametro ristorante non deve essere nullo.
    Il parametro p non deve essere nullo.
    La variabile lista_prenotazioni deve essere un elenco di oggetti Prenotazione non nullo.
    La classe Ricettario deve avere un metodo getRicettaByNome che restituisce un oggetto Ricetta corrispondente al nome passato come argomento.
    Il metodo Xml.leggiMenuTematico restituisce un elenco di oggetti MenuTematico.

    Postcondizioni:
    Viene calcolato il carico di lavoro totale considerando tutte le prenotazioni presenti nella lista per la stessa data di prenotazione di p.
    Per ciascuna prenotazione, vengono recuperati i piatti prenotati dalla mappa lista_prenotazioni_piatti.
    Per ciascun piatto, viene calcolato il carico di lavoro in base alla ricetta o al piatto tematico corrispondente.
    Il carico di lavoro totale viene aggiornato sommando i carichi di lavoro dei singoli piatti.
    Viene verificato se il carico di lavoro totale, incluso il carico di lavoro dei piatti richiesti da p, è minore o uguale al carico di lavoro massimo del ristorante.
    Restituisce true se il carico di lavoro è accettabile, altrimenti restituisce false.*/

    public boolean checkCaricoLavoro(Ristorante ristorante, Prenotazione p) {
        double caricoDiLavoro = 0;
        for (Prenotazione prenotati : lista_prenotazioni) {
            if (prenotati.getData().equals(p.getData())) {

                HashMap<Prenotabile, Integer> lista_piatti = prenotati.getLista_prenotazioni_piatti();

                for (Map.Entry<Prenotabile, Integer> entry : lista_piatti.entrySet()) {
                    String nome_prenotabile = entry.getKey().toString();
                    int quantita = entry.getValue();

                    Ricetta ricetta = Ricettario.getRicettaByNome(nome_prenotabile);

                    if (ricetta != null) {
                        caricoDiLavoro += ricetta.getCarico_lavoro_porzione() * quantita;
                    } else {
                        ArrayList<MenuTematico> MenuTematici = Xml.leggiMenuTematico();
                        for (MenuTematico m : MenuTematici) {
                            if (m.getNome().equalsIgnoreCase(nome_prenotabile)) {
                                ArrayList<Piatto> piatti = m.getPiatti();
                                for (Piatto piatto : piatti) {
                                    caricoDiLavoro += piatto.getCarico_lavoro() * quantita;
                                }
                            }
                        }
                    }
                }
            }
        }

        if (caricoDiLavoro <= ristorante.getCarico_lavoro_ristorante()) return true;
        return false;

    }

    /*Precondizioni:
    La variabile lista_prenotazioni deve essere un elenco di oggetti Prenotazione non nullo.
    Il metodo Xml.eliminaPrenotazione elimina una prenotazione dal sistema di persistenza.

    Postcondizioni:
    Vengono identificate le prenotazioni scadute in base alla data corrente.
    Le prenotazioni scadute vengono aggiunte all'elenco da_eliminare.
    Le prenotazioni scadute vengono rimosse dalla lista_prenotazioni e dal sistema di persistenza chiamando Xml.eliminaPrenotazione.*/

    public void eliminaPrenotazioniScadute() {
        ArrayList<Prenotazione> da_eliminare = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Prenotazione p : lista_prenotazioni) {
            LocalDate data_prenotazione = p.getData();
            if (data_prenotazione.isBefore(today)) da_eliminare.add(p);
        }

        for (Prenotazione p : da_eliminare) {
            lista_prenotazioni.remove(p);
            Xml.eliminaPrenotazione(p);
        }
    }

    @Override
    public String toString() {
        return lista_prenotazioni.toString().replace('[', ' ').replace(']', ' ');
    }

}
