// 6109. 추억의 2048게임

import java.util.*;
import java.io.*;

public class Solution_6109_추억의2048게임_박진 {
	
	static int T;
	static int N;
	static String D;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			D = st.nextToken();
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if (D.equals("left")) {	// left
				for (int i = 0; i < N; i++) {	// 숫자 합치기
					for (int j = 0; j < N - 1; j++) {
						if (map[i][j] == 0)
							continue;
						for (int j2 = j + 1; j2 < N; j2++) {
							if(map[i][j] == map[i][j2]) {
								map[i][j] += map[i][j2];
								map[i][j2] = 0;
								break;
							} else {
								if (map[i][j2] > 0) {
									break;
								}
							}
						}
					}
				}
				for (int i = 0; i < N; i++) {	// 왼쪽으로 밀기
					for (int j = 1; j < N; j++) {
						int tempj = j;
						int index = j;
						if (map[i][j] > 0) {
							while(--index >= 0) {
								if (map[i][index] == 0) {
									tempj = index;
								}
							}
							if (tempj != j) {
								map[i][tempj] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			} else if (D.equals("right")) {	// right
				for (int i = 0; i < N; i++) {	// 숫자 합치기
					for (int j = N - 1; j > 0; j--) {
						if (map[i][j] == 0)
							continue;
						for (int j2 = j - 1; j2 >= 0; j2--) {
							if(map[i][j] == map[i][j2]) {
								map[i][j] += map[i][j2];
								map[i][j2] = 0;
								break;
							} else {
								if (map[i][j2] > 0) {
									break;
								}
							}
						}
					}
				}
				for (int i = 0; i < N; i++) {	// 오른쪽으로 밀기
					for (int j = N - 2; j >= 0; j--) {
						int tempj = j;
						int index = j;
						if (map[i][j] > 0) {
							while(++index < N) {
								if (map[i][index] == 0) {
									tempj = index;
								}
							}
							if (tempj != j) {
								map[i][tempj] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			} else if (D.equals("up")) {	// up
				for (int j = 0; j < N; j++) {	// 숫자 합치기
					for (int i = 0; i < N - 1; i++) {
						if (map[i][j] == 0)
							continue;
						for (int i2 = i + 1; i2 < N; i2++) {
							if(map[i][j] == map[i2][j]) {
								map[i][j] += map[i2][j];
								map[i2][j] = 0;
								break;
							} else {
								if (map[i2][j] > 0) {
									break;
								}
							}
						}
					}
				}
				for (int j = 0; j < N; j++) {	// 위쪽으로 밀기
					for (int i = 1; i < N; i++) {
						int tempi = i;
						int index = i;
						if (map[i][j] > 0) {
							while(--index >= 0) {
								if (map[index][j] == 0) {
									tempi = index;
								}
							}
							if (tempi != i) {
								map[tempi][j] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			} else {	// down
				for (int j = 0; j < N; j++) {	// 숫자 합치기
					for (int i = N - 1; i > 0; i--) {
						if (map[i][j] == 0)
							continue;
						for (int i2 = i - 1; i2 >= 0; i2--) {
							if(map[i][j] == map[i2][j]) {
								map[i][j] += map[i2][j];
								map[i2][j] = 0;
								break;
							} else {
								if (map[i2][j] > 0) {
									break;
								}
							}
						}
					}
				}
				for (int j = 0; j < N; j++) {	// 아래쪽으로 밀기
					for (int i = N - 2; i >= 0; i--) {
						int tempi = i;
						int index = i;
						if (map[i][j] > 0) {
							while(++index < N) {
								if (map[index][j] == 0) {
									tempi = index;
								}
							}
							if (tempi != i) {
								map[tempi][j] = map[i][j];
								map[i][j] = 0;
							}
						}
					}
				}
			}
			
			sb.append("#").append(tc).append("\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(map[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}// end of tc
		System.out.println(sb);
	}// end of main

}
