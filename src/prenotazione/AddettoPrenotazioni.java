package prenotazione;

import resto.RuoloUtente;
import resto.Utente;
import ristorante.Ristorante;

import java.util.ArrayList;


public class AddettoPrenotazioni extends Utente {

    private ArrayList<Prenotazione> lista_prenotazioni;

    public AddettoPrenotazioni(String username, String password, ArrayList<Prenotazione> l) {
        super(username, password, RuoloUtente.ADDETTO_PRENOTAZIONI);
        this.lista_prenotazioni = l;
    }

    public AddettoPrenotazioni(String username, String password) {
        super(username, password, RuoloUtente.ADDETTO_PRENOTAZIONI);
    }

    public ArrayList<Prenotazione> getLista_prenotazioni() {
        return lista_prenotazioni;
    }

    public void setLista_prenotazioni(ArrayList<Prenotazione> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
    }

    public static boolean checkPosti(){
        int posti = 0;/*
        for(Prenotazione prenotazione : lista_prenotazioni){
            posti += prenotazione.getNum_coperti();
        }

        if (posti <= ) return true;*/
        return false;
    }

    public static void checkCarico(Ristorante r){}
}
