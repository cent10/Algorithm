// Silver IV - 1920 : 수 찾기

/*
 * 	63932 kb, 516 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_1920_수찾기_Map {

	static int N, M;
	static Map<Integer, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			map.put(num, num);
		}

		M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(st.nextToken());
			if (map.containsKey(num)) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}// end of main
}
