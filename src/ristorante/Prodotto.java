package ristorante;

import resto.Misura;

import java.util.Objects;

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

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", u_misura=" + u_misura +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome().toUpperCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Prodotto)) return false;

        if (obj instanceof Bevanda) {
            Bevanda b = (Bevanda) obj;
            return getNome().equalsIgnoreCase(b.getNome());
        } else {
            Prodotto p = (Prodotto) obj;
            return getNome().equalsIgnoreCase(p.getNome());
        }
    }

}
