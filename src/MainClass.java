import resto.Costante;
import resto.MyMenu;
import ristorante.Gestore;

public class MainClass {
    public static void main(String[] args) {

        Gestore gestore = new Gestore();
        opzioniGestore(gestore);

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
                opzioniGestore(gestore);
        }
    }
    
    
    public static void Menu(Gestore gestore){
        MyMenu menu = new MyMenu("Operazioni da parte del gestore", Costante.voci);
        switch (menu.scegli()){
            case 1:
                gestore.visualizzaMenuTematici();
                Menu(gestore);
                break;
            case 2:
                gestore.creaMenuTematico();
                Menu(gestore);
                break;
            case 3:
                gestore.visualizzaRicettario();
                Menu(gestore);
                break;
            case 4:
                gestore.creaRicetta();
                Menu(gestore);
                break;
            case 5:
                break;
        }
    }
}
