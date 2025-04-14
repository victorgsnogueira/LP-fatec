package atv15;
import java.util.Scanner;

public class atv15 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.print("Digite o número de horas trabalhadas no mês: ");
        int horasTrabalhadas = ler.nextInt();
        
        System.out.print("Digite o salário por hora: ");
        double salarioPorHora = ler.nextDouble();
        
        int horasSemanais = 40;
        int semanasNoMes = 4;
        int horasNormais = horasSemanais * semanasNoMes;
        double salarioTotal;
        
        if (horasTrabalhadas > horasNormais) {
            int horasExtras = horasTrabalhadas - horasNormais;
            double valorHoraExtra = salarioPorHora * 1.5;
            salarioTotal = (horasNormais * salarioPorHora) + (horasExtras * valorHoraExtra);
        } else {
            salarioTotal = horasTrabalhadas * salarioPorHora;
        }
        
        System.out.printf("O salário total é: R$ %.2f\n", salarioTotal);
    }
}