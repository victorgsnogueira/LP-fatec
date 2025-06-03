package fatec.classes;

public class Bicicleta extends Veiculo {
    private int numMarchas;
    private boolean temFreioDisco;

    public Bicicleta(String marca, String modelo, int ano, double peso, int numMarchas, boolean temFreioDisco) {
        super(marca, modelo, ano, peso);
        this.numMarchas = numMarchas;
        this.temFreioDisco = temFreioDisco;
    }

    public int getNumMarchas() {
        return numMarchas;
    }

    public void setNumMarchas(int numMarchas) {
        this.numMarchas = numMarchas;
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