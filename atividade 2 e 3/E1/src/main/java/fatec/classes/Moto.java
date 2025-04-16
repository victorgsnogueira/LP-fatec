package fatec.classes;

public class Moto extends Veiculo {

    private boolean temCarenagem;

    public Moto(String marca, String modelo, int ano, double peso, boolean temCarenagem) {
        super(marca, modelo, ano, peso);
        this.temCarenagem = temCarenagem;
    }

    public boolean isTemCarenagem() {
        return temCarenagem;
    }

    public void setTemCarenagem(boolean temCarenagem) {
        this.temCarenagem = temCarenagem;
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

