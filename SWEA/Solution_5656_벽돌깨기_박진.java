// 5656. [모의 SW 역량테스트] 벽돌 깨기

import java.util.*;
import java.io.*;

public class Solution_5656_벽돌깨기_박진 {

	/**
	 * ① 구슬은 좌, 우로만 움직일 수 있어서 항상 맨 위에 있는 벽돌만 깨트릴 수 있다.
	 * ② 벽돌은 숫자 1 ~ 9 로 표현되며,
	 * 	  구술이 명중한 벽돌은 상하좌우로 ( 벽돌에 적힌 숫자 - 1 ) 칸 만큼 같이 제거된다.
	 * 
	 * 최대한 많은 벽돌을 제거해야함.
	 * 남은 벽돌의 개수는?
	 * 
	 *  1 ≤ N ≤ 4
	 *  2 ≤ W ≤ 12
	 *  2 ≤ H ≤ 15
	 */
	
	static class Point {
		int i, j;
		int num;
		public Point(int i, int j, int num) {
			this.i = i;
			this.j = j;
			this.num = num;
		}
	}
	
	static int N;		// 구슬을 쏠 수 있는 횟수
	static int W, H;	// W x H
	static int[][] map;
	static int result;	// 남은 벽돌의 최소개수
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	static boolean[][] isVisited;
	static Queue<Point> queue = new LinkedList<Point>();
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			isVisited = new boolean[H][W];
			result = Integer.MAX_VALUE;
			
			shot(0, map);
			
			sb.append("#").append(tc).append(" ").append(result==Integer.MAX_VALUE?0:result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main
	
	private static void shot(int n, int[][] m) {
		if(n == N) {	// 기저조건
			int temp = getResult(m);
			if (result > temp)
				result = temp;
			return;
		}
		
		for (int w = 0; w < W; w++) {
			int[][] copiedMap = copyMap(m);
			for (int h = 0; h < H; h++) {
				if(copiedMap[h][w] > 0) {
					remove(new Point(h, w, copiedMap[h][w]), copiedMap);
					
					move(copiedMap);
					
					shot(n+1, copiedMap);
					break;
				}
			}
		}
	}

	/* 벽돌을 제거하는 메소드 */
	private static void remove(Point p, int[][] m) {
		queue.offer(p);
		isVisited[p.i][p.j] = true;
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			m[point.i][point.j] = 0;
			
			for (int d = 0; d < 4; d++) {
				int nexti = point.i;
				int nextj = point.j;
				for (int i = 0; i < point.num-1; i++) {
					nexti = nexti + di[d];
					nextj = nextj + dj[d];
					
					if (nexti < 0 || nextj < 0 || nexti >= H || nextj >= W || isVisited[nexti][nextj] == true)
						continue;
					if (m[nexti][nextj] == 0)
						continue;
					
					isVisited[nexti][nextj] = true;
					queue.offer(new Point(nexti, nextj, m[nexti][nextj]));
				}
			}
		}
		for (int i = 0; i < H; i++) {
			Arrays.fill(isVisited[i], false);
		}
	}
	
	/* 벽돌을 이동시키는 메소드 */
	private static void move(int[][] m) {
		for (int w = 0; w < W; w++) {
			for (int h = H-1; h > 0; h--) {
				if (m[h][w] == 0) {
					for (int h2 = h-1; h2 >= 0; h2--) {
						if(m[h2][w] > 0) {
							m[h][w] = m[h2][w];
							m[h2][w] = 0;
							break;
						}
					}
				}
			}
		}
	}
	
	/* 남은 벽돌의 개수를 구하는 메소드 */
	private static int getResult(int[][] m) {
		int r = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(m[i][j] > 0) {
					r++;
				}
			}
		}
		return r;
	}
	
	/* map 복사하는 메소드 */
	private static int[][] copyMap(int[][] m) {
		int[][] temp = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				temp[i][j] = m[i][j];
			}
		}
		return temp;
	}
}
