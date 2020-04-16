// Silver III - 2579 : 계단 오르기

import java.util.Scanner;

public class Main_B_2579_계단오르기_박진 {

	static int N;
	static int[] score;
	static int[] dp;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	// 계단의 개수
		score = new int[301];	// 각 계단의 점수
		dp = new int[301];	// 각 계단을 오를 때까지 얻을 수 있는 총점수의 최댓값
		for (int i = 1; i <= N; i++) {
			score[i] = sc.nextInt();
		}
		
		dp[1] = score[1];
		dp[2] = score[1] + score[2];
		
		for (int i = 3; i <= N; i++) {
			dp[i] = Integer.max(dp[i - 3] + score[i - 1] + score[i], dp[i - 2] + score[i]);
		}
		
		System.out.println(dp[N]);
	}// end main
}
