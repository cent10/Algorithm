// 1949. [모의 SW 역량테스트] 등산로 조성

/*
 * BFS
 * 93,312 kb, 224 ms
 */

import java.util.*;
import java.io.*;

public class Solution_1949_등산로조성_박진 {

	static class Point {
		int i, j;
		int cnt; // 등산로 길이

		public Point(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}

	static int T;
	static int N, K; // 한 변의 길이 N, 최대 공사 가능 깊이 K
	static int[][] map;
	static Queue<Point> queue = new LinkedList<Point>();
	static int result;

	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			ArrayList<Point> top = new ArrayList<Point>(); // 가장 높은 봉우리들의 위치를 저장
			int maxHeight = 0; // 가장 높은 봉우리의 높이
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = maxHeight < map[i][j] ? map[i][j] : maxHeight;
				}
			}

			result = 0;

			// 가장 높은 봉우리들 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHeight) {
						top.add(new Point(i, j, 1));
					}
				}
			}

			// 지형을 깎지 않고 bfs
			for (int i = 0; i < top.size(); i++) {
				bfs(top.get(i));
			}

			// 지형을 하나씩 깎아보면서 bfs
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int k = 1; k <= K; k++) {
						map[i][j] -= k;
						for (int l = 0; l < top.size(); l++) {
							if (i == top.get(l).i && j == top.get(l).j)
								continue;
							bfs(top.get(l));
						}
						map[i][j] += k;
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		} // end of tc
		System.out.println(sb);
	}// end of main

	public static void bfs(Point point) {
		queue.offer(point);
		while (!queue.isEmpty()) {
			Point p = queue.poll();
			if (result < p.cnt) {
				result = p.cnt;
			}
			for (int d = 0; d < 4; d++) {
				int nexti = p.i + di[d];
				int nextj = p.j + dj[d];

				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)
					continue;
				if (map[nexti][nextj] >= map[p.i][p.j])
					continue;

				queue.offer(new Point(nexti, nextj, p.cnt + 1));
			}
		}
	}

}