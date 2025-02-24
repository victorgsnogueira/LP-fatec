package atv13;

import java.util.Scanner;

public class atv13 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.print("Digite o primeiro valor:");
        int valor1 = ler.nextInt();

        System.out.print("Digite o segundo valor:");
        int valor2 = ler.nextInt();

        if (valor1 < valor2) {
            System.out.println("Ordem crescente: " + valor1 + " " + valor2);
            return;
        }
        System.out.println("Ordem crescente: " + valor2 + " " + valor1);
    }
}