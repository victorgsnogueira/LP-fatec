package fatec.classes;

public abstract class Veiculo {
    private String marca;
    private String modelo;
    private int ano;
    private double peso;

    public Veiculo(String marca, String modelo, int ano, double peso) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.peso = peso;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public abstract String Acelerar();

    public abstract String Frear();
}
