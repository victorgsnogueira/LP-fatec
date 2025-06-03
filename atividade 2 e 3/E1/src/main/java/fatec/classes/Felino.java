package fatec.classes;

public class Felino extends Animal {
    private double tamanhoGarras;

    public Felino(String nome, int idade, double peso, double tamanhoGarras) {
        super(nome, idade, peso);
        this.tamanhoGarras = tamanhoGarras;
    }

    public double getTamanhoGarras() {
        return tamanhoGarras;
    }

    public void setTamanhoGarras(double tamanhoGarras) {
        this.tamanhoGarras = tamanhoGarras;
    }

    @Override
    public String FazerSom() {
        return (getNome() + " está miando.");
    }

    public String SubirArvore() {
        return (getNome() + " está subindo em uma árvore.");
    }

    public String Cacar() {
        return (getNome() + " está caçando presas.");
    }
}
