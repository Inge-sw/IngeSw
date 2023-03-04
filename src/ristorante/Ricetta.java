package ristorante;

public class Ricetta {
    private String nome;
    private List<Ingrediente> ingredienti;
    private String procedimento;

    public Ricetta(String nome, List<Ingrediente> ingredienti, String procedimento) {
        this.nome = nome;
        this.ingredienti = ingredienti;
        this.procedimento = procedimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public void aggiungiIngrediente(Ingrediente ingrediente) {
        ingredienti.add(ingrediente);
    }

    public void rimuoviIngrediente(Ingrediente ingrediente) {
        ingredienti.remove(ingrediente);
    }

    @Override
    public String toString() {
        return "Ricetta{" +
                "nome='" + nome + '\'' +
                ", ingredienti=" + ingredienti +
                ", procedimento='" + procedimento + '\'' +
                '}';
    }
}

