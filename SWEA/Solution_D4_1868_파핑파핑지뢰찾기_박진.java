// D4 - 1868 : 파핑파핑 지뢰찾기

import java.util.*;
import java.io.*;

public class Solution_D4_1868_파핑파핑지뢰찾기_박진 {

	static class Point implements Comparable<Point> {
		int i, j;
		int num;

		public Point(int i, int j, int num) {
			super();
			this.i = i;
			this.j = j;
			this.num = num;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.num, o.num);
		}
	}

	static int N;
	static char[][] map;
	static boolean[][] isSelected;
	static int result;

	static int[] di = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dj = { 0, 0, -1, 1, -1, 1, -1, 1 };

	static Queue<Point> pq = new PriorityQueue<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new char[N][N];
			isSelected = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				map[i] = br.readLine().toCharArray();
			}

			result = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == '.') {
						pq.offer(new Point(i, j, getNumOfMine(i, j)));
					}
				}
			}

			while (!pq.isEmpty()) {
				if (isSelected[pq.peek().i][pq.peek().j] == false)
					bfs(pq.poll());
				else
					pq.poll();
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		} // end tc
		System.out.println(sb);
	}// end main

	static Queue<Point> queue = new LinkedList<>();

	private static void bfs(Point point) {
		queue.offer(point);
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			isSelected[current.i][current.j] = true;
			if (current.num == 0) {
				for (int d = 0; d < 8; d++) {
					int nexti = current.i + di[d];
					int nextj = current.j + dj[d];

					if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)
						continue;
					if (isSelected[nexti][nextj])
						continue;
					if (map[nexti][nextj] == '*')
						continue;

					isSelected[nexti][nextj] = true;
					queue.offer(new Point(nexti, nextj, getNumOfMine(nexti, nextj)));
				}
			}
		}
		result++;
	}

	// 해당 좌표 주변의 지뢰 갯수를 리턴하는 메소드
	private static int getNumOfMine(int r, int c) {
		int tempCnt = 0;

		for (int d = 0; d < 8; d++) {
			int nexti = r + di[d];
			int nextj = c + dj[d];

			if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)
				continue;
			if (map[nexti][nextj] == '*')
				tempCnt++;
		}

		return tempCnt;
	}
}
