package resto;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe utile per ricevere dati input da tastiera in console
 */
public class InputDati {
    private static Scanner lettore = creaScanner();

    private final static String ERRORE_FORMATO = "Mi vuoi per caso rompere? Inserisci un dato valido";
    private final static String ERRORE_MINIMO = "Non prendermi in giro e scegli un valore maggiore o uguale a ";
    private final static String ERRORE_STRINGA_VUOTA = "Non hai inserito alcun carattere, non sei molto fantasioso eh";
    private final static String ERRORE_MASSIMO = "Non prendermi in giro e scegli un valore minore o uguale a ";
    private final static String MESSAGGIO_AMMISSIBILI = "Non e' quello che ti ho chiesto. I caratteri ammissibili sono: ";

    public final static char RISPOSTA_SI = 'S';
    public final static char RISPOSTA_NO = 'N';

    private static Scanner creaScanner() {
        Scanner creato = new Scanner(System.in);
        return creato;
    }

    /**
     * Legge una {@linkplain String} da input senza nessun controllo
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static String leggiStringa(String messaggio) {
        System.out.print(messaggio);
        return lettore.nextLine();
    }

    /**
     * Legge una {@linkplain String} da input, controllando che non sia vuota
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static String leggiStringaNonVuota(String messaggio) {
        boolean finito = false;
        String lettura = null;
        do {
            lettura = leggiStringa(messaggio);
            lettura = lettura.trim();
            if (lettura.length() > 0)
                finito = true;
            else
                System.out.println(ERRORE_STRINGA_VUOTA);
        } while (!finito);

        return lettura;
    }

    /**
     * Legge un carattere da input senza nessun controllo
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static char leggiChar(String messaggio) {
        boolean finito = false;
        char valoreLetto = '\0';
        do {
            String lettura = leggiStringaNonVuota(messaggio);
            valoreLetto = lettura.charAt(0);
            finito = true;

        } while (!finito);
        return valoreLetto;
    }

    /**
     * Legge un carattere da input, controllando che sia all'interno della
     * {@linkplain String} {@code ammissibili}
     *
     * @param messaggio   da visualizzare in {@code Console}
     * @param ammissibili e' la {@linkplain String} che contiene tutti i char disponibili
     */
    public static char leggiUpperChar(String messaggio, String ammissibili) {
        boolean finito = false;
        char valoreLetto = '\0';
        do {
            valoreLetto = leggiChar(messaggio);
            valoreLetto = Character.toUpperCase(valoreLetto);
            if (ammissibili.indexOf(valoreLetto) != -1)
                finito = true;
            else
                System.out.println(MESSAGGIO_AMMISSIBILI + ammissibili);
        } while (!finito);
        return valoreLetto;
    }

    /**
     * Legge un intero da input, controllando che ci� che si inserisce sia testo
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static Integer leggiIntero(String messaggio) {
        boolean finito = false;
        int valoreLetto = 0;
        do {
            try {
                valoreLetto = Integer.parseInt(leggiStringaNonVuota(messaggio));
                finito = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(ERRORE_FORMATO);
                // String daButtare = lettore.next();
            }
        } while (!finito);
        return valoreLetto;
    }

    /**
     * Legge un intero da input Positivo {@code >=1}
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static int leggiInteroPositivo(String messaggio) {
        return leggiInteroConMinimo(messaggio, 1);
    }

    /**
     * Legge un intero da input che non sia negativo {@code >=0}
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static int leggiInteroNonNegativo(String messaggio) {
        return leggiInteroConMinimo(messaggio, 0);
    }

    /**
     * Legge un intero da input, assicurandosi che sia inserito un valore
     * {@code >=minimo}
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static int leggiInteroConMinimo(String messaggio, int minimo) {
        boolean finito = false;
        int valoreLetto = 0;
        do {
            valoreLetto = leggiIntero(messaggio);
            if (valoreLetto >= minimo)
                finito = true;
            else
                System.out.println(ERRORE_MINIMO + minimo);
        } while (!finito);

        return valoreLetto;
    }

    /**
     * Legge un intero da input, assicurandosi che
     *
     * @param messaggio da visualizzare in {@code Console}
     */
    public static int leggiIntero(String messaggio, int minimo, int massimo) {
        boolean finito = false;
        int valoreLetto = 0;
        do {
            valoreLetto = leggiIntero(messaggio);
            if (valoreLetto >= minimo && valoreLetto <= massimo)
                finito = true;
            else if (valoreLetto < minimo)
                System.out.println(ERRORE_MINIMO + minimo);
            else
                System.out.println(ERRORE_MASSIMO + massimo);
        } while (!finito);

        return valoreLetto;
    }

    public static double leggiDouble(String messaggio) {
        boolean finito = false;
        double valoreLetto = 0;
        do {
            try {
                valoreLetto = Double.parseDouble(leggiStringaNonVuota(messaggio));
                finito = true;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(ERRORE_FORMATO);
                // String daButtare = lettore.next();
            }
        } while (!finito);
        return valoreLetto;
    }

    public static float leggiFloat(String messaggio, int minimo, int massimo) {
        return (float) leggiDouble(messaggio, minimo, massimo);
    }

    public static double leggiDoubleConMinimo(String messaggio, double minimo) {
        boolean finito = false;
        double valoreLetto = 0;
        do {
            valoreLetto = leggiDouble(messaggio);
            if (valoreLetto >= minimo)
                finito = true;
            else
                System.out.println(ERRORE_MINIMO + minimo);
        } while (!finito);

        return valoreLetto;
    }

    public static double leggiDouble(String messaggio, int minimo, int massimo) {
        boolean finito = false;
        double valoreLetto = 0;
        do {
            valoreLetto = leggiDouble(messaggio);
            if (valoreLetto >= minimo && valoreLetto <= massimo)
                finito = true;
            else if (valoreLetto < minimo)
                System.out.println(ERRORE_MINIMO + minimo);
            else
                System.out.println(ERRORE_MASSIMO + massimo);
        } while (!finito);

        return valoreLetto;
    }

    public static boolean yesOrNo(String messaggio) {
        String mioMessaggio = messaggio + "(" + RISPOSTA_SI + "/" + RISPOSTA_NO + ")";
        char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(RISPOSTA_SI) + String.valueOf(RISPOSTA_NO));

        if (valoreLetto == RISPOSTA_SI)
            return true;
        else
            return false;
    }

    /**
     * <b>Metodo</B> che capisce se il tasto <b>{@code INVIO}</B> e' stato premuto
     *
     * @param daOutputtare e' la {@linkplain String} con il contenuto da mandare in output
     * @param messaggio    e' il messaggio di 'attesa' finche' non si preme invio
     */
    public static void isInvioPremuto(String daOutputtare, String messaggio) {
        System.out.println(daOutputtare);
        System.out.print(messaggio);
        //lettore.next();
        try {
            System.in.read();
        } catch (Exception e) {
        }
        //System.out.flush();
        //lettore.nextLine();
    }

    public static void pulisciConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /*public static void pulisciConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            //  Handle any exceptions.
        }
    }*/

    public static void isInvioPremutoEPulisciConsole(String daOutputtare, String messaggio) {
        isInvioPremuto(daOutputtare, messaggio);
        pulisciConsole();
        System.out.println(System.lineSeparator());
    }

    public static LocalDate leggiData(String messaggio) {

        LocalDate d = LocalDate.parse(InputDati.leggiStringaNonVuota(messaggio));
        return d;
    }
}