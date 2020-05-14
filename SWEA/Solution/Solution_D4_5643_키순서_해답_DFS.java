// D4 - 5643 : [Professional] 키 순서

import java.util.*;
import java.io.*;

public class Solution_D4_5643_키순서_해답_DFS {

	static int result;
	static int N;
	static int M;
	static int[][] map;
	static int cnt;
	static boolean[] v;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int t = 1; t <= T; t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N+1][N+1];
			for (int i = 0; i < M; i++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				map[x][y] = 1;
			}
			result = 0;
			// 구현: 모든 정점에서 나보다 큰 사람 수 세고, 나보다 작은 사람 세고
			for (int i = 1; i <= N; i++) {
				// 나보다 큰 사람 세기
				cnt = 0;
				v = new boolean[N+1];
				dfs(i);
				// 나보다 작은 사람 세기
				v = new boolean[N+1];
				dfs1(i);
				if(cnt == N - 1) {	// 그 합이 N-1이면 알 수 있다. --> result++;
					result++;
				}
			}
			// 출력
			System.out.println("#" + t + " " + result);
		}
	}

	private static void dfs(int idx) {
		v[idx] = true;
		for (int i = 1; i <= N; i++) {
			if (v[i])
				continue;
			if (map[idx][i] == 0)
				continue;
			cnt++;
			dfs(i);
		}
	}
	
	private static void dfs1(int idx) {
		v[idx] = true;
		for (int i = 1; i <= N; i++) {
			if (v[i])
				continue;
			if (map[i][idx] == 0)
				continue;
			cnt++;
			dfs1(i);
		}
	}

}
