package atv10;
import java.util.Scanner;

public class atv10 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.print("Digite a nota da primeira avaliação: ");
        double nota1 = ler.nextDouble();

        System.out.print("Digite a nota da segunda avaliação: ");
        double nota2 = ler.nextDouble();

        double media = (nota1 + nota2) / 2;

        System.out.println("Média: " + media);

        if (media >= 6) {
            System.out.println("Aluno aprovado!");
            return;
        }
            System.out.println("Aluno reprovado!");
    }
}