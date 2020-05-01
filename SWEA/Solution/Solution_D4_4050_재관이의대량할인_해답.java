// D4 - 4050. 재관이의 대량 할인

import java.util.*;
import java.io.*;

public class Solution_D4_4050_재관이의대량할인_해답 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] c = new int[N];
			
			for (int i = 0; i < N; i++) {
				c[i] = sc.nextInt();
			}
			
			Arrays.sort(c);
			
			int cnt = 1;
			int sum = 0;
			
			for (int i = N - 1; i >= 0; i--) {
				if(cnt++ % 3 == 0)
					continue;
				sum += c[i];
			}
			
			System.out.println("#" + tc + " " + sum);
		}
	}

}
