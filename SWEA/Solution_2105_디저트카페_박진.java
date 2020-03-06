// 2105 : [모의 SW 역량테스트] 디저트 카페

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_2105_디저트카페_박진 {

	static int N;
	static int[][] map;
	static boolean[] isSelected;	// 먹은 디저트인지 아닌지.
	static int result;
	static boolean isCycle;
	
	// 0:남동, 1:남서, 2:북서, 3:북동
	static int[] di = { 1, 1, -1, -1 };
	static int[] dj = { 1, -1, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			isSelected = new boolean[101];	// 1 ~ 100
			isCycle = false;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dfs(i, j, i, j, 0, 1, 0);
				}
			}
			if (isCycle == false)
				result = -1;
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	private static void dfs(int starti, int startj, int i, int j, int dir, int cnt, int numOfChange) {
		if (numOfChange > 3)	// 가지치기
			return;
		
		if (starti == i-1 && startj == j+1 && numOfChange > 1) {	// 기저조건
			isCycle = true;
			if (result < cnt)
				result = cnt;
			return;
		}
		
		isSelected[map[i][j]] = true;
		
		for (int d = dir, end = dir + 1; d <= end; d++) {
			int nexti = i + di[d%4];
			int nextj = j + dj[d%4];
			
			if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)
				continue;
			if (isSelected[map[nexti][nextj]] == true)
				continue;
			
			isSelected[map[nexti][nextj]] = true;
			if (dir == (d%4))
				dfs(starti, startj, nexti, nextj, d%4, cnt+1, numOfChange);
			else
				dfs(starti, startj, nexti, nextj, d%4, cnt+1, numOfChange+1);
			isSelected[map[nexti][nextj]] = false;
		}
		isSelected[map[i][j]] = false;
	}

}
