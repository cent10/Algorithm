import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution_D4_7699_수지의수지맞는여행_해답1 {

	static int result;
	static int R, C;
	static char[][] map;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int[][] v;
	static Set<Character> set = new HashSet();	// 중복된 값은 넣지 않음.
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			set.clear();
//			입력
			R = sc.nextInt();
			C = sc.nextInt();
			map = new char[R][C];
			v = new int[R][C];	// 0: 방문안함, 1: 방문함
			for (int i = 0; i < R; i++) {
				String s = sc.next();
				for (int j = 0; j < C; j++) {
					map[i][j] = s.charAt(j);
				}
			}
//			처리
			dfs(0, 0, 1);
//			출력
			System.out.println("#" + tc + " " + result + "\n");
		}
	}

	private static void dfs(int i, int j, int cnt) {
//		종료
		result = Math.max(result, cnt);
//		실행 & 재귀호출
		v[i][j] = 1;
		set.add(map[i][j]);
		int nexti, nextj;
		for (int d = 0; d < 4; d++) {
			nexti = i + di[d];
			nextj = j + dj[d];
//			범위체크
			if (nexti < 0 || nextj < 0 || nexti >= R || nextj >= C)
				continue;
//			방문체크
			if(v[nexti][nextj] == 1)
				continue;
//			알파벳 중복 체크
			if(set.contains(map[nexti][nextj]))
				continue;
//			재귀호출
			dfs(nexti, nextj, cnt + 1);
			v[nexti][nextj] = 0;
			set.remove(map[nexti][nextj]);
		}
	}

}
