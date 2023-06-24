package ristorante;

import input.InputDati;
import letturaFile.FileTesto;
import letturaFile.Xml;
import resto.*;

import java.util.ArrayList;

public class Gestore {

    Ristorante ristorante;
    Ricettario ricettario;
    ArrayList<MenuTematico> menu_tematici;

    public Gestore() {
        avvio();
    }
    /*
    * Precondizione: Nessuna.
    * Postcondizione: Inizializza l'istanza della classe "Gestore"
    * creando un nuovo ristorante, leggendo il ricettario e i menu tematici dal file XML.
    * */
    public void avvio() {
        creaRistorante();
        ricettario = Xml.leggiRicettario();
        menu_tematici = Xml.leggiMenuTematico();
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Stampa le informazioni del ristorante sulla console.
    * */
    public void visulizzaRistorante() {
        System.out.println(ristorante.toString());
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Crea un nuovo oggetto di tipo "Ristorante" chiedendo all'utente
    * di inserire il nome e il numero di posti a sedere.
    * */
    public void creaRistorante() {
        String nome_ristorante = InputDati.leggiStringaNonVuota("Inserisci un nome per il tuo ristorante: ");
        int num_posti = InputDati.leggiInteroNonNegativo("Inserisci il numero di posti a sedere: ");

        ristorante = new Ristorante(nome_ristorante, num_posti);
    }

    /*
    * Precondizione: Deve essere passato un intero come argomento "scelta" (2 o diverso da 2).
    * Postcondizione: Modifica il nome o il numero di posti a sedere del ristorante
    * in base alla scelta dell'utente. Stampa un messaggio di conferma.
    * */
    public void modificaRistorante(int scelta) {
        if (scelta == 2) {
            ristorante.setNome(InputDati.leggiStringaNonVuota("Inserire un nuovo nome per il ristorante: "));
        } else {
            ristorante.setNum_posti(InputDati.leggiInteroPositivo("Inserire posti disponibili per il ristorante: "));
        }
        System.out.println("Modificato con successo!");
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Stampa le informazioni sui menu tematici presenti nell'arraylist "menu_tematici" sulla console.
    * */
    public void visualizzaMenuTematici() {
        for (MenuTematico m : menu_tematici) {
            System.out.println(m.stampa().replace('[', ' ').replace(']', ' '));
            System.out.println("\n");
        }
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Stampa le informazioni sul ricettario sulla console.
    * */
    public void visualizzaRicettario() {
        System.out.println(ricettario.toString().replace('[', ' ').replace(']', ' '));
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Chiede all'utente di inserire i dettagli per creare una nuova ricetta
    * (nome, porzione, tempo di preparazione, stagioni e ingredienti) e la aggiunge al ricettario.
    * */
    public void creaRicetta() {

        String check;
        ArrayList<Ingrediente> ingredienti = new ArrayList<>();
        ArrayList<Stagioni> stagioni;

        String nome_ricetta;
        do {
            nome_ricetta = InputDati.leggiStringa("Inserisci il nome della ricetta: ");
            if (!checkNomeRicetta(nome_ricetta)) {
                System.out.println("Il nome della ricetta esiste gia'");
                boolean ancora = InputDati.yesOrNo("Reinserire il nome? ");
                if (!ancora) break;
            }
        } while (!checkNomeRicetta(nome_ricetta));

        if (checkNomeRicetta(nome_ricetta)) {
            int porzione = InputDati.leggiInteroNonNegativo("Inserisci il numero di porzione: ");
            int tempo = InputDati.leggiInteroNonNegativo("Inserisci il tempo di preparazione: ");

            stagioni = aggiungiStagione();

            do {             // Inserimento degli ingredienti
                String nome_ingrediente = InputDati.leggiStringa("Inserisci il nome dell'ingrediente: ");
                int dosaggio = InputDati.leggiIntero("Inserisci il dosaggio: ");
                ingredienti.add(new Ingrediente(nome_ingrediente, dosaggio));
                check = InputDati.leggiStringa("Inserire un altro ingrediente?(Si/No)");
            } while (check.equalsIgnoreCase("si"));

            ricettario.aggiungiRicetta(nome_ricetta, stagioni, porzione, tempo, ingredienti);
        }

    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Chiede all'utente di inserire i dettagli per creare un nuovo menu tematico (nome, stagioni e piatti)
    * e lo aggiunge all'arraylist "menu_tematici".
    * */
    public void creaMenuTematico() {

        ArrayList<Piatto> piatti = new ArrayList<>();
        ArrayList<Stagioni> stagioni;
        boolean corrispondenza_stagione;
        boolean duplicato;

        String nome_menu;
        do {
            nome_menu = InputDati.leggiStringa("Inserisci il nome del menu tematico: ").toUpperCase();
            if (!checkNomeTematico(nome_menu)) {
                System.out.println("il menu tematico con questo nome esiste già");
                boolean ancora = InputDati.yesOrNo("Reinserire il nome? ");
                if (!ancora) break;
            }
        } while (!checkNomeTematico(nome_menu));

        if (checkNomeTematico((nome_menu))) {
            stagioni = aggiungiStagione();

            Ricettario.stampaRicette();
            String piatto = InputDati.leggiStringa("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");

            while (!piatto.equalsIgnoreCase("0")) {
                if (Ricettario.getRicettaByNome(piatto) == null) {
                    piatto = InputDati.leggiStringa("Il piatto non fa parte delle ricette presenti, reinserire (0 per uscire): ");
                } else {
                    duplicato = false;
                    for (Piatto value : piatti) {
                        if (value.getNome().equalsIgnoreCase(piatto)) {
                            duplicato = true;
                            break;
                        }
                    }
                    if (duplicato) System.out.println("Piatto gia' inserito");
                    else {
                        Ricetta ricetta = Ricettario.getRicettaByNome(piatto);
                        corrispondenza_stagione = false;
                        for (Stagioni value : stagioni) {
                            for (int j = 0; j < ricetta.getStagione().size(); j++) {
                                if (value.equals(ricetta.getStagione().get(j))) {
                                    corrispondenza_stagione = true;
                                    break;
                                }
                            }
                        }

                        if (!corrispondenza_stagione)
                            System.out.println("Il piatto non e' disponibile in quella stagione");
                        else piatti.add(new Piatto(Ricettario.getRicettaByNome(piatto)));
                    }

                    piatto = InputDati.leggiStringaNonVuota("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");
                }
            }
            if (!piatti.isEmpty()) {
                MenuTematico da_aggiungere = new MenuTematico(nome_menu, stagioni, piatti);
                menu_tematici.add(da_aggiungere);
                Xml.aggiungiMenuTematico(menu_tematici.get(menu_tematici.size() - 1));
            }

        }
    }

    /*
    * Precondizione: Deve essere passata una stringa come argomento "stagione".
    * Postcondizione: Restituisce true se la stringa corrisponde a una delle stagioni valide (Inverno, Primavera, Estate, Autunno),
    * altrimenti restituisce false.
    * */
    public boolean checkStagione(String stagione) {
        return stagione.equalsIgnoreCase(Costante.INVERNO) || stagione.equalsIgnoreCase(Costante.PRIMAVERA) || stagione.equalsIgnoreCase(Costante.ESTATE) || stagione.equalsIgnoreCase(Costante.AUTUNNO);
    }

    /*
    * Precondizione: Deve essere passata una stringa come argomento "nome".
    * Postcondizione: Restituisce true se la stringa "nome" non corrisponde al nome di alcun menu tematico presente
    * nell'arraylist "menu_tematici", altrimenti restituisce false.
    * */
    public boolean checkNomeTematico(String nome) {
        for (int i = 0; i < menu_tematici.size(); i++) {
            if (menu_tematici.get(i).getNome().equalsIgnoreCase(nome)) return false;
        }
        return true;
    }

    /*
    * Precondizione: Deve essere passata una stringa come argomento "nome".
    * Postcondizione: Restituisce true se la stringa "nome" non corrisponde al nome di alcuna ricetta presente nel ricettario,
    * altrimenti restituisce false.
    * */
    public boolean checkNomeRicetta(String nome) {
        for (int i = 0; i < ricettario.getRicette().size(); i++) {
            Ricetta ricetta = ricettario.getRicette().get(i);
            if (ricetta.getNome().equalsIgnoreCase(nome)) return false;
        }

        return true;
    }

    /*
    * Precondizione: Deve essere passato un oggetto "prodotto" di tipo "Prodotto" e una stringa "nome_file".
    * Postcondizione: Aggiunge il prodotto al ristorante se non esiste già e lo aggiunge anche al file di testo "nome_file".
    * */
    public void aggiungiProdotto(Prodotto prodotto, String nome_file) {

        if (ristorante.checkProdotto(prodotto)) System.out.println("esiste già!");
        else {
            ristorante.addProdotto(prodotto);
            FileTesto.aggiungiProdotto(prodotto, nome_file);
        }
    }

    /*
    * Precondizione: Deve essere passata una stringa "discriminante".
    * Postcondizione: Stampa le informazioni dei prodotti presenti nel ristorante che corrispondono al discriminante specificato.
    * */
    public void visualizzaProdotti(String discriminante) {
        ristorante.visualizza(discriminante);
    }

    /*
    *Precondizione: Nessuna.
    * Postcondizione: Chiede all'utente di inserire una o più stagioni e restituisce un arraylist di stagioni valide
    * (Inverno, Primavera, Estate, Autunno).
    * */
    public ArrayList<Stagioni> aggiungiStagione() {
        ArrayList<Stagioni> stagioni = new ArrayList<>();
        String check;


        String stagione_str;
        do {
            boolean duplicato = false;
            stagione_str = InputDati.leggiStringa("Inserisci la disponibilita'(Inverno/Primavera/Estate/Autunno): ");
            if (checkStagione(stagione_str)) {
                for (Stagioni value : stagioni) {
                    if (value.name().equalsIgnoreCase(stagione_str)) {
                        duplicato = true;
                        break;
                    }
                }
                if (duplicato) System.out.println("Stagione gia' inserita");
                else stagioni.add(Stagioni.getStagione(stagione_str));
            } else {
                System.out.println("stagione invalida!");
            }
            check = InputDati.leggiStringa("Inserire un altra stagione?(Si/No)");
        } while (check.equalsIgnoreCase("si") || stagioni.size() == 0);

        return stagioni;
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Restituisce l'oggetto "ricettario" della classe "Gestore".
    * */
    public Ricettario getRicettario() {
        return ricettario;
    }

    /*
    * Precondizione: Nessuna.
    * Postcondizione: Restituisce l'oggetto "ristorante" della classe "Gestore".
    * */
    public Ristorante getRistorante() {
        return ristorante;
    }
}
