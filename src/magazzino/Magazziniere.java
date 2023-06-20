package magazzino;

import resto.RuoloUtente;
import resto.Utente;


public class Magazziniere{

    /*public Magazziniere(String username, String password) {
        super(username, password, RuoloUtente.GESTORE);

    }*/
    Magazzino magazzino;

    public Magazziniere(){
        magazzino = new Magazzino();
    };

    @Override
    public String toString() {
        return "Magazziniere{" +
                "magazzino=" + magazzino +
                '}';
    }
}
