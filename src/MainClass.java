import resto.*;
import ristorante.Bevanda;
import ristorante.GeneriExtra;
import ristorante.Gestore;

public class MainClass {
    public static void main(String[] args) {

        UserDB userDB = new UserDB();
        login(userDB);


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
            case 3:
                gestore.modificaRistorante(3);
                opzioniRistorante(gestore);
            case 4:
                opzioniBevande(gestore);
            case 5:
                opzioniGeneri(gestore);
            case 6:
                opzioniGestore(gestore);
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
            case 3:
                break;
            case 4:
                opzioniGestore(gestore);
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
            case 3:
                break;
            case 4:
                opzioniGestore(gestore);
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
                break;
            case 4:
                opzioniRistorante(gestore);
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
                break;
            case 4:
                opzioniRistorante(gestore);
        }
    }

    public static void login(UserDB userDB){
        MyMenu menu = new MyMenu("SIGN UP SIGN IN", Costante.MENU_USERDB);
        switch (menu.scegli()){
            case 1:
                String user = InputDati.leggiStringaNonVuota("Inserisci il tuo username: ");
                String pw = InputDati.leggiStringaNonVuota("Inserisci la tua password: ");
                userDB.register(user, pw);
                login(userDB);
                break;
            case 2:
                if(userDB.getUtenti().keySet().isEmpty())
                    System.out.println("Non ci sono utenti registrati");
                else {
                    String user1 = InputDati.leggiStringaNonVuota("Inserisci il tuo username: ");
                    String pw1 = InputDati.leggiStringaNonVuota("Inserisci la tua password: ");
                    if(userDB.login(user1, pw1)) {
                        switch (userDB.getUtenti().get(user1).getRuolo()){
                            case GESTORE:
                                opzioniGestore((Gestore) userDB.getUtenti().get(user1));
                                break;

                            case ADDETTO_PRENOTAZIONI:
                                break;

                            case MAGAZZINIERE:
                                break;
                        }
                    }
                    else System.out.println("Username o password errati");
                }
                login(userDB);
                break;
            case 3:
                break;
        }
    }
}
