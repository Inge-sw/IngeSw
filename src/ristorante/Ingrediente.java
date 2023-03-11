package ristorante;

import resto.Misura;

public class Ingrediente extends Prodotto{

    private double quantita;

    public Ingrediente(String nome, double quantita) {
        super(nome, Misura.GRAMMI);
        this.quantita = quantita;
    }

    public double getQuantita() {
        return quantita;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public String toString() {
        return "\n\t- " + super.getNome() + " " + quantita + " " + Misura.GRAMMI;
    }
}
