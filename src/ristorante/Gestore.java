package ristorante;

import resto.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Gestore extends Utente {

    Ristorante ristorante;
    Ricettario ricettario;
    ArrayList<MenuTematico> menu_tematici;

    public Gestore(String username, String password) {
        super(username, password, RuoloUtente.GESTORE);
        creaRistorante();
        ricettario = Xml.leggiRicettario();
        menu_tematici = Xml.leggiMenuTematico();
    }

    public void avvio(){
        creaRistorante();
        ricettario = Xml.leggiRicettario();
        menu_tematici = Xml.leggiMenuTematico();
    }

    public void visulizzaRistorante(){
        System.out.println(ristorante.toString());
    }

    public void creaRistorante(){
        String nome_ristorante = InputDati.leggiStringaNonVuota("Inserisci un nome per il tuo ristorante: ");
        int num_posti = InputDati.leggiInteroNonNegativo("Inserisci il numero di posti a sedere: ");

        ristorante = new Ristorante(nome_ristorante, num_posti);
    }

    public void modificaRistorante(int scelta){
        if (scelta == 2) {
            ristorante.setNome(InputDati.leggiStringaNonVuota("Inserire un nuovo nome per il ristorante: "));
            System.out.println("Modificato con successo!");
        }
        else {
            ristorante.setNum_posti(InputDati.leggiInteroPositivo("Inserire posti disponibili per il ristorante: "));
            System.out.println("Modificato con successo!");
        }
    }

    public void visualizzaMenuTematici(){
       System.out.println(menu_tematici.toString().replace('[', ' ').replace(']', ' '));
    }

    public void visualizzaRicettario(){
        System.out.println(ricettario.toString().replace('[', ' ').replace(']', ' '));
    }

    public void creaRicetta(){

        String check;
        ArrayList<Ingrediente> ingredienti = new ArrayList<>();
        ArrayList<Stagioni> stagioni;

        String nome_ricetta = InputDati.leggiStringa("Inserisci il nome della ricetta: ");
        int porzione = InputDati.leggiInteroNonNegativo("Inserisci il numero di porzione: ");
        int tempo = InputDati.leggiInteroNonNegativo("Inserisci il tempo di preparazione: ");

        stagioni = aggiungiStagione();

        do{             // Inserimento degli ingredienti
            String nome_ingrediente = InputDati.leggiStringa("Inserisci il nome dell'ingrediente: ");
            int dosaggio = InputDati.leggiIntero("Inserisci il dosaggio: ");
            ingredienti.add(new Ingrediente(nome_ingrediente, dosaggio));
            check = InputDati.leggiStringa("Inserire un altro ingrediente?(Si/No)");
        }while(check.equalsIgnoreCase("si"));

        ricettario.aggiungiRicetta(nome_ricetta, stagioni, porzione, tempo, ingredienti);

    }

    public void creaMenuTematico(){

        ArrayList<Piatto> piatti = new ArrayList<>();
        ArrayList<Stagioni> stagioni;
        boolean corrispondenza_stagione;
        boolean duplicato;

        String nome_menu = InputDati.leggiStringa("Inserisci il nome del menu tematico: ");

        stagioni = aggiungiStagione();


        Ricettario.stampaRicette();
        String piatto = InputDati.leggiStringa("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");
        do{
            if (Ricettario.getRicettaByNome(piatto) == null){
                piatto = InputDati.leggiStringa("Il piatto non fa parte delle ricette presenti, reinserire (0 per uscire): ");
            }
            else{
                duplicato = false;
                for (int i = 0; i < piatti.size(); i++){
                    if (piatti.get(i).getNome().equalsIgnoreCase(piatto)){
                        duplicato = true;
                        break;
                    }
                }
                if (duplicato) System.out.println("Piatto gia' inserito");
                else {
                    Ricetta ricetta = Ricettario.getRicettaByNome(piatto);
                    corrispondenza_stagione = false;
                    for (int i = 0; i < stagioni.size(); i++){
                        for (int j = 0; j < ricetta.getStagione().size(); j++){
                            if (stagioni.get(i).equals(ricetta.getStagione().get(j))){
                                corrispondenza_stagione = true;
                                break;
                            }
                        }
                    }

                    if (!corrispondenza_stagione) System.out.println("Il piatto non e' disponibile in quella stagione");
                    else piatti.add(new Piatto(Ricettario.getRicettaByNome(piatto)));
                }
                piatto = InputDati.leggiStringa("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");
            }
        }while(!piatto.equalsIgnoreCase("0") || piatti.size() == 0);

        menu_tematici.add(new MenuTematico(nome_menu, stagioni, piatti));
        Xml.aggiungiMenuTematico(menu_tematici.get(menu_tematici.size() - 1));
    }

    public void visualizzaBevande(){
        HashMap<Bevanda, Double> bevande = ristorante.getBevande_persona();
        bevande.forEach((key, value) -> {
            System.out.println("- " + key.getNome() + " " + value + " " + key.getU_misura());
        });
    }

    public void aggiungiBevande(){
        boolean duplicato = false;
        String bevanda_input = InputDati.leggiStringaNonVuota("Inserisci una nuova bevanda: ");
        HashMap<Bevanda, Double> bevande_esistenti = ristorante.getBevande_persona();

        if (bevande_esistenti.containsKey(new Bevanda(bevanda_input.toUpperCase()))){
            duplicato = true;
        }

        if (duplicato) System.out.println("La bevanda esiste già");
        else{
            Bevanda bevanda_da_aggiungere = new Bevanda(bevanda_input.toUpperCase());
            ristorante.getBevande_persona().put(bevanda_da_aggiungere, ristorante.getQnt());
            FileTesto.aggiungiBevande(bevanda_da_aggiungere);
        }
    }

    public void aggiungiGeneri(){
        boolean duplicato = false;
        String genere_input = InputDati.leggiStringaNonVuota("Inserisci un nuovo genere: ");
        HashMap<GeneriExtra, Double> generi_esistenti = ristorante.getGeneri_extra_persona();

        GeneriExtra genere_da_aggiungere = new GeneriExtra(genere_input.toUpperCase());

        if (generi_esistenti.containsKey(genere_da_aggiungere)){
            duplicato = true;
        }

        if (duplicato) System.out.println("Il genere esiste già");
        else{

            ristorante.getGeneri_extra_persona().put(genere_da_aggiungere, ristorante.getQnt());
            FileTesto.aggiungiGenere(genere_da_aggiungere);
        }
    }

    public void visualizzaGeneri(){
        HashMap<GeneriExtra, Double> generi = ristorante.getGeneri_extra_persona();
        generi.forEach((key, value) -> {
            System.out.println("- " + key.getNome() + " " + value + " " + key.getU_misura());
        });
    }

    public boolean checkStagione(String stagione){
            if(stagione.equalsIgnoreCase(Costante.INVERNO) || stagione.equalsIgnoreCase(Costante.PRIMAVERA) || stagione.equalsIgnoreCase(Costante.ESTATE) || stagione.equalsIgnoreCase(Costante.AUTUNNO))
                return true;

        return false;
    }

    public ArrayList<Stagioni> aggiungiStagione(){
        ArrayList<Stagioni> stagioni = new ArrayList<>();
        String check;


        String stagione_str;
        do{
            boolean duplicato = false;
            stagione_str = InputDati.leggiStringa("Inserisci la disponibilita'(Inverno/Primavera/Estate/Autunno): ");
            if(checkStagione(stagione_str)){
                for (int i = 0; i < stagioni.size(); i++){
                    if (stagioni.get(i).name().equalsIgnoreCase(stagione_str)){
                        duplicato = true;
                        break;
                    }
                }
                if (duplicato)  System.out.println("Stagione gia' inserita");
                else stagioni.add(Stagioni.getStagione(stagione_str));
            }
            else {
                System.out.println("stagione invalida!");
            }
            check = InputDati.leggiStringa("Inserire un altra stagione?(Si/No)");
        }while(check.equalsIgnoreCase("si") || stagioni.size() == 0);

        return stagioni;
    }

    public Ristorante getRistorante() {
        return ristorante;
    }
}
