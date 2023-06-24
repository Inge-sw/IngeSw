package magazzino;

import prenotazione.Prenotazione;
import resto.InputDati;
import resto.Xml;
import ristorante.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Magazziniere {
    Magazzino magazzino;
    ArrayList<String> ingredienti = new ArrayList<>();
    HashMap<String, Double> lista_della_spesa = new HashMap<>();
    HashMap<String, Double> merci_neccessari = new HashMap<>();

    public Magazziniere() {
        magazzino = new Magazzino();
    }

    public void ottieniIngredienti(Gestore gestore) {
        ArrayList<Ricetta> ricette = gestore.getRicettario().getRicette();

        for (Ricetta ricetta : ricette) {
            for (Ingrediente ingrediente : ricetta.getIngredienti()) {
                ingredienti.add(ingrediente.getNome());
            }
        }
        checkIngredienti();
    }

    public void checkIngredienti() {
        boolean presente;
        HashMap<String, Double> scorta = magazzino.getScorta();
        for (String s : ingredienti) {
            presente = false;
            String nome_da_aggiungere = s.toLowerCase();
            for (Map.Entry<String, Double> entry : scorta.entrySet()) {
                String nome_ingrediente = entry.getKey();

                if (nome_da_aggiungere.equalsIgnoreCase(nome_ingrediente)) {
                    presente = true;
                    break;
                }
            }
            if (!presente) Xml.aggiungiMerce(nome_da_aggiungere, "kg");
        }

    }

    public void acquistaMerci() {
        boolean exit, presente;
        HashMap<String, Double> scorte = magazzino.getScorta();

        for (String s : ingredienti) {
            System.out.println(s);
        }
        do {
            presente = false;
            String merce = InputDati.leggiStringaNonVuota("Inserisci la merce da acquistare: ").toLowerCase();


            for (String s : ingredienti) {
                if (s.equalsIgnoreCase(merce)) {
                    presente = true;
                    break;
                }
            }
            if (presente) {
                double quantita = InputDati.leggiInteroNonNegativo("Quantità: ?");
                if (scorte.containsKey(merce))
                    scorte.replace(merce, scorte.get(merce) + quantita);
                else
                    scorte.put(merce, quantita);
            } else
                System.out.println("Merce non presenta nella lista");

            exit = InputDati.yesOrNo("Uscire?");
        } while (!exit);

        Xml.aggiornaMerce(scorte);

    }

    public void rimuoviMerci() {
        boolean exit, presente;
        HashMap<String, Double> scorte = new HashMap<>();

        for (Map.Entry<String, Double> entry : magazzino.getScorta().entrySet()) {
            if (entry.getValue() > 0) {
                scorte.put(entry.getKey(), entry.getValue());
                System.out.println(entry.getKey() + "-->" + entry.getValue());
            }
        }

        do {
            presente = false;
            String merce = InputDati.leggiStringaNonVuota("Inserisci la merce da rimuovere: ").toLowerCase();


            for (Map.Entry<String, Double> entry : magazzino.getScorta().entrySet()) {
                if (entry.getKey().equalsIgnoreCase(merce)) {
                    presente = true;
                    break;
                }
            }
            if (presente) {
                int quantita = InputDati.leggiInteroNonNegativo("Quantità: ?");
                if (scorte.get(merce) - quantita < 0)
                    System.out.println("Impossibile rimuovere l'ingrediente");
                else
                    scorte.replace(merce, scorte.get(merce) - quantita);
            } else
                System.out.println("Merce non presenta nella lista");

            exit = InputDati.yesOrNo("Uscire?");
        } while (!exit);

        Xml.aggiornaMerce(scorte);
    }


    public void creaListaSpesa(ArrayList<Prenotazione> prenotazioni) {
        calcolaMerciNecessari(prenotazioni);

        for (Map.Entry<String, Double> entry : merci_neccessari.entrySet()) {
            String nome_ingrediente = entry.getKey();
            double quantita_richiesta;
            if (magazzino.getScorta().get(nome_ingrediente) == null)
                quantita_richiesta = entry.getValue();
            else
                quantita_richiesta = entry.getValue() - magazzino.getScorta().get(nome_ingrediente);
            if (quantita_richiesta < 0)
                quantita_richiesta = 0;
            lista_della_spesa.put(entry.getKey(), quantita_richiesta);

        }
        if (lista_della_spesa == null)
            System.out.println("Lista della spesa vuota , non ci sono prenotazioni per il giorno corrente");
        else
            System.out.println(stampaListaSpesa(lista_della_spesa));

    }

    public void calcolaMerciNecessari(ArrayList<Prenotazione> prenotazioni) {
        LocalDate today = LocalDate.now();

        for (Prenotazione prenotazione : prenotazioni) {
            if (prenotazione.getData().equals(today)) {
                HashMap<Prenotabile, Integer> piatti = prenotazione.getLista_prenotazioni_piatti();

                for (Map.Entry<Prenotabile, Integer> entry : piatti.entrySet()) {
                    String nome_prenotabile = entry.getKey().toString();
                    int quantita = entry.getValue();

                    Ricetta ricetta = Ricettario.getRicettaByNome(nome_prenotabile);

                    if (ricetta != null) {
                        checkMerciGiornaliero(ricetta.getIngredienti(), quantita, ricetta.getPorzioni());
                    } else {
                        ArrayList<MenuTematico> MenuTematici = Xml.leggiMenuTematico();
                        for (MenuTematico m : MenuTematici) {
                            if (m.getNome().equalsIgnoreCase(nome_prenotabile)) {
                                ArrayList<Piatto> piatti_del_menu = m.getPiatti();
                                for (Piatto piatto : piatti_del_menu) {
                                    Ricetta ricetta_del_piatto = piatto.getRicetta();
                                    checkMerciGiornaliero(ricetta_del_piatto.getIngredienti(), quantita, ricetta_del_piatto.getPorzioni());
                                }
                            }
                        }
                    }
                }

            }
        }

    }

    public void checkMerciGiornaliero(ArrayList<Ingrediente> ingredienti, int quantita, int moltiplicatore) {
        for (Ingrediente ingrediente : ingredienti) {
            String nome_ingrediente = ingrediente.getNome();

            if (merci_neccessari.containsKey(nome_ingrediente))
                merci_neccessari.replace(ingrediente.getNome(), merci_neccessari.get(nome_ingrediente) + ingrediente.getQuantita() * quantita * moltiplicatore);
            else
                merci_neccessari.put(ingrediente.getNome(), ingrediente.getQuantita() * quantita * moltiplicatore);
        }
    }

    public String stampaListaSpesa(HashMap<String, Double> lista_spesa) {
        String da_concatenare = "";

        for (Map.Entry<String, Double> entry : lista_spesa.entrySet()) {
            da_concatenare += "\n\t" + entry.getKey() + " = " + entry.getValue() + " Kg";
        }

        return "Lista della spesa:" + da_concatenare;
    }

    @Override
    public String toString() {
        return magazzino.toString();
    }


}
