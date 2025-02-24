package atv8;
import java.util.Scanner;

public class atv8 {
    public static void main(String[] args) {
        int valor;

        Scanner ler = new Scanner(System.in);

        System.out.print("Digite um valor: ");
        valor = ler.nextInt();

        if (valor >= 0) {
            System.out.println("O valor é positivo.");
            return;
        }
            System.out.println("O valor é negativo.");
    }
}