package ristorante;

import java.util.ArrayList;
import java.util.HashMap;

public class Gestore extends Utente {

    // parametri di configurazione del ristorante
    private int caricoLavoroPersona;
    private int numeroPostiSedere;
    private HashMap<String, Double> bevande;
    private HashMap<String, Double> generiExtra;
    private double consumoProCapiteBevande;
    private double consumoProCapiteGeneriExtra;
    private HashMap<String, String> piattoRicetta;
    private HashMap<String, Menu> menu;

    // costruttore
    public Gestore(String nome, String cognome, String username, String password) {
        super(nome, cognome, username, password);
        this.bevande = new HashMap<String, Double>();
        this.generiExtra = new HashMap<String, Double>();
        this.piattoRicetta = new HashMap<String, String>();
        this.menu = new HashMap<String, Menu>();
    }

    // getter e setter per i parametri di configurazione del ristorante
    public int getCaricoLavoroPersona() {
        return caricoLavoroPersona;
    }

    public void setCaricoLavoroPersona(int caricoLavoroPersona) {
        this.caricoLavoroPersona = caricoLavoroPersona;
    }

    public int getNumeroPostiSedere() {
        return numeroPostiSedere;
    }

    public void setNumeroPostiSedere(int numeroPostiSedere) {
        this.numeroPostiSedere = numeroPostiSedere;
    }

    public HashMap<String, Double> getBevande() {
        return bevande;
    }

    public void setBevande(HashMap<String, Double> bevande) {
        this.bevande = bevande;
    }

    public HashMap<String, Double> getGeneriExtra() {
        return generiExtra;
    }

    public void setGeneriExtra(HashMap<String, Double> generiExtra) {
        this.generiExtra = generiExtra;
    }

    public double getConsumoProCapiteBevande() {
        return consumoProCapiteBevande;
    }

    public void setConsumoProCapiteBevande(double consumoProCapiteBevande) {
        this.consumoProCapiteBevande = consumoProCapiteBevande;
    }

    public double getConsumoProCapiteGeneriExtra() {
        return consumoProCapiteGeneriExtra;
    }

    public void setConsumoProCapiteGeneriExtra(double consumoProCapiteGeneriExtra) {
        this.consumoProCapiteGeneriExtra = consumoProCapiteGeneriExtra;
    }

    public HashMap<String, String> getPiattoRicetta() {
        return piattoRicetta;
    }

    public void setPiattoRicetta(HashMap<String, String> piattoRicetta) {
        this.piattoRicetta = piattoRicetta;
    }

    public HashMap<String, Menu> getMenu() {
        return menu;
    }

    public void setMenu(HashMap<String, Menu> menu) {
        this.menu = menu;
    }

    // metodo per inizializzare i parametri di configurazione del ristorante
    public void inizializzaParametriRistorante(int caricoLavoroPersona, int numeroPostiSedere,
                                               HashMap<String, Double> bevande,


