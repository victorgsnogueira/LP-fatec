package atv2;

import java.util.Scanner;

public class atv2 {
    public static void main(String[] args) {
        float total, branco, nulos, validos;

        Scanner ler = new Scanner(System.in);

        System.out.print("entre com a quantidade de votos brancos:");
        branco = ler.nextInt();
        System.out.print("entre com a quantidade de votos nulos:");
        nulos = ler.nextInt();
        System.out.print("entre com a quantidade de votos validos:");
        validos = ler.nextInt();

        total = validos + branco + nulos;

        branco = branco / total * 100;
        nulos = nulos / total * 100;
        validos = validos / total * 100;

        System.out.println("Total de votos: " + total);
        System.out.printf("Porcentagem de votos brancos: %.2f%n", branco);
        System.out.printf("Porcentagem de votos nulos: %.2f%n", nulos);
        System.out.printf("Porcentagem de votos válidos: %.2f%n", validos);

    }
}