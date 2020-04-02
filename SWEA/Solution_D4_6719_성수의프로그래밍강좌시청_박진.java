// D4 - 6719 : 성수의 프로그래밍 강좌 시청

import java.io.*;
import java.util.*;

public class Solution_D4_6719_성수의프로그래밍강좌시청_박진 {

	static int T;
	static int N, K;	// 볼 수 있는 강좌의 수, 선택할 강좌의 수
	static int[] M;		// 강좌의 수준
	static double A;	// 성수의 실력
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			A = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				M[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(M);
//			System.out.println(Arrays.toString(M));
			for (int i = N - K; i < N; i++) {
//				System.out.println("M[i] = " + M[i]);
				A = (A + M[i]) / 2;
			}
			
//			System.out.printf("%f", A);
			sb.append("#").append(tc).append(" ").append(A).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

}
