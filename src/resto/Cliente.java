package resto;

import prenotazione.AddettoPrenotazioni;
import prenotazione.Prenotazione;
import ristorante.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Cliente extends Utente{

    public Cliente(String username, String password) {
        super(username, password, RuoloUtente.CLIENTE);
    }

    public Prenotazione effettuaPrenotazione(){
        int num_coperti = InputDati.leggiInteroNonNegativo("Inserisci num persone della prenotazione");
        LocalDate data= null;
       do {
           try {
               data = InputDati.leggiData("Inserisci la data (formato YYYY-MM-DD)");

           } catch (Exception e) {
               System.out.println(e.getMessage());
           }
       }while (data == null || data.isBefore(LocalDate.now())) ;

        Prenotazione p = new Prenotazione(num_coperti, data);
        aggiungiCibo(p);

        AddettoPrenotazioni.checkPosti();

        return p;
    }


    private void aggiungiCibo(Prenotazione p){
        boolean exit;
        HashMap<String, Prenotabile> prenotabili = leggiMenu(p.getData().getMonthValue());
        do{
            String piatto = InputDati.leggiStringaNonVuota("Inserisci nome del piatto / menu' tematico");
            int persone = InputDati.leggiInteroNonNegativo("Per quante persone?");

            p.addLista_prenotazioni(prenotabili.get(piatto), persone);
            exit = InputDati.yesOrNo("Uscire?");
        }while(exit == false);

        if(!p.check_posti()) System.out.println("ERRORE NELLA PRENOTAZIONE");
    }


    private HashMap<String, Prenotabile> leggiMenu(int mese){
        Ricettario ricettario = Xml.leggiRicettario();
        ArrayList<MenuTematico> menuTematici = Xml.leggiMenuTematico();

        HashMap<String, Prenotabile> piattiPrenotabili = new HashMap<>();

        System.out.println("PIATTI DEL MENU' ALLA CARTA:");
        for (int i = 0; i < ricettario.getRicette().size(); i++){
            Ricetta ricetta = ricettario.getRicette().get(i);
            if (ricetta.getStagione().contains(Stagioni.getStagione(mese))) {
                piattiPrenotabili.put(ricetta.getNome(), new Piatto(ricetta));
                System.out.println(ricetta.getNome());
            }
        }

        System.out.println("MENU' TEMATICI");
        for(MenuTematico menuTematico : menuTematici){
            if (menuTematico.getStagione().contains(Stagioni.getStagione(mese))) {
                piattiPrenotabili.put(menuTematico.getNome(), menuTematico);
                System.out.println(menuTematico.getNome());
            }
        }
        return piattiPrenotabili;
    }
}
