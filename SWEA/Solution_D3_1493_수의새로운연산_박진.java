// D3 - 1493 : 수의 새로운 연산
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T;       // 테스트 케이스의 수
    static int p, q;    // 두 정수 p,q
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
         
         
        // T개의 테스트 케이스
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            // 입력
            st = new StringTokenizer(br.readLine(), " ");
            p = Integer.parseInt(st.nextToken());
            q = Integer.parseInt(st.nextToken());
             
            // 알고리즘
            int iForResult = 0;
            int jForResult = 0;
             
            int x = 1;
            int y = 1;
            int iteration = 1;
            int value = 1;
            while (value != p) {
                iteration++;
                x = 1;
                y = iteration;
                while(true) {
                    value++;
                    if (value == p) break;
                    if ((x == iteration) && (y == 1)) break;
                    x++;
                    y--;
                }
            }
             
            iForResult += x;
            jForResult += y;
//          System.out.println("x = " + x);
//          System.out.println("y = " + y);
             
            x = 1;
            y = 1;
            iteration = 1;
            value = 1;
            while (value != q) {
                iteration++;
                x = 1;
                y = iteration;
                while(true) {
                    value++;
                    if (value == q) break;
                    if ((x == iteration) && (y == 1)) break;
                    x++;
                    y--;
                }
            }
             
            iForResult += x;
            jForResult += y;
//          System.out.println("x = " + x);
//          System.out.println("y = " + y);
             
//          System.out.println("iForResult = " + iForResult);
//          System.out.println("jForResult = " + jForResult);
             
            x = 1;
            y = 1;
            iteration = 1;
            value = 1;
        L:  while (true) {
                iteration++;
                x = 1;
                y = iteration;
                while(true) {
                    value++;
                    if (x == iForResult && y == jForResult) break L;
                    if ((x == iteration) && (y == 1)) break;
                    x++;
                    y--;
                }
            }
             
            sb.append("#").append(tc).append(" ").append(value).append("\n");
        }
        // 출력
        System.out.println(sb);
    }
}
