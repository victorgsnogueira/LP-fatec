package atv7;

import java.util.Scanner;

public class atv7 {
    public static void main(String[] args) {
        int valor;
        Scanner ler = new Scanner(System.in);

        System.out.print("Digite um valor: ");
        valor = ler.nextInt();

        if (valor > 10) {
            System.out.println("maior que 10");
            return;
        } 
            System.out.println("menor que 10");
    }
}