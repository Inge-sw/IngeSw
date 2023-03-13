package ristorante;

import resto.Costante;
import resto.InputDati;
import resto.Stagioni;
import resto.Xml;

import java.util.ArrayList;

public class Gestore {

    Ricettario ricettario;
    ArrayList<MenuTematico> menu_tematici;

    public Gestore(){
        creaRistorante();
        ricettario = Xml.leggiRicettario();
        menu_tematici = Xml.leggiMenuTematico();
    }

    public void creaRistorante(){
        String nome_ristorante = InputDati.leggiStringaNonVuota("Inserisci un nome per il tuo ristorante: ");
        int num_posti = InputDati.leggiInteroNonNegativo("Inserisci il numero di posti a sedere: ");
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

        String nome_menu = InputDati.leggiStringa("Inserisci il nome del menu tematico: ");

        stagioni = aggiungiStagione();

        boolean duplicato = false;
        Ricettario.stampaRicette();
        String piatto = InputDati.leggiStringa("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");
        do{
            if (Ricettario.getRicettaByNome(piatto) == null){
                piatto = InputDati.leggiStringa("Il piatto non fa parte delle ricette presenti, reinserire (0 per uscire): ");
            }
            else{
                for (int i = 0; i < piatti.size(); i++){
                    if (piatti.get(i).getNome().equalsIgnoreCase(piatto)){
                        duplicato = true;
                        break;
                    }
                }
                if (duplicato) System.out.println("Piatto gia' inserito");
                else piatti.add(new Piatto(Ricettario.getRicettaByNome(piatto)));
                piatto = InputDati.leggiStringa("Inserisci il piatto da inserire nel menu tematico (0 per uscire): ");
            }
        }while(!piatto.equalsIgnoreCase("0") || piatti.size() == 0);

        menu_tematici.add(new MenuTematico(nome_menu, stagioni, piatti));
        Xml.aggiungiMenuTematico(menu_tematici.get(menu_tematici.size() - 1));
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

}
