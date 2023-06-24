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

    public Ricetta(String nome, ArrayList<Stagioni> stagione, int porzioni, int tempo_preparazione, ArrayList<Ingrediente> ingredienti) {
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

    public int getPorzioni() {
        return porzioni;
    }

    public int getTempo_preparazione() {
        return tempo_preparazione;
    }

    public double getCarico_lavoro_porzione() {
        return carico_lavoro_porzione;
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

    public String toString() {
        return "\n-----------" + nome + "-----------" +
                "\nPorzioni: " + porzioni +
                "\nTempo di preparazione: " + tempo_preparazione +
                "\nDisponibilita': " + stagione +
                "\nIngredienti: " + ingredienti;
    }
}
