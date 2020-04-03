// Silver III - 1699 : 제곱수의 합

// 참고: http://occidere.blog.me/220792326120

import java.util.Arrays;
import java.util.Scanner;

public class Main_B_1699_제곱수의합_박진 {

	static int N;
	static int[] dp;
	static int result;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		dp = new int[N+1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			int rootN = (int)Math.sqrt(i);
			for (int j = 1; j <= rootN; j++) {
				dp[i] = Math.min(dp[i], dp[i - (j * j)] + 1);
			}
		}
//		for (int i = 1; i <= N; i++) {
//			System.out.println("dp["+i+"] = " + dp[i]);
//		}
		System.out.println(dp[N]);
	}
}
