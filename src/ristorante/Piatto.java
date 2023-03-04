package ristorante;

public class Piatto {
    private String nome;
    private String descrizione;
    private List<Ingrediente> ingredienti;
    private boolean vegano;
    private boolean senzaGlutine;
    private double prezzo;

    public Piatto(String nome, String descrizione, List<Ingrediente> ingredienti, boolean vegano,
                  boolean senzaGlutine, double prezzo) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.ingredienti = ingredienti;
        this.vegano = vegano;
        this.senzaGlutine = senzaGlutine;
        this.prezzo = prezzo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public boolean isVegano() {
        return vegano;
    }

    public void setVegano(boolean vegano) {
        this.vegano = vegano;
    }

    public boolean isSenzaGlutine() {
        return senzaGlutine;
    }

    public void setSenzaGlutine(boolean senzaGlutine) {
        this.senzaGlutine = senzaGlutine;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    @Override
    public String toString() {
        String veganoStr = vegano ? "Vegano" : "Non Vegano";
        String senzaGlutineStr = senzaGlutine ? "Senza Glutine" : "Con Glutine";

        return String.format("%s (%s, %s) - %s - Prezzo: %.2f euro", nome, veganoStr, senzaGlutineStr,
                descrizione, prezzo);
    }
}

