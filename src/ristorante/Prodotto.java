package ristorante;

import resto.Misura;

public class Prodotto {
    private String nome;
    private Misura u_misura;

    public Prodotto(String nome, Misura u_misura) {
        this.nome = nome;
        this.u_misura = u_misura;
    }

    public Prodotto(String nome) {
        this.nome = nome;
        this.u_misura = null;
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

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", u_misura=" + u_misura +
                '}';
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
