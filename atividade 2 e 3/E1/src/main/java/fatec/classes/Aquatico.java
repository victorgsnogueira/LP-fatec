package fatec.classes;

public class Aquatico extends Animal {
    private double profundidadeMaxima;

    public Aquatico(String nome, int idade, double peso, double profundidadeMaxima) {
        super(nome, idade, peso);
        this.profundidadeMaxima = profundidadeMaxima;
    }

    public double getProfundidadeMaxima() {
        return profundidadeMaxima;
    }

    public void setProfundidadeMaxima(double profundidadeMaxima) {
        this.profundidadeMaxima = profundidadeMaxima;
    }

    @Override
    public String FazerSom() {
        return(getNome() + " está fazendo som de animal aquático.");
    }

    public String Nadar() {
        return(getNome() + " está nadando.");
    }

    public String Mergulhar() {
        return(getNome() + " está mergulhando.");
    }
}
