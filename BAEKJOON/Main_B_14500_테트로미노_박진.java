// Gold IV - 14500 : 테트로미노

/*
 * 	38,140 kb, 580 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_14500_테트로미노_박진 {

	static int N, M;	// 종이의 세로 크기 N과 가로 크기 M  (4 ≤ N, M ≤ 500)
	static int map[][];	// 종이
	static int result = 0;	// 테트로미노가 놓인 칸에 쓰여 있는 수들의 합의 최대값
	
	static int[][] d1 = { { 0, 1 }, { 0, 2 }, { 0, 3 } };
	static int[][] d2 = { { 1, 0 }, { 2, 0 }, { 3, 0 } };
	
	static int[][] d3 = { { 0, 1 }, { 1, 0 }, { 1, 1 } };
	
	static int[][] d4 = { { 1, 0 }, { 2, 0 }, { 2, 1 } };
	static int[][] d5 = { { 1, 0 }, { 2, 0 }, { 2, -1 } };
	static int[][] d6 = { { 0, 1 }, { 1, 0 }, { 2, 0 } };
	static int[][] d7 = { { 0, 1 }, { 1, 1 }, { 2, 1 } };
	static int[][] d8 = { { 1, 0 }, { 0, 1 }, { 0, 2 } };
	static int[][] d9 = { { 0, 1 }, { 0, 2 }, { 1, 2 } };
	static int[][] d10 = { { 1, 0 }, { 1, 1 }, { 1, 2 } };
	static int[][] d11 = { { 1, 0 }, { 1, -1 }, { 1, -2 } };
	
	static int[][] d12 = { { 1, 0 }, { 1, 1 }, { 2, 1 } };
	static int[][] d13 = { { 1, 0 }, { 1, -1 }, { 2, -1 } };
	static int[][] d14 = { { 0, 1 }, { 1, 0 }, { 1, -1 } };
	static int[][] d15 = { { 0, 1 }, { 1, 1 }, { 1, 2 } };
	
	static int[][] d16 = { { 1, 0 }, { 1, -1 }, { 1, 1 } };
	static int[][] d17 = { { 0, 1 }, { 0, 2 }, { 1, 1 } };
	static int[][] d18 = { { 1, 0 }, { 2, 0 }, { 1, 1 } };
	static int[][] d19 = { { 1, 0 }, { 2, 0 }, { 1, -1 } };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// 테트로미노에 있는 수들의 합 구하기
				go(i,j,d1); go(i,j,d2); go(i,j,d3); go(i,j,d4); go(i,j,d5); go(i,j,d6); go(i,j,d7); go(i,j,d8); go(i,j,d9); go(i,j,d10);
				go(i,j,d11); go(i,j,d12); go(i,j,d13); go(i,j,d14); go(i,j,d15); go(i,j,d16); go(i,j,d17); go(i,j,d18); go(i,j,d19);
			}
		}
		
		System.out.println(result);
	}// end of main

	private static void go(int i, int j, int[][] d) {
		int temp = map[i][j];
		
		for (int dir = 0; dir < 3; dir++) {
			int nexti = i + d[dir][0];
			int nextj = j + d[dir][1];
			
			if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M) {
				return;
			}
			
			temp += map[nexti][nextj];
		}
		
		// 최대값 갱신
		result = result < temp ? temp : result;
		
		return;
	}
}
