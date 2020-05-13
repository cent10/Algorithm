// Gold I - 1194 : 달이 차오른다, 가자.

import java.util.*;
import java.io.*;

public class Main_B_1194_달이차오른다가자_박진 {

	static class Point {
		int i, j;	// 위치
		int cnt;	// 이동 횟수
		int key;	// 가지고 있는 키 (bitmask : 000000 ~ 111111)
		
		public Point(int i, int j, int cnt, int key) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.key = key;
		}
	}
	
	static int N, M;
	static char[][] map;
	static boolean[][][] visit;
	static Queue<Point> queue = new LinkedList<>();
	static int result = Integer.MAX_VALUE;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][1 << 6];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
//		for(char[] c : map) {
//			System.out.println(Arrays.toString(c));
//		}
		
	L:	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {	// 민식이 위치 찾아서 BFS 시작.
					map[i][j] = '.';
					bfs(new Point(i, j, 0, 0));
					break L;
				}
			}
		}
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}// end of main

	private static void bfs(Point point) {
		queue.offer(point);
		visit[point.i][point.j][0] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if (map[cur.i][cur.j] == '1') {	// 출구 도착 --> 탈출
				result = cur.cnt;
				return;
			}
			
			if(map[cur.i][cur.j] >= 'a' && map[cur.i][cur.j] <= 'f') {	// 열쇠를 발견하면
				cur.key = cur.key | (1 << (map[cur.i][cur.j] - 'a'));	// 가지고 있는 열쇠 갱신
//				map[cur.i][cur.j] = '.';	// 맵에서 열쇠 지우기
			}
			
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)	// 범위 체크
					continue;
				if (visit[nexti][nextj][cur.key])	// 방문 체크
					continue;
				if (map[nexti][nextj] == '#')	// 벽 체크
					continue;
				if(map[nexti][nextj] >= 'A' && map[nexti][nextj] <= 'F') {	// 문에 해당하는 열쇠가 있는지 체크
					if ((cur.key & (1 << (map[nexti][nextj] - 'A'))) == 0) {	// 해당 열쇠가 없다면, 못 지나감
						continue;
					}
				}
				
				visit[nexti][nextj][cur.key] = true;
				queue.offer(new Point(nexti, nextj, cur.cnt + 1, cur.key));
			}
		}
	}

}
