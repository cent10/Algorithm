// D5 - 7793 : 오! 나의 여신님

import java.util.*;
import java.io.*;

public class Solution_D5_7793_오나의여신님_박진 {

	/**
	 * 수연이의 위치는 ‘S’, 여신의 공간은 ‘D’, 돌의 위치는 ‘X’, 악마는 ‘*’
	 * ‘.’는 평범한 지역으로, 수연이가 이동할 수도 있으며 “악마의 손아귀” 스킬이 확장될 수도 있다.
	 * 전체 지도에서 ‘S’와 ‘D’는 정확히 한 번 나타난다.
	 */
	
	static class Point {
		int i, j;
		public Point (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int N, M;
	static char[][] map;
	static boolean[][] isVisited;
	static int starti, startj, desti, destj;	// 수연이의 위치, 여신의 위치
	static int result;	// 안전 지역에 도달하는 최소 시간
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	static Queue<Point> queue = new LinkedList<Point>();
	static Queue<Point> devilQueue = new LinkedList<Point>();
	
	public static void main(String[] args) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new char[N][M];
			isVisited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == 'S') {
						starti = i;
						startj = j;
					}
					else if (map[i][j] == 'D') {
						desti = i;
						destj = j;
					}
					else if (map[i][j] == '*') {
						devilQueue.offer(new Point(i, j));
					}
				}
			}//end input
			result = Integer.MAX_VALUE;
			
			int cnt = 0;
			queue.offer(new Point(starti, startj));
		L:	while(!queue.isEmpty()) {	// BFS
				int size = queue.size();
				
				devilSkill();	// 악마의 손아귀 스킬 사용
				
				while (size-- > 0) {	// while문이 종료되면 1초 지난것
					// 수연이 이동
					Point p = queue.poll();
					if (p.i == desti && p.j == destj) {
						result = cnt;
						break L;
					}
					
					for (int d = 0; d < 4; d++) {
						int nexti = p.i + di[d];
						int nextj = p.j + dj[d];
						
						if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
							continue;
						if (map[nexti][nextj] == 'X' || map[nexti][nextj] == '*')
							continue;
						if (isVisited[nexti][nextj] == true)
							continue;
						
						queue.offer(new Point(nexti, nextj));
						isVisited[nexti][nextj] = true;
					}
				}
				cnt++;
			}
			
			sb.append("#").append(tc).append(" ").append(result==Integer.MAX_VALUE?"GAME OVER":result).append("\n");
			queue.clear();
			devilQueue.clear();
		}// end tc
		System.out.println(sb);
	}// end main

	/* 악마의 손아귀 스킬 */
	private static void devilSkill() {
		int s = devilQueue.size();
		for(int i = 0; i < s; i++) {
			Point temp = devilQueue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nexti = temp.i + di[d];
				int nextj = temp.j + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
					continue;
				if (map[nexti][nextj] == 'D' || map[nexti][nextj] == 'X' || map[nexti][nextj] == '*')
					continue;
				
				map[nexti][nextj] = '*';
				devilQueue.offer(new Point(nexti, nextj));
			}
		}
	}

}
