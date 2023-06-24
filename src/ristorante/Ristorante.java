package ristorante;

import resto.FileTesto;

import java.util.HashMap;

public class Ristorante {

    private String nome;
    private int num_posti;
    private double carico_lavoro_persona;
    private double carico_lavoro_ristorante;
    private HashMap<Bevanda, Double> bevande_persona;
    private double qnt;
    private HashMap<GeneriExtra, Double> generi_extra_persona;

    public Ristorante(String nome, int num_posti) {
        this.nome = nome;
        this.num_posti = num_posti;
        this.bevande_persona = FileTesto.leggiBevande();
        this.generi_extra_persona = FileTesto.leggiGeneri();
        this.qnt = 5;
        bevanda_persona();
        setGeneri_extra_persona();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum_posti() {
        return num_posti;
    }

    public void setNum_posti(int num_posti) {
        this.num_posti = num_posti;
    }

    public double getCarico_lavoro_ristorante() {
        return carico_lavoro_ristorante;
    }

    public void bevanda_persona() {
        for (Bevanda bevanda : this.bevande_persona.keySet()) {
            this.bevande_persona.put(bevanda, qnt);
        }
    }

    public void setGeneri_extra_persona() {
        this.generi_extra_persona.replaceAll((g, v) -> qnt);
    }

    @Override
    public String toString() {
        return "\n-----------RISTORANTE: " + nome + "-----------" +
                "\n - nome: " + nome +
                "\n - posti disponibili: " + num_posti;
    }

    public boolean checkProdotto(Prodotto da_aggiungere) {
        if (isBevanda(da_aggiungere)) {
            return this.bevande_persona.containsKey(da_aggiungere);
        } else {
            return this.generi_extra_persona.containsKey(da_aggiungere);
        }
    }

    private boolean isBevanda(Prodotto da_aggiungere) {
        return da_aggiungere instanceof Bevanda;
    }

    private boolean isGenere(Prodotto da_aggiungere) {
        return da_aggiungere instanceof GeneriExtra;
    }

    public void addProdotto(Prodotto da_aggiungere) {
        if (isBevanda(da_aggiungere)) {
            this.bevande_persona.put((Bevanda) da_aggiungere, qnt);
        } else if (isGenere(da_aggiungere)) {
            this.generi_extra_persona.put((GeneriExtra) da_aggiungere, qnt);
        }
    }

    public void visualizza(String discriminante) {
        if (discriminante.equalsIgnoreCase("BEVANDE")) {
            this.bevande_persona.forEach((key, value) -> System.out.println("- " + key.getNome() + " " + value + " " + key.getU_misura()));
        } else if (discriminante.equalsIgnoreCase("GENERI")) {
            this.generi_extra_persona.forEach((key, value) -> System.out.println("- " + key.getNome() + " " + value + " " + key.getU_misura()));
        }
    }
}
