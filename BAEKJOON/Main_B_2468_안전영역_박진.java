// Silver II - 2468 : 안전 영역

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_2468_안전영역_박진 {

	static int N;
	static int[][] map;
	static int result = 0;
	
	static int maxHeight = 0;	// 가장 높은 지역의 높이
	
	static Queue<Point> queue = new LinkedList<Point>();
	static boolean[][] isSelected;
	
	static class Point {
		int i;
		int j;
		
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (maxHeight < map[i][j])
					maxHeight = map[i][j];
			}
		}
		
		// 알고리즘
		for (int rain = 0; rain <= maxHeight; rain++) {
			isSelected = new boolean[N][N];
			int count = 0;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > rain && isSelected[i][j] == false) {
						bfs(new Point(i, j), rain);
						count++;
					}
				}
			}
			
			if (result < count)
				result = count;
		}
		
		System.out.println(result);
	}

	// 잠기지 않는 지역들을 bfs로 탐색하면서 true로 바꾸는 메소드
	static private void bfs(Point start, int rain) {
		queue.offer(start);
		isSelected[start.i][start.j] = true;
		
		Point p;
		while (!queue.isEmpty()) {
			p = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nexti = p.i + di[d];
				int nextj = p.j + dj[d];
				
				if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= N || isSelected[nexti][nextj] == true)
					continue;
				
				if (map[nexti][nextj] <= rain)	// 잠기는 지역은 무시
					continue;
				
				isSelected[nexti][nextj] = true;
				queue.offer(new Point(nexti, nextj));
			}
		}
	}
}
