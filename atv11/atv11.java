package atv11;

import java.util.Scanner;

public class atv11 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.print("Digite o ano atual: ");
        int anoAtual = ler.nextInt();

        System.out.print("Digite o ano de nascimento: ");
        int anoNascimento = ler.nextInt();

        int idade = anoAtual - anoNascimento;

        if (idade >= 18) {
            System.out.println("Você pode votar este ano.");
            return;
        }
        System.out.println("Você não pode votar este ano.");
    }
}