package prenotazione;

import ristorante.Prenotabile;

import java.time.LocalDate;
import java.util.HashMap;

public class Prenotazione {
    private int num_coperti;
    private LocalDate data;
    HashMap<Prenotabile, Integer> lista_prenotazioni;

    public Prenotazione(int num_coperti, LocalDate data) {
        this.num_coperti = num_coperti;
        this.data = data;
        this.lista_prenotazioni = new HashMap<>();
    }

    public int getNum_coperti() {
        return num_coperti;
    }

    public void setNum_coperti(int num_coperti) {
        this.num_coperti = num_coperti;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public HashMap<Prenotabile, Integer> getLista_prenotazioni() {
        return lista_prenotazioni;
    }

    public void addLista_prenotazioni(Prenotabile elem, int qnt) {
        this.lista_prenotazioni.put(elem, Integer.valueOf(qnt));
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "num_coperti=" + num_coperti +
                ", data=" + data +
                ", lista_prenotazioni=" + lista_prenotazioni +
                '}';
    }
}
