import resto.Xml;
import ristorante.Ricetta;

import java.util.ArrayList;

public class MainClass {
    public static void main(String[] args) {

        ArrayList<Ricetta> Ricetta = new ArrayList<>();

        System.out.println("Hello World!");
        Xml.leggiRicettario("Ricettario.xml", Ricetta);
    }
}
