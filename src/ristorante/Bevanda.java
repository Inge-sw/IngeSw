package ristorante;

import resto.Misura;

public class Bevanda extends Prodotto{

    public Bevanda(String nome) {
        super(nome, Misura.LITRI);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
