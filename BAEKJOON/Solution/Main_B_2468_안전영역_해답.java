
// Silver II - 2468 : 안전 영역

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_B_2468_안전영역_해답 {

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	static int N;
	static int[][] map;
	static int result = 0;
	static boolean[][] v;
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		// 솔루션
		int cnt = 0;
		for (int k = 0; k <= 100; k++) {
			v = new boolean[N][N];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (v[i][j])
						continue;
					// 나보다 낮거나 같은거
					if (map[i][j] <= k)
						continue;
					// 안전영역1
					bfs(i, j, k);
					cnt++;
				}
			}
			result = Math.max(result, cnt);
		}
		System.out.println(result);
	}

	private static void bfs(int r, int c, int level) {
		Queue<Point> q = new LinkedList<Point>();
		q.offer(new Point(r, c));
		v[r][c] = true;
		Point cur = null;
		while (!q.isEmpty()) {
			cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int ni = cur.i + di[d];
				int nj = cur.j + dj[d];
				if (ni < 0 || nj < 0 || ni >= N || nj >= N)
					continue;
				if (v[ni][nj])
					continue;
				if (map[ni][nj] <= level)
					continue;
				v[ni][nj] = true;
				q.offer(new Point(ni, nj));
			}
		}
	}

}
