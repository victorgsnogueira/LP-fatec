package fatec.classes;

public class Aviao extends Veiculo {
    private int numMotores;
    private double envergadura;

    public Aviao(String marca, String modelo, int ano, double peso, int numMotores, double envergadura) {
        super(marca, modelo, ano, peso);
        this.numMotores = numMotores;
        this.envergadura = envergadura;
    }

    public int getNumMotores() {
        return numMotores;
    }

    public void setNumMotores(int numMotores) {
        this.numMotores = numMotores;
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