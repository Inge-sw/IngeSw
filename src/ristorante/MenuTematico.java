package ristorante;

import resto.Stagioni;

import java.util.ArrayList;

public class MenuTematico extends Menu {

    String nome;
    ArrayList<Stagioni> stagione;

    /*
    L'attributo piatti contiene un elenco non nullo di oggetti Piatto.
    L'attributo nome è una stringa non nulla e non vuota.
    L'attributo stagione contiene un elenco non nullo di oggetti Stagioni.
    Questo significa che ogni istanza di MenuTematico deve soddisfare queste condizioni per garantire la coerenza interna della classe.
     */

    public MenuTematico(String nome, ArrayList<Stagioni> stagione, ArrayList<Piatto> piatti) {
        super(piatti);
        this.nome = nome;
        this.stagione = stagione;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce il valore dell'attributo nome.
     */

    public String getNome() {
        return nome;
    }

    /*
    recondizioni: nome è una stringa non nulla e non vuota.
    Postcondizioni: Imposta il valore dell'attributo nome con il valore specificato da nome.
     */

    public void setNome(String nome) {
        this.nome = nome;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce l'oggetto ArrayList<Stagioni> dell'attributo stagione.
     */

    public ArrayList<Stagioni> getStagione() {
        return stagione;
    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce una stringa formattata contenente il nome del menu in maiuscolo, la disponibilità stagionale e l'elenco dei piatti presenti nel menu.
     */

    public String stampa() {
        return "----------" + nome.toUpperCase() + "----------" +
                "\n Disponibilita': " + stagione + "\n" +
                "Piatti presenti nel menu: " + super.toString();

    }

    /*
    Precondizioni: Nessuna.
    Postcondizioni: Restituisce il valore dell'attributo nome.
     */

    @Override
    public String toString() {
        return nome;
    }
}
