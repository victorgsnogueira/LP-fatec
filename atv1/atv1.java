import java.util.Scanner;

public class atv1 {
    public static void main(String[] args) {
        int idade, anos, meses, dias;

        Scanner ler = new Scanner(System.in);
        System.out.print("Entre com o valor de anos:");
        anos = ler.nextInt();
        System.out.print("Entre com o valor de meses:");
        meses = ler.nextInt();
        System.out.print("Entre com o valor de dias:");
        dias = ler.nextInt();


        idade = anos*365 + meses*30 + dias;

        System.out.println(idade + " dias");

        
    }
}
