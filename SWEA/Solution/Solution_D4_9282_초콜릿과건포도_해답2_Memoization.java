// D4 - 9282 : 초콜릿과 건포도
 
/*
memoization을 이용하여 시간초과 해결
*/

import java.util.*;
 
public class Solution_D4_9282_초콜릿과건포도_해답2_Memoization {
 
    static int result;
    static int n, m;    // 행과 열의 개수
    static int[][] map;
    static int[][][][] dp;  // y값, x값, h값, w값
     
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            m = sc.nextInt();
            map = new int[n][m];
            dp = new int[n+1][m+1][n+1][m+1];   // 경계검사를 하지 않기 위해 +1을 해줌.
            for(int[][][] d1 : dp) {    // dp 초기화
                for(int[][] d2 : d1) {
                    for(int[] d3 : d2) {
                        Arrays.fill(d3, Integer.MAX_VALUE);
                    }
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
//          사이즈가 정해져있으면
//          처리
            result = dfs(0, 0, n, m);
//          출력
            System.out.println("#" + tc + " " + result);
        }
    }
 
    private static int dfs(int y, int x, int h, int w) {
//      종료 (단일조각이 될 경우(height와 width가 모두 1이 될 경우))
        if (h == 1 && w == 1) {
            return 0;
        }
        if(dp[y][x][h][w] != Integer.MAX_VALUE) {   // 어떠한 초콜릿 덩어리의 값은 변하지가 않기 때문에, 이미 한번 구했다면 아래 과정을 생략하는 것.
            return dp[y][x][h][w];
        }
//      실행
//      기존에 있던 초콜릿 덩어리의 건포도 개수
        int sum = 0;
        for (int i = y; i < y + h; i++) {
            for (int j = x; j < x + w; j++) {
                sum += map[i][j];
            }
        }
//      가로로 잘라서 비용을 최소비용을 구한다.
        for (int i = 1; i < h; i++) {
//          위쪽 비용
            int sum1 = dfs(y, x, i, w);
//          아래쪽 비용
            int sum2 = dfs(y + i, x, h-i, w);
            int sum3 = sum + sum1 + sum2;
            dp[y][x][h][w] = Math.min(dp[y][x][h][w], sum3);
        }
//      세로로 잘라서 비용을 최소비용을 구한다.
        for (int i = 1; i < w; i++) {
//          왼쪽 비용
            int sum1 = dfs(y, x, h, i);
//          오른쪽 비용
            int sum2 = dfs(y, x+i, h, w-i);
            int sum3 = sum + sum1 + sum2;
            dp[y][x][h][w] = Math.min(dp[y][x][h][w], sum3);
        }
//      재귀호출
        return dp[y][x][h][w];
    }
}