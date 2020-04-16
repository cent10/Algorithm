// Silver III - 2579 : 계단 오르기

/*
 * 재귀
 * 7%에서 시간초과
 */

import java.util.Scanner;

public class Main_B_2579_계단오르기_박진_TLE {

	static int N;
	static int[] score;
	static int result = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// 계단의 개수
		score = new int[N+1];	// 각 계단의 점수 & 각 계단을 오를 때까지 얻을 수 있는 총점수의 최댓값
		for (int i = 1; i <= N; i++) {
			score[i] = sc.nextInt();
		}
		
		jump(0, 0, 0);
		
		System.out.println(result);
	}// end main

	/**
	 * @param index : 현재 위치
	 * @param preJump : 직전에 한계단 점프(1)했는지, 두계단 점프(2)했는지 파악
	 */
	public static void jump(int index, int preJump, int total) {
		if (index > N) return;
		
		total += score[index];
		
		if (index == N) {
			if (result < total)
				result = total;
			return;
		}
		
		if (index > 1) {
			if (preJump == 1) {
				jump(index + 2, 2, total);
			} else if (preJump == 2) {
				jump(index + 1, 1, total);
				jump(index + 2, 2, total);
			}
		} else {
			jump(index + 1, 1, total);
			jump(index + 2, 2, total);
		}
	}
}
