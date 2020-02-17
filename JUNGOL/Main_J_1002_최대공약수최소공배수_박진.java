// Beginner Coder - 1002 : 최대공약수, 최소공배수
 
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;  // 입력 받을 정수의 개수
        n = sc.nextInt();
         
        int[] dataArr = new int[n]; // 입력 받을 정수들을 저장할 배열
        for (int i = 0; i < n; i++)
        {
            dataArr[i] = sc.nextInt();
        }
         
        int gcd = dataArr[0];   // 최대공약수
        int lcm = dataArr[0];   // 최소공배수
 
        for (int i = 1; i < n; i++)
        {
            gcd = get_gcd(gcd, dataArr[i]);
            lcm = lcm / get_gcd(lcm, dataArr[i]) * dataArr[i];
        }
        System.out.println(gcd + " " + lcm);
        sc.close();
    }
 
    private static int get_gcd(int gcd, int dataArr) {
        int temp;
        int result = 1;
         
        if (gcd < dataArr)   // 작은 값이 앞에 있으면, 큰 값을 앞으로 오도록 교환
        {
            temp = dataArr;
            dataArr = gcd;
            gcd = temp;
        }
         
        for (int i = 2; i <= dataArr; i++)
        {
            if ( (gcd % i == 0) && (dataArr % i == 0) )
            {
                gcd = gcd / i;
                dataArr = dataArr / i;
                 
                result = result * i;
                 
                i--;
            }
        }
         
        return result;
    }
}
