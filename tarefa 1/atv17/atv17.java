package atv17;

import java.util.Scanner;

public class atv17 {
    public static void main(String[] args) {
        double P1, E1, E2, API, SUB, X, NOTA;
        String aluno, PONTO;

        Scanner ler = new Scanner(System.in);
        
        System.out.print("Digite o nome do(a) aluno(a): ");
        aluno = ler.nextLine();

        System.out.print("Digite a nota da P1: ");
        P1 = ler.nextDouble();

        System.out.print("Digite a nota da E1: ");
        E1 = ler.nextDouble();

        System.out.print("Digite a nota da E2: ");
        E2 = ler.nextDouble();

        System.out.print("Digite a nota da API: ");
        API = ler.nextDouble();

        System.out.print("Digite a nota da SUB: ");
        SUB = ler.nextDouble();

        ler.nextLine();

        System.out.print("Tem Ponto Extra?(sim ou nao) ");
        PONTO = ler.nextLine();

        String RESP = PONTO.toLowerCase();       
        if(RESP.equals("sim")){
            System.out.print("Digite a nota do Ponto Extra: ");
            X = ler.nextDouble();
        }
        else{
            X = 0;
        }

        NOTA = (P1*0.6+((E1+E2)/2)*0.4)*0.5+(Math.max(((P1*0.6+((E1+E2)/2)*0.4)-5.9),0)/((P1*0.6+((E1+E2)/2)*0.4)-5.9))*(API*0.5)+X+(SUB*0.3);

        if(NOTA > 10){
            NOTA = 10;
        }
        
        System.out.println("A nota final do(a) aluno(a) "+aluno+" é: "+NOTA);
    }

}
