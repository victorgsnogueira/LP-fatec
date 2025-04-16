package fatec.classes;

public class Felino extends Animal {
    private boolean gostaDeDormir;

    public Felino(String nome, int idade, double peso, boolean gostaDeDormir) {
        super(nome, idade, peso);
        this.gostaDeDormir = gostaDeDormir;
    }

    public boolean isGostaDeDormir() {
        return gostaDeDormir;
    }

    public void setGostaDeDormir(boolean gostaDeDormir) {
        this.gostaDeDormir = gostaDeDormir;
    }

    @Override
    public String FazerSom() {
        return (getNome() + " está miando.");
    }

    public String EscalarArvore() {
        return (getNome() + " está escalando uma árvore.");
    }

    public String CacaPresas() {
        return (getNome() + " está caçando presas.");
    }
}
