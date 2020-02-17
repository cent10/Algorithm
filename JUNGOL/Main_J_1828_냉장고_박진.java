// Intermediate Coder - 1828 : 냉장고
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
    static int N;       // N개의 화학 물질
    static int[][] C;   // 각 Ci마다 최저 보관 온도 xi와 최고 보관 온도 yi
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        C = new int[N][2];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            C[i][0] = Integer.parseInt(st.nextToken());
            C[i][1] = Integer.parseInt(st.nextToken());
            // System.out.println(C[i][0] + " " + C[i][1]);
        }
        // 정렬
        int temp = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (C[i][0] > C[j][0]) {
                    temp = C[j][0];
                    C[j][0] = C[i][0];
                    C[i][0] = temp;
                    temp = C[j][1];
                    C[j][1] = C[i][1];
                    C[i][1] = temp;
                }
            }
        }
//      for (int i = 0; i < N; i++) {
//          System.out.println(C[i][0] + " " + C[i][1]);
//      }
         
        int result = 0;
        int tempIndex;
        int maxNum = 0;
        for (int i = 0; i < N; i++) {
            maxNum = 0;
            result++;
            for (int j = C[i][0]; j <= C[i][1]; j++) {
                tempIndex = 0;
                for (int k = i + 1; k < N; k++) {
                    if (j >= C[k][0] && j <= C[k][1]) {
                        tempIndex++;
                    } else break;
                }
                if (maxNum < tempIndex)
                    maxNum = tempIndex;
            }
            i += maxNum;
        }
        System.out.println(result);
    }
 
}
