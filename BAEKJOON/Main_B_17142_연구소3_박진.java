// Gold IV - 17142 : 연구소 3 

/*
 * 조합 & BFS
 * 44,220 kb, 264 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_17142_연구소3_박진 {

	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Point [i=" + i + ", j=" + j + "]";
		}
	}
	
	static int N, M;	// 연구소의 크기 N(4 ≤ N ≤ 50), 놓을 수 있는 바이러스의 개수 M(1 ≤ M ≤ 10)
	static int[][] map;	// 0은 빈 칸, 1은 벽, 2는 바이러스의 위치
	static int[][] tempMap;
	static ArrayList<Point> virus = new ArrayList<>();
	static int virusCnt = 0;	// 바이러스 개수
	static int wallCnt = 0;	// 벽 개수
	static boolean[] virusComb;
	static int result = Integer.MAX_VALUE;	// 모든 빈 칸에 바이러스를 퍼뜨리는 최소 시간
	
	static boolean[][] visit;
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					virus.add(new Point(i, j));
					virusCnt++;
				} else if (map[i][j] == 1) {
					wallCnt++;
				}
			}
		}
		
		virusComb = new boolean[virus.size()];
		combination(0, 0);	// 활성화할 바이러스의 조합
		
		// 출력
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}// end of main

	// 조합
	private static void combination(int idx, int cnt) {
		if (cnt == M) {	// 활성화할 바이러스의 조합이 만들어짐.
			
//			System.out.println(Arrays.toString(virusComb));
			
			// 바이러스를 퍼뜨려보기 위해서 맵 복사
			tempMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					tempMap[i][j] = map[i][j];
				}
			}
			
			// visit 초기화
			visit = new boolean[N][N];
			
			// 바이러스 퍼뜨려보기
			spreadVirus();
			
			return;
		}
		
		// 재귀
		for (int i = idx; i < virusComb.length; i++) {
			virusComb[i] = true;
			combination(i + 1, cnt + 1);
			virusComb[i] = false;
		}
	}

	// BFS
	private static void spreadVirus() {
		Queue<Point> queue = new LinkedList<>();
		
		for (int i = 0; i < virusComb.length; i++) {
			if (virusComb[i]) {
				queue.offer(virus.get(i));
				visit[virus.get(i).i][virus.get(i).j] = true;
			}
		}
		
		int time = 0;	// 시간
		int temp = virusCnt;	// 현재 조합에서 퍼뜨릴 수 있는 바이러스들의 개수 카운팅
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			if (temp + wallCnt == N * N)
				break;
			
			while(size-- > 0) {
				Point cur = queue.poll();
				for (int d = 0; d < 4; d++) {
					int nexti = cur.i + di[d];
					int nextj = cur.j + dj[d];
					
					if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)	// 범위 체크
						continue;
					if (visit[nexti][nextj])	// 방문 체크
						continue;
					if (tempMap[nexti][nextj] == 1)	// 벽 체크
						continue;
					
					queue.offer(new Point(nexti, nextj));
					if (tempMap[nexti][nextj] == 0) {
						temp++;
						tempMap[nexti][nextj] = 2;
//						tempMap[nexti][nextj] = time+1;
					}
					visit[nexti][nextj] = true;
				}
			}
			time++;
		}
		
		
		if (check(temp)) {	// 모든 빈 칸에 바이러스를 퍼뜨려졌는지 체크
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(tempMap[i][j]+" ");
//				}
//				System.out.println();
//			}
//			System.out.println("time = " + time);
			
			result = result > time ? time : result;	// 모든 빈 칸에 바이러스를 퍼뜨렸다면 결과값 갱신
		}
	}

	private static boolean check(int temp) {
		if (temp + wallCnt == N * N)
			return true;
		return false;
	}

}
