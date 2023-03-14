package prenotazione;

import resto.RuoloUtente;
import resto.Utente;


public class AddettoPrenotazioni extends Utente {

    public AddettoPrenotazioni(String username, String password) {
        super(username, password, RuoloUtente.ADDETTO_PRENOTAZIONI);
    }
}
