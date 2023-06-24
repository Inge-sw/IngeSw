package ristorante;

import resto.Misura;

public class Ingrediente extends Prodotto {

    private double quantita;

    /*
    * Precondizione: Devono essere passati una stringa "nome" non vuota e un valore double "quantita".
    * Postcondizione: Crea un nuovo oggetto di tipo "Ingrediente" con il nome specificato, la misura predefinita "GRAMMI"
    * ereditata dalla classe "Prodotto" e imposta la quantità dell'ingrediente con il valore specificato.
    * */
    public Ingrediente(String nome, double quantita) {
        super(nome, Misura.GRAMMI);
        this.quantita = quantita;
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Restituisce la quantità dell'ingrediente.
    * */
    public double getQuantita() {
        return quantita;
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Restituisce una stringa che rappresenta l'ingrediente.
    * */
    public String toString() {
        return "\n\t- " + super.getNome() + " " + quantita + " " + Misura.GRAMMI;
    }
}
