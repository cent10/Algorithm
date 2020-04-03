// D4 - 4530 : 극한의 청소 작업

// 참고: https://swexpertacademy.com/main/talk/solvingTalk/boardCommuView.do?searchCondition=COMMU_DETAIL-COMMU_TITLE-NICK_NAME_TAG&commuId=AWV70JlKxbwDFAV4&searchKeyword=4530&orderBy=RECOMMEND_DESC&pageSize=30&pageIndex=1

import java.io.*;
import java.util.*;

public class Solution_D4_4530_극한의청소작업_박진 {

	static int T;
	static long A, B;
	static long result;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			A = Long.parseLong(st.nextToken());
			B = Long.parseLong(st.nextToken());
			// end input
			
			A = changeNum(A);
			B = changeNum(B);
			
			A = getDecimal(A);
			B = getDecimal(B);
			
			if (A < 0 && B > 0)
				result = B - A - 1;
			else
				result = B - A;
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	// 9진수 형태 수를 10진수로 변환
	public static long getDecimal(long num) {
		boolean isNeg = false;
		if (num < 0) {
			isNeg = true;
			num = -num;
		}
		
		long lengthOfNum = (long) (Math.log10(num) + 1);
		long result = 0;
		long multiple = 1;
		for (int i = 0; i < lengthOfNum; i++) {
			long cur = num % 10;
			num /= 10;
			result += (cur * multiple);
			multiple *= 9;
		}
		
		if (isNeg)
			return -result;
		else
			return result;
	}
	
	// 입력받은 수가 9진수라고 생각하기 위해 숫자를 9진수 형태로 변환
	public static long changeNum(long num) {
		boolean isNeg = false;
		if (num < 0) {
			isNeg = true;
			num = -num;
		}
		
		long lengthOfNum = (long) (Math.log10(num) + 1);
		long result = 0;
		long multiple = 1;
		for (int i = 0; i < lengthOfNum; i++) {
			long cur = num % 10;
			if (cur > 4) {
				cur--;
			}
			num /= 10;
			result += (cur * multiple);
			multiple *= 10;
		}
		
		if (isNeg)
			return -result;
		else
			return result;
	}
}
