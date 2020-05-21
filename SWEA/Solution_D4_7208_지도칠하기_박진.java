// (User Problem) D4 - 7208 : 지도 칠하기

/*
 * 조합
 * 19,696 kb, 136 ms
 */

import java.util.*;
import java.io.*;

public class Solution_D4_7208_지도칠하기_박진 {

	static int T;
	static int N;	// 나라의 수
	static int[] color;	// 각 나라에 배정 된 색상 값 (1 ~ 4)
	static int[][] adjArr;	// 국가간 인접 정보 (인접된 국가는 1, 인접되지 않은 국가는 0)
	static int result;	// 최소 변경 된 색의 수
	
	static int[] comb;	// 색 조합
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			color = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				color[i] = Integer.parseInt(st.nextToken());
			}
			adjArr = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					adjArr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = Integer.MAX_VALUE;
			
			// 가능한 색 조합 구하기
			comb = new int[N];
			combination(0, 0);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	private static void combination(int index, int cnt) {
		if (cnt == N) {	// 기저조건
			int temp = Integer.MAX_VALUE;
			// 인접 국가가 동일 색이 나오지 않는지 체크
			for (int i = 0; i < N; i++) {
				if (!isPossible(i))	// 인접 국가 중 동일 색이 존재하면 불가능
					return;
			}
			
			temp = 0;
			// 동일 색이 존재하지 않는다면
			for (int i = 0; i < N; i++) {	// 변경된 색의 개수 체크
				if (color[i] != comb[i])
					temp++;
			}
			// 결과 값 갱신
			result = result > temp ? temp : result;
			
			return;
		}
		
		// 재귀
		for (int i = 1; i <= 4; i++) {
			comb[index] = i;
			combination(index + 1, cnt + 1);
		}
	}

	// 인접 국가 중 동일 색이 존재해서 불가능하면 false, 동일 색이 존재하지 않아서 가능하면 true 리턴
	private static boolean isPossible(int index) {
		for (int i = 0; i < N; i++) {
			if (adjArr[index][i] > 0) {
				if (comb[index] == comb[i])
					return false;
			}
		}
		
		return true;
	}

}
