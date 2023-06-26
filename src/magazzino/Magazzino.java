package magazzino;

import letturaFile.Xml;

import java.util.HashMap;
import java.util.Map;

public class Magazzino {

    HashMap<String, Double> scorta;

    /*
    Precondizione: nessuna.
    Postcondizione: al termine del metodo, l'attributo scorta viene inizializzato con i dati letti dal metodo Xml.leggiMerci().
     */

    public Magazzino() {
        scorta = Xml.leggiMerci();
    }

    /*
    Precondizione: la variabile scorta deve contenere un riferimento valido a un oggetto HashMap<String, Double>.
    Postcondizione: l'attributo scorta viene aggiornato con il nuovo valore fornito come argomento.
     */

    public void setScorta(HashMap<String, Double> scorta) {
        this.scorta = scorta;
    }

    /*
    Precondizione: nessuna.
    Postcondizione: viene restituito il valore dell'attributo scorta.
     */

    public HashMap<String, Double> getScorta() {
        return scorta;
    }

    /*
    Precondizione: nessuna.
    Postcondizione: viene restituita una stringa che rappresenta le merci rimanenti nel magazzino, ottenuta attraverso
        l'iterazione degli elementi dell'attributo scorta e la concatenazione delle loro chiavi e valori.
     */

    @Override
    public String toString() {
        String da_concatenare = "";
        for (Map.Entry<String, Double> entry : scorta.entrySet()) {
            da_concatenare += "\n\t" + entry.getKey() + " = " + entry.getValue() + "Kg";
        }
        return "Merci rimanenti nel magazzino: " +
                da_concatenare;
    }
}
