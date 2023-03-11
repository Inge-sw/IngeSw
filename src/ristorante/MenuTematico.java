package ristorante;

import resto.Stagioni;

public class MenuTematico extends Menu{

    String nome;
    Stagioni stagione;

    public MenuTematico(String nome, Stagioni stagione) {
        super();
        this.nome = nome;
        this.stagione = stagione;
    }
}
