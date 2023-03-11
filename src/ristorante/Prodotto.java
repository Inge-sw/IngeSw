package ristorante;

import resto.Misura;

public class Prodotto {
    private String nome;
    private Misura u_misura;

    public Prodotto(String nome, Misura u_misura) {
        this.nome = nome;
        this.u_misura = u_misura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Misura getU_misura() {
        return u_misura;
    }

    public void setU_misura(Misura u_misura) {
        this.u_misura = u_misura;
    }
}
