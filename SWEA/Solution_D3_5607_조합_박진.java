// D3 - 5607 : [Professional] 조합

import java.util.Scanner;

public class Solution_D3_5607_조합_박진 {

	/*
	 * - 조합: nCr = n! / ( r! * (n-r)! )
	 * - 페르마의 소정리: a^(p-1) ≡ 1 (mod p)
	 */ 

	static int T;
	static int N, R;
	static long result; 
	static final int MOD = 1234567891;
	static long[] factorial;	// 팩토리얼을 저장할 변수
	
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			N = sc.nextInt();
			R = sc.nextInt();
			factorial = new long[N+1];
			factorial[0] = 1;
			for (int i = 1; i <= N; i++) {	// 팩토리얼 값 미리 저장
				factorial[i] = (i * factorial[i-1]) % MOD;
			}
			long temp = (factorial[R] * factorial[N - R]) % MOD;
			temp = calPower(temp, MOD - 2);
			
			result = (factorial[N] * temp) % MOD;
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	public static long calPower(long x, long n) {
		if (n == 0)
			return 1;
		long tmp = calPower(x, n / 2);
		long ret = (tmp * tmp) % MOD;
		if (n % 2 == 0)
			return ret;
		else
			return (ret * x) % MOD;
	}
}
