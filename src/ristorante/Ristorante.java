package ristorante;

import letturaFile.FileTesto;

import java.util.HashMap;

public class Ristorante {

    private String nome;
    private int num_posti;
    private double carico_lavoro_persona;
    private double carico_lavoro_ristorante;
    private HashMap<Bevanda, Double> bevande_persona;
    private double qnt;
    private HashMap<GeneriExtra, Double> generi_extra_persona;

    /*
    Precondizione: nessuna.
    Postcondizione: dopo l'esecuzione del costruttore Ristorante, viene creato un nuovo oggetto Ristorante con i parametri nome e num_posti.
        Gli attributi bevande_persona e generi_extra_persona vengono inizializzati leggendo i dati da file tramite i metodi FileTesto.leggiBevande() e FileTesto.leggiGeneri().
        L'attributo qnt viene impostato a 5. Vengono quindi chiamati i metodi bevanda_persona() e setGeneri_extra_persona().
     */

    public Ristorante(String nome, int num_posti) {
        this.nome = nome;
        this.num_posti = num_posti;
        this.bevande_persona = FileTesto.leggiBevande();
        this.generi_extra_persona = FileTesto.leggiGeneri();
        this.qnt = 5;
        bevanda_persona();
        setGeneri_extra_persona();
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce il valore corrente della variabile nome dell'oggetto.*/

    public String getNome() {
        return nome;
    }

    /*Precondizioni: nome è una stringa non nulla.
    Postcondizioni: Imposta il valore della variabile nome dell'oggetto con il valore fornito.*/

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce il valore corrente della variabile num_posti dell'oggetto.*/

    public int getNum_posti() {
        return num_posti;
    }

    /*Precondizioni: num_posti è un intero non negativo.
    Postcondizioni: Imposta il valore della variabile num_posti dell'oggetto con il valore fornito.*/

    public void setNum_posti(int num_posti) {
        this.num_posti = num_posti;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Restituisce il valore corrente della variabile carico_lavoro_ristorante dell'oggetto.*/

    public double getCarico_lavoro_ristorante() {
        return carico_lavoro_ristorante;
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Imposta la quantità qnt per ciascuna bevanda nella mappa bevande_persona dell'oggetto.*/

    public void bevanda_persona() {
        for (Bevanda bevanda : this.bevande_persona.keySet()) {
            this.bevande_persona.put(bevanda, qnt);
        }
    }

    /*Precondizioni: Nessuna.
    Postcondizioni: Imposta la quantità qnt per ciascun genere extra nella mappa generi_extra_persona dell'oggetto.*/

    public void setGeneri_extra_persona() {
        this.generi_extra_persona.replaceAll((g, v) -> qnt);
    }

    /*Precondizioni: da_aggiungere è un oggetto di tipo Prodotto non nullo.
    Postcondizioni: Restituisce true se da_aggiungere è presente come chiave nella mappa bevande_persona o generi_extra_persona dell'oggetto,
        a seconda del tipo di prodotto. Altrimenti, restituisce false.*/

    public boolean checkProdotto(Prodotto da_aggiungere) {
        if (isBevanda(da_aggiungere)) {
            return this.bevande_persona.containsKey(da_aggiungere);
        } else {
            return this.generi_extra_persona.containsKey(da_aggiungere);
        }
    }

    /*Precondizioni: da_aggiungere è un oggetto di tipo Prodotto non nullo.
    Postcondizioni: Restituisce true se da_aggiungere è un'istanza di Bevanda. Altrimenti, restituisce false.*/

    private boolean isBevanda(Prodotto da_aggiungere) {
        return da_aggiungere instanceof Bevanda;
    }

    /*Precondizioni: da_aggiungere è un oggetto di tipo Prodotto non nullo.
    Postcondizioni: Restituisce true se da_aggiungere è un'istanza di GeneriExtra. Altrimenti, restituisce false.*/

    private boolean isGenere(Prodotto da_aggiungere) {
        return da_aggiungere instanceof GeneriExtra;
    }

    /*Precondizioni: da_aggiungere è un oggetto di tipo Prodotto non nullo.
    Postcondizioni: Aggiunge da_aggiungere come chiave nella mappa bevande_persona o generi_extra_persona dell'oggetto,
        a seconda del tipo di prodotto. Imposta la quantità qnt come valore corrispondente.*/

    public void addProdotto(Prodotto da_aggiungere) {
        if (isBevanda(da_aggiungere)) {
            this.bevande_persona.put((Bevanda) da_aggiungere, qnt);
        } else if (isGenere(da_aggiungere)) {
            this.generi_extra_persona.put((GeneriExtra) da_aggiungere, qnt);
        }
    }

    /*Precondizioni: discriminante è una stringa non nulla.
    Postcondizioni: Visualizza i dettagli delle bevande o dei generi extra presenti nella mappa bevande_persona o generi_extra_persona dell'oggetto,
        a seconda del valore di discriminante. Stampa il nome, la quantità e l'unità di misura di ciascun prodotto.*/

    public void visualizza(String discriminante) {
        if (discriminante.equalsIgnoreCase("BEVANDE")) {
            this.bevande_persona.forEach((key, value) -> System.out.println("- " + key.getNome() + " " + value + " " + key.getU_misura()));
        } else if (discriminante.equalsIgnoreCase("GENERI")) {
            this.generi_extra_persona.forEach((key, value) -> System.out.println("- " + key.getNome() + " " + value + " " + key.getU_misura()));
        }
    }

    @Override
    public String toString() {
        return "\n-----------RISTORANTE: " + nome + "-----------" +
                "\n - nome: " + nome +
                "\n - posti disponibili: " + num_posti;
    }
}
