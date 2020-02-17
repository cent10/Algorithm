// D3 - 1220 : [S/W 문제해결 기본] 5일차 - Magnetic
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    // 테이블 위에는 N극, 테이블 아래에는 S극
    // 1은 N극 성질을 가지는 자성체를 2는 S극 성질을 가지는 자성체를 의미
    static int[][] arr = new int[100][100]; // 테이블의 크기는 100x100
    static int result;  // 교착 상태의 개수
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        // 총 10개의 테스트 케이스
        for (int tc = 1; tc <= 10; tc++) {
            br.readLine();  // 정사각형 테이블의 한 변의 길이는 버린다.
            result = 0;
             
            for (int i = 0; i < 100; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
             
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    if (arr[j][i] == 1) {               // 1(N극 성질을 가지는 자성체)을 만났을 때:
                        for (int k = j+1; k < 100; k++) {
                            if (arr[k][i] == 2) {       // 2를 만나면 교착상태 +1
                                result++;
                                j = k;
                                break;
                            }
                            else if ( arr[k][i] == 1) { // 1을 만나면 그 위치부터 탐색
                                j = k - 1;
                                break;
                            }
                        }
                    }
                }
            }
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}
