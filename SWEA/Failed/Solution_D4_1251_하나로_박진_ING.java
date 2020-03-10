// D4 - 1251 : [S/W 문제해결 응용] 4일차 - 하나로

import java.util.*;
import java.io.*;

public class Solution_D4_1251_하나로_박진_ING {

	static int N;
	static int[][] xy;
	static double E;
	static boolean[] isSelected;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			xy = new int[N][2];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					xy[j][i] = Integer.parseInt(st.nextToken());
				}
			}
			E = Double.parseDouble(br.readLine());
			isSelected = new boolean[N];
			result = Integer.MAX_VALUE;
			
			for (int n = 0; n < N; n++) {
				isSelected[n] = true;
				dfs(xy[n][0], xy[n][1], 0, 0);
				isSelected[n] = false;
			}
			
			System.out.println("#" + tc + " "  + Math.round(result*E));
//			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	private static void dfs(int x, int y, int cnt, int distance) {
		if (result <= distance)	// 가지치기
			return;
		
		if (cnt == N-1) {	// 기저조건
			if (result > distance)
				result = distance;
			return;
		}
		
		for (int n = 0; n < N; n++) {
			if (isSelected[n] == false) {
				isSelected[n] = true;
				dfs(xy[n][0], xy[n][1], cnt+1, distance + getDistance(x, y, xy[n][0], xy[n][1]));
				isSelected[n] = false;
			}
		}
	}

	// 해저터널 길이(L)의 제곱을 구해주는 메소드
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return (Math.abs(x1-x2))*(Math.abs(x1-x2)) + (Math.abs(y1-y2))*(Math.abs(y1-y2));
	}
}
