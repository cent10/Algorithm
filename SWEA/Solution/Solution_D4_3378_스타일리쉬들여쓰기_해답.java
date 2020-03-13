import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// D4 - 3378 : 스타일리쉬 들여쓰기

/**
 * <알고리즘을 잘 하려면...>
 * 1. 프로그래밍 언어 문법
 * 2. IDE 이클립스 (사용방법, 단축키, 자동완성, 디버깅 등)
 * 3. 문제 독해능력 (문제 분석)
 * 4. 다른 사람의 소스코드 분석
 * 5. 기발한 두뇌: 아이디어 --> (좋은 소스코드들을 많이 접하면서 채울 수 있음.)
 * 6. 구현능력: 생각의 절차를 코드로 옮기는 것 --> (문제를 많이 풀어봐야함.)
 * 7. 자료구조, 알고리즘 설계기법
 * 8. 최적화: 입출력 방법, 변수&메서드의 사용 방법
 * 
 * - 자바: 가독성, 주석, 재활용성, 구조화
 * - 알고리즘: 시간, 공간
 * 
 * - 삼성 역랑테스트 기출 --> 백준
 * - 카카오 코딩테스트 기출 --> 프로그래머스
 */

/*
 * R, C, S 중복 순열 (재귀,반복문 모두 가능하지만, 3개로 고정돼있기 때문에 더 빠른 반복문으로 풀어보자.)
 */

public class Solution_D4_3378_스타일리쉬들여쓰기_해답 {

	static int[][] m;
	static int[][] dap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());	// 마스터의 코드 줄 수
			int q = Integer.parseInt(st.nextToken());	// 나의 코드 줄 수
			
			m = new int[p][4];	// '.', 열린소괄호, 열린중괄호, 열린대괄호의 개수
			for (int i = 0; i < p; i++) {
				// 한줄을 입력받아서
				String line = br.readLine();
				// 앞부분에 나온 '.'의 개수
				int index = 0;
				while(line.charAt(index) == '.') {
					index++;
				}
				m[i][0] = index;
				// 괄호의 개수는 누적처리
				if (i > 0) {	// 누적하기 위해서 이전 값으로 초기화
					m[i][1] = m[i-1][1];	// 소괄호
					m[i][2] = m[i-1][2];	// 중괄호
					m[i][3] = m[i-1][3];	// 대괄호
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(': m[i][1]++; break;
					case ')': m[i][1]--; break;
					case '{': m[i][2]++; break;
					case '}': m[i][2]--; break;
					case '[': m[i][3]++; break;
					case ']': m[i][3]--; break;
					}
				}
			} // 마스터의 스타일리쉬 코드 분석 for
			
			// 내 코드 분석
			dap = new int[q][4];	// '.', 열린소괄호, 열린중괄호, 열린대괄호의 개수
			for (int i = 0; i < q; i++) {
				// 한줄을 입력받아서
				String line = br.readLine();
				// 앞부분에 나온 '.'의 개수
				int index = 0;
				// 괄호의 개수는 누적처리
				if (i > 0) {	// 누적하기 위해서 이전 값으로 초기화
					dap[i][1] = dap[i-1][1];	// 소괄호
					dap[i][2] = dap[i-1][2];	// 중괄호
					dap[i][3] = dap[i-1][3];	// 대괄호
				}
				for (int j = index; j < line.length(); j++) {
					switch (line.charAt(j)) {
					case '(': dap[i][1]++; break;
					case ')': dap[i][1]--; break;
					case '{': dap[i][2]++; break;
					case '}': dap[i][2]--; break;
					case '[': dap[i][3]++; break;
					case ']': dap[i][3]--; break;
					}
				}
			} // 나의 코드 분석 for
			
			// dap[i][0] : 초기값 -2 .의 개수를 몇개
			for (int i = 0; i < q; i++) {
				dap[i][0] = -2;	// 안쓰는 값으로 초기화
			}
			// 중복 순열
			for (int R = 1; R <= 20; R++) {
				for (int C = 1; C <= 20; C++) {
					for (int S = 1; S <= 20; S++) {
						if (check(R,C,S)) {	// 마스터 코드에서 해가 되는가?
							cal(R,C,S);
						}
					}
				}
			}
			sb.append("#").append(tc).append(" 0");	// 첫번째 줄의 들여쓰기는 0으로 일정
			for (int i = 1; i < dap.length; i++) {
				sb.append(" ").append(dap[i][0]);
			}
			sb.append("\n");
		} // end of testCase
		System.out.print(sb);
	} // end of main

	/** 내 코드에서 들여쓰기를 각 라인에 몇개씩 해야하는지 구해서 dap 배열에 저장 */
	private static void cal(int R, int C, int S) {
		for (int i = 1; i < dap.length; i++) {
			int x = dap[i-1][1]*R + dap[i-1][2]*C + dap[i-1][3]*S;
			if (dap[i][0] == -2) {	// 답을 구한적이 없으면
				dap[i][0] = x;
			} else if (dap[i][0] != x) {	// 기존값과 다른 들여쓰기 값이 생긴다면
				dap[i][0] = -1;
			}
		}
	}

	/** 마스터 코드에서 해가 되는지 체크해서 리턴 */
	private static boolean check(int R, int C, int S) {
		for (int i = 1; i < m.length; i++) {
			if (m[i][0] != m[i-1][1]*R + m[i-1][2]*C + m[i-1][3]*S) {
				return false;
			}
		}
		return true;
	}
} // end of class
