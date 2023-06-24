package ristorante;

import resto.Misura;

import java.util.Objects;

public class Bevanda extends Prodotto {

    /*
    * Precondizione: Deve essere passata una stringa "nome" non vuota come argomento.
    * Postcondizione: Crea un nuovo oggetto di tipo "Bevanda" con il nome specificato e la misura predefinita "LITRI"
    * ereditata dalla classe "Prodotto".
    * */
    public Bevanda(String nome) {
        super(nome, Misura.LITRI);
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Restituisce il codice hash calcolato dalla classe padre "Prodotto" per l'oggetto "Bevanda".
    * */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /*
    *Precondizione: Deve essere passato un oggetto "obj" come argomento.
    * Postcondizione: Restituisce true se l'oggetto "obj" è uguale all'oggetto "Bevanda" corrente,
    * utilizzando il metodo "equals" della classe padre "Prodotto". Restituisce false altrimenti.
    * */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
