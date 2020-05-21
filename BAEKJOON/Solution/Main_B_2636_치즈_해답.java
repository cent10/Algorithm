// Gold V - 2636 : 치즈

import java.util.*;
import java.io.*;

public class Main_B_2636_치즈_해답 {

	static int N, M;	// 행, 열
	static int[][] cheese;	// 치즈판 (0:방문하지 않은공기, 1:치즈, -1:방문한 공기, 2:녹을 치즈)
	static int last;	// 마지막 남은 치즈 개수
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheese = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		// 치즈가 없을 때까지 공기를 중심으로 탐색
		do {
			dfs(0, 0);	// 공기의 첫좌표인 (0,0)부터 시작
			time++;
		}while(cheeseCheck());
		// 치즈가 있는지 체크
		
		System.out.println(time - 1);
		System.out.println(last);
	}// end of main

	private static void dfs(int r, int c) {
		cheese[r][c] = -1;	// 공기에 대한 방문 처리
		int nr, nc;
		// 공기를 중심으로 사방에 치즈가 있는지 검사
		for (int d = 0; d < 4; d++) {
			nr = r + dr[d];
			nc = c + dc[d];
			if(nr > -1 && nr < N && nc > -1 && nc < M) {
				// 치즈(1)면 -> 공기 옆 치즈이므로 녹을 치즈(2)로 표시
				if(cheese[nr][nc] == 1) {
					cheese[nr][nc] = 2;
				} else if(cheese[nr][nc] == 0) {	// 공기(0)면 -> 계속 탐색
					dfs(nr, nc);
				}
			}
		}
	}

	private static boolean cheeseCheck() {
		int count = 0;
		// 전체 치즈판을 탐색하면서
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cheese[i][j] == 1)
					continue;
				if (cheese[i][j] == 2)	// 녹을 치즈(2)면 count를 센 후에 녹여준다. --> 0으로 바꾼다.
					count++;
				// 녹을 치즈와 방문한 공기(-1)면 다시 탐색하기 위해서 0으로 바꿔준다.
				cheese[i][j] = 0;
			}
		}
		
		if (count == 0) {
			return false;
		} else {
			last = count;
			return true;
		}
	}

}
