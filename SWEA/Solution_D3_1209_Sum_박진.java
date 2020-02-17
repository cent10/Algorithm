// D3 - 1290 : [S/W 문제해결 기본] 2일차 - Sum
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int[][] arr = new int[100][100];
    static int maxOfSum;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        // 총 10개의 테스트 케이스
        for (int tc = 1; tc <= 10; tc++) {
            int sum;
            maxOfSum = 0;
            br.readLine();  // 테스트 케이스 번호 버리기
             
            // 배열 입력
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
//          for(int[] a : arr) {
//              System.out.println(Arrays.toString(a));
//          }
             
            // 각 행의 합
            for (int i = 0; i < 100; i++) {
                sum = 0;
                for (int j = 0; j < 100; j++) {
                    sum += arr[i][j];
                }
                if (maxOfSum < sum) {
                    maxOfSum = sum;
                }
            }
             
            // 각 열의 합
            for (int i = 0; i < 100; i++) {
                sum = 0;
                for (int j = 0; j < 100; j++) {
                    sum += arr[j][i];
                }
                if (maxOfSum < sum) {
                    maxOfSum = sum;
                }
            }
             
            // 첫번째 대각선의 합
            sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += arr[i][i];
            }
            if (maxOfSum < sum) {
                maxOfSum = sum;
            }
             
            // 두번째 대각선의 합
            sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += arr[i][100-i-1];
            }
            if (maxOfSum < sum) {
                maxOfSum = sum;
            }
             
            sb.append("#").append(tc).append(" ").append(maxOfSum).append("\n");
        }
        System.out.println(sb);
    }
}
