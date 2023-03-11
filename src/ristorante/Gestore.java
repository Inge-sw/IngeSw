package ristorante;

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
       System.out.println(menu_tematici.toString());
    }

    public void visualizzaRicettario(){
        System.out.println(ricettario.toString());
    }

    public void creaRicetta(){

        boolean check_stagione = false;
        String check = null;
        ArrayList<Ingrediente> ingredienti = new ArrayList<>();

        String nome_ricetta = InputDati.leggiStringa("Inserisci il nome della ricetta: ");
        int porzione = InputDati.leggiInteroNonNegativo("Inserisci il numero di porzione: ");
        int tempo = InputDati.leggiInteroNonNegativo("Inserisci il tempo di preparazione: ");

        String stagione = null;
        while(!check_stagione){         //Inserimento della stagione e controlla se è giusto è meno
            stagione = InputDati.leggiStringa("Inserisci la disponiblità della ricetta(Inverno/Primavera/Estate/Autunno): ");
            if(stagione.equalsIgnoreCase("inverno") || stagione.equalsIgnoreCase("primavera") || stagione.equalsIgnoreCase("estate") || stagione.equalsIgnoreCase("autunno"))
                check_stagione = true;
        }

        do{             // Inserimento degli ingredienti
            String nome_ingrediente = InputDati.leggiStringa("Inserisci il nome dell'ingrediente: ");
            int dosaggio = InputDati.leggiIntero("Inserisci il dosaggio: ");
            ingredienti.add(new Ingrediente(nome_ingrediente, dosaggio));
            check = InputDati.leggiStringa("Inserire un altro ingrediente?(Si/No)");
        }while(check.equalsIgnoreCase("si"));

        ricettario.aggiungiRicetta(nome_ricetta, Stagioni.getStagione(stagione), porzione, tempo, ingredienti);

    }

    public void creaMenuTematico(){

    }

}
