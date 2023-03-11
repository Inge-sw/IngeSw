package resto;

/**
 * Questa classe rappresenta un menu testuale generico a piu' voci
 * Si suppone che la voce per uscire sia sempre associata alla scelta 0
 * e sia presentata in fondo al menu
 */

public class MyMenu {

    //final private static String CORNICE = "--------------------------------";
    protected final static String VOCE_USCITA = "0\tEsci";
    final private static String RICHIESTA_INSERIMENTO = "Digita il numero dell'opzione desiderata > ";
    protected final static int LUNGHEZZA_RICHIESTA = RICHIESTA_INSERIMENTO.length();

    private String titolo;
    private String[] voci;


    public MyMenu(String titolo, String[] voci) {
        this.titolo = titolo;
        this.voci = voci;
    }

    public int scegli() {
        stampaMenu();
        return InputDati.leggiIntero(RICHIESTA_INSERIMENTO, 1, voci.length);
    }

    public void stampaMenu() {
        System.out.println(BelleStringhe.incorniciaCentrato(getTitolo(), LUNGHEZZA_RICHIESTA));
        for (int i = 0; i < voci.length; i++) {
            System.out.println((i + 1) + ")\t" + voci[i]);
        }

        //System.out.println('\n' + VOCE_USCITA + '\n');
        System.out.println('\n');

    }

    protected String getTitolo() {
        return titolo;
    }

    protected String[] getVoci() {
        return voci;
    }
}