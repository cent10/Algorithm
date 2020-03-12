// 4012 : [모의 SW 역량테스트] 요리사

import java.util.*;
import java.io.*;

public class Solution_4012_요리사_박진 {

	static int N;	// 식재료의 수
	static int[][] S;	// 시너지 정보
	static boolean[] isSelected;
	static int[] comb1;
	static int[] comb2;
	static int result;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			S = new int[N+1][N+1];	// 1 ~ N
			isSelected = new boolean[N+1];	// 1 ~ N
			comb1 = new int[N/2+1];	// 1 ~ N/2
			comb2 = new int[N/2+1];	// 1 ~ N/2
			StringTokenizer st = null;
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 1; j <= N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			result = Integer.MAX_VALUE;
			
			combination(0, 1);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	private static void combination(int cnt, int cur) {	// 하나의 음식에 대한 식재료 조합
		if (cnt == N/2) {
			int index = 1;
			for (int i = 1; i <= N; i++) {
				if(isSelected[i] == false) {
					comb2[index] = i;
					index++;
				}
			}
//			System.out.println(Arrays.toString(comb1));
//			System.out.println(Arrays.toString(comb2));
//			System.out.println(Arrays.toString(isSelected));
			
			int score1 = 0;
			for (int i = 1; i < comb1.length-1; i++) {
				for (int j = i+1; j < comb1.length; j++) {
					score1 += S[comb1[i]][comb1[j]] + S[comb1[j]][comb1[i]];
				}
			}
//			System.out.println("score1 = " + score1);
			
			int score2 = 0;
			for (int i = 1; i < comb2.length-1; i++) {
				for (int j = i+1; j < comb2.length; j++) {
					score2 += S[comb2[i]][comb2[j]] + S[comb2[j]][comb2[i]];
				}
			}
//			System.out.println("score2 = " + score2);
			
			int tempResult = Math.abs(score1 - score2);
//			System.out.println("tempResult = " + tempResult);
			if (result > tempResult)
				result = tempResult;
			
//			System.out.println("===========");
			return;
		}
		
		for (int i = cur; i <= N; i++) {
			comb1[cnt+1] = i;
			isSelected[i] = true;
			combination(cnt+1, i+1);
			isSelected[i] = false;
		}
	}
}
