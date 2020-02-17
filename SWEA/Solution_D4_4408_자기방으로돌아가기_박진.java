// D4 - 4408 : 자기 방으로 돌아가기
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            int[] corridor = new int[200];
            int N = Integer.parseInt(br.readLine());
            int start;
            int destination;
             
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                start = Integer.parseInt(st.nextToken());
                destination = Integer.parseInt(st.nextToken());
                 
                if (start > destination) {
                    int temp = start;
                    start = destination;
                    destination = temp;
                }
                 
                if (start % 2 == 1) {
                    start = start/2;
                } else {
                    start = start/2-1;
                }
                if (destination % 2 == 1) {
                    destination = destination/2;
                } else {
                    destination = destination/2-1;
                }
                 
                for (int j = start; j <= destination; j++) {
                    corridor[j]++;
                }
            }
             
            Arrays.sort(corridor);
             
            sb.append("#").append(tc).append(" ").append(corridor[199]).append("\n");
        }
        System.out.println(sb);
    }
}
