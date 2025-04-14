package atv14;
import java.util.Scanner;

public class atv14 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        System.out.print("Digite a hora de início do jogo (0-23):");
        int horaInicio = ler.nextInt();

        System.out.print("Digite a hora de fim do jogo (0-23):");
        int horaFim = ler.nextInt();

        int duracaoJogo;
        if (horaFim >= horaInicio) {
            duracaoJogo = horaFim - horaInicio;
        } else {
            duracaoJogo = 24 - horaInicio + horaFim;
        }

        System.out.println("A duração do jogo foi de " + duracaoJogo + " horas.");
    }
}