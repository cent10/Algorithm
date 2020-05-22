// D4 - 3234 : 준환이의 양팔저울

/*
 * dfs
 * 시간초과
 */

import java.util.*;
import java.io.*;

public class Solution_D4_3234_준환이의양팔저울_박진_TLE {

	static int T;
	static int N;	// 추의 개수
	static int[] weight;	// 추들의 무게
	static int result;	// 무게추를 올리는 방법의 수
	
	public static void main(String[] args) throws Exception {
//		System.out.println(9*8*7*6*5*4*3*2*1);
//		System.out.println(9*8*7*6*5*4*3*2*1 * 512);
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			weight = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				weight[i] = Integer.parseInt(st.nextToken());
			}
			
			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	private static void dfs(int left, int right, int cnt) {
		if (left < right)	// 가지치기 (왼쪽에 올라가 있는 무게의 총합보다 더 커져서는 안 된다.)
			return;
		
		if (cnt == N) {	// 기저조건
			result++;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (weight[i] > 0) {
				int temp = weight[i];
				weight[i] = 0;	// 저울에 올린 추는 0으로 바꿔줌
				dfs(left + temp, right, cnt+1);	// 왼쪽에 올리기
				dfs(left, right + temp, cnt+1);	// 오른쪽에 올리기
				weight[i] = temp;	// 원래 무게로 복구
			}
		}
	}

}
