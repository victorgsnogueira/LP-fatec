package atv5;

import java.util.Scanner;

public class atv5 {
    public static void main(String[] args) {
        int numCarros;
        double comissaoVariavel = 0, salarioFixo, comissaoFixa, total;
        float[] valorCarro;
        Scanner ler = new Scanner(System.in);

        System.out.print("Entre com o salario fixo: ");
        salarioFixo = ler.nextFloat();

        System.out.print("Entre com a comissao fixa: ");
        comissaoFixa = ler.nextFloat();

        System.out.print("Quantos carros foram vendidos? ");
        numCarros = ler.nextInt();
        valorCarro = new float[numCarros];

        for(int i = 0; i < numCarros; i++) {
        System.out.print("Qual foi o valor por carro vendido? ");

        valorCarro[i] = ler.nextFloat();

        comissaoVariavel = comissaoVariavel + valorCarro[i] * 0.05;
        }

        total = salarioFixo + (comissaoFixa * numCarros) + comissaoVariavel;
        System.out.println("O total a ser pago é: " + total);
    }
}