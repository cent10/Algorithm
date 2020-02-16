// Gold II - 3109 : 빵집

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_3109_빵집_박진 {

	static int R, C;
	static char[][] map;
	
	static boolean[][] isSelected;
	static boolean isLinked;

	static int count = 0;
	
	// 오른쪽 위 대각선, 오른쪽, 오른쪽 아래 대각선
	static int[] di = { -1, 0, 1 };
	static int[] dj = { 1, 1, 1 };
	
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		isSelected = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
//			System.out.println(Arrays.toString(map[i]));
		}
		
		// 알고리즘
		for (int i = 0; i < R; i++) {
			isLinked = false;
			dfs(i, 0);
		}
		
		System.out.println(count);
	}
	
	private static void dfs(int r, int c) {
		// 기저 조건
		if (c == C-1) {
			count++;
			isLinked = true;
			return;
		}
		
		for (int d = 0; d < 3; d++) {
			int nexti = r + di[d];
			int nextj = c + dj[d];
			
			if (isLinked == true)
				return;
			
			if ( nexti < 0 || nextj < 0 || nexti >= R || nextj >= C )
				continue;
			if (map[nexti][nextj] == 'x')
				continue;
			if (isSelected[nexti][nextj] == true)
				continue;
			
			isSelected[nexti][nextj] = true;
			dfs(nexti, nextj);
		}
	}
}
