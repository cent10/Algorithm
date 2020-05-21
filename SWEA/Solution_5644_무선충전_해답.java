// 5644. [모의 SW 역량테스트] 무선 충전

import java.util.*;
import java.io.*;

public class Solution_5644_무선충전_해답 {

	static class BC {
		int x, y, c, p;	// 좌표(x, y), 충전 범위(c), 처리량(p)
		int idx;	// BC의 index

		public BC(int x, int y, int c, int p, int idx) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
			this.idx = idx;
		}

		@Override
		public String toString() {
			return "BC [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + ", idx=" + idx + "]";
		}
	}
	
	static int T;	// 테스트 케이스의 개수
	static int max;	// 최대 충전량
	static int M, A;	// 총 이동 시간(M), BC의 개수(A)
	static int[] aPath, bPath;	// a, b의 이동 좌표
	static int ax, ay, bx, by;	// a, b의 좌표
	static BC[] list;
	static LinkedList<BC> containA = new LinkedList<>();
	static LinkedList<BC> containB = new LinkedList<>();
	
	// 제자리, 상, 우, 하, 좌
	static int[] dr = {0, -1, 0, 1, 0};
	static int[] dc = {0, 0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 데이터 입력
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			aPath = new int[M];
			bPath = new int[M];
			list = new BC[A];
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				aPath[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++) {
				bPath[i] = Integer.parseInt(st.nextToken());
			}
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				list[i] = new BC(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i);
			}
			
			// 초기화
			max = 0;
			ax = ay = 1;
			bx = by = 10;
			
			// 이동 좌표만큼 반복하면서 범위 내에 있는 BC 찾고 충전 가능한 Max 값으로 충전 후 이동
			for (int i = 0; i < M; i++) {
				max += calc();	// 충전
				ax += dc[aPath[i]];
				ay += dr[aPath[i]];
				bx += dc[bPath[i]];
				by += dr[bPath[i]];
			}
			max += calc();	// 마지막 이동에 대한 충전
			
			// 결과 출력
			System.out.println("#" + tc + " " + max);
		}// end of tc
	}// end of main

	private static int calc() {
		// BC 개수만큼 반복해서 범위 내에 있으면 keep
		for (BC bc : list) {
			if (isRange(ax, ay, bc)) {
				containA.add(bc);
			}
			if (isRange(bx, by, bc)) {
				containB.add(bc);
			}
		}
		
		// max값 구하기
		int val = 0;
		int sizeA = containA.size();
		int sizeB = containB.size();
		
		if(sizeB == 0) {	// A만 BC가 있는 경우
			for (BC bc : containA) {
				val = Math.max(val, bc.p);
			}
		} else if(sizeA == 0) {	// B만 BC가 있는 경우
			for (BC bc : containB) {
				val = Math.max(val, bc.p);
			}
		} else if (sizeA > 0 && sizeB > 0) {	// A, B 모두 BC가 있는 경우
			for (BC bcA : containA) {
				for (BC bcB : containB) {
					if (bcA.idx == bcB.idx) {	// 같은 BC 내에 A와 B 모두 있는 경우
						val = Math.max(val, bcA.p);
					} else {	// 서로 다른 BC 내에 A와 B가 있는 경우
						val = Math.max(val, bcA.p + bcB.p);
					}
				}
			}
		}
		
		// container 초기화
		containA.clear();
		containB.clear();
		
		return val;
	}

	private static boolean isRange(int x, int y, BC bc) {
		int d = Math.abs(x - bc.x) + Math.abs(y - bc.y);
		return d <= bc.c ? true : false;
	}
	
}
