package atv9;
import java.util.Scanner;

public class atv9 {
    public static void main(String[] args) {
        int quantidade;
        double custoTotal, preco;

        Scanner ler = new Scanner(System.in);
    

        System.out.print("Digite o número de maçãs compradas: ");
        quantidade = ler.nextInt();

        if (quantidade < 12) {
            preco = 1.30;
        } 
        else {
            preco = 1.00;
        }

        custoTotal = quantidade * preco;

        System.out.printf("O custo total da compra é R$ %.2f%n", custoTotal);
    }
}