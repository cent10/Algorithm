// Beginner Coder - 2074 : 홀수 마방진
 
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        int n; // 정사각형의 크기
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] arr = new int[n][n]; // 정사각형 배열
        int row = 0; // arr배열의 행 인덱스
        int col = n / 2; // arr배열의 열 인덱스
        int num = 1; // 정사각형에 넣을 숫자
 
        arr[row][col] = num; // 첫 번째 숫자인 1을 넣는 위치는 첫 번째 행 가운데이다.
 
        while (num != n * n) {
            num++;
            if (((num - 1) % n) != 0) // 숫자가 N의 배수가 아니면 왼쪽 위로 이동하여 다음의 숫자를 넣는다.
            {
                if (col == 0) {
                    col = n - 1;
                } else {
                    col--;
                }
 
                if (row == 0) {
                    row = n - 1;
                } else {
                    row--;
                }
            } else // 숫자가 N의 배수이면 바로 아래의 행으로 이동하여 다음의 수를 넣고
            {
                if (row == n - 1) {
                    row = 0;
                } else {
                    row++;
                }
            }
            arr[row][col] = num;
        }
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
 
        sc.close();
    }
}
