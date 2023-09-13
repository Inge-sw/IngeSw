package letturaFile;

import ristorante.Prodotto;

import java.util.HashMap;

public class Controller {
    FileTestoRepository repo;

    public Controller(FileTestoRepository repo) {
        this.repo = repo;
    }

    public HashMap<Prodotto, Double> leggi() {
        return repo.leggi();
    }

    public void aggiungi(Prodotto prod, String filename) {
        repo.aggiungi(prod, filename);
    }

    public void setRepo(FileTestoRepository repo2) {
        this.repo =repo2;
    }
}
