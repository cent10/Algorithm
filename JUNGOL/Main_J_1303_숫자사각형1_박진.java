// Beginner Coder - 1303 : 숫자사각형1
 
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;   // n:높이, m:너비
        n = sc.nextInt();
        m = sc.nextInt();
        int arr[][] = new int[n][m];
        int num = 1;
         
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                arr[i][j] = num;
                num++;
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
