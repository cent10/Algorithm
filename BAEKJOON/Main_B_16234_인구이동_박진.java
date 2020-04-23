// Gold V - 16234 : 인구 이동

/*
 * 20,988 kb, 428 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_16234_인구이동_박진 {

	static int N, L, R;
	static int[][] map;
	static int[] parent;	// 연합
	static int[][] sum;	// 연합의 인구수, 연합을 이루고 있는 칸의 개수
	static int result;	// 인구이동이 발생하는 횟수
	
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		parent = new int[N*N];
		sum = new int[N*N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		result = -1;
		boolean flag;	// 연합이 있으면 true, 없으면 false
		do {
//			System.out.println("==처음==");
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			result++;
			flag = false;
			makeSet();	// parent 초기화
			for (int i = 0; i < N * N; i++) {	// sum 초기화
				Arrays.fill(sum[i], 0);
			}
//			System.out.println(Arrays.toString(parent));
			for (int n = 0; n < N * N; n++) {	// 연합 찾기
				int i = n / N;
				int j = n % N;
				
				for (int d = 0; d < 4; d++) {
					int nexti = i + di[d];
					int nextj = j + dj[d];
					int nextn = (nexti * N) + nextj;
					
					if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N)	// 범위 체크
						continue;
					int temp = Math.abs(map[i][j] - map[nexti][nextj]);
					if(temp < L || temp > R)	// 인구차이 체크
						continue;
					
					if (union(n, nextn)) {	// 연합이 하나라도 생기면 flag를 true로 바꿔서 다시 반복문을 돌도록 함.
						flag = true;
					}
				}
			}
			
			for (int n = 0; n < N * N; n++) {	// 다시 한번 findSet을 해줌으로써, 같은 연합에 속한 나라들이 하나의 parent를 가리키도록 해줌.
				parent[n] = findSet(n);
			}
//			System.out.println(Arrays.toString(parent));
			
			if (flag) {
				for (int n = 0; n < N * N; n++) {	// 연합의 인구수와 연합을 이루고 있는 칸의 개수 구하기
					int i = n / N;
					int j = n % N;
					sum[parent[n]][0] += map[i][j];
					sum[parent[n]][1] += 1;
				}
				
//				System.out.print("sum[i][0] : ");
//				for (int i = 0; i < N *N; i++) {
//					System.out.print(sum[i][0] + ", ");
//				}
//				System.out.println();
				
				for (int n = 0; n < N * N; n++) {	// 인구 이동
					int i = n / N;
					int j = n % N;
					map[i][j] = sum[parent[n]][0] / sum[parent[n]][1];
				}
//				System.out.println("==인구이동==");
//				for (int i = 0; i < N; i++) {
//					for (int j = 0; j < N; j++) {
//						System.out.print(map[i][j] + " ");
//					}
//					System.out.println();
//				}
//				System.out.println();
			}
		}while(flag);
		
		System.out.println(result);
	}// end of main
	
	public static void makeSet() {
		for (int i = 0; i < N*N; i++) {
			parent[i] = i;
		}
	}
	
	public static int findSet(int a) {
		if (parent[a] == a)
			return a;
		return parent[a] = findSet(parent[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) {
			if (aRoot < bRoot) {
				parent[bRoot] = findSet(aRoot);
			} else {
				parent[aRoot] = findSet(bRoot);
			}
			return true;
		}
		return false;
	}

}
