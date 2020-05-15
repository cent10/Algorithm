// Gold III - 1941 : 소문난 칠공주

/*
 * 조합(DFS) & BFS
 * 146272 kb, 560 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_1941_소문난칠공주_박진 {

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static char[][] map = new char[5][5];	// 'S'(이다‘솜’파), 'Y'(임도‘연’파)
	static boolean[] check = new boolean[25];	// 7명의 여학생 조합을 구할 배열
	static int result = 0;	//  ‘소문난 칠공주’를 결성할 수 있는 모든 경우의 수
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 5; i++) {
			map[i] = sc.next().toCharArray();
		}
		
		combination(0, 0);
		
		System.out.println(result);
	}// end of main

	private static void combination(int index, int cnt) {
		if (cnt == 7) {	// 기저조건
			// ‘소문난 칠공주’를 결성할 수 있는지 체크
			if (sevenPrincess()) {	// 결성할 수 있다면 result 증가
				result++;
			}
			return;
		}
		
		// 재귀
		for (int i = index; i < 25; i++) {
			check[i] = true;
			combination(i + 1, cnt + 1);
			check[i] = false;
		}
	}

	// ‘소문난 칠공주’를 결성할 수 있으면 true 리턴
	private static boolean sevenPrincess() {
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visit = new boolean[5][5];
		
		for (int i = 0; i < 25; i++) {
			if (check[i]) {
				queue.offer(new Point(i / 5, i % 5));
				visit[i / 5][i % 5] = true;
				break;
			}
		}
		
		int num = 0;	// 인접한 학생의 수
		int sNum = 0;	// 이다솜파의 수
		
		// bfs (결성 가능 여부 체크)
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			num++;
			if (map[cur.i][cur.j] == 'S') {
				sNum++;
			}
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= 5 || nextj >= 5)
					continue;
				if (visit[nexti][nextj])
					continue;
				if (!check[nexti * 5 + nextj])
					continue;
				
				visit[nexti][nextj] = true;
				queue.offer(new Point(nexti, nextj));
			}
		}
		
		if (num == 7 && sNum >= 4)
			return true;
		
		return false;
	}

	
}
