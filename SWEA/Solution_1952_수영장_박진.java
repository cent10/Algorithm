// 1952 : [모의 SW 역량테스트] 수영장

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_수영장_박진 {

	static int T;
	
	// 1일 이용권의 요금, 1달 이용권의 요금, 3달 이용권의 요금, 1년 이용권의 요금
	static int oneDay, oneMonth, threeMonth, oneYear;
	
	//1월부터 12월까지의 이용 계획 (인덱스 0은 사용x)
	static int[] plan = new int[13];
	
	static int result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			oneDay = Integer.parseInt(st.nextToken());
			oneMonth = Integer.parseInt(st.nextToken());
			threeMonth = Integer.parseInt(st.nextToken());
			oneYear = Integer.parseInt(st.nextToken());
			
			result = oneYear;
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 1; i <= 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			getFee(1, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end Test Case
		System.out.println(sb);
	}// end main

	private static void getFee(int i, int cnt, int fee) {
		
		if (cnt >= 12) {
			if (result > fee) {
				result = fee;
			}
			return;
		}
		
		if (plan[i] > 0) {
			// 1일 이용권 선택
			getFee(i+1, cnt+1, fee + plan[i]*oneDay);
			// 1달 이용권 선택
			getFee(i+1, cnt+1, fee + oneMonth);
			// 3달 이용권 선택
			getFee(i+3, cnt+3, fee + threeMonth);
		} else {
			getFee(i+1, cnt+1, fee);
		}
	}
}
