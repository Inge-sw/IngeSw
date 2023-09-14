package Testing;

import letturaFile.Xml;
import resto.Stagioni;
import ristorante.*;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Testing {
    ArrayList<Stagioni> stagioni = new ArrayList<>(Arrays.asList(Stagioni.getStagione("Autunno"), Stagioni.getStagione("Primavera"), Stagioni.getStagione("Inverno")));
    Gestore g = new Gestore("Paolo");
    ArrayList<Ingrediente> ings = new ArrayList<>();
    Ingrediente ing = new Ingrediente("lalala", 5.0);

    @org.junit.jupiter.api.Test
    void checkStagioneDuplicata() {
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

    @org.junit.jupiter.api.Test
    void checkCheUnMenuCorrettoVengaAggiuntoAiMenu() {
        ArrayList<Piatto> piatti = new ArrayList<>();
        String nome_menu = "menù nuovo 2";

        ings.add(ing);
        Ricetta ric = new Ricetta("pasta ragu", stagioni, 3, 4, ings);
        piatti.add(new Piatto(ric));

        MenuTematico menu = new MenuTematico(nome_menu, stagioni, piatti);
        g.aggiungiMenu(menu);

        assertTrue(g.getMenu_tematici().contains(menu));
    }
}