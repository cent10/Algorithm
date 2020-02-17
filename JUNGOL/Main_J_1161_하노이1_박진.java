// Beginner Coder - 1161 : 하노이1
 
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
         
        hanoi(N, 1, 2, 3);
         
        sc.close();
    }
     
    private static void hanoi(int n, int from, int by, int to) {
        if (n == 0) return;
         
        hanoi(n-1, from, to, by);
        System.out.println(n + " : " + from + " -> " + to);
        hanoi(n-1, by, from, to);
    }
}
