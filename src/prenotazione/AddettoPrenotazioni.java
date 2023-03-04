package prenotazione;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddettoPrenotazioni extends Utente {

    private Ristorante ristorante;

    public AddettoPrenotazioni(String nome, String cognome, Ristorante ristorante) {
        super(nome, cognome);
        this.ristorante = ristorante;
    }

    public void raccogliPrenotazione(Prenotazione prenotazione) {
        LocalDate oggi = LocalDate.now();
        LocalDate dataPrenotazione = prenotazione.getData();

        if (dataPrenotazione.isBefore(oggi)) {
            System.out.println("Attenzione: la prenotazione è scaduta.");
            return;
        }

        int copertiPrenotati = prenotazione.getNumeroCoperti();
        int copertiOccupati = ristorante.getCopertiOccupati(dataPrenotazione);
        int postiDisponibili = ristorante.getPostiDisponibili();

        if (copertiPrenotati + copertiOccupati > postiDisponibili) {
            System.out.println("Attenzione: il numero di coperti prenotati supera i posti disponibili.");
            return;
        }

        int caricoLavoroPrenotazione = prenotazione.getCaricoLavoro();
        int caricoLavoroOccupato = ristorante.getCaricoLavoro(dataPrenotazione);
        int caricoLavoroSostenibile = ristorante.getCaricoLavoroSostenibile();

        if (caricoLavoroPrenotazione + caricoLavoroOccupato > caricoLavoroSostenibile) {
            System.out.println("Attenzione: il carico di lavoro prenotato supera il limite sostenibile.");
            return;
        }

        ristorante.aggiungiPrenotazione(prenotazione);
        System.out.println("Prenotazione aggiunta con successo.");
    }

    public void rimuoviPrenotazioniScadute() {
        LocalDate oggi = LocalDate.now();
        List<Prenotazione> prenotazioni = ristorante.getPrenotazioni();

        List<Prenotazione> prenotazioniDaRimuovere = new ArrayList<>();
        for (Prenotazione p : prenotazioni) {
            LocalDate dataPrenotazione = p.getData();
            if (dataPrenotazione.isBefore(oggi)) {
                prenotazioniDaRimuovere.add(p);
            }
        }

        ristorante.rimuoviPrenotazioni(prenotazioniDaRimuovere);
    }
}
