// Silver III - 11727 : 2×n 타일링 2

/*
DP (동적계획법(다이나믹 프로그래밍))
14,288 kb
104 ms
*/

import java.util.Scanner;

public class Main_B_11727_2xn타일링2_박진 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] memo = new int[1001];
		memo[1] = 1;
		memo[2] = 3;
		for (int i = 3; i <= n; i++) {
			memo[i] = (memo[i-1] + 2*memo[i-2]) % 10007;
		}
		System.out.println(memo[n]);
	}

}
