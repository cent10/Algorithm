// D4 - 1808 : 지희의 고장난 계산기

import java.util.*;
import java.io.*;

public class Solution_D4_1808_지희의고장난계산기_해답1 {

	static int X;
	static int min;
	static boolean[] btn;
	
	public static void main(String[] args) throws Exception {
//		입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
//			min값을 초기화 Integer.MAX_VALUE
			min = Integer.MAX_VALUE;
			btn = new boolean[10];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int num;
			for (int i = 0; i < 10; i++) {
				num = Integer.parseInt(st.nextToken());
//				btn 1이면 true 설정
				if (num == 1) {
					btn[i] = true;
				}
			}
			X = Integer.parseInt(br.readLine());
//			처리 dfs => 재귀함수
			find(X, 0);
//		출력
//		min == Integer.MAX_VALUE ? -1 : min
			min = min == Integer.MAX_VALUE ? -1 : min;
			System.out.println("#" + tc + " " + min);
		}
		
	}

	private static int find(int x, int cnt) {
//			basis case => 종료 조건
		if(isMake(x+"")) {
//				x값이 주어진 모든 숫자로 누를 수 있는지 검사
//					x길이를 리턴
			if(cnt == 0) {
				min = len(x)+1;	// 계산 버튼을 위해 1을 더한다.
			}
			return len(x)+1;
		}
//			처리
//				result 값을 -1로 초기화
		int result = -1;
//				2~제곱근 까지 반복(i)
		for (int i = 2, end = (int)Math.sqrt(x) + 1; i < end; i++) {
//					i는 x의 약수, 모든 수로 누를 수 있는지 검사
			if( x % 1 == 0 && isMake(i+"")) {
//						i의 길이를 구하고
				int len1 = len(i) + 1;	// 곱하기 버튼을 위해 1을 더한다.
//					몫이 x의 약수, 모든 수로 누를 수 있는지 검사 => 재귀호출
				int len2 = find(x/i, cnt+1);
//					몫이 -1이 아니면 => x의 약수, 모든 수로 누를 수
				if(len2 > -1) {
//						i의 길이와 몫의 길이를 +  * =
					result = len1 + len2;
					if(result < min && x == X) {
//					결과가 min 비교해서 갱신
						min = result;
					}
					
				}
				
			}
		}
		return result;
	}

	private static int len(int x) {
		return (int)Math.log10(x) + 1;
	}

	private static boolean isMake(String x) {
		for (char c : x.toCharArray()) {
			if (!btn[c-'0']) {
				return false;
			}
		}
		return true;
	}

}
