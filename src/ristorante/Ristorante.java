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
        this.bevande_persona = FileTesto.leggiBevande("Bevande.txt");
        this.generi_extra_persona = FileTesto.leggiGeneri("Generi.txt");
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

    public double getCarico_lavoro_persona() {
        return carico_lavoro_persona;
    }

    public void setCarico_lavoro_persona(double carico_lavoro_persona) {
        this.carico_lavoro_persona = carico_lavoro_persona;
    }

    public double getCarico_lavoro_ristorante() {
        return carico_lavoro_ristorante;
    }

    public void setCarico_lavoro_ristorante(double carico_lavoro_ristorante) {
        this.carico_lavoro_ristorante = carico_lavoro_ristorante;
    }

    public HashMap<Bevanda, Double> getBevande_persona() {
        return bevande_persona;
    }

    public void bevanda_persona() {
        for (Bevanda bevanda : this.bevande_persona.keySet()){
            this.bevande_persona.put(bevanda, qnt);
        }
    }

    public HashMap<GeneriExtra, Double> getGeneri_extra_persona() {
        return generi_extra_persona;
    }

    public double getQnt() {
        return qnt;
    }

    public void setQnt(double qnt) {
        this.qnt = qnt;
    }

    public void setGeneri_extra_persona() {
        for (GeneriExtra genere : this.generi_extra_persona.keySet()){
                this.generi_extra_persona.put(genere, qnt);
        }
    }

    @Override
    public String toString() {
        return "\n-----------RISTORANTE: " + nome + "-----------" +
                "\n - nome: " + nome +
                "\n - posti disponibili: " + num_posti;
    }
}
