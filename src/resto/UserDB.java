package resto;

import magazzino.Magazziniere;
import prenotazione.AddettoPrenotazioni;
import ristorante.Gestore;

import java.util.HashMap;

public class UserDB {
    private HashMap<String, Utente> utenti;

    public UserDB() {
        utenti = new HashMap<>();
    }

    public HashMap<String, Utente> getUtenti() {
        return utenti;
    }

    public void addUtente(Utente utente) {
        utenti.put(utente.getUsername(), utente);
    }

    public boolean checkUtente(String username, String password) {
        if (utenti.containsKey(username)) {
            return utenti.get(username).getPassword().equals(password);
        }
        return false;
    }

    public boolean removeUtente(String username, String password) {
        if (checkUtente(username, password)){
            utenti.remove(username);
            return true;
        }
        return false;
    }

    public boolean changeUsername(String username, String password, String newUsername) {
        if (checkUtente(username, password)){
            utenti.get(username).setUsername(newUsername);
            return true;
        }
        return false;
    }

    public boolean changePassword(String username, String password, String newPassword) {
        if (checkUtente(username, password)){
            utenti.get(username).setPassword(newPassword);
            return true;
        }
        return false;
    }

    //metodo per la registrazione di un utente
    public boolean register(String username, String password) {
        RuoloUtente role = RuoloUtente.
                getRuolo(InputDati.leggiStringaNonVuota("Inserisci il tuo ruolo: "));
        if (!utenti.containsKey(username)) {
            switch (role) {
                case GESTORE:
                    Gestore gestore = new Gestore(username, password);
                    addUtente(gestore);
                    return true;
                case ADDETTO_PRENOTAZIONI:
                    AddettoPrenotazioni addettoPrenotazioni = new AddettoPrenotazioni(username, password);
                    addUtente(addettoPrenotazioni);
                    return true;
                case MAGAZZINIERE:
                    Magazziniere magazziniere = new Magazziniere(username, password);
                    addUtente(magazziniere);
                    return true;
            }
        }
        return false;
    }

    //metodo per il login di un utente
    public boolean login(String username, String password) {
        if (checkUtente(username, password)) return true;
        return false;
    }
}
