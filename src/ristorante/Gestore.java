package ristorante;

import input.BelleStringhe;
import input.InputDati;
import letturaFile.Controller;
import letturaFile.FTBevande;
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
        ArrayList<Ingrediente> ingredienti = new ArrayList<>();
        ArrayList<Stagioni> stagioni;

        stagioni = aggiuntaStagione();
        int porzione = InputDati.leggiInteroNonNegativo("Inserisci il numero di porzione: ");
        int tempo = InputDati.leggiInteroNonNegativo("Inserisci il tempo di preparazione: ");

        collectIngredients(ingredienti);

        String nome_ricetta = selezioneNomeRicetta();

        ricettario.aggiungiRicetta(new Ricetta(nome_ricetta, stagioni, porzione, tempo, ingredienti));

    }

    private void collectIngredients(ArrayList<Ingrediente> ingredienti) {
        String altro_ingrediente;
        do {             // Inserimento degli ingredienti
            String nome_ingrediente = InputDati.leggiStringa("Inserisci il nome dell'ingrediente: ");
            int dosaggio = InputDati.leggiIntero("Inserisci il dosaggio: ");
            ingredienti.add(new Ingrediente(nome_ingrediente, dosaggio));
            altro_ingrediente = InputDati.leggiStringa("Inserire un altro ingrediente?(Si/No)");
        } while (altro_ingrediente.equalsIgnoreCase("si"));
    }

    private String selezioneNomeRicetta() {
        String nome_ricetta;
        do {
            nome_ricetta = InputDati.leggiStringa("Inserisci il nome della ricetta: ");
            if (!checkNomeRicetta(nome_ricetta)) {
                BelleStringhe.stampa("Il nome della ricetta esiste gia'");
                boolean ancora = InputDati.yesOrNo("Reinserire il nome? ");
                if (!ancora) break;
            }
        } while (!checkNomeRicetta(nome_ricetta));
        return nome_ricetta;
    }

    /*
     * Precondizione: Nessuna.
     * Postcondizione: Chiede all'utente di inserire i dettagli per creare un nuovo menu tematico (nome, stagioni e piatti)
     * e lo aggiunge all'arraylist "menu_tematici".
     * */
    public void creaMenuTematico() {
        ArrayList<Piatto> piatti = new ArrayList<>();

        ArrayList<Stagioni> stagioni = aggiuntaStagione();
        String nome_menu = selezionaNomeMenu();

        Ricettario.stampaRicette();
        String piatto = InputDati.leggiStringa("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");
        collectRecipes(piatti, stagioni, piatto);

        aggiungiMenu(piatti, stagioni, nome_menu);
    }

    private void aggiungiMenu(ArrayList<Piatto> piatti, ArrayList<Stagioni> stagioni, String nome_menu) {
        if (!piatti.isEmpty()) {
            MenuTematico da_aggiungere = new MenuTematico(nome_menu, stagioni, piatti);
            menu_tematici.add(da_aggiungere);
            Xml.aggiungiMenuTematico(menu_tematici.get(menu_tematici.size() - 1));
        }
    }

    private void collectRecipes(ArrayList<Piatto> piatti, ArrayList<Stagioni> stagioni, String piatto) {
        while (!piatto.equalsIgnoreCase("0")) {
            if (Ricettario.getRicettaByNome(piatto) == null) {
                piatto = InputDati.leggiStringa("Il piatto non fa parte delle ricette presenti, reinserire (0 per uscire): ");
            } else {
                if (checkPiattoDuplicato(piatti, piatto)) BelleStringhe.stampa("Piatto gia' inserito!");
                else {
                    Ricetta ricetta = Ricettario.getRicettaByNome(piatto);

                    if (!canAddToMenu(stagioni, ricetta))
                        BelleStringhe.stampa("Il piatto non è disponibile in quella stagione!");
                    else piatti.add(new Piatto(ricetta));
                }

                piatto = InputDati.leggiStringaNonVuota("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");
            }
        }
    }

    private boolean canAddToMenu(ArrayList<Stagioni> stagioni, Ricetta ricetta) {
        boolean corrispondenza_stagione = false;

        for (Stagioni value : stagioni) {
            for (int j = 0; j < ricetta.getStagione().size(); j++) {
                if (value.equals(ricetta.getStagione().get(j))) {
                    corrispondenza_stagione = true;
                    break;
                }
            }
        }
        return corrispondenza_stagione;
    }

    private boolean checkPiattoDuplicato(ArrayList<Piatto> piatti, String piatto) {
        boolean duplicato = false;

        for (Piatto value : piatti) {
            if (value.getNome().equalsIgnoreCase(piatto)) {
                duplicato = true;
                break;
            }
        }
        return duplicato;
    }

    private String selezionaNomeMenu() {
        String nome_menu;
        do {
            nome_menu = InputDati.leggiStringa("Inserisci il nome del menu tematico: ").toUpperCase();
            if (!checkNomeTematico(nome_menu)) {
                System.out.println("il menu tematico con questo nome esiste già");
                boolean ancora = InputDati.yesOrNo("Reinserire il nome? ");
                if (!ancora) break;
            }
        } while (!checkNomeTematico(nome_menu));
        return nome_menu;
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

        if (ristorante.checkProdotto(prodotto)) BelleStringhe.stampa("Esiste già!");
        else {
            ristorante.addProdotto(prodotto);
            Controller c = new Controller(new FTBevande());

            c.aggiungi(prodotto,nome_file);
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
    public ArrayList<Stagioni> aggiuntaStagione() {
        ArrayList<Stagioni> stagioni = new ArrayList<>();
        String inserire_altro;
        String stagione_str;

        do {
            stagione_str = InputDati.leggiStringa("Inserisci la disponibilita'(Inverno/Primavera/Estate/Autunno): ");
            if (Stagioni.checkStagione(stagione_str)) {
                if (checkStagioneDuplicata(stagioni, stagione_str)) BelleStringhe.stampa("Stagione gia' inserita");
                else aggiungiStagione(stagioni, Stagioni.getStagione(stagione_str));

            } else {
                BelleStringhe.stampa("Stagione invalida");
            }
            inserire_altro = InputDati.leggiStringa("Inserire un altra stagione?(Si/No)");
        } while (inserire_altro.equalsIgnoreCase("si") || stagioni.size() == 0);

        return stagioni;
    }

    private void aggiungiStagione(ArrayList<Stagioni> dove_aggiungere, Stagioni da_aggiungere) {
        dove_aggiungere.add(da_aggiungere);
    }

    private boolean checkStagioneDuplicata(ArrayList<Stagioni> stagioni, String stagione_str) {
        boolean duplicato = false;

        for (Stagioni value : stagioni) {
            if (value.name().equalsIgnoreCase(stagione_str)) {
                duplicato = true;
                break;
            }
        }

        return duplicato;
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
