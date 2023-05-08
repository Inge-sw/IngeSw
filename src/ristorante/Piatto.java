package ristorante;

import resto.Stagioni;

import java.util.ArrayList;

public class Piatto implements Prenotabile{
    private String nome;
    private ArrayList<Stagioni> stagione;
    private Ricetta ricetta;
    private double carico_lavoro;

    public Piatto(Ricetta ricetta) {
        this.nome = ricetta.getNome();
        this.stagione = ricetta.getStagione();
        this.ricetta = ricetta;
        this.carico_lavoro = ricetta.getCarico_lavoro_porzione();
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

    public Ricetta getRicetta() {
        return ricetta;
    }

    public double getCarico_lavoro() {
        return carico_lavoro;
    }

    @Override
    public String toString() {
        return "\n\t-" + nome;
    }
}
