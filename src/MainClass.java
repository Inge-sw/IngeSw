import resto.Costante;
import resto.InputDati;
import resto.MyMenu;
import resto.Xml;
import ristorante.Gestore;
import ristorante.Ricetta;
import ristorante.Ricettario;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {

        Gestore gestore = new Gestore();
        Menu(gestore);

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
