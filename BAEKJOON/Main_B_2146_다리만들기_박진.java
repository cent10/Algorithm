// Gold III - 2146 : 다리 만들기

/*
 * BFS
 * 78,152 kb, 220 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_2146_다리만들기_박진 {

	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static class Bridge {
		int i, j, d;
		public Bridge(int i, int j, int d) {
			this.i = i;
			this.j = j;
			this.d = d;
		}
	}
	
	static int N;
	static int[][] map;
	static boolean[][] visit;
	static Queue<Point> queue = new LinkedList<Point>();
	
	static boolean[][] visit2;
	static Queue<Bridge> queue2 = new LinkedList<Bridge>();
	
	static int result = Integer.MAX_VALUE;
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int islandNum = 0;	// 섬 번호
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && !visit[i][j]) {
					islandNum++;
					bfs(new Point(i, j), islandNum);	// 섬 구분하기
				}
			}
		}
		
		// 섬의 가장자리에서부터 시작해서 다른 섬의 가장자리에 도달하는 다리의 길이를 구하기
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] > 0) {
					boolean flag = false;	// 섬의 가장자리이면 true, 아니면 false
					for (int d = 0; d < 4; d++) {	// 섬의 가장자리 인지 파악
						int nexti = i + di[d];
						int nextj = j + dj[d];
						if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)
							continue;
						
						if (map[nexti][nextj] == 0) {
							flag = true;
							break;
						}
					}
					if (flag) {
						int start = map[i][j];
						visit2 = new boolean[N][N];
						queue2.offer(new Bridge(i, j, 0));
						visit2[i][j] = true;
					L:	while(!queue2.isEmpty()) {
							Bridge cur = queue2.poll();
							for (int d = 0; d < 4; d++) {	// 섬의 가장자리 인지 파악
								int nexti = cur.i + di[d];
								int nextj = cur.j + dj[d];
								if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N || visit2[nexti][nextj])
									continue;
								if (map[nexti][nextj] == start)
									continue;
								
								if (map[nexti][nextj] > 0) {
									result = result > cur.d ? cur.d : result;
									queue2.clear();
									break L;
								}
								
								visit2[nexti][nextj] = true;
								queue2.offer(new Bridge(nexti, nextj, cur.d + 1));
							}
						}
					}
				}
			}
		}
		
		System.out.println(result);
	}// end of main

	// 섬 구분을 위한 번호 붙이기 (BFS)
	private static void bfs(Point point, int islandNum) {
		queue.offer(point);
		visit[point.i][point.j] = true;
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			map[cur.i][cur.j] = islandNum;
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N || visit[nexti][nextj])
					continue;
				
				if (map[nexti][nextj] == 1) {
					visit[nexti][nextj] = true;
					queue.offer(new Point(nexti,nextj));
				}
			}
		}
	}

}
