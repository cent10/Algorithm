// Gold V - 16236 : 아기 상어

/*
 * BFS
 * 278,268 kb, 612 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_16236_아기상어_박진 {

	static class Point implements Comparable<Point> {
		int i, j;
		int d;	// 아기상어와의 거리
		public Point(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}

		@Override
		public int compareTo(Point o) {
			if (this.d == o.d) {
				if (this.i == o.i) {
					return Integer.compare(this.j, o.j);
				} else {
					return Integer.compare(this.i, o.i);
				}
			}
			return Integer.compare(this.d, o.d);
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", d=" + d + "]";
		}
	}
	
	static int N; // 공간의 크기 N(2 ≤ N ≤ 20)
	static int[][] map;
	/*
	 * 0: 빈 칸
	 * 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
	 * 9: 아기 상어의 위치
	 */
	static int sharki, sharkj;	// 아기상어 위치
	static int sharkSize = 2;	// 아기 상어의 크기
	static int eatCnt = 0;	// 아기 상어가 먹은 물고기 수 카운팅
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	static Queue<Point> queue = new LinkedList<>();	// bfs를 위한 큐
	static boolean[][] visit;
	
	static PriorityQueue<Point> pq = new PriorityQueue<>();	// 먹을 수 있는 물고기들
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sharki = i;
					sharkj = j;
					map[i][j] = 0;
				}
			}
		}
		
		int time = 0;
		while (true) {
			// 먹을 수 있는 물고기들 찾기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && map[i][j] < 7) {
						if (map[i][j] < sharkSize) {	// 아기상어보다 크기가 작은 물고기인지 체크
							// 아기상어와 물고기 사이의 거리 구한 후, 먹을 후보군에 추가
							for(boolean[] v : visit) {	// visit 초기화
								Arrays.fill(v, false);
							}
							bfs(sharki, sharkj, i, j);
						}
					}
				}
			}
			// 먹을 수 있는 물고기들이 없으면 종료
			if (pq.size() == 0) {
				break;
			}
			// 아기상어 이동 후 물고기 먹기
			sharki = pq.peek().i;
			sharkj = pq.peek().j;
			eatCnt++;
			map[pq.peek().i][pq.peek().j] = 0;
			if (eatCnt == sharkSize) {
				eatCnt = 0;
				sharkSize++;
			}
			time += pq.peek().d;
			pq.clear();
		}
		
		
		System.out.println(time);
	}// end of main

	// 아기상어와 물고기 사이의 거리 구하기
	private static void bfs(int sharki, int sharkj, int fishi, int fishj) {
		queue.offer(new Point(sharki, sharkj, 0));
		visit[sharki][sharkj] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			if (cur.i == fishi && cur.j == fishj) {
				pq.offer(new Point(fishi, fishj, cur.d));
				queue.clear();
				return;
			}
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N || visit[nexti][nextj])
					continue;
				if (map[nexti][nextj] > sharkSize)
					continue;
				
				visit[nexti][nextj] = true;
				queue.offer(new Point(nexti, nextj, cur.d + 1));
			}
		}
	}

}
