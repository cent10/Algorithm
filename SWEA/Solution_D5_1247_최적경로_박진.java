// D5 - 1247 : [S/W 문제해결 응용] 3일차 - 최적 경로

/*
26,116 kb
366 ms
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_D5_1247_최적경로_박진 {

	static class Point {
		int i, j;
		public Point (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int T;
	static int N;	// 고객의 수
	static Point[] point;	// 회사의 좌표, N명의 고객의 좌표, 집의 좌표를 저장
	static boolean[] isVisited;
	
	static int result;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = Integer.MAX_VALUE;		// result값 초기화
			
			N = Integer.parseInt(br.readLine());
			point = new Point[N+2];
			isVisited = new boolean[N+2];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			point[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 회사의 좌표
			point[N+1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 집의 좌표
			for (int i = 1; i <= N; i++) {
				point[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));	// 고객들의 좌표
			}//end input
			
			isVisited[0] = true;
			dfs(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}//end tc
		System.out.println(sb);
	}//end main

	private static void dfs(int start, int cnt, int sum) {
		// 가지치기
		if ( (start == N+1) && (cnt < N+1) )
			return;
		// 가지치기
		if (result < sum)
			return;
		
		// 기저조건
		if ( (start == N+1) && (cnt == N+1) ) {
			if (result > sum) {
				result = sum;
			}
			return;
		}
		
		for (int i = 1; i <= N+1; i++) {
			if (isVisited[i] == true)
				continue;
			
			isVisited[i] = true;
			dfs(i, cnt+1, sum + getDistance(point[start], point[i]));
			isVisited[i] = false;
		}
	}
	
	private static int getDistance(Point a, Point b) {
		return (Math.abs(a.i-b.i) + Math.abs(a.j-b.j));
	}
}
