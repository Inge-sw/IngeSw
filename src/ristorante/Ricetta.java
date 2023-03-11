package ristorante;

import resto.Misura;
import resto.Stagioni;

import java.util.ArrayList;

public class Ricetta {
    private ArrayList<Ingrediente> ingredienti;
    private int porzioni;
    private int tempo_preparazione;
    private double carico_lavoro_porzione;
    private String nome;
    private Stagioni stagione;

    public Ricetta(String nome, Stagioni stagione,int porzioni, int tempo_preparazione) {
        this.nome = nome;
        this.stagione = stagione;
        this.ingredienti = new ArrayList<Ingrediente>();
        this.porzioni = porzioni;
        this.tempo_preparazione = tempo_preparazione;
        this.carico_lavoro_porzione = tempo_preparazione / ((double) porzioni);
    }

    public Ricetta(String nome, Stagioni stagione,int porzioni, int tempo_preparazione, ArrayList<Ingrediente> ingredienti) {
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

    public Stagioni getStagione() {
        return stagione;
    }

    public void setStagione(Stagioni stagione) {
        this.stagione = stagione;
    }

    @Override
    public String toString() {
        return "Nome della ricetta: " + nome +
                "\nPorzioni: " + porzioni +
                "\nTempo di preparazione: " + tempo_preparazione +
                "\nIngredienti: " +
                "\n\t -" + ingredienti;
    }
}
