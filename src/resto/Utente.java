package resto;

public class Utente {
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private String email;
    private String telefono;
    private boolean isRegistrato;

    public Utente(String nome, String cognome, String username, String password, String email, String telefono) {
        this.nome = nome;
        this.cognome = cognome;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefono = telefono;
        this.isRegistrato = false;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isRegistrato() {
        return isRegistrato;
    }

    public void setRegistrato(boolean registrato) {
        isRegistrato = registrato;
    }
}

