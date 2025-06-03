package fatec.classes;

public class Mamifero extends Animal {
    private boolean temPelos;
    private String tipoAlimentacao;

    public Mamifero(String nome, int idade, double peso, boolean temPelos, String tipoAlimentacao) {
        super(nome, idade, peso);
        this.temPelos = temPelos;
        this.tipoAlimentacao = tipoAlimentacao;
    }

    public boolean isTemPelos() {
        return temPelos;
    }

    public void setTemPelos(boolean temPelos) {
        this.temPelos = temPelos;
    }

    public String getTipoAlimentacao() {
        return tipoAlimentacao;
    }

    public void setTipoAlimentacao(String tipoAlimentacao) {
        this.tipoAlimentacao = tipoAlimentacao;
    }

    @Override
    public String FazerSom() {
        return (getNome() + " está fazendo som de mamífero.");
    }

    public String Amamentar() {
        return (getNome() + " está amamentando seus filhotes.");
    }

    public String Dormir() {
        return (getNome() + " está dormindo.");
    }
} 