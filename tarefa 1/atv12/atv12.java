package atv12;

import java.util.Scanner;

public class atv12 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.print("Digite o primeiro valor: ");
        int valor1 = ler.nextInt();

        System.out.print("Digite o segundo valor: ");
        int valor2 = ler.nextInt();

        if (valor1 > valor2) {
            System.out.println("O maior valor é: " + valor1);
            return;
        }
        System.out.println("O maior valor é: " + valor2);
    }
}