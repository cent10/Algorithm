// D5 - 1907 : 모래성 쌓기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D5_1907_모래성쌓기_박진_TLE {

	static int T;
	static int H, W;
	static int result;
	
	static int[][] map;
	static int[][] afterMap;
	static boolean isChanged;
	
	static int[] di = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dj = { 0, 0, -1, 1, -1, 1, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			afterMap = new int[H][W];
			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					if (str.charAt(j) == '.') {
						map[i][j] = 0;
						afterMap[i][j] = 0;
					}
					else {
						map[i][j] = str.charAt(j) - 48;
						afterMap[i][j] = str.charAt(j) - 48;
					}
//					System.out.print(map[i][j] + " ");
				}
//				System.out.println();
			}// end input
			result = 0;
			isChanged = true;
			
			while(isChanged) {
//				System.out.println("1. result = " + result);
				isChanged = false;
				copyArr();
				for (int i = 0; i < H; i++) {
					for (int j = 0; j < W; j++) {
						if (map[i][j] > 0 && map[i][j] < 9) {
							int cnt = 0;
							for (int d = 0; d < 8; d++) {
								int nexti = i + di[d];
								int nextj = j + dj[d];
								
								if (map[nexti][nextj] == 0)
									cnt++;
								
								if (cnt >= map[i][j]) {	// 주변 8방향(상하좌우 대각선)에 모래가 있지 않은 칸의 개수가 현재 칸의 튼튼함보다 많거나 같을 경우
									afterMap[i][j] = 0;	// 파도에 의해 무너짐
									isChanged = true;
									break;
								}
							}
						}
					}
				}
				if (isChanged)
					result++;
//				System.out.println("2. result = " + result);
//				for (int i = 0; i < H; i++) {
//					for (int j = 0; j < W; j++) {
//						System.out.print(afterMap[i][j]);
//					}
//					System.out.println();
//				}
//				System.out.println("==============");
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	private static void copyArr() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				map[i][j] = afterMap[i][j];
			}
		}
	}
}
