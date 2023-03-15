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

    public Ricetta(String nome, ArrayList<Stagioni> stagione,int porzioni, int tempo_preparazione) {
        this.nome = nome;
        this.stagione = stagione;
        this.ingredienti = new ArrayList<>();
        this.porzioni = porzioni;
        this.tempo_preparazione = tempo_preparazione;
        this.carico_lavoro_porzione = tempo_preparazione / ((double) porzioni);
    }

    public Ricetta(String nome, ArrayList<Stagioni> stagione,int porzioni, int tempo_preparazione, ArrayList<Ingrediente> ingredienti) {
        this.nome = nome;
        this.stagione = stagione;
        this.ingredienti = ingredienti;
        this.porzioni = porzioni;
        this.tempo_preparazione = tempo_preparazione;
        this.carico_lavoro_porzione = tempo_preparazione / ((double) porzioni);
    }

    public ArrayList<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(ArrayList<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public int getPorzioni() {
        return porzioni;
    }

    public void setPorzioni(int porzioni) {
        this.porzioni = porzioni;
    }

    public int getTempo_preparazione() {
        return tempo_preparazione;
    }

    public void setTempo_preparazione(int tempo_preparazione) {
        this.tempo_preparazione = tempo_preparazione;
    }

    public double getCarico_lavoro_porzione() {
        return carico_lavoro_porzione;
    }

    public void setCarico_lavoro_porzione(double carico_lavoro_porzione) {
        this.carico_lavoro_porzione = carico_lavoro_porzione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ArrayList<Stagioni> getStagione() {
        return stagione;
    }

    public void setStagione(ArrayList<Stagioni> stagione) {
        this.stagione = stagione;
    }

    public String stampaNome(){
        return "\n - " + nome;
    }

    public String toString() {
        return "\n-----------" + nome + "-----------" +
                "\nPorzioni: " + porzioni +
                "\nTempo di preparazione: " + tempo_preparazione +
                "\nDisponibilita': " + stagione +
                "\nIngredienti: " + ingredienti;
    }
}
