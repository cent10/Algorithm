// Intermediate Coder - 3522 : Tutorial 동적계획법(Dynamic Programming)
 
import java.util.Scanner;
 
public class Main {
 
    static long[] memory_fibo = new long[100001];
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        long result;
         
        memory_fibo[1] = 1;
        memory_fibo[2] = 1;
         
        result = dynamicFibonacci(N);
        System.out.println(result);
         
        sc.close();
    }
     
    private static long dynamicFibonacci(int n) {
        for (int i = 3; i <= n; i++) {
            memory_fibo[i] = memory_fibo[i-1] + memory_fibo[i-2];
            memory_fibo[i] %= 1000000007;
        }
         
        return memory_fibo[n];
    }
}
