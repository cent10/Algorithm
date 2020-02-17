// Beginner Coder - 1523 : 별삼각형1

import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 높이
        int M = sc.nextInt();   // 종류
         
        if (N < 1 || N > 100) {
            System.out.println("INPUT ERROR!");
            sc.close();
            return;
        }
         
        switch(M) {
        case 1: // 정류1
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i+1; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            break;
             
        case 2: // 정류2
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            break;
             
        case 3: // 정류3
            for (int i = 0; i < N; i++) {
                for (int j = i+1; j < N; j++) {
                    System.out.print(" ");
                }
                for (int j = 0; j < (i+1)*2-1; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            break;
             
        default:
            System.out.println("INPUT ERROR!");
            break;
        }
         
        sc.close();
    }
}
