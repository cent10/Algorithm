// D4 - 3234 : 준환이의 양팔저울

import java.util.*;
import java.io.*;

public class Solution_D4_3234_준환이의양팔저울_해답 {

	static int N;	// 추의 개수
	static int[] weight;	// 추들의 무게
	static int ans;	// 추를 올려놓기 가능한 모든 경우의 수
	static boolean[] used;	// 앞에 올려놓은 추 제외하고 놔볼 때 사용할 배열
	
	static int[] facto;	// foctorial 미리 계산해서 1!~9! 저장해두기
	static int[] pow;	// 2의 거듭제곱 미리 계산해서 저장해두기
	static int totalWeight;	// 모든 추의 무게 합산해서 현재 올려둔 추 빼고 나머지 얼만큼인지 보기!
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		
		int TC = sc.nextInt();
		
		for (int tc = 1; tc <= TC; tc++) {
			N = sc.nextInt();
			
			weight = new int[N];
			used = new boolean[N];
			
			facto = new int[N+1];
			pow = new int[N+1];
			facto[0] = facto[1] = pow[0] = 1;
			totalWeight = 0;
			
			for (int i = 0; i < N; i++) {
				weight[i] = sc.nextInt();
				facto[i+1] = facto[i] * (i+1);	// 1! ~ 9!
				pow[i+1] = pow[i] * 2;	// 2^1 ~ 2^9
				totalWeight += weight[i];	// 추들의 총 무게 합
			}
			
			ans = 0;
			perm(0, 0, 0, totalWeight);
			System.out.println("#"+tc+" "+ans);
			
		}// end of tc
	}// end of main

	/**
	 * @param idx: 인덱스
	 * @param leftSum: 왼쪽 합
	 * @param rightSum: 오른쪽 합
	 * @param remain: 남은 무게
	 */
	private static void perm(int idx, int leftSum, int rightSum, int remain) {
		if (remain + rightSum <= leftSum) {	// 남은 추를 다 오른쪽에 놔도 왼쪽보다 안무거움.
			ans += facto[N-idx] * pow[N-idx];
			return;
		}
		
		if(idx == N) {	// N개의 추를 다 놔봤다!
			ans++;	// 경우의 수 하나 증가!
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(!used[i]) {	// 미사용중인 i번째 추다! 놔보자!
				used[i] = true;
				perm(idx+1, leftSum+weight[i], rightSum, remain - weight[i]);
				if (rightSum + weight[i] <= leftSum) {
					perm(idx+1, leftSum, rightSum+weight[i], remain - weight[i]);	// 오른쪽에 추를 놔도 왼쪽 이하여야 함!
				}
				used[i] = false;
			}
		}
	}

}
