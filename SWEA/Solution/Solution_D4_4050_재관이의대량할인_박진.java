// D4 - 4050. 재관이의 대량 할인

import java.util.*;
import java.io.*;

public class Solution_D4_4050_재관이의대량할인_박진 {

	static int T;
	static int N;
	static int[] cloth;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			cloth = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int total = 0;	// 모든 옷 가격의 합
			for (int i = 0; i < N; i++) {
				cloth[i] = Integer.parseInt(st.nextToken());
				total += cloth[i];
			}
			Arrays.sort(cloth);
			
			int totalSale = 0;	// 총 할인받을 금액
			int length = cloth.length;
			for (int i = length - 1; i >= 0; i = i - 3) {
				if (i - 2 >= 0)
					totalSale += cloth[i - 2];
			}
			
			sb.append("#").append(tc).append(" ").append(total - totalSale).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

}
