// 4014. [모의 SW 역량테스트] 활주로 건설

import java.io.*;
import java.util.*;

public class Solution_4014_활주로건설_박진 {

	static int N, X;	// 지도 크기 N (2 ≤ N ≤ 100), 경사로의 길이 L (1 ≤ L ≤ N)
	static int[][] map;
	static int result;	// 지나갈 수 있는 길의 개수
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 구현
			// 초기화
			result = 2 * N;
			// 가로길 체크
		L:	for (int i = 0; i < N; i++) {
				boolean[] check = new boolean[N];	// 경사로가 이미 놓여있는지 체크 (놓여있으면 true)
				for (int j = 0; j < N - 1; j++) {
					// 바로 다음 경로 체크
					if (map[i][j] == map[i][j + 1]) {	// 높이가 같을 때는 다음 경로 확인
						continue;
					} else if (map[i][j] - map[i][j + 1] == 1) {	// 1만큼 낮은 경로일 때
						int tempCnt = 0;	// 경사로를 놓을 수 있는 바닥의 개수 카운팅
						for (int k = 1; k <= X; k++) {
							// 범위 체크
							if (j + k >= N) {
								break;
							}
							// 높이 체크
							if (map[i][j] != map[i][j + k] + 1) {
								result--;
								continue L;
							} else {
								tempCnt++;
								check[j + k] = true;
							}
						}
						// 경사로 놓을 바닥 개수 비교
						if (tempCnt != X) {
							result--;
							continue L;
						}
					} else if (map[i][j] - map[i][j + 1] == -1) {	// 1만큼 높은 경로일 때
						int tempCnt = 0;	// 경사로를 놓을 수 있는 바닥의 개수 카운팅
						for (int k = 1; k <= X; k++) {
							// 범위 체크
							if (j + 1 - k < 0) {
								break;
							}
							// 높이 체크
							if (map[i][j + 1] != map[i][j + 1 - k] + 1) {
								result--;
								continue L;
							} else {
								if (!check[j + 1 - k]) {	// 아직 경사로가 안놓인 바닥이라면
									tempCnt++;
								} else {	// 이미 경사로가 놓여있다면
									result--;
									continue L;
								}
							}
						}
						// 경사로 놓을 바닥 개수 비교
						if (tempCnt != X) {
							result--;
							continue L;
						}
					} else {	// 높이 차이가 2이상일 때
						result--;
						continue L;	// 지나갈 수 없는 길이므로, 다른 길 체크하러 감
					}
				}
			}
			
			// 세로길 체크
			L:	for (int j = 0; j < N; j++) {
				boolean[] check = new boolean[N];	// 경사로가 이미 놓여있는지 체크 (놓여있으면 true)
				for (int i = 0; i < N - 1; i++) {
					// 바로 다음 경로 체크
					if (map[i][j] == map[i + 1][j]) {	// 높이가 같을 때는 다음 경로 확인
						continue;
					} else if (map[i][j] - map[i + 1][j] == 1) {	// 1만큼 낮은 경로일 때
						int tempCnt = 0;	// 경사로를 놓을 수 있는 바닥의 개수 카운팅
						for (int k = 1; k <= X; k++) {
							// 범위 체크
							if (i + k >= N) {
								break;
							}
							// 높이 체크
							if (map[i][j] != map[i + k][j] + 1) {
								result--;
								continue L;
							} else {
								tempCnt++;
								check[i + k] = true;
							}
						}
						// 경사로 놓을 바닥 개수 비교
						if (tempCnt != X) {
							result--;
							continue L;
						}
					} else if (map[i][j] - map[i + 1][j] == -1) {	// 1만큼 높은 경로일 때
						int tempCnt = 0;	// 경사로를 놓을 수 있는 바닥의 개수 카운팅
						for (int k = 1; k <= X; k++) {
							// 범위 체크
							if (i + 1 - k < 0) {
								break;
							}
							// 높이 체크
							if (map[i + 1][j] != map[i + 1 - k][j] + 1) {
								result--;
								continue L;
							} else {
								if (!check[i + 1 - k]) {	// 아직 경사로가 안놓인 바닥이라면
									tempCnt++;
								} else {	// 이미 경사로가 놓여있다면
									result--;
									continue L;
								}
							}
						}
						// 경사로 놓을 바닥 개수 비교
						if (tempCnt != X) {
							result--;
							continue L;
						}
					} else {	// 높이 차이가 2이상일 때
						result--;
						continue L;	// 지나갈 수 없는 길이므로, 다른 길 체크하러 감
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

}
