package fatec.classes;

public class Bicicleta extends Veiculo {
    private int numeroMarchas;
    private boolean temFreioDisco;

    public Bicicleta(String marca, String modelo, int ano, double peso, int numeroMarchas, boolean temFreioDisco) {
        super(marca, modelo, ano, peso);
        this.numeroMarchas = numeroMarchas;
        this.temFreioDisco = temFreioDisco;
    }

    public int getNumeroMarchas() {
        return numeroMarchas;
    }

    public void setNumeroMarchas(int numeroMarchas) {
        this.numeroMarchas = numeroMarchas;
    }

    public boolean isTemFreioDisco() {
        return temFreioDisco;
    }

    public void setTemFreioDisco(boolean temFreioDisco) {
        this.temFreioDisco = temFreioDisco;
    }

    @Override
    public String Acelerar() {
        return ("A bicicleta está sendo pedalada mais rápido.");
    }

    @Override
    public String Frear() {
        return ("A bicicleta está freando.");
    }

    public String TrocarMarcha() {
        return ("A bicicleta está trocando de marcha.");
    }
} 