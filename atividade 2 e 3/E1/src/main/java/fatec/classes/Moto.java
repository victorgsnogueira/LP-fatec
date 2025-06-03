package fatec.classes;

public class Moto extends Veiculo {

    private int cilindrada;

    public Moto(String marca, String modelo, int ano, double peso, int cilindrada) {
        super(marca, modelo, ano, peso);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }

    @Override
    public String Acelerar() {
        return ("A moto está acelerando.");
    }

    @Override
    public String Frear() {
        return ("A moto está freando.");
    }

    public String Empinar() {
        return ("A moto está empinando.");
    }
}

