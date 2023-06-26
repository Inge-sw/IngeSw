package cliente;

import input.InputDati;
import letturaFile.Xml;
import prenotazione.AddettoPrenotazioni;
import prenotazione.Prenotazione;
import resto.Stagioni;
import ristorante.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cliente {

    public Cliente() {
    }

    /*
    Precondizione: gestore è un oggetto valido di tipo Gestore e addetto è un oggetto valido di tipo AddettoPrenotazioni.
    Postcondizione: il metodo effettua il processo di prenotazione seguendo una serie di passaggi. Viene richiesto all'utente di inserire il numero di persone
            e la data per la prenotazione. Viene quindi creata una nuova istanza di Prenotazione con i valori inseriti. Successivamente, viene verificato
            se il numero di posti e il carico di lavoro sono accettabili per il ristorante gestito dall'addetto. Se entrambe le condizioni sono verificate,
            viene chiamato il metodo aggiungiCibo(p) per aggiungere piatti o menu alla prenotazione. Se l'aggiunta dei piatti ha successo,
            la prenotazione viene aggiunta alla lista delle prenotazioni dell'addetto e salvata nel file XML delle prenotazioni. In caso contrario,
            viene stampato un messaggio di errore.
     */

    public void effettuaPrenotazione(Gestore gestore, AddettoPrenotazioni addetto) {
        int num_coperti = InputDati.leggiInteroNonNegativo("Inserisci num persone della prenotazione");
        LocalDate data = null;
        do {
            try {
                data = InputDati.leggiData("Inserisci la data (formato YYYY-MM-DD)");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (data == null || data.isBefore(LocalDate.now()));

        Prenotazione p = new Prenotazione(num_coperti, data);

        if (addetto.checkPosti(gestore.getRistorante(), p) && addetto.checkCaricoLavoro(gestore.getRistorante(), p)) {
            if (aggiungiCibo(p)) {
                addetto.getLista_prenotazioni().add(p);
                Xml.aggiungiPrenotazione(p);
            }
        } else {
            System.out.println("ERRORE, SUPERATO I POSTI/CARICO DI LAVORO");
        }
    }

    /*
    Precondizione: p è un oggetto valido di tipo Prenotazione.
    Postcondizione: il metodo gestisce l'aggiunta di piatti o menu alla prenotazione p. Viene richiesto all'utente di inserire il nome del piatto o
        menu e il numero di persone. Viene quindi chiamato il metodo add_piatti() della prenotazione p per aggiungere il piatto o menu alla lista delle prenotazioni.
        L'utente viene anche chiesto se desidera uscire dal processo di aggiunta dei piatti. Se il numero di piatti nella prenotazione non è valido,
        viene stampato un messaggio di errore e viene restituito false. Altrimenti, viene restituito true.
     */

    private boolean aggiungiCibo(Prenotazione p) {
        boolean exit;
        HashMap<String, Prenotabile> prenotabili = leggiMenu(p.getData().getMonthValue());
        do {
            String piatto = InputDati.leggiStringaNonVuota("Inserisci nome del piatto / menu' tematico: ");
            int persone = InputDati.leggiInteroNonNegativo("Per quante persone? ");

            if (prenotabili.containsKey(piatto.toLowerCase()))
                p.add_piatti(prenotabili.get(piatto), persone);
            else
                System.out.println("Piatto inserito non esiste");
            exit = InputDati.yesOrNo("Uscire?");
        } while (!exit);

        if (!p.check_numero_piatti()) {
            System.out.println("ERRORE NELLA PRENOTAZIONE");
            return false;
        } else return true;
    }

    /*
    Precondizione: mese è un valore intero valido che rappresenta il mese corrente.
    Postcondizione: il metodo legge il ricettario e i menu tematici dal file XML. Viene creato un oggetto HashMap chiamato piattiPrenotabili
        che contiene i piatti alla carta e i menu tematici disponibili per il mese specificato. Vengono stampati i nomi dei piatti alla carta
        e dei menu tematici disponibili. Infine, l'HashMap viene restituito.
     */

    private HashMap<String, Prenotabile> leggiMenu(int mese) {
        Ricettario ricettario = Xml.leggiRicettario();
        ArrayList<MenuTematico> menuTematici = Xml.leggiMenuTematico();

        HashMap<String, Prenotabile> piattiPrenotabili = new HashMap<>();

        System.out.println("PIATTI DEL MENU' ALLA CARTA:");
        for (int i = 0; i < ricettario.getRicette().size(); i++) {
            Ricetta ricetta = ricettario.getRicette().get(i);
            if (ricetta.getStagione().contains(Stagioni.getStagione(mese))) {
                piattiPrenotabili.put(ricetta.getNome().toLowerCase(), new Piatto(ricetta));
                System.out.println(ricetta.getNome());
            }
        }

        System.out.println("MENU' TEMATICI");
        for (MenuTematico menuTematico : menuTematici) {
            if (menuTematico.getStagione().contains(Stagioni.getStagione(mese))) {
                piattiPrenotabili.put(menuTematico.getNome().toLowerCase(), menuTematico);
                System.out.println(menuTematico.getNome());
            }
        }
        return piattiPrenotabili;
    }
}
