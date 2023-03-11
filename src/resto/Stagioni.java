package resto;

public enum Stagioni {
    PRIMAVERA, ESTATE, AUTUNNO, INVERNO;

    public static Stagioni getStagione(int mese) {
        if (mese >= 3 && mese <= 5) {
            return PRIMAVERA;
        } else if (mese >= 6 && mese <= 8) {
            return ESTATE;
        } else if (mese >= 9 && mese <= 11) {
            return AUTUNNO;
        } else {
            return INVERNO;
        }
    }

    //da string a stagioni
    public static Stagioni getStagione(String stagione) {
        if (stagione.equalsIgnoreCase("primavera")) {
            return PRIMAVERA;
        } else if (stagione.equalsIgnoreCase("estate")) {
            return ESTATE;
        } else if (stagione.equalsIgnoreCase("autunno")) {
            return AUTUNNO;
        } else {
            return INVERNO;
        }
    }
}
