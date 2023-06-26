package magazzino;

import prenotazione.Prenotazione;
import letturaFile.FileTesto;
import input.InputDati;
import letturaFile.Xml;
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

    /*
    Prima dell'esecuzione del costruttore "Magazziniere", la variabile "magazzino" è inizializzata a null o contiene un riferimento valido a un oggetto "Magazzino".
    Dopo l'esecuzione del costruttore "Magazziniere", la variabile "magazzino" contiene un nuovo oggetto "Magazzino" correttamente inizializzato.
    L'invariante garantisce che, al termine del costruttore "Magazziniere", la variabile "magazzino" sia sempre in uno stato valido e pronto per essere utilizzata.
     */
    public Magazziniere() {
        magazzino = new Magazzino();
    }

    /*Precondizioni:
    Il parametro gestore non deve essere nullo.
    La classe Gestore deve avere un metodo getRicettario() che restituisce un oggetto non nullo.
    La classe Ricettario deve avere un metodo getRicette() che restituisce un elenco di oggetti Ricetta.
    La classe Ricetta deve avere un metodo getIngredienti() che restituisce un elenco di oggetti Ingrediente.

    Postcondizioni:
    L'array ingredienti deve contenere i nomi degli ingredienti ottenuti da tutte le ricette presenti nel ricettario.
    Viene chiamato il metodo checkIngredienti().*/

    public void ottieniIngredienti(Gestore gestore) {
        ArrayList<Ricetta> ricette = gestore.getRicettario().getRicette();

        for (Ricetta ricetta : ricette) {
            for (Ingrediente ingrediente : ricetta.getIngredienti()) {
                ingredienti.add(ingrediente.getNome());
            }
        }
        checkIngredienti();
    }
    /*Precondizioni:
    La variabile magazzino deve essere inizializzata e deve avere un metodo getScorta() che restituisce una mappa non nulla.
    La variabile ingredienti deve essere un elenco non nullo.

    Postcondizioni:
    Se un ingrediente presente in ingredienti non è presente nella mappa scorta, viene chiamato il metodo Xml.aggiungiMerce per aggiungere l'ingrediente al magazzino.*/

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

        HashMap<Bevanda, Double> bevande = FileTesto.leggiBevande();
        HashMap<GeneriExtra, Double> generi = FileTesto.leggiGeneri();

        for (Map.Entry<Bevanda, Double> entry : bevande.entrySet()) {
            if (!scorta.containsKey(entry.getKey().getNome().toLowerCase())) {
                Xml.aggiungiMerce(entry.getKey().getNome().toLowerCase(), "L");
            }
        }

        for (Map.Entry<GeneriExtra, Double> entry : generi.entrySet()) {
            if (!scorta.containsKey(entry.getKey().getNome().toLowerCase())) {
                Xml.aggiungiMerce(entry.getKey().getNome().toLowerCase(), "Kg");
            }
        }

    }

    /*Precondizioni:
    La variabile magazzino deve essere inizializzata e deve avere un metodo getScorta() e setScorta() che gestiscono la mappa delle scorte.
    Le variabili bevande e generi devono essere inizializzate e devono contenere le corrispondenti mappe di bevande e generi extra.

    Postcondizioni:
    Vengono acquisite nuove merci dall'utente e vengono aggiornate le scorte nel magazzino.
    Viene chiamato il metodo Xml.aggiornaMerce per aggiornare il file XML delle merci con le scorte aggiornate.*/
    public void acquistaMerci() {
        boolean exit;
        HashMap<String, Double> scorte = magazzino.getScorta();

        HashMap<Bevanda, Double> bevande = FileTesto.leggiBevande();
        HashMap<GeneriExtra, Double> generi = FileTesto.leggiGeneri();

        for (String s : ingredienti) {
            System.out.println(s);
        }

        for (Map.Entry<Bevanda, Double> entry : bevande.entrySet()) {
            System.out.println(entry.getKey());
        }
        for (Map.Entry<GeneriExtra, Double> entry : generi.entrySet()) {
            System.out.println(entry.getKey());
        }

        do {
            String merce = InputDati.leggiStringaNonVuota("Inserisci la merce da acquistare: ").toLowerCase();

            if (scorte.containsKey(merce)) {
                double quantita = InputDati.leggiInteroNonNegativo("Quantita': ?");
                if (scorte.containsKey(merce))
                    scorte.replace(merce, scorte.get(merce) + quantita);
                else
                    scorte.put(merce, quantita);
            } else
                System.out.println("Merce non presenta nella lista");

            exit = InputDati.yesOrNo("Uscire?");
        } while (!exit);

        Xml.aggiornaMerce(scorte);
        magazzino.setScorta(Xml.leggiMerci());

    }

    /*Precondizioni:
    La variabile magazzino deve essere inizializzata e deve avere un metodo getScorta() e setScorta() che gestiscono la mappa delle scorte.

    Postcondizioni:
    Vengono rimossi quantità specificate di merci dalla scorta del magazzino.
    Viene chiamato il metodo Xml.aggiornaMerce per aggiornare il file XML delle merci con le scorte aggiornate.*/

    public void rimuoviMerci() {
        boolean exit, presente;
        HashMap<String, Double> scorte_positive = new HashMap<>();

        for (Map.Entry<String, Double> entry : magazzino.getScorta().entrySet()) {
            if (entry.getValue() > 0) {
                scorte_positive.put(entry.getKey(), entry.getValue());
                System.out.println(entry.getKey() + "-->" + entry.getValue());
            }
        }

        do {
            String merce = InputDati.leggiStringaNonVuota("Inserisci la merce da rimuovere: ").toLowerCase();

            if (magazzino.getScorta().containsKey(merce)) {
                int quantita = InputDati.leggiInteroNonNegativo("Quantità: ?");
                if (scorte_positive.get(merce) - quantita < 0)
                    System.out.println("Impossibile rimuovere l'ingrediente");
                else
                    scorte_positive.replace(merce, scorte_positive.get(merce) - quantita);
            } else
                System.out.println("Merce non presenta nella lista");

            exit = InputDati.yesOrNo("Uscire?");
        } while (!exit);

        for (Map.Entry<String, Double> entry : scorte_positive.entrySet()) {
            magazzino.getScorta().replace(entry.getKey(), entry.getValue());
        }

        Xml.aggiornaMerce(magazzino.getScorta());
    }

    /*Precondizioni:
    La variabile prenotazioni deve essere un elenco non nullo di oggetti Prenotazione.

    Postcondizioni:
    Viene calcolato l'elenco delle merci necessarie in base alle prenotazioni del giorno corrente.
    Viene creato un elenco della spesa (mappa) con i nomi degli ingredienti e le relative quantità richieste.
    Viene restituita una stringa contenente l'elenco della spesa.*/

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

    /*Precondizioni:
    La variabile prenotazioni deve essere un elenco non nullo di oggetti Prenotazione.

    Postcondizioni:
    Vengono calcolate le merci necessarie in base alle prenotazioni del giorno corrente.
    Le merci necessarie vengono memorizzate nella mappa merci_neccessari.*/

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

    /*Precondizioni:
    La variabile ingredienti deve essere un elenco non nullo di oggetti Ingrediente.
    Le variabili quantita e moltiplicatore devono essere valori numerici validi

    Postcondizioni:
    Viene verificato se ciascun ingrediente presente nella lista ingredienti è presente nella mappa merci_neccessari.
    Se l'ingrediente è già presente nella mappa, la sua quantità viene aggiornata in base alla quantità richiesta.
    Se l'ingrediente non è presente nella mappa, viene aggiunto con la quantità richiesta moltiplicata per il moltiplicatore.*/

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
