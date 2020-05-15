// Gold III - 1941 : 소문난 칠공주

import java.io.*;
import java.util.*;

public class Main_B_1941_소문난칠공주_해답 {

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static char[][] map = new char[5][5];	// 'S'(이다‘솜’파), 'Y'(임도‘연’파)
	static int result = 0;	//  ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수
	static boolean[] v = new boolean[25];
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String s = "";
		for (int i = 0; i < 5; i++) {
			s = sc.next();
			for (int j = 0; j < 5; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		// 로직 구현
		for (int i = 0; i < 25; i++) {
			dfs(i, 1, 0);
		}
		
		System.out.println(result);
	}// end of main

	private static void dfs(int idx, int cnt, int sCnt) {
		v[idx] = true;
		
		if(map[idx/5][idx%5] == 'S') {
			sCnt++;
		}
		
		// 종료
		if (cnt == 7) {
			// 판단
			if(sCnt >= 4) {	// 4명 이상이냐
				if(bfs(idx/5, idx%5)) {	// 연결되어 있느냐
					result++;
				}
			}
			v[idx] = false;
			return;
		}
		
		for (int i = idx + 1; i < 25; i++) {
			if (v[i])
				continue;
			dfs(i, cnt + 1, sCnt);
		}
		// 백트래킹
		v[idx] = false;
	}

	private static boolean bfs(int i, int j) {
		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(i, j));
		boolean[][] v1 = new boolean[5][5];
		v1[i][j] = true;
		int vCnt = 1;
		while(!q.isEmpty()) {
			Point cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				
				if (ni < 0 || nj < 0 || ni >= 5 || nj >= 5)
					continue;
				if (v1[ni][nj])
					continue;
				if(!v[ni * 5 + nj])
					continue;
				
				v1[ni][nj] = true;
				q.offer(new Point(ni, nj));
				vCnt++;
			}
		}
		
		return vCnt == 7 ? true : false;
	}

}
