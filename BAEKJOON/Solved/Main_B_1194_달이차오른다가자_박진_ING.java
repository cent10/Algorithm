// Gold I - 1194 : 달이 차오른다, 가자.

import java.util.*;
import java.io.*;

public class Main_B_1194_달이차오른다가자_박진_ING {

	static class Point {
		int i, j;	// 위치
		int cnt;	// 이동 횟수
		boolean[] key;	// 가지고 있는 열쇠 기록
		
		public Point(int i, int j, int cnt, boolean[] key) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
			this.key = key;
		}

		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + ", cnt=" + cnt + ", key=" + Arrays.toString(key) + "]";
		}
	}
	
	static int N, M;
	static char[][] map;
	static boolean[][] visit;
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
		visit = new boolean[N][M];
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
					bfs(new Point(i, j, 0, new boolean[6]));
					break L;
				}
			}
		}
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}// end of main

	private static void bfs(Point point) {
		queue.offer(point);
		visit[point.i][point.j] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			
			if (map[cur.i][cur.j] == '1') {	// 출구 도착 --> 탈출
				result = cur.cnt;
				return;
			}
			
			if(map[cur.i][cur.j] >= 'a' && map[cur.i][cur.j] <= 'f') {	// 기존에 없던 열쇠를 줍는다면
				if (!cur.key[map[cur.i][cur.j] - 'a']) {
					cur.key[map[cur.i][cur.j] - 'a'] = true;
					for (int i = 0; i < N; i++) {	// visit 초기화
						Arrays.fill(visit[i], false);
					}
				}
				map[cur.i][cur.j] = '.';
			}
			
			for (int d = 0; d < 4; d++) {
				int nexti = cur.i + di[d];
				int nextj = cur.j + dj[d];
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)	// 범위 체크
					continue;
				if (visit[nexti][nextj])	// 방문 체크
					continue;
				if (map[nexti][nextj] == '#')	// 벽 체크
					continue;
				if(map[nexti][nextj] >= 'A' && map[nexti][nextj] <= 'F') {	// 문에 해당하는 열쇠가 있는지 체크
					if (!cur.key[map[nexti][nextj] - 'A']) {	// 해당 열쇠가 없을 때
						continue;
					}
				}
				
				boolean[] tempKey = new boolean[6];
				copyKey(cur.key, tempKey);
				
				visit[nexti][nextj] = true;
				queue.offer(new Point(nexti, nextj, cur.cnt + 1, tempKey));
			}
		}
	}
	
	public static void copyKey(boolean[] originKey, boolean[] newKey) {
		for (int i = 0; i < newKey.length; i++) {
			newKey[i] = originKey[i];
		}
	}

}
