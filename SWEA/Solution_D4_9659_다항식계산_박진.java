// D4 - 9659 : 다항식 계산

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_D4_9659_다항식계산_박진 {

	static int T;
	static int N;
	static int[] ti, ai, bi;
	static int M;
	static int[] x;
	static long[][] f;	// fN(xM)들을 저장
	static final int MOD = 998244353;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			ti = new int[N+1];
			ai = new int[N+1];
			bi = new int[N+1];
			StringTokenizer st = null;
			for (int i = 2; i <= N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				ti[i] = Integer.parseInt(st.nextToken());
				ai[i] = Integer.parseInt(st.nextToken());
				bi[i] = Integer.parseInt(st.nextToken());
			}
			M = Integer.parseInt(br.readLine());
			x = new int[M+1];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= M; i++) {
				x[i] = Integer.parseInt(st.nextToken());
			}// end input
			f = new long[N+1][M+1];
			Arrays.fill(f[0], 1);	// f0(x) = 1
			for (int i = 1; i <= M; i++) {	// f1(x) = x
				f[1][i] = x[i];
			}
			
			for (int i = 2; i <= N; i++) {
				switch (ti[i]) {
				case 1:
					for (int j = 1; j <= M; j++) {
						f[i][j] = (f[ai[i]][j] + f[bi[i]][j]) % MOD;
					}
					break;
				case 2:
					for (int j = 1; j <= M; j++) {
						f[i][j] = (ai[i] * f[bi[i]][j]) % MOD;
					}
					break;
				case 3:
					for (int j = 1; j <= M; j++) {
						f[i][j] = (f[ai[i]][j] * f[bi[i]][j]) % MOD;
					}
					break;
				}
			}
			
			sb.append("#").append(tc);
			for (int i = 1; i <= M; i++) {
				sb.append(" ").append(f[N][i]);
			}
			sb.append("\n");
		}// end tc
		System.out.print(sb);
	}// end main

}
