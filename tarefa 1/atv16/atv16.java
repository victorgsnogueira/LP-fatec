package atv16;

public class atv16 {
    public static void main(String []args){
        double janeiro, fevereiro, marco, media, total;
        
        janeiro = 15000;
        fevereiro = 23000;
        marco = 17000;

        total = janeiro+fevereiro+marco;

        media = total/3;

        String mediaF = String.format("%.2f", media);

        System.out.println("O total gasto no trimestre é de: " + total);
        System.out.print("A media por mês é : " + mediaF);
        
        
    }
}