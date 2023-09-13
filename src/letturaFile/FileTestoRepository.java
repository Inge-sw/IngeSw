package letturaFile;

import ristorante.GeneriExtra;
import ristorante.Prodotto;

import java.util.HashMap;

public interface FileTestoRepository {
    public HashMap<Prodotto, Double> leggi();

    public void aggiungi(Prodotto prod, String filename);
}
