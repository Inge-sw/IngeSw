package prenotazione;

public class Prenotazione {
    private int numeroCoperti;
    private LocalDate dataPrenotazione;
    private List<Piatto> piattiPrenotati;
    private String nomeCliente;

    public Prenotazione(int numeroCoperti, LocalDate dataPrenotazione, List<Piatto> piattiPrenotati, String nomeCliente) {
        this.numeroCoperti = numeroCoperti;
        this.dataPrenotazione = dataPrenotazione;
        this.piattiPrenotati = piattiPrenotati;
        this.nomeCliente = nomeCliente;
    }

    public int getNumeroCoperti() {
        return numeroCoperti;
    }

    public void setNumeroCoperti(int numeroCoperti) {
        this.numeroCoperti = numeroCoperti;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public List<Piatto> getPiattiPrenotati() {
        return piattiPrenotati;
    }

    public void setPiattiPrenotati(List<Piatto> piattiPrenotati) {
        this.piattiPrenotati = piattiPrenotati;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public double getPrezzoTotale() {
        double prezzoTotale = 0.0;
        for (Piatto p : piattiPrenotati) {
            prezzoTotale += p.getPrezzo();
        }
        return prezzoTotale;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "numeroCoperti=" + numeroCoperti +
                ", dataPrenotazione=" + dataPrenotazione +
                ", piattiPrenotati=" + piattiPrenotati +
                ", nomeCliente='" + nomeCliente + '\'' +
                '}';
    }
}

