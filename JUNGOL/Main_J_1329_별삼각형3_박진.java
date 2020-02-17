import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 높이
         
        if ( (N < 1) || (N > 100) || (N % 2 == 0) ) {
            System.out.println("INPUT ERROR!");
            sc.close();
            return;
        }
         
        for (int i = 1; i <= N/2; i++) {
            for (int j = 1; j < i; j++) {
                System.out.print(" ");
            }
             
            for (int j = 1; j <= (i-1)*2+1; j++) {
                System.out.print("*");
            }
             
            System.out.println();
        }
 
        for (int i = N/2+1; i <= N; i++) {
            for (int j = i; j <= N-1; j++) {
                System.out.print(" ");
            }
             
            for (int j = i*2-1; j < N*2; j++) {
                System.out.print("*");
            }
             
            System.out.println();
        }
         
        sc.close();
    }
}
