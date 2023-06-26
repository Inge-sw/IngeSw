package ristorante;

import resto.Stagioni;

import java.util.ArrayList;

public class Piatto implements Prenotabile {
    private String nome;
    private ArrayList<Stagioni> stagione;
    private Ricetta ricetta;
    private double carico_lavoro;

    /*
    Precondizione: ricetta è un oggetto valido di tipo Ricetta.
    Postcondizione: dopo l'esecuzione del costruttore Piatto, viene creato un nuovo oggetto Piatto. Gli attributi nome,
        stagione, ricetta e carico_lavoro vengono inizializzati con i valori corrispondenti presi dalla ricetta fornita come parametro.
     */

    public Piatto(Ricetta ricetta) {
        this.nome = ricetta.getNome();
        this.stagione = ricetta.getStagione();
        this.ricetta = ricetta;
        this.carico_lavoro = ricetta.getCarico_lavoro_porzione();
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce il nome dell'oggetto come una stringa.
    */

    public String getNome() {
        return nome;
    }

    /*
    Precondizioni: nome è una stringa non nulla.
    Postcondizioni: Imposta il nome dell'oggetto con il valore specificato dal parametro nome.
    */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce l'oggetto di tipo Ricetta associato a questo oggetto.
    */

    public Ricetta getRicetta() {
        return ricetta;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce il valore del carico di lavoro come un numero decimale.
     */

    public double getCarico_lavoro() {
        return carico_lavoro;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce una rappresentazione testuale dell'oggetto, che corrisponde al suo nome come una stringa.
     */

    @Override
    public String toString() {
        return nome;
    }

}
