package prenotazione;

import resto.RuoloUtente;
import resto.Utente;
import resto.Xml;
import ristorante.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class AddettoPrenotazioni {

    private ArrayList<Prenotazione> lista_prenotazioni;
/*
    public AddettoPrenotazioni(String username, String password, ArrayList<Prenotazione> l) {
        super(username, password, RuoloUtente.ADDETTO_PRENOTAZIONI);
        this.lista_prenotazioni = l;
    }

    public AddettoPrenotazioni(String username, String password) {
        super(username, password, RuoloUtente.ADDETTO_PRENOTAZIONI);
    }

*/

    public AddettoPrenotazioni(ArrayList<Prenotazione> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
        eliminaPrenotazioniScadute();
    }

    public ArrayList<Prenotazione> getLista_prenotazioni() {
        return lista_prenotazioni;
    }

    public void setLista_prenotazioni(ArrayList<Prenotazione> lista_prenotazioni) {
        this.lista_prenotazioni = lista_prenotazioni;
    }

    public boolean checkPosti(Ristorante ristorante, Prenotazione p) {
        int posti = p.getNum_coperti();
        for (Prenotazione prenotati : lista_prenotazioni) {
            if (prenotati.getData().equals(p.getData()))
                posti += prenotati.getNum_coperti();
        }

        if (posti <= ristorante.getNum_posti()) return true;
        return false;
    }

    public boolean checkCaricoLavoro(Ristorante ristorante, Prenotazione p){
        double caricoDiLavoro = 0;
        for (Prenotazione prenotati : lista_prenotazioni){
            if (prenotati.getData().equals(p.getData())) {

                HashMap<Prenotabile, Integer> lista_piatti = prenotati.getLista_prenotazioni_piatti();

                for (Map.Entry<Prenotabile, Integer> entry : lista_piatti.entrySet()){
                    String nome_prenotabile = entry.getKey().toString();
                    int quantita = entry.getValue();

                    Ricetta ricetta = Ricettario.getRicettaByNome(nome_prenotabile);

                    if (ricetta != null){
                        caricoDiLavoro += ricetta.getCarico_lavoro_porzione() * quantita;
                    }else{
                        ArrayList<MenuTematico> MenuTematici = Xml.leggiMenuTematico();
                        for (MenuTematico m : MenuTematici){
                            if (m.getNome().equalsIgnoreCase(nome_prenotabile)){
                                ArrayList<Piatto> piatti = m.getPiatti();
                                for (Piatto piatto : piatti){
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
        return "Lista delle prenotazioni:\n " +
                lista_prenotazioni +
                '}';
    }

    public static void checkCarico(Ristorante r) {

    }
}
