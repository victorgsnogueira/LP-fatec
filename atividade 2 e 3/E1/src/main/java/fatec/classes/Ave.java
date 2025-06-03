package fatec.classes;

public class Ave extends Animal {
    private double envergadura;
    private boolean voa;
    private int id;

    public Ave(String nome, int idade, double peso, double envergadura, boolean voa) {
        super(nome, idade, peso);
        this.envergadura = envergadura;
        this.voa = voa;
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public void setEnvergadura(double envergadura) {
        this.envergadura = envergadura;
    }

    public boolean isVoa() {
        return voa;
    }

    public void setVoa(boolean voa) {
        this.voa = voa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String FazerSom() {
        return "A ave " + getNome() + " está cantando!";
    }

    public String Voar() {
        if (voa) {
            return "A ave " + getNome() + " está voando!";
        }
        return "A ave " + getNome() + " não pode voar!";
    }

    public String FazerNinho() {
        return "A ave " + getNome() + " está construindo seu ninho!";
    }
} 