package ristorante;

import resto.Stagioni;

import java.util.ArrayList;

public class MenuTematico extends Menu {

    String nome;
    ArrayList<Stagioni> stagione;

    public MenuTematico(String nome, ArrayList<Stagioni> stagione, ArrayList<Piatto> piatti) {
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

    public ArrayList<Stagioni> getStagione() {
        return stagione;
    }

    public void setStagione(ArrayList<Stagioni> stagione) {
        this.stagione = stagione;
    }

    public String stampa() {
        return "----------" + nome.toUpperCase() + "----------" +
                "\n Disponibilita': " + stagione + "\n" +
                "Piatti presenti nel menu: " + super.toString();

    }

    @Override
    public String toString() {
        return nome;
    }
}
