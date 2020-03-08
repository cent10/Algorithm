// 5656. [모의 SW 역량테스트] 벽돌 깨기

import java.util.*;

import javafx.geometry.Orientation;

import java.io.*;

public class Solution_5656_벽돌깨기_박진_ING {

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
	static int[][] map2;
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
			map2 = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					map2[i][j] = map[i][j];
				}
			}
			isVisited = new boolean[H][W];
			result = Integer.MAX_VALUE;
			
//			findTop();	// 맨 위에 있는 벽돌들 찾기
//			int size = topQueue.size();
			play(0);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main
	
	/* N번 구슬을 쏘는 메소드 */
	private static void play(int n) {	// DFS
		copyMapToMap2();
		
		int temp = getResult();
		if (n == N || temp == 0) {	// 기저조건
			if (result > temp)
				result = temp;
			return;
		}
		
		for (int col = 0; col < W; col++) {
			for (int row = H-1; row >= 0; row--) {
				if (map[row][col] > 0 && row == 0) {
					remove(new Point(row, col, map[row][col]));
					move();
					play(n+1);
					copyMap2ToMap();
				}
				else if (row > 0 && map[row][col] > 0 && map[row-1][col] == 0) {
					remove(new Point(row, col, map[row][col]));
					move();
					play(n+1);
					copyMap2ToMap();
				}
			}
		}
	}

//	static Queue<Point> topQueue = new LinkedList<Point>();
//	/* 맨 위에 있는 벽돌들을 찾는 메소드 */
//	private static void findTop() {
//		for (int i = 0; i < H; i++) {
//			for (int j = 0; j < W; j++) {
//				if (i == 0) {
//					if (map[i][j] != 0) {
//						topQueue.offer(new Point(i, j, map[i][j]));
//					}
//				}
//				else {
//					if (map[i][j] != 0 && map[i-1][j] == 0) {
//						topQueue.offer(new Point(i, j, map[i][j]));
//					}
//				}
//			}
//		}
//	}
	
	/* 벽돌을 제거하는 메소드 */
	private static void remove(Point p) {
		queue.offer(p);
		isVisited[p.i][p.j] = true;
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			map[point.i][point.j] = 0;
			
			for (int d = 0; d < 4; d++) {
				int nexti = point.i;
				int nextj = point.j;
				for (int i = 0; i < point.num-1; i++) {
					nexti = nexti + di[d];
					nextj = nextj + dj[d];
					
					if (nexti < 0 || nextj < 0 || nexti >= H || nextj >= W || isVisited[nexti][nextj] == true)
						continue;
					if (map[nexti][nextj] == 0)
						continue;
					
					isVisited[nexti][nextj] = true;
					queue.offer(new Point(nexti, nextj, map[nexti][nextj]));
				}
			}
		}
		for (int i = 0; i < H; i++) {
			Arrays.fill(isVisited[i], false);
		}
	}
	
	/* 벽돌을 이동시키는 메소드 */
	private static void move() {
		for (int i = H-1; i > 0; i--) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] == 0 && map[i-1][j] > 0) {
					map[i][j] = map[i-1][j];
					map[i-1][j] = 0;
				}
			}
		}
	}
	
	/* 남은 벽돌의 개수를 구하는 메소드 */
	private static int getResult() {
		int r = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(map[i][j] > 0) {
					r++;
				}
			}
		}
		return r;
	}
	
	private static void copyMapToMap2() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map2[i][j] = map[i][j];
			}
		}
	}
	
	private static void copyMap2ToMap() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = map2[i][j];
			}
		}
	}
}
