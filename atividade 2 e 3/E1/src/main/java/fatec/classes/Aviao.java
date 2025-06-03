package fatec.classes;

public class Aviao extends Veiculo {
    private int numeroMotores;
    private double envergadura;

    public Aviao(String marca, String modelo, int ano, double peso, int numeroMotores, double envergadura) {
        super(marca, modelo, ano, peso);
        this.numeroMotores = numeroMotores;
        this.envergadura = envergadura;
    }

    public int getNumeroMotores() {
        return numeroMotores;
    }

    public void setNumeroMotores(int numeroMotores) {
        this.numeroMotores = numeroMotores;
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public void setEnvergadura(double envergadura) {
        this.envergadura = envergadura;
    }

    @Override
    public String Acelerar() {
        return ("O avião está aumentando a velocidade para decolar.");
    }

    @Override
    public String Frear() {
        return ("O avião está reduzindo a velocidade para pousar.");
    }

    public String Decolar() {
        return ("O avião está decolando.");
    }

    public String Pousar() {
        return ("O avião está pousando.");
    }
} 