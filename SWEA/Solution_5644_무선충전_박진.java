// 5644. [모의 SW 역량테스트] 무선 충전

/*
 * 시뮬레이션
 * 23,156 kb, 130 ms
 */

import java.io.*;
import java.util.*;

public class Solution_5644_무선충전_박진 {
	
	static class BC {	// BC의 정보
		int x, y, c, p;
		public BC(int x, int y, int c, int p) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
		@Override
		public String toString() {
			return "BC [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
		}
	}
	
	static int T;
	static int M, A;	// 총 이동 시간(M), BC의 개수(A)
	
	// 0:이동X, 1:상, 2:우, 3:하, 4:좌
	static int[] infoA;	// A의 이동 정보
	static int[] infoB;	// B의 이동 정보
	
	// BC의 정보
	static int X, Y, C, P;	// 좌표(X, Y), 충전 범위(C), 처리량(P)
	static ArrayList<BC> bcList;	// BC 정보들
	
	static int result;	// 모든 사용자의 충전량 합의 최대값
	
	static int[] di = { 0, 0, 1, 0, -1 };
	static int[] dj = { 0, -1, 0, 1, 0 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			infoA = new int[M];
			infoB = new int[M];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				infoA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				infoB[i] = Integer.parseInt(st.nextToken());
			}
			bcList = new ArrayList<>();
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				X = Integer.parseInt(st.nextToken());
				Y = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				P = Integer.parseInt(st.nextToken());
				bcList.add(new BC(X, Y, C, P));
			}
			
			result = 0;
			// A 위치
			int aX = 1;
			int aY = 1;
			// B 위치
			int bX = 10;
			int bY = 10;
			for (int m = 0; m <= M; m++) {	// 반복 시작
				
//				System.out.println("== " + m + "번째 ==");
				
				// 충전
				int sum = 0;	// 한 턴에서의 두 사용자의 충전량의 최대합
				boolean[] checkA = new boolean[A];	// A가 사용가능한 BC 체크
				boolean[] checkB = new boolean[A];	// B가 사용가능한 BC 체크
				for (int i = 0; i < A; i++) {	// 사용자들이 사용가능한 BC 체크
					if (getDistance(aX, aY, bcList.get(i).x, bcList.get(i).y) <= bcList.get(i).c)
						checkA[i] = true;
					if (getDistance(bX, bY, bcList.get(i).x, bcList.get(i).y) <= bcList.get(i).c)
						checkB[i] = true;
				}
				// 가능한 점수 조합 찾기
				for (int i = 0; i < A; i++) {
					for (int j = 0; j < A; j++) {
						int tempA = 0;
						int tempB = 0;
						int tempSum = 0;
						if (checkA[i]) {
							tempA = bcList.get(i).p;
						}
						if (checkB[j]) {
							tempB = bcList.get(j).p;
						}
						tempSum = tempA + tempB;
						if ((i == j) && checkA[i] && checkB[j]) {	// 두 사용자가 같은 BC를 사용할 때
							tempSum /= 2;
						}
						sum = sum < tempSum ? tempSum : sum;
						
//						System.out.println("tempA = " + tempA);
//						System.out.println("tempB = " + tempB);
						
					}
				}
				
//				System.out.println("A --> " + Arrays.toString(checkA));
//				System.out.println("B --> " + Arrays.toString(checkB));
//				System.out.println("합: " + sum);
				
				result += sum;
				// 이동
				if (m == M)	// 맨 마지막에는 이동하지 않음
					continue;
				aX = aX + di[infoA[m]];
				aY = aY + dj[infoA[m]];
				bX = bX + di[infoB[m]];
				bY = bY + dj[infoB[m]];
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	// 거리 반환 메소드
	private static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1- x2) + Math.abs(y1 - y2);
	}
}
