package Testing;

import letturaFile.Xml;
import org.junit.rules.ExpectedException;
import resto.Stagioni;
import ristorante.Gestore;
import ristorante.MenuTematico;
import ristorante.Piatto;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Testing {

    String[] list = new String[] {"Autunno","Primavera","Inverno"};
    ArrayList<Stagioni> stagioni = new ArrayList<>();
    Gestore g = new Gestore("Paolo");

    @org.junit.jupiter.api.Test
    void checkStagioneDuplicata() {

        for (String elem : list)
            stagioni.add(Stagioni.getStagione(elem));

        assertFalse(g.checkStagioneDuplicata(stagioni,"Estate"));
        assertTrue(g.checkStagioneDuplicata(stagioni,"Inverno"));
    }

    @org.junit.jupiter.api.Test
    void checkStagioneValidaByValue() {
        assertEquals(Stagioni.INVERNO, Stagioni.getStagione(1));
        assertEquals(Stagioni.INVERNO, Stagioni.getStagione(2));
        assertEquals(Stagioni.AUTUNNO, Stagioni.getStagione(11));
        assertEquals(Stagioni.INVERNO, Stagioni.getStagione(12));
    }

    @org.junit.jupiter.api.Test
    void checkStagioneInvalidaByValue() {
        assertNull(Stagioni.getStagione(0));
        assertNull(Stagioni.getStagione(13));
    }

    @org.junit.jupiter.api.Test
    void checkStagioneValidaByString() {
        assertEquals(Stagioni.ESTATE, Stagioni.getStagione("estate"));
    }

    @org.junit.jupiter.api.Test
    void checkStagioneInvalidaByString() {
        assertNull(Stagioni.getStagione("Input non valido"));
    }

    @org.junit.jupiter.api.Test
    void checkCheUnMenuVuotoNonVengaAggiuntoAiMenu() {
        ArrayList<Piatto> piatti = new ArrayList<>();
        String nome_menu = "menù nuovo";

        MenuTematico menu = new MenuTematico(nome_menu, stagioni, piatti);
        g.aggiungiMenu(menu);

        assertFalse(g.getMenu_tematici().contains(menu));
    }

}