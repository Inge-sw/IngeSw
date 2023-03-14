package resto;

public enum RuoloUtente {
    GESTORE,
    ADDETTO_PRENOTAZIONI,
    MAGAZZINIERE;

    public static RuoloUtente getRuolo(String role) {
        if (role.equalsIgnoreCase("gestore")) {
            return GESTORE;
        } else if (role.equalsIgnoreCase("magazziniere")) {
            return MAGAZZINIERE;
        } else if (role.equalsIgnoreCase("addetto_prenotazioni")) {
            return ADDETTO_PRENOTAZIONI;
        } else {
            return null;
        }
    }
}
