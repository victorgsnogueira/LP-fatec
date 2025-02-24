package atv4;

import java.util.Scanner;

public class atv4 {
    public static void main(String[] args) {
        double custoFabrica, percentualDistribuidor, impostos, custoFinal;

        Scanner ler = new Scanner(System.in);

        System.out.print("Qual o valor de custo de fabrica: ");
        custoFabrica = ler.nextDouble();

        percentualDistribuidor = 0.28;
        impostos = 0.45;

        custoFinal = custoFabrica + (custoFabrica * percentualDistribuidor) + (custoFabrica * impostos);

        System.out.println("O custo final do carro é: " + custoFinal);
    }
}