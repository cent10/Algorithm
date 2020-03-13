// D4 - 3378 : 스타일리쉬 들여쓰기

import java.util.*;
import java.io.*;

public class Solution_D4_3378_스타일리쉬들여쓰기_박진 {

	static int p, q;
	static String[] master;
	static String[] me;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
			master = new String[p];
			me = new String[q];
			for (int i = 0; i < p; i++) {
				master[i] = br.readLine();
			}
			for (int i = 0; i < q; i++) {
				me[i] = br.readLine();
			}// end input
			
			int[][] masterCnt = new int[p][4];
			for (int i = 0; i < p; i++) {	// 마스터의 스타일 분석
				if (i > 0) {
					masterCnt[i][1] += masterCnt[i-1][1];
					masterCnt[i][2] += masterCnt[i-1][2];
					masterCnt[i][3] += masterCnt[i-1][3];
				}
				
				int tempIndex = 0;
				while (master[i].charAt(tempIndex) == '.') {
					masterCnt[i][0]++;
					tempIndex++;
				}
				for (int j = tempIndex; j < master[i].length(); j++) {
					switch (master[i].charAt(j)) {
						case '(': masterCnt[i][1]++; break;
						case ')': masterCnt[i][1]--; break;
						case '{': masterCnt[i][2]++; break;
						case '}': masterCnt[i][2]--; break;
						case '[': masterCnt[i][3]++; break;
						case ']': masterCnt[i][3]--; break;
					}
				}
			}	// 마스터의 스타일 분석
			
			int[][] meCnt = new int[q][4];
			for (int i = 0; i < q; i++) {	// 나의 스타일 분석
				if (i > 0) {
					meCnt[i][1] += meCnt[i-1][1];
					meCnt[i][2] += meCnt[i-1][2];
					meCnt[i][3] += meCnt[i-1][3];
				}
				for (int j = 0; j < me[i].length(); j++) {
					switch (me[i].charAt(j)) {
						case '(': meCnt[i][1]++; break;
						case ')': meCnt[i][1]--; break;
						case '{': meCnt[i][2]++; break;
						case '}': meCnt[i][2]--; break;
						case '[': meCnt[i][3]++; break;
						case ']': meCnt[i][3]--; break;
					}
				}
			}	// 나의 스타일 분석
			
			for (int i = 1; i < q; i++) {	// 나의 들여쓰기 횟수 초기화
				meCnt[i][0] = -2;
			}
			for (int r = 1; r <= 20; r++) {	// R, C, S 구하기
				for (int c = 1; c <= 20; c++) {
					for (int s = 1; s <= 20; s++) {
						int checkCnt = 1;
						for (int i = 1; i < p; i++) {
							if (masterCnt[i][0] == r*masterCnt[i-1][1] + c*masterCnt[i-1][2] + s*masterCnt[i-1][3]) {
								checkCnt++;
							}
						}
						
						if (checkCnt == p) {	// 이 R,C,S 조합은 마스터의 스타일을 만족하므로, 이 조합으로 나의 스타일에 적용.
							for (int i = 1; i < q; i++) {
								int temp = r*meCnt[i-1][1] + c*meCnt[i-1][2] + s*meCnt[i-1][3];
								if (meCnt[i][0] == -2) {
									meCnt[i][0] = temp;
								} else {
									if (meCnt[i][0] != temp) {
										meCnt[i][0] = -1;
									}
								}
							}
						}
					}
				}
			}	// R, C, S 구하기
			sb.append("#").append(tc);
			for (int i = 0; i < q; i++) {
				sb.append(" ").append(meCnt[i][0]);
			}
			sb.append("\n");
		}// end tc
		System.out.print(sb);
	}// end main
}
