package fatec.classes;

public class Ave extends Animal {
    private boolean podeVoar;
    private double envergadura;

    public Ave(String nome, int idade, double peso, boolean podeVoar, double envergadura) {
        super(nome, idade, peso);
        this.podeVoar = podeVoar;
        this.envergadura = envergadura;
    }

    public boolean isPodeVoar() {
        return podeVoar;
    }

    public void setPodeVoar(boolean podeVoar) {
        this.podeVoar = podeVoar;
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public void setEnvergadura(double envergadura) {
        this.envergadura = envergadura;
    }

    @Override
    public String FazerSom() {
        return (getNome() + " está cantando.");
    }

    public String Voar() {
        if (podeVoar) {
            return (getNome() + " está voando.");
        }
        return (getNome() + " não pode voar.");
    }

    public String ConstruirNinho() {
        return (getNome() + " está construindo um ninho.");
    }
} 