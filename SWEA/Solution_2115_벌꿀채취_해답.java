// 2115 : [모의 SW 역량테스트] 벌꿀채취

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 완전탐색 : 부분집합 --> 조합
public class Solution_2115_벌꿀채취_해답 {

	static int T;			// 총 테스트 케이스의 개수 T
	static int N, M, C;		// N: 벌통크기, M: 연속된벌통수, C: 채취량
	static int[][] map;		// 입력된 벌통정보
	static int[][] maxMap;	// i,j위치에서 가질수 있는 최대이익
	static int result;	// 두 일꾼이 꿀을 채취하여 얻을 수 있는 최대 수익
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			maxMap = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}// end input
			
			// 1. 각 i,j 위치에서 연속된 M개를 고려하여 취할 수 있는 부분집합의 최대이익 계산
			makeMaxMap();
			// 2. 두 일꾼의 조합
			sb.append("#").append(tc).append(" ").append(getMaxBenefit()).append("\n");
		}
		// 출력
		System.out.println(sb);
	}// end main
	
	private static void makeMaxMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {
				makeMaxSubset(i, j, 0, 0, 0);
			}
		}
	}
	
	// i: 행위치, j: 열위치, cnt: 고려해보고 있는 원소의 수
	// sum: 부분집합에 속한 원소들의 합
	// powSum: 부분집합에 속한 원소들의 이익의 합
	private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {
		// 가지치기
		if (sum > C)	// 부분집합의 합이 목표량 C를 초과하면 리턴
			return;
		
		// 기저조건
		if (cnt == M) {
			if(maxMap[i][j-M] < powSum) {
				maxMap[i][j-M] = powSum;
			}
			return;
		}
		
		// (i,j)위치의 원소 선택
		makeMaxSubset(i, j+1, cnt+1, sum+map[i][j], powSum+map[i][j]*map[i][j]);
		
		// (i,j)위치의 원소 비선택
		makeMaxSubset(i, j+1, cnt+1, sum, powSum);
	}
	
	private static int getMaxBenefit() {
		int max = 0;	// max: 조합적 선택 후의 최대이익값
		int temp = 0;
		
		// 1. 일꾼A의 기준 선택
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <= N-M; j++) {	// 일꾼A
				// 2. 일꾼B 선택
				// 2-1. 같은 행기준 선택
				for (int j2 = j+M; j2 <= N-M; j2++) {
					temp = maxMap[i][j] + maxMap[i][j2];	// 두 일꾼 조합의 이익
					if (max < temp) {
						max = temp;
					}
				}
				// 2-2. 다음행부터 마지막행까지 선택
				for (int i2 = i+1; i2 < N; i2++) {
					for (int j2 = 0; j2 <= N-M; j2++) {
						temp = maxMap[i][j] + maxMap[i2][j2];	// 두 일꾼 조합의 이익
						if (max < temp) {
							max = temp;
						}
					}
				}
			}
		}
		return max;
	}
}
