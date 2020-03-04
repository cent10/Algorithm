// D4 - 9282 : 초콜릿과 건포도

/*
완전탐색으로 풀기 때문에 시간초과 발생함.
*/

import java.util.*;

public class Solution_D4_9282_초콜릿과건포도_해답1_TLE {

	static int result;
	static int n, m;	// 행과 열의 개수
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			n = sc.nextInt();
			m = sc.nextInt();
			map = new int[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					map[i][j] = sc.nextInt();
				}
			}
//			사이즈가 정해져있으면
//			처리
			result = dfs(0, 0, n, m, Integer.MAX_VALUE);
//			출력
			System.out.println("#" + tc + " " + result);
		}
	}

	private static int dfs(int y, int x, int h, int w, int min) {
//		종료 (단일조각이 될 경우(height와 width가 모두 1이 될 경우))
		if (h == 1 && w == 1) {
			return 0;
		}
//		실행
//		기존에 있던 초콜릿 덩어리의 건포도 개수
		int sum = 0;
		for (int i = y; i < y + h; i++) {
			for (int j = x; j < x + w; j++) {
				sum += map[i][j];
			}
		}
//		가로로 잘라서 비용을 최소비용을 구한다.
		for (int i = 1; i < h; i++) {
//			위쪽 비용
			int sum1 = dfs(y, x, i, w, min);
//			아래쪽 비용
			int sum2 = dfs(y + i, x, h-i, w, min);
			int sum3 = sum + sum1 + sum2;
			min = Math.min(min, sum3);
		}
//		세로로 잘라서 비용을 최소비용을 구한다.
		for (int i = 1; i < w; i++) {
//			왼쪽 비용
			int sum1 = dfs(y, x, h, i, min);
//			오른쪽 비용
			int sum2 = dfs(y, x+i, h, w-i, min);
			int sum3 = sum + sum1 + sum2;
			min = Math.min(min, sum3);
		}
//		재귀호출
		return min;
	}
}
