// D4 - 9282 : 초콜릿과 건포도

/*
memoization을 이용하여 시간초과 해결 후, 조금 더 최적화
*/

import java.util.Arrays;
import java.util.Scanner;

public class Solution_D4_9282_초콜릿과건포도_해답3_Memoization_최적화 {
	static int result;
	static int n, m; // 행, 열 갯수
	static int[][] map;
	static int[][][][] dp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt();
		for (int t = 1; t <= TC; t++) {
			n = sc.nextInt();
			m = sc.nextInt();
			map = new int[n][m];
			dp = new int[n + 1][m + 1][n + 1][m + 1];
			for (int[][][] d1 : dp) {
				for (int[][] d2 : d1) {
					for (int[] d3 : d2) {
						Arrays.fill(d3, Integer.MAX_VALUE);
					}
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					map[i][j] = sc.nextInt();
				}
			}
//			사이즈정해져있으면
//			처리
			result = dfs(0, 0, n, m);
//			출력
			System.out.println("#" + t + " " + result);
		}

	}

	static int dfs(int y, int x, int h, int w) {
// 		종료
		if (w == 1 && h == 1) {
			return 0;
		}
		if (dp[y][x][h][w] != Integer.MAX_VALUE) {
			return dp[y][x][h][w];
		}
// 		실행
// 		기존에 있던 덩어리의 건포도 개수
		int sum = 0;
		for (int i = y; i < y + h; i++) {
			for (int j = x; j < x + w; j++) {
				sum += map[i][j];
			}
		}
// 		가로로 잘라서 비용을 최소비용을 구한다.
		for (int i = 1; i < h; i++) {
// 			위쪽 비용
			if (dp[y][x][i][w] == Integer.MAX_VALUE) {
				dp[y][x][i][w] = dfs(y, x, i, w);
			}
// 			아래쪽 비용
			if (dp[y + i][x][h - i][w] == Integer.MAX_VALUE) {
				dp[y + i][x][h - i][w] = dfs(y + i, x, h - i, w);
			}
			int sum3 = sum + dp[y][x][i][w] + dp[y + i][x][h - i][w];
			dp[y][x][h][w] = Math.min(dp[y][x][h][w], sum3);
		}
// 		세로로 잘라서 비용을 최소비용을 구한다.
		for (int i = 1; i < w; i++) {
// 			왼쪽 비용
			if (dp[y][x][h][i] == Integer.MAX_VALUE) {
				dp[y][x][h][i] = dfs(y, x, h, i);
			}
// 			오른쪽 비용
			if (dp[y][x + i][h][w - i] == Integer.MAX_VALUE) {
				dp[y][x + i][h][w - i] = dfs(y, x + i, h, w - i);
			}
			// int sum3 = sum + dp[y][x][h][i] + dp[y][x+i][h][w-i];
			dp[y][x][h][w] = Math.min(dp[y][x][h][w], sum + dp[y][x][h][i] + dp[y][x + i][h][w - i]);
		}
// 		재귀호출
		return dp[y][x][h][w];
	}

}
