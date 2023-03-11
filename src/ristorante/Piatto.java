package ristorante;

import resto.Stagioni;

public class Piatto {
    private String nome;
    private Stagioni stagione;
    private Ricetta ricetta;
    private double carico_lavoro;

    public Piatto(Ricetta ricetta) {
        this.nome = ricetta.getNome();
        this.stagione = ricetta.getStagione();
        this.ricetta = ricetta;
        this.carico_lavoro = ricetta.getCarico_lavoro_porzione();
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

    public Ricetta getRicetta() {
        return ricetta;
    }

    public double getCarico_lavoro() {
        return carico_lavoro;
    }

    @Override
    public String toString() {
        return "Piatto{" +
                "nome='" + nome + '\'' +
                ", stagione=" + stagione +
                ", ricetta=" + ricetta +
                ", carico_lavoro=" + carico_lavoro +
                '}';
    }
}
