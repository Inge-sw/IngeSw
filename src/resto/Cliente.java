package resto;

import prenotazione.Prenotazione;
import ristorante.Prenotabile;

import java.time.LocalDate;
import java.util.HashSet;

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
       }while (data == null) ;

        Prenotazione p = new Prenotazione(num_coperti, data);

        //aggiungiCibo(p);
        return p;
    }

   /* private void aggiungiCibo(Prenotazione p){
        boolean exit;
        HashSet<Prenotabile> prenotabili =
        do{

            String piatto = InputDati.leggiStringaNonVuota("Inserisci nome del piatto / menù tematico");
            int persone = InputDati.leggiInteroNonNegativo("Per quante persone?");

            p.addLista_prenotazioni();
            exit = InputDati.yesOrNo("Uscire?");
        }while(exit = false);
    }*/
}
