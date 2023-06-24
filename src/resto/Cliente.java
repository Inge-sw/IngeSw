package resto;

import input.InputDati;
import letturaFile.Xml;
import prenotazione.AddettoPrenotazioni;
import prenotazione.Prenotazione;
import ristorante.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cliente {

    public Cliente() {
    }

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


    private boolean aggiungiCibo(Prenotazione p) {
        boolean exit;
        HashMap<String, Prenotabile> prenotabili = leggiMenu(p.getData().getMonthValue());
        do {
            String piatto = InputDati.leggiStringaNonVuota("Inserisci nome del piatto / menu' tematico: ");
            int persone = InputDati.leggiInteroNonNegativo("Per quante persone? ");

            p.add_piatti(prenotabili.get(piatto), persone);
            exit = InputDati.yesOrNo("Uscire?");
        } while (!exit);

        if (!p.check_numero_piatti()) {
            System.out.println("ERRORE NELLA PRENOTAZIONE");
            return false;
        } else return true;
    }


    private HashMap<String, Prenotabile> leggiMenu(int mese) {
        Ricettario ricettario = Xml.leggiRicettario();
        ArrayList<MenuTematico> menuTematici = Xml.leggiMenuTematico();

        HashMap<String, Prenotabile> piattiPrenotabili = new HashMap<>();

        System.out.println("PIATTI DEL MENU' ALLA CARTA:");
        for (int i = 0; i < ricettario.getRicette().size(); i++) {
            Ricetta ricetta = ricettario.getRicette().get(i);
            if (ricetta.getStagione().contains(Stagioni.getStagione(mese))) {
                piattiPrenotabili.put(ricetta.getNome(), new Piatto(ricetta));
                System.out.println(ricetta.getNome());
            }
        }

        System.out.println("MENU' TEMATICI");
        for (MenuTematico menuTematico : menuTematici) {
            if (menuTematico.getStagione().contains(Stagioni.getStagione(mese))) {
                piattiPrenotabili.put(menuTematico.getNome(), menuTematico);
                System.out.println(menuTematico.getNome());
            }
        }
        return piattiPrenotabili;
    }
}
