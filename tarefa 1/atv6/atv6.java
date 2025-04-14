package atv6;
import java.util.Scanner;

public class atv6 {
    public static void main(String[] args) {
        double f, c;

        Scanner ler = new Scanner(System.in);


        System.out.print("Digite a temperatura em graus Fahrenheit: ");
        f = ler.nextDouble();

        c = (f - 32) * 5 / 9;

        System.out.println("A temperatura em graus Celsius é: " + c);
    }
}