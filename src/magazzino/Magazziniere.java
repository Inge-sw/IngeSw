package magazzino;

import resto.RuoloUtente;
import resto.Utente;


public class Magazziniere extends Utente {

    public Magazziniere(String username, String password) {
        super(username, password, RuoloUtente.GESTORE);

    }
}
