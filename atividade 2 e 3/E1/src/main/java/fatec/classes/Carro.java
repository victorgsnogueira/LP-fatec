package fatec.classes;

public class Carro extends Veiculo {

    private int numPortas;

    public Carro(String marca, String modelo, int ano, double peso, int numPortas) {
        super(marca, modelo, ano, peso);
        this.numPortas = numPortas;
    }

    public int getNumPortas() {
        return numPortas;
    }

    public void setNumPortas(int numPortas) {
        this.numPortas = numPortas;
    }

    @Override
    public String Acelerar() {
        return ("O carro está acelerando.");
    }

    @Override
    public String Frear() {
        return ("O carro está freando.");
    }

    public String AbrirPorta() {
        return ("A porta do carro está sendo aberta.");
    }
}
