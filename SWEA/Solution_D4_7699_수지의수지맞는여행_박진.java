// D4 - 7699 : 수지의 수지 맞는 여행

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D4_7699_수지의수지맞는여행_박진 {

	static int T;
	static int R, C;
	static char[][] island;
	static boolean[] isSelected;
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			isSelected = new boolean[26];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			island = new char[R][C];
			for (int i = 0; i < R; i++) {
				island[i] = br.readLine().toCharArray();
			}// end input
			
			isSelected[island[0][0] - 'A'] = true;
			dfs(0, 0, 1);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	private static void dfs(int x, int y, int cnt) {
		if (result < cnt) {
			result = cnt;
		}
		
		if (result == 26)
			return;
		
		for (int d = 0; d < 4; d++) {
			int nexti = x + di[d];
			int nextj = y + dj[d];
			
			if (nexti < 0 || nextj < 0 || nexti >= R || nextj >= C || isSelected[island[nexti][nextj] - 'A'] == true)
				continue;
			
			isSelected[island[nexti][nextj] - 'A'] = true;
			dfs(nexti, nextj, cnt + 1);
			isSelected[island[nexti][nextj] - 'A'] = false;
		}
	}
}
