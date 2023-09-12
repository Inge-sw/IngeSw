package input;

import java.util.ArrayList;

/**
 * Classe che fa delle indentazioni alle stringhe in console
 */

public class BelleStringhe {

    private final static String SPAZIO = " ";
    public static final char TRATTINO = '-';
    private final static String CORNICE = "---------------------------------------------------";
    private final static String ACAPO = System.lineSeparator(); //"\n";

    public static String incornicia(String s) {
        StringBuffer res = new StringBuffer();

        res.append(CORNICE + ACAPO);
        res.append(s + ACAPO);
        res.append(CORNICE + ACAPO);

        return res.toString();

    }

    public static String incorniciaCentrato(String s, int lunghezzaCornice) {
        StringBuilder res = new StringBuilder();
        String sCentrato = BelleStringhe.centrata(s, lunghezzaCornice);
        String cornice = BelleStringhe.stampaCornice(lunghezzaCornice, TRATTINO);
        res.append(cornice);
        res.append(ACAPO);
        res.append(sCentrato);
        res.append(ACAPO);
        res.append(cornice);
        res.append(ACAPO);
        return res.toString();

    }

    public static String incorniciaCentrato(String s, int lunghezzaCornice, char c) {
        StringBuilder res = new StringBuilder();
        String sCentrato = BelleStringhe.centrata(s, lunghezzaCornice);
        String cornice = BelleStringhe.stampaCornice(lunghezzaCornice, c);
        res.append(cornice);
        res.append(ACAPO);
        res.append(sCentrato);
        res.append(ACAPO);
        res.append(cornice);
        res.append(ACAPO);
        return res.toString();

    }

    public static String incolonna(String s, int larghezza) {
        StringBuffer res = new StringBuffer(larghezza);
        int numCharDaStampare = Math.min(larghezza, s.length());
        res.append(s.substring(0, numCharDaStampare));
        for (int i = s.length() + 1; i <= larghezza; i++)
            res.append(SPAZIO);
        return res.toString();
    }

    public static String centrata(String s, int larghezza) {
        StringBuffer res = new StringBuffer(larghezza);
        if (larghezza <= s.length())
            res.append(s.substring(larghezza));
        else {
            int spaziPrima = (larghezza - s.length()) / 2;
            int spaziDopo = larghezza - spaziPrima - s.length();
            for (int i = 1; i <= spaziPrima; i++)
                res.append(SPAZIO);

            res.append(s);

            for (int i = 1; i <= spaziDopo; i++)
                res.append(SPAZIO);
        }
        return res.toString();

    }

    public static String ripetiChar(char elemento, int larghezza) {
        StringBuffer result = new StringBuffer(larghezza);
        for (int i = 0; i < larghezza; i++) {
            result.append(elemento);
        }
        return result.toString();

    }

    public static String rigaIsolata(String daIsolare) {
        StringBuffer result = new StringBuffer();
        result.append(ACAPO);
        result.append(daIsolare);
        result.append(ACAPO);
        return result.toString();
    }

    public static String stampaCornice(int lunghezza, char segno) {
        StringBuilder c = new StringBuilder();
        for (int i = 0; i <= lunghezza; i++) {
            c.append(segno);
        }
        return c.toString();
    }

    public static String stampaCornice(String messaggio, char segno) {
        return stampaCornice(messaggio.length(), segno);

    }

    public static String stampaStringaCorniceCentrato(String titolo) {
        return stampaStringaCorniceCentrato(titolo, BelleStringhe.TRATTINO);
    }

    public static String stampaStringaCorniceCentrato(String titolo, char segnoCornice) {
        String titoloCentrato = BelleStringhe.stampaStringCentrato(titolo);
        int lunghezzaMassima = lunghezzaMassimaString(titolo);
        StringBuilder fine = new StringBuilder(BelleStringhe.stampaCornice(lunghezzaMassima, segnoCornice));
        fine.append(ACAPO);
        fine.append(titoloCentrato);
        //fine.append(ACAPO);
        fine.append(BelleStringhe.stampaCornice(lunghezzaMassima, segnoCornice));
        fine.append(ACAPO);
        return fine.toString();
    }

    public static String stampaStringCentrato(String daStampare) {
        ArrayList<Integer> aCapo = new ArrayList<>();
        ArrayList<String> tutteLeStringhe = new ArrayList<>();
        {
            Character allafine = daStampare.charAt(daStampare.length() - 1);
            if (allafine != '\n')
                daStampare += ACAPO;
        }
        int i = 0;
        int j = 0;
        while (i != -1) {
            i = daStampare.indexOf(ACAPO, j);
            if (i != -1) {
                aCapo.add(i);
                tutteLeStringhe.add(daStampare.substring(j, i));
                j = i + 2;
            }
        }
        if (tutteLeStringhe.isEmpty()) {
            tutteLeStringhe.add(daStampare);
            aCapo.add(daStampare.length());
        }
        int lunghezzaMassimaStri = aCapo.get(0);
        for (i = 1; i < aCapo.size(); i++) {
            int lungh = aCapo.get(i) - aCapo.get(i - 1);
            if (lungh > lunghezzaMassimaStri)
                lunghezzaMassimaStri = lungh;
        }
        StringBuilder fine = new StringBuilder();
        for (String string : tutteLeStringhe) {
            fine.append(BelleStringhe.centrata(string, lunghezzaMassimaStri + 1));
            fine.append(ACAPO);
        }
        return fine.toString();
    }

    public static int lunghezzaMassimaString(String daStampare) {
        ArrayList<Integer> aCapo = new ArrayList<>();
        int i = 0, j = 0;
        while (i != -1) {
            i = daStampare.indexOf(ACAPO, j);
            if (i != -1) {
                aCapo.add(i);
                j = i + 2;
            }
        }
        if (aCapo.isEmpty())
            aCapo.add(daStampare.length());
        int lunghezzaMassimaStri = aCapo.get(0);
        for (i = 1; i < aCapo.size(); i++) {
            int lungh = aCapo.get(i) - aCapo.get(i - 1);
            if (lungh > lunghezzaMassimaStri)
                lunghezzaMassimaStri = lungh;
        }
        return lunghezzaMassimaStri;

    }

    public static int rigaPiuLunga(String str) {
        int j = 0;
        int i = 0;
        while (i != -1) {
            i = str.indexOf('\n', i + 1);
            if (i - j >= j) {
                j = i - j;
            }
        }
        return j;

    }

    public static void stampa(String str){
        System.out.println(str);
    }
}