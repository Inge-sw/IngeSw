package ristorante;

import resto.Stagioni;
import letturaFile.Xml;

import java.util.ArrayList;

public class Ricettario {

    private static ArrayList<Ricetta> ricette;

    /*
    Precondizione: ricette è un oggetto valido di tipo ArrayList<Ricetta>.
    Postcondizione: dopo l'esecuzione del costruttore Ricettario, viene creato un nuovo oggetto Ricettario.
        L'attributo statico ricette della classe Ricettario viene assegnato con il valore fornito come parametro ricette.
     */

    public Ricettario(ArrayList<Ricetta> ricette) {
        Ricettario.ricette = ricette;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce l'elenco delle ricette come un oggetto di tipo ArrayList<Ricetta>.*/

    public ArrayList<Ricetta> getRicette() {
        return ricette;
    }

    /*Precondizioni: nome è una stringa non nulla, stagione è un elenco di oggetti di tipo Stagioni,
            porzioni è un intero positivo, tempo_preparazione è un intero positivo, ingredienti è un elenco di oggetti di tipo Ingrediente.
    Postcondizioni: Aggiunge una nuova istanza di Ricetta all'elenco delle ricette. La nuova ricetta ha i dettagli specificati dai parametri
            e viene anche salvata in un file XML tramite il metodo Xml.aggiungiRicetta.
     */

    public void aggiungiRicetta(String nome, ArrayList<Stagioni> stagione, int porzioni, int tempo_preparazione, ArrayList<Ingrediente> ingredienti) {
        ricette.add(new Ricetta(nome, stagione, porzioni, tempo_preparazione, ingredienti));
        Xml.aggiungiRicetta(new Ricetta(nome, stagione, porzioni, tempo_preparazione, ingredienti));
    }

    public void aggiungiRicetta(Ricetta da_aggiungere) {
        ricette.add(da_aggiungere);
        Xml.aggiungiRicetta(da_aggiungere);
    }

    /*Precondizioni: nome è una stringa non nulla.
    Postcondizioni: Restituisce l'istanza di Ricetta corrispondente al nome specificato se presente nell'elenco delle ricette. Altrimenti, restituisce null.*/

    public static Ricetta getRicettaByNome(String nome) {
        for (Ricetta ricetta : ricette) {
            if (ricetta.getNome().equalsIgnoreCase(nome)) {
                return ricetta;
            }
        }
        return null;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Stampa il nome di ciascuna ricetta presente nell'elenco delle ricette in lettere maiuscole, preceduto da un trattino ("-").*/

    public static void stampaRicette() {
        for (Ricetta ricetta : ricette) {
            System.out.println("- " + ricetta.getNome().toUpperCase());
        }
    }

    public String toString() {
        return ricette.toString();
    }
}
