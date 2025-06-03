package fatec.classes;

public class Reptil extends Animal {
    private boolean temEscamas;
    private String tipoPele;

    public Reptil(String nome, int idade, double peso, boolean temEscamas, String tipoPele) {
        super(nome, idade, peso);
        this.temEscamas = temEscamas;
        this.tipoPele = tipoPele;
    }

    public boolean isTemEscamas() {
        return temEscamas;
    }

    public void setTemEscamas(boolean temEscamas) {
        this.temEscamas = temEscamas;
    }

    public String getTipoPele() {
        return tipoPele;
    }

    public void setTipoPele(String tipoPele) {
        this.tipoPele = tipoPele;
    }

    @Override
    public String FazerSom() {
        return (getNome() + " está fazendo som de réptil.");
    }

    public String TrocarPele() {
        return (getNome() + " está trocando de pele.");
    }

    public String TomarSol() {
        return (getNome() + " está tomando sol para se aquecer.");
    }
} 