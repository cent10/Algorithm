// Gold IV - 16973 : 직사각형 탈출

/*
 * 120,892 kb
 * 1,328 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_16973_직사각형탈출_박진 {

	static class Point {
		int r, c;
		int cnt;
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int N, M;
	static boolean[][] map;
	static int H, W, Sr, Sc, Fr, Fc;
	static int result;
	static boolean[][] isVisited;
	static Queue<Point> queue = new LinkedList<Point>();
	static boolean isPossible;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N+1][M+1];
		isVisited = new boolean[N+1][M+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= M; j++) {
				map[i][j] = (Integer.parseInt(st.nextToken()) == 0) ? false : true;
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}
		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		Sr = Integer.parseInt(st.nextToken());
		Sc = Integer.parseInt(st.nextToken());
		Fr = Integer.parseInt(st.nextToken());
		Fc = Integer.parseInt(st.nextToken());
		
		bfs(new Point(Sr, Sc, 0));
		
		System.out.print((isPossible == true) ? result : -1);
		
	}// end main

	private static void bfs(Point point) {
		queue.offer(point);
		isVisited[point.r][point.c] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if (cur.r == Fr && cur.c == Fc) {
				result = cur.cnt;
				isPossible = true;
				return;
			}
			
		L:	for (int d = 0; d < 4; d++) {
				int nexti = cur.r + di[d];
				int nextj = cur.c + dj[d];
				
				if (nexti <= 0 || nextj <= 0 || nexti > N || nextj > M)
					continue;
				if (isVisited[nexti][nextj])
					continue;
				
				switch (d) {
				case 0:	// 상
					for (int i = cur.c; i < cur.c + W; i++) {
						if(map[cur.r - 1][i] == true)
							continue L;
					}
					break;
				case 1:	// 하
					if (nexti + H - 1 > N)
						continue;
					
					for (int i = cur.c; i < cur.c + W; i++) {
						if(map[cur.r + H][i] == true)
							continue L;
					}
					break;
				case 2:	// 좌
					for (int i = cur.r; i < cur.r + H; i++) {
						if(map[i][cur.c - 1] == true)
							continue L;
					}
					break;
				case 3:	// 우
					if (nextj + W - 1 > M)
						continue;
					
					for (int i = cur.r; i < cur.r + H; i++) {
						if(map[i][cur.c + W] == true)
							continue L;
					}
					break;
				}
				
				if (nexti == Fr && nextj == Fc) {
					result = cur.cnt + 1;
					isPossible = true;
					return;
				}
				
				queue.offer(new Point(nexti, nextj, cur.cnt+1));
				isVisited[nexti][nextj] = true;
			}
		}
	}

}
