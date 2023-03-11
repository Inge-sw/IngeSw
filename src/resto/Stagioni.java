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
}
