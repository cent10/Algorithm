// Gold V - 15683 : 감시

/*
69,460 kb
344 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_15683_감시_해답 {

	static class CCTV {
		int x, y;
		int type;
		public CCTV(int x, int y, int type) {
			this.x = x;
			this.y = y;
			this.type = type;
		}
	}
	
	static int N, M; 		// 사무실의 세로 크기 N과 가로 크기 M
	static int[][] map;
	static int result = Integer.MAX_VALUE;
	static ArrayList<CCTV> list = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (1 <= map[i][j] && map[i][j] <= 5) {
					list.add(new CCTV(j, i, map[i][j]));
				}
			}
		}
		
		dfs(0, map);
		
		System.out.println(result);
	}// end of main

	private static void dfs(int idx, int[][] nMap) {
		// 종료
		if(idx == list.size()) {
			int cnt = 0;
			// 사각지대 개수 세기
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (nMap[i][j] == 0) {
						cnt++;
					}
				}
			}
			result = Math.min(result, cnt);
			return;
		}
		
		// 재귀호출
		// 리스트에서 CCTV 뽑아서 감시 솔루션
		CCTV cctv = list.get(idx);
		int[][] vMap = new int[N][M];
		
		switch (cctv.type) {
		case 1:	// 1번 감시 카메라
			for (int d = 0; d < 4; d++) {
				// 맵 복사
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						vMap[i][j] = nMap[i][j];
					}
				}
				// 감시
				detect(cctv.x, cctv.y, vMap, d);
				// 다음번째 CCTV 호출
				dfs(idx + 1, vMap);
				// 새로운 맵을 만들어 넘겨줬으므로 백트래킹을 하지 않아도 됨.
			}
			break;
		case 2:	// 2번 감시 카메라
			for (int d = 0; d < 2; d++) {
				// 맵 복사
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						vMap[i][j] = nMap[i][j];
					}
				}
				// 감시
				detect(cctv.x, cctv.y, vMap, d);
				detect(cctv.x, cctv.y, vMap, d + 2);
				// 다음번째 CCTV 호출
				dfs(idx + 1, vMap);
				// 새로운 맵을 만들어 넘겨줬으므로 백트래킹을 하지 않아도 됨.
			}
			break;
		case 3:	// 3번 감시 카메라
			for (int d = 0; d < 4; d++) {
				// 맵 복사
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						vMap[i][j] = nMap[i][j];
					}
				}
				// 감시
				detect(cctv.x, cctv.y, vMap, d);
				detect(cctv.x, cctv.y, vMap, (d + 1) % 4);
				// 다음번째 CCTV 호출
				dfs(idx + 1, vMap);
				// 새로운 맵을 만들어 넘겨줬으므로 백트래킹을 하지 않아도 됨.
			}
			break;
		case 4:	// 4번 감시 카메라
			for (int d = 0; d < 4; d++) {
				// 맵 복사
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						vMap[i][j] = nMap[i][j];
					}
				}
				// 감시
				detect(cctv.x, cctv.y, vMap, d);
				detect(cctv.x, cctv.y, vMap, (d + 1) % 4);
				detect(cctv.x, cctv.y, vMap, (d + 2) % 4);
				// 다음번째 CCTV 호출
				dfs(idx + 1, vMap);
				// 새로운 맵을 만들어 넘겨줬으므로 백트래킹을 하지 않아도 됨.
			}
			break;
		case 5:	// 5번 감시 카메라
			// 맵 복사
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					vMap[i][j] = nMap[i][j];
				}
			}
			// 감시
			detect(cctv.x, cctv.y, vMap, 0);
			detect(cctv.x, cctv.y, vMap, 1);
			detect(cctv.x, cctv.y, vMap, 2);
			detect(cctv.x, cctv.y, vMap, 3);
			// 다음번째 CCTV 호출
			dfs(idx + 1, vMap);
			// 새로운 맵을 만들어 넘겨줬으므로 백트래킹을 하지 않아도 됨.
			break;
		}
	}

	private static void detect(int x, int y, int[][] cMap, int dir) {
		// 0:좌, 1:상, 2:우, 3:하
		switch (dir) {
		case 0:	// 좌
			for (int i = x; i >= 0; i--) {
				if(cMap[y][i] == 6) {	// 벽을 만나면, 더이상 갈 수 없음
					break;
				}
				cMap[y][i] = 9;
			}
			break;
		case 1:	// 상
			for (int i = y; i >= 0; i--) {
				if(cMap[i][x] == 6) {	// 벽을 만나면, 더이상 갈 수 없음
					break;
				}
				cMap[i][x] = 9;
			}
			break;
		case 2:	// 우
			for (int i = x; i < M; i++) {
				if(cMap[y][i] == 6) {	// 벽을 만나면, 더이상 갈 수 없음
					break;
				}
				cMap[y][i] = 9;
			}
			break;
		case 3:	// 하
			for (int i = y; i < N; i++) {
				if(cMap[i][x] == 6) {	// 벽을 만나면, 더이상 갈 수 없음
					break;
				}
				cMap[i][x] = 9;
			}
			break;
		}
	}
	
}
