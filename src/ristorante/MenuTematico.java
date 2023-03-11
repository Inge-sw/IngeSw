package ristorante;

import resto.Stagioni;

import java.util.ArrayList;

public class MenuTematico extends Menu{

    String nome;
    Stagioni stagione;

    public MenuTematico(String nome, Stagioni stagione, ArrayList<Piatto> piatti) {
        super(piatti);
        this.nome = nome;
        this.stagione = stagione;
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
        return "\n-------------" + "MenuTematico: " + nome + "-------------" +
                super.toString();
    }
}
