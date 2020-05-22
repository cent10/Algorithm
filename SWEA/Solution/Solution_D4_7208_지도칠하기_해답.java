// (User Problem) D4 - 7208 : 지도 칠하기

import java.util.*;
import java.io.*;

public class Solution_D4_7208_지도칠하기_해답 {

	static int T;
	static int[][] map;
	static int N;
	static int[] color;
	static int[] fill;
	static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 데이터 입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			color = new int[N];
			fill = new int[N];
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dfs(0);	// 중복순열
			
			// 결과를 출력
			System.out.println("#" + tc + " " + min);
		}// end of tc
	}// end of main

	private static void dfs(int cnt) {
		if(cnt == N) {
			// 인접된 국가가 다른 color로 칠할 수 있는 순열인 경우
			if(check()) {
				// 순열 생성 후, 인접한 국가의 색이 다르게 작성된 순열이 생성된 경우, 기존의 color와 생성된 순열이 다른지를 카운트
				int count = 0;
				for (int i = 0; i < N; i++) {
					if (color[i] != fill[i]) {
						count++;
					}
				}
				min = Math.min(count, min);
			}
			return;
		}
		
		// 중복 순열
		for (int i = 1; i <= 4; i++) {
			fill[cnt] = i;
			dfs(cnt + 1);
		}
	}

	private static boolean check() {
		for (int i = 0; i < N; i++) {	// cur
			for (int j = 0; j < N; j++) {	// 인접 국가
				if(map[i][j] == 1) {
					if(map[i][j] == 1 && fill[i] == fill[j]) {
						return false;
					}
				}
			}
		}
		return true;
	}

}
