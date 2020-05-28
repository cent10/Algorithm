// 1953. [모의 SW 역량테스트] 탈주범 검거

/*
 * 시뮬레이션
 * 26,276 kb, 143 ms
 */

import java.io.*;
import java.util.*;

public class Solution_1953_탈주범검거_박진 {

	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	// 세로 크기 N, 가로 크기 M, 맨홀 뚜껑이 위치한장소의 세로 위치 R, 가로 위치 C, 그리고 탈출 후 소요된 시간 L
	static int N, M, R, C, L;
	static int[][] map;	// 숫자 1 ~ 7은 해당 위치의 터널 구조물 타입을 의미
	static boolean[][] visit;
	static int result;
	
	// 상, 하, 좌, 우
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N= Integer.parseInt(st.nextToken());
			M= Integer.parseInt(st.nextToken());
			R= Integer.parseInt(st.nextToken());
			C= Integer.parseInt(st.nextToken());
			L= Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visit = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = 0;
			
			// BFS
			Queue<Point> queue = new LinkedList<>();
			
			queue.offer(new Point(R, C));
			visit[R][C] = true;
			int time = 0;
			
			while (!queue.isEmpty()) {
				int size = queue.size();
				
				if (time == L)
					break;
				
				while(size-- > 0) {
					Point cur = queue.poll();
					result++;
					int type = map[cur.i][cur.j];
					
					for (int d = 0; d < 4; d++) {	// 0:상, 1:하, 2:좌, 3:우
						switch (type) {
						case 2: if (d == 2 || d == 3) continue; break;
						case 3: if (d == 0 || d == 1) continue; break;
						case 4: if (d == 1 || d == 2) continue; break;
						case 5: if (d == 0 || d == 2) continue; break;
						case 6: if (d == 0 || d == 3) continue; break;
						case 7: if (d == 1 || d == 3) continue; break;
						}
						
						int nexti = cur.i + di[d];
						int nextj = cur.j + dj[d];
						
						if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)	// 범위 체크
							continue;
						if (visit[nexti][nextj])	// 방문 체크
							continue;
						if (map[nexti][nextj] == 0)	// 터널인지 체크
							continue;
						
						if (check(nexti, nextj, cur.i, cur.j)) {	// 이동가능한 터널인지 체크
							queue.offer(new Point(nexti,nextj));
							visit[nexti][nextj] = true;
						}
					}
				}
				time++;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	private static boolean check(int i1, int j1, int i2, int j2) {
		int type = map[i1][j1];
		
		for (int d = 0; d < 4; d++) {	// 0:상, 1:하, 2:좌, 3:우
			switch (type) {
			case 2: if (d == 2 || d == 3) continue; break;
			case 3: if (d == 0 || d == 1) continue; break;
			case 4: if (d == 1 || d == 2) continue; break;
			case 5: if (d == 0 || d == 2) continue; break;
			case 6: if (d == 0 || d == 3) continue; break;
			case 7: if (d == 1 || d == 3) continue; break;
			}
			
			int nexti = i1 + di[d];
			int nextj = j1 + dj[d];
			
			if (nexti == i2 && nextj == j2) {
				return true;
			}
		}
		
		return false;
	}

}
