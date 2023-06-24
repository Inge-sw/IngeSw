import magazzino.Magazziniere;
import prenotazione.AddettoPrenotazioni;
import resto.*;
import ristorante.Bevanda;
import ristorante.GeneriExtra;
import ristorante.Gestore;

public class MainClass {
    public static void main(String[] args) {
        opzioniRuolo();
    }


    public static void opzioniRuolo(){
        Gestore gestore = new Gestore();
        AddettoPrenotazioni addetto = new AddettoPrenotazioni(Xml.leggiPrenotazioni());
        Magazziniere magazziniere = new Magazziniere();

        MyMenu menu_ruolo = new MyMenu("SCEGLI RUOLO", Costante.MENU_RUOLO);

        switch(menu_ruolo.scegli()){
            case 1:
                opzioniGestore(gestore);
                break;
            case 2:
                opzioniAddetto(addetto);
                break;
            case 3:
                opzioniMagazziniere(magazziniere, addetto, gestore);
                break;
            case 4:
                Cliente cliente = new Cliente();
                cliente.effettuaPrenotazione(gestore, addetto);
                break;
            case 5:
                break;
        }
    }

    public static void opzioniGestore(Gestore gestore){
        MyMenu menu_gestore = new MyMenu("OPERAZIONI GESTORE", Costante.MENU_GESTORE);
        switch (menu_gestore.scegli()){
            case 1:
                opzioniRistorante(gestore);
                break;
            case 2:
                opzioniMenuTematici(gestore);
                break;
            case 3:
                opzioniRicettario(gestore);
                break;
            case 4:
                break;
        }
    }

    public static void opzioniAddetto(AddettoPrenotazioni addetto){
        MyMenu menu_addetto = new MyMenu("OPERAZIONI ADDETTO PRENOTAZIONI", Costante.MENU_ADDETTO);
        switch (menu_addetto.scegli()){
            case 1:
                System.out.println(addetto.toString());
                opzioniAddetto(addetto);
                break;
            case 2:
                break;
        }
    }

    public static void opzioniMagazziniere(Magazziniere magazziniere, AddettoPrenotazioni addetto, Gestore gestore){
        MyMenu menu_magazziniere = new MyMenu("OPERAZIONI MAGAZZINIERE", Costante.MENU_MAGAZZINIERE);
        switch (menu_magazziniere.scegli()){
            case 1:
                System.out.println(magazziniere.toString());
                opzioniMagazziniere(magazziniere, addetto, gestore);
                break;
            case 2:
                magazziniere.ottieniIngredienti(gestore);
                magazziniere.acquistaMerci();
                opzioniMagazziniere(magazziniere, addetto, gestore);
                break;
            case 3:
                magazziniere.rimuoviMerci();
                opzioniMagazziniere(magazziniere, addetto, gestore);
                break;
            case 4 :
                magazziniere.creaListaSpesa(addetto.getLista_prenotazioni());
                opzioniMagazziniere(magazziniere, addetto, gestore);
                break;
            case 5:
                break;
        }
    }

    public static void opzioniRistorante(Gestore gestore){
        MyMenu menu_ricettario = new MyMenu("GESTIONE RISTORANTE", Costante.MENU_RISTORANTE);
        switch (menu_ricettario.scegli()){
            case 1:
                gestore.visulizzaRistorante();
                opzioniRistorante(gestore);
                break;
            case 2:
                gestore.modificaRistorante(2);
                opzioniRistorante(gestore);
                break;
            case 3:
                gestore.modificaRistorante(3);
                opzioniRistorante(gestore);
                break;
            case 4:
                opzioniBevande(gestore);
                break;
            case 5:
                opzioniGeneri(gestore);
                break;
            case 6:
                opzioniGestore(gestore);
                break;
        }
    }

    public static void opzioniMenuTematici(Gestore gestore){
        MyMenu menu_menu = new MyMenu("GESTIONE MENU TEMATICI", Costante.MENU_MENU);
        switch (menu_menu.scegli()){
            case 1:
                gestore.visualizzaMenuTematici();
                opzioniMenuTematici(gestore);
                break;
            case 2:
                gestore.creaMenuTematico();
                opzioniMenuTematici(gestore);
                break;
            case 3:
                opzioniGestore(gestore);
                break;
        }
    }
    
    public static void opzioniRicettario(Gestore gestore){
        MyMenu menu_ricettario = new MyMenu("GESTIONE RICETTARIO", Costante.MENU_RICETTARIO);
        switch (menu_ricettario.scegli()){
            case 1:
                gestore.visualizzaRicettario();
                opzioniRicettario(gestore);
                break;
            case 2:
                gestore.creaRicetta();
                opzioniRicettario(gestore);
                break;
            case 3:
                opzioniGestore(gestore);
                break;
        }
    }

    public static void opzioniBevande(Gestore gestore){
        MyMenu menu_bevanda = new MyMenu("GESTIONE BEVANDE", Costante.MENU_BEVANDA);
        switch (menu_bevanda.scegli()){
            case 1:
                gestore.visualizzaProdotti("bevande");
                opzioniBevande(gestore);
                break;
            case 2:
                do {
                    String bevanda_input = InputDati.leggiStringaNonVuota("Inserisci nuova bevanda: ").toUpperCase();
                    Bevanda bevanda = new Bevanda(bevanda_input);
                    gestore.aggiungiProdotto(bevanda,"Bevande.txt");
                }while(InputDati.yesOrNo("Inserire ancora?"));
                opzioniBevande(gestore);
                break;
            case 3:
                opzioniRistorante(gestore);
                break;
        }
    }

    public static void opzioniGeneri(Gestore gestore){
        MyMenu menu_bevanda = new MyMenu("GESTIONE GENERI EXTRA ", Costante.MENU_GENERI);
        switch (menu_bevanda.scegli()){
            case 1:
                gestore.visualizzaProdotti("generi");
                opzioniGeneri(gestore);
                break;
            case 2:
                do {
                    String genere_input = InputDati.leggiStringaNonVuota("Inserisci nuovo genere: ").toUpperCase();
                    GeneriExtra genere = new GeneriExtra(genere_input);
                    gestore.aggiungiProdotto(genere,"Generi.txt");
                }while(InputDati.yesOrNo("Inserire ancora?"));
                opzioniGeneri(gestore);
                break;
            case 3:
                opzioniRistorante(gestore);
                break;
        }
    }
}
