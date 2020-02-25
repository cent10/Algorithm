// Gold IV - 17135 : 캐슬 디펜스

/*
20%에서 '틀렸습니다.'
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_17135_캐슬디펜스_박진_ING {

	static int N;	// 행의 수 N
	static int M;	// 열의 수 M,
	static int D;	// 궁수의 공격 거리 제한 D
	static int[][] gameBoard;
	static int[][] copiedGameBoard;
	
	static int result = 0;
	
	static int[] archerLocation = new int[3];					// 궁수들의 위치를 임시로 저장할 배열 (col 위치만 저장)
	static ArrayList<int[]> archer = new ArrayList<int[]>();	// 궁수들의 위치 조합을 저장할 array list
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 입력
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		gameBoard = new int[N][M];
		copiedGameBoard = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				gameBoard[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(gameBoard[i][j] + " ");
			}
//			System.out.println();
		}
		
		// 알고리즘
		combination(0, 0);
//		for (int i = 0; i < archer.size(); i++) {
//			System.out.println(archer.get(i)[0] + " " + archer.get(i)[1] + " " + archer.get(i)[2]);
//		}
		int size = archer.size();	// 궁수들의 위치 조합의 개수
		for (int i = 0; i < size; i++) {
			copyGameBoard();	// 게임 보드 복사
			int count = 0;
			
			archerLocation[0] = archer.get(i)[0];
			archerLocation[1] = archer.get(i)[1];
			archerLocation[2] = archer.get(i)[2];
			for (int turn = 0; turn <= M; turn++) {
				// 제거할 적 위치 체크
				for (int j = 0; j < 3; j++) {	// 궁수 3명 각각 체크
					int minD = Integer.MAX_VALUE;
					int minCol = Integer.MAX_VALUE;
					int enemyI = 0;
					int enemyJ = 0;
					boolean flag = false;
					for (int k = N-1; k >= 0; k--) {
						for (int s = 0; s < M; s++) {
							int d = getDistance(N, archerLocation[j], k, s);
							if (D < d)
								continue;
							if (copiedGameBoard[k][s] > 0) {	// 적이면
								if (D >= d) {
									if(minD > d) {
										enemyI = k;
										enemyJ = s;
										minD = d;
										minCol = s;
										flag = true;
									}
									else if (minD == d) {
										if (minCol > s) {
											enemyI = k;
											enemyJ = s;
											minD = d;
											minCol = s;
											flag = true;
										}
									}
									else
										continue;
									
								}
								else
									continue;
							}
						}
					}
					if (flag == true) {
						copiedGameBoard[enemyI][enemyJ]++;
					}
				}
				
				// 적 제거
				for (int k = N-1; k >= 0; k--) {
					for (int s = 0; s < M; s++) {
						if (copiedGameBoard[k][s] > 1) {
							copiedGameBoard[k][s] = 0;
							count++;
						}
					}
				}
				
				// 적 이동
				for (int j = N-1; j > 0; j--) {
					for (int k = 0; k < M; k++) {
						copiedGameBoard[j][k] = copiedGameBoard[j-1][k];
					}
				}
				Arrays.fill(copiedGameBoard[0], 0);
			}
			
//			System.out.println("count = " + count);
			
			if(result < count)
				result = count;
		}
		
		System.out.println(result);
		
	}
	
	private static void combination(int index, int count) {
		if(count == 3) {
			archer.add(new int[] {archerLocation[0], archerLocation[1], archerLocation[2]});
			return;
		}
		
		for (int i = index; i < M; i++) {
			archerLocation[count] = i;
			combination(i+1, count+1);
		}
	}
	
	// 궁수와 적과의 거리를 계산해서 리턴해주는 메소드
	private static int getDistance(int aR, int aC, int eR, int eC) {
		return Math.abs(aR-eR) + Math.abs(aC-eC);
	}
	
	// 게임보드 복사하는 메소드
	private static void copyGameBoard() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copiedGameBoard[i][j] = gameBoard[i][j];
			}
		}
	}
}
