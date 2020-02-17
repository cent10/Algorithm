// Beginner Coder - 1309 : 팩토리얼
 
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
         
        System.out.print(factorial(n));
 
        sc.close();
    }
     
    // 재귀를 이용한 팩토리얼 구하는 메소드
    public static long factorial(int n)
    {
        if (n == 1)
        {
            System.out.println("1! = 1");
            return 1;
        }
        System.out.println(n + "! = " + n + " * " + (n-1) + "!");
        return (n * factorial(n-1));
    }
}
