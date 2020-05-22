// 2112. [모의 SW 역량테스트] 보호 필름

import java.io.*;
import java.util.*;

public class Solution_2112_보호필름_해답 {

	static int[][] map;
	static int D, W;	// 행, 열
	static int K;	// 합격기준
	static int min;
	//약품의 투여 여부를 구성하는 부분집합
	static int[] list;	// 0: 약품을 투여하지 않은 경우, 1: A약물을 투여한 경우, 2: B약물을 투여한 경우
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 초기화
			min = Integer.MAX_VALUE;
			
			// 데이터 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[D][W];
			list = new int[D];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 부분집합
			dfs(0, 0);
			
			// 결과출력
			System.out.println("#" + tc + " " + min);
		}
	}

	private static void dfs(int row, int count) {
		if (row == D) {	// 기저조건
			// 약품을 투여해서 합격기준(K)를 통과한 경우
			if (check()) {
				min = Math.min(min, count);
			}
			return;
		}
		
		if (count >= min)	// 가지치기
			return;
		
		// 부분집합
		// 약품을 투여하지 않은 경우
		list[row] = 0;
		dfs(row+1, count);	// 투약하지 않았기 때문에 count는 그대로
		
		// A약물을 투여한 경우
		list[row] = 1;
		dfs(row+1, count+1);
		
		// B약물을 투여한 경우
		list[row] = 2;
		dfs(row+1, count+1);
	}

	private static boolean check() {
		int count;	// 연속된 셀을 세는 카운트
		int cur, next;	// 검사할 현재 셀과 다음 셀
		
		for (int i = 0; i < W; i++) {	// 열
			count = 1;
			for (int j = 0; j < D-1; j++) {	// 행
				cur = map[j][i];
				next = map[j+1][i];
				if (list[j] > 0) {	// 현재 행에 약품을 투여
					cur = list[j] - 1;	// a:1, b:2 --> a:0, b:1
				}
				if (list[j+1] > 0) {	// 현재 행에 약품을 투여
					next = list[j+1] - 1;	// a:1, b:2 --> a:0, b:1
				}
				if (cur == next) {	// 연속된 경우
					count++;
					if (count >= K) {	// 해당 열은 합격기준 통과
						break;
					}
				} else {	// 연속되지 않은 경우
					count = 1;
				}
			}
			if (count < K)	// 합격기준 통과하지 못함
				return false;
		}
		
		return true;
	}
	
}
