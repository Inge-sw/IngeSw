package ristorante;

import java.util.ArrayList;

public class Menu {

    private String nome;
    private ArrayList<Piatto> piatti;

    public Menu(String nome) {
        this.nome = nome;
        this.piatti = new ArrayList<>();
    }

    public void aggiungiPiatto(Piatto piatto) {
        piatti.add(piatto);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Piatto> getPiatti() {
        return piatti;
    }

    public double getCaricoLavoro() {
        double caricoLavoro = 0.0;
        for(Piatto p : piatti) {
            caricoLavoro += p.getCaricoLavoro();
        }
        return caricoLavoro;
    }

    public double getPrezzo() {
        double prezzo = 0.0;
        for(Piatto p : piatti) {
            prezzo += p.getPrezzo();
        }
        return prezzo;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Menu ").append(nome).append("\n");
        for(Piatto p : piatti) {
            sb.append("- ").append(p.getNome()).append(" (").append(p.getCaricoLavoro()).append("): €").append(p.getPrezzo()).append("\n");
        }
        return sb.toString();
    }
}

