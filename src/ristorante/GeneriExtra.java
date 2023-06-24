package ristorante;

import resto.Misura;

public class GeneriExtra extends Prodotto {

    /*
    * Precondizione: Deve essere passata una stringa "nome" non vuota come argomento.
    * Postcondizione: Crea un nuovo oggetto di tipo "GeneriExtra" con il nome specificato
    * e la misura predefinita "PEZZI" ereditata dalla classe "Prodotto".
    * */
    public GeneriExtra(String nome) {
        super(nome, Misura.PEZZI);
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Restituisce il codice hash calcolato dalla classe padre "Prodotto" per l'oggetto "GeneriExtra".
    * */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /*
    * Precondizione: Deve essere passato un oggetto "obj" come argomento.
    * ostcondizione: Restituisce true se l'oggetto "obj" è uguale all'oggetto "GeneriExtra" corrente,
    * utilizzando il metodo "equals" della classe padre "Prodotto". Restituisce false altrimenti.
    * */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
