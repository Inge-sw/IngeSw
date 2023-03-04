package ristorante;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ristorante {
    private int caricoLavoroPerPersona;
    private int postiSedereDisponibili;
    private List<String> bevande;
    private List<String> generiExtra;
    private int consumoProCapiteBevande;
    private int consumoProCapiteGeneriExtra;
    private Map<String, String> piattoRicetta;
    private Map<String, Menu> menu;
    private List<Prenotazione> prenotazioni;
    private Map<String, Integer> magazzino;
    private Map<String, Integer> giacenzeIniziali;
    private LocalDate dataCorrente;

    public Ristorante(int caricoLavoroPerPersona, int postiSedereDisponibili, List<String> bevande,
                      List<String> generiExtra, int consumoProCapiteBevande, int consumoProCapiteGeneriExtra,
                      Map<String, String> piattoRicetta, Map<String, Menu> menu) {
        this.caricoLavoroPerPersona = caricoLavoroPerPersona;
        this.postiSedereDisponibili = postiSedereDisponibili;
        this.bevande = bevande;
        this.generiExtra = generiExtra;
        this.consumoProCapiteBevande = consumoProCapiteBevande;
        this.consumoProCapiteGeneriExtra = consumoProCapiteGeneriExtra;
        this.piattoRicetta = piattoRicetta;
        this.menu = menu;
        this.prenotazioni = new ArrayList<>();
        this.magazzino = new HashMap<>();
        this.giacenzeIniziali = new HashMap<>();
        this.dataCorrente = LocalDate.now();
    }

    public void inizializzaMagazzino(Map<String, Integer> giacenzeIniziali) {
        this.magazzino.putAll(giacenzeIniziali);
        this.giacenzeIniziali.putAll(giacenzeIniziali);
    }

    public void aggiornaMagazzino(String prodotto, int quantita) {
        int vecchiaQuantita = magazzino.getOrDefault(prodotto, 0);
        int nuovaQuantita = vecchiaQuantita + quantita;
        magazzino.put(prodotto, nuovaQuantita);
    }

    public void raccogliPrenotazione(Prenotazione prenotazione) throws EccezionePrenotazione {
        // Verifica se la prenotazione è scaduta
        if (prenotazione.getData().isBefore(dataCorrente)) {
            throw new EccezionePrenotazione("La prenotazione è scaduta");
        }

        // Verifica se il numero totale di coperti prenotati supera il numero di posti a sedere disponibili
        int numeroCopertiPrenotati = prenotazione.getNumeroCoperti();
        int numeroCopertiTotaliPrenotati = prenotazioni.stream()
                .filter(p -> p.getData().equals(prenotazione.getData()))
                .mapToInt(Prenotazione::getNumeroCoperti)
                .sum();
        int postiDisponibili = postiSedereDisponibili - numeroCopertiTotaliPrenotati;
        if (numeroCopertiPrenotati > postiDisponibili) {
            throw new EccezionePrenotazione("Il numero di coperti prenotati supera

