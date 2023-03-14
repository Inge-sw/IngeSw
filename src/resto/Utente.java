package resto;

public class Utente {
    private String username;
    private String password;
    private RuoloUtente ruolo;

    public Utente(String username, String password, RuoloUtente role) {
        this.username = username;
        this.password = password;
        this.ruolo = role;
    }

    public RuoloUtente getRuolo() {
        return ruolo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

