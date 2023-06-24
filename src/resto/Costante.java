package resto;

public class Costante {

    public static final String XML_RICETTARIO = "File/Ricettario.xml";
    public static final String XML_MENU = "File/Menu.xml";
    public static final String XML_PRENOTAZIONI = "File/Prenotazioni.xml";
    public static final String XML_MAGAZZINO = "File/Magazzino.xml";

    public static final String FILE_BEVANDE = "File/Bevande.txt";
    public static final String FILE_GENERI = "File/Generi.txt";

    public static final String NOME = "nome";
    public static final String NOME_RICETTA = "nomeRicetta";

    public static final String RICETTA = "ricetta";
    public static final String CARICO_LAVORO_PER_PORZIONE = "caricoLavoroPerPorzione";
    public static final String INGREDIENTI = "ingredienti";
    public static final String INGREDIENTE = "ingrediente";
    public static final String TEMPO = "tempo";
    public static final String DOSAGGIO = "dosaggio";
    public static final String UNITA = "unità";
    public static final String TEMP = "tempo";
    public static final String PORZIONE = "porzione";
    public static final String STAGIONE = "stagione";
    public static final String PIATTO = "piatto";
    public static final String PIATTI = "piatti";
    public static final String MENU_TEMATICO = "menuTematico";
    public static final String DISPONIBLITA = "disponibilità";

    public static final String INVERNO = "inverno";
    public static final String ESTATE = "estate";
    public static final String AUTUNNO = "autunno";
    public static final String PRIMAVERA = "primavera";

    public static final String ERRORE_LETTURA = "Impossibile leggere il file xml";

    public static final String[] MENU_RUOLO = {
            "Gestore",
            "Addetto prenotazione",
            "Magazziniere",
            "Cliente"
    };

    public static final String[] MENU_GESTORE = {
            "Gestisci il ristorante",
            "Gestisci i menu tematici",
            "Gestisci il ricettario",
            "Esci"
    };

    public static final String[] MENU_ADDETTO = {
            "Visualizza prenotazioni",
            "Esci"
    };

    public static final String[] MENU_MAGAZZINIERE = {
            "Visualizza scorte",
            "Acquista prodotti",
            "Rimuovi prodotti",
            "Crea lista spesa",
            "Esci"
    };

    public static final String[] MENU_RISTORANTE = {
            "Visualizza i dati del ristorante",
            "Modifica il nome del ristorante",
            "Modifica i posto disponibili del ristorante",
            "Gestisci le bevande",
            "Gestisci i generi extra",
            "Indietro"
    };

    public static final String[] MENU_MENU = {
            "Visualizza i menu tematici",
            "Crea un menu tematico",
            "Indietro"
    };

    public static final String[] MENU_RICETTARIO = {
            "Visualizza il ricettario",
            "Crea una ricetta",
            "Indietro"
    };

    public static final String[] MENU_USERDB = {
            "Sign up",
            "Sign in",
            "Esci"
    };

    public static final String[] MENU_BEVANDA = {
            "Visualizza l'insieme delle bevande",
            "Aggiungi una bevanda",
            "Indietro"
    };

    public static final String[] MENU_GENERI = {
            "Visualizza l'insieme dei generi extra",
            "Aggiungi un genere extra",
            "Indietro"
    };

}
