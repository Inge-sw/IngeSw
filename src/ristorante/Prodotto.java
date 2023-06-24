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

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce il nome del prodotto come una stringa.
    */

    public String getNome() {
        return nome;
    }

    /*
    Precondizioni: nome è una stringa non nulla.
    Postcondizioni: Imposta il nome del prodotto con il valore specificato dal parametro nome.
    */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce l'unità di misura del prodotto come un oggetto di tipo Misura.
    */

    public Misura getU_misura() {
        return u_misura;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce una stringa che rappresenta il prodotto, includendo il nome e l'unità di misura.
    */

    @Override
    public String toString() {
        return "Prodotto{" +
                "nome='" + nome + '\'' +
                ", u_misura=" + u_misura +
                '}';
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce il valore hash del nome del prodotto in lettere maiuscole.
    */

    @Override
    public int hashCode() {
        return Objects.hash(getNome().toUpperCase());
    }

    /*
    Precondizioni: obj è un oggetto non nullo.
    Postcondizioni: Restituisce true se l'oggetto obj è uguale al prodotto corrente in base al nome (ignorando maiuscole/minuscole), false altrimenti.
        Se obj è un'istanza di Bevanda, viene eseguito un confronto specifico per le bevande.
    */

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
