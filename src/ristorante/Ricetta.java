package ristorante;

import resto.Stagioni;

import java.util.ArrayList;

public class Ricetta {
    private ArrayList<Ingrediente> ingredienti;
    private int porzioni;
    private int tempo_preparazione;
    private double carico_lavoro_porzione;
    private String nome;
    private ArrayList<Stagioni> stagione;

    /*
    Precondizione: nome è una stringa non vuota, stagione è un oggetto valido di tipo ArrayList<Stagioni>, porzioni
        è un valore intero non negativo, tempo_preparazione è un valore intero non negativo e ingredienti è un oggetto valido di tipo ArrayList<Ingrediente>.
    Postcondizione: dopo l'esecuzione del costruttore Ricetta, viene creato un nuovo oggetto Ricetta. Gli attributi nome, stagione,
        ingredienti, porzioni e tempo_preparazione vengono inizializzati con i valori forniti come parametri corrispondenti. L'attributo carico_lavoro_porzione
        viene calcolato dividendo il tempo di preparazione per il numero di porzioni, convertito in valore decimale.
     */
    public Ricetta(String nome, ArrayList<Stagioni> stagione, int porzioni, int tempo_preparazione, ArrayList<Ingrediente> ingredienti) {
        this.nome = nome;
        this.stagione = stagione;
        this.ingredienti = ingredienti;
        this.porzioni = porzioni;
        this.tempo_preparazione = tempo_preparazione;
        this.carico_lavoro_porzione = tempo_preparazione / ((double) porzioni);
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce l'elenco degli ingredienti della ricetta come un oggetto di tipo ArrayList<Ingrediente>.*/

    public ArrayList<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce il numero di porzioni della ricetta come un valore intero.*/

    public int getPorzioni() {
        return porzioni;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce il tempo di preparazione della ricetta come un valore intero.*/

    public int getTempo_preparazione() {
        return tempo_preparazione;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce il carico di lavoro per porzione della ricetta come un valore decimale.*/

    public double getCarico_lavoro_porzione() {
        return carico_lavoro_porzione;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce il nome della ricetta come una stringa.*/

    public String getNome() {
        return nome;
    }

    /*Precondizioni: nome è una stringa non nulla.
    Postcondizioni: Imposta il nome della ricetta con il valore specificato dal parametro nome.*/

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce l'elenco delle stagioni della ricetta come un oggetto di tipo ArrayList<Stagioni>.*/

    public ArrayList<Stagioni> getStagione() {
        return stagione;
    }

    public String toString() {
        return "\n-----------" + nome + "-----------" +
                "\nPorzioni: " + porzioni +
                "\nTempo di preparazione: " + tempo_preparazione +
                "\nDisponibilita': " + stagione +
                "\nIngredienti: " + ingredienti;
    }
}
