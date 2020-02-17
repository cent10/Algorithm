// Beginner Coder - 1901 : 소수 구하기
 
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 처리해야할 수의 개수
        int[] M = new int[N];   // 처리해야할 N개의 수를 저장할 배열
         
        for (int i = 0; i < N; i++)
        {
            M[i] = sc.nextInt();
             
            int numForChecking = 0; // 소수인지 아닌지 체크할 숫자를 넣을 변수
            int minusCnt = 0;       // M보다 작으면서 가장 가까운 소수가 얼마나 떨어져있는지 카운팅하는 변수
            int plusCnt = 0;        // M보다 크면서 가장 가까운 소수가 얼마나 떨어져있는지 카운팅하는 변수
            int smallerPrimeNum;    // M보다 작으면서 가장 가까운 소수를 저장할 변수
            int biggerPrimeNum;     // M보다 크면서 가장 가까운 소수를 저장할 변수
             
            if (isPrimeNum(M[i]))   // 자기자신이 소수이면 자기 출력
            {
                System.out.println(M[i]);
                continue;
            }
             
            int temp = M[i];
             
            while(true) // M보다 작으면서 가장 가까운 소수를 찾기 위한 반복문
            {
                numForChecking = --temp;
                minusCnt++;
                if (isPrimeNum(numForChecking))
                {
                    smallerPrimeNum = numForChecking;
                    break;
                }
            }
             
            temp = M[i];
 
            while(true) // M보다 크면서 가장 가까운 소수를 찾기 위한 반복문 
            {
                numForChecking = ++temp;
                plusCnt++;
                if (isPrimeNum(numForChecking))
                {
                    biggerPrimeNum = numForChecking;
                    break;
                }
            }
             
            if (minusCnt > plusCnt)  // M보다 큰 가장 가까운 소수  출력
            {
                System.out.println(biggerPrimeNum);
            }
            else if (minusCnt < plusCnt) // M보다 작은 가장 가까운 소수 출력
            {
                System.out.println(smallerPrimeNum);
            }
            else    // 차이가 같으므로 가장 가까운 두 개의 소수 모두 출력
            {
                System.out.println(smallerPrimeNum + " " + biggerPrimeNum);
            }
        }
    }
 
    // 주어진 수가 소수(prime number)인지 확인하는 메소드
    public static boolean isPrimeNum(int n)
    {
        int end = n/2;
         
        if (n == 1) // 1은 소수가 아님
        {
            return false;
        }
        else if (n == 2)    // 2는 소수임
        {
            return true;
        }
        else
        {
            for (int i = 2; i <= end; i++)
            {
                if ((n % i) == 0)
                {
                    return false;   // 1과 자기 자신 외에는 약수를 가지면 소수가 아님
                }
            }
            return true;    // 1과 자기 자신 외에는 약수를 갖지 않으면 소수임
        }
    }
}
