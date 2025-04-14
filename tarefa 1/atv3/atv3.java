package atv3;

import java.util.Scanner;

public class atv3 {
    public static void main(String[] args) {
        float salario, reajuste, novoSalario;
        Scanner ler = new Scanner(System.in);

        System.out.print("Entre com o salário mensal atual do funcionário: ");
        salario = ler.nextFloat();
        System.out.print("Entre com o percentual de reajuste: ");
        reajuste = ler.nextFloat();

        novoSalario = salario + (salario * reajuste / 100);

        System.out.printf("O novo salário do funcionário é: %.2f%n", novoSalario);
    }
}