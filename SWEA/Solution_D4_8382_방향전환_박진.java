// D4 - 8382 : 방향 전환

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_8382_방향전환_박진 {

	static class Point {
		int i, j;
		boolean dir;
		public Point (int i, int j, boolean dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}
	}
	
	static int T;
	static int x1, y1, x2, y2;
	static boolean[][] isVisited;
	static int cnt;
	static int result;
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x1 = Integer.parseInt(st.nextToken()) + 100;
			y1 = Integer.parseInt(st.nextToken()) + 100;
			x2 = Integer.parseInt(st.nextToken()) + 100;
			y2 = Integer.parseInt(st.nextToken()) + 100;

			result = Integer.MAX_VALUE;
			
			isVisited = new boolean[201][201];
			cnt = 0;
			bfs(new Point(x1, y1, false));
			
			isVisited = new boolean[201][201];
			cnt = 0;
			bfs(new Point(x1, y1, true));
			
			if (x1 == x2 && y1 == y2)
				result = -1;
			
			sb.append("#").append(tc).append(" ").append(result+1).append("\n");
		}
		System.out.println(sb);
	}

	static Queue<Point> queue = new LinkedList<Point>();
	
	private static void bfs(Point start) {
		queue.offer(start);
		isVisited[start.i][start.j] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size-- > 0) {
				Point point = queue.poll();
				
				int startFor, endFor;
				boolean direction;
				if (point.dir == false) {
					startFor = 0;
					endFor = 2;
					direction = true;
				}
				else {
					startFor = 2;
					endFor = 4;
					direction = false;
				}
				for (int d = startFor; d < endFor; d++) {
					int nexti = point.i + di[d];
					int nextj = point.j + dj[d];
					
					if (nexti < 0 || nextj < 0 || nexti >= 201 || nextj >= 201 || isVisited[nexti][nextj] == true)
						continue;
					
					if (nexti == x2 && nextj == y2) {
						if (result > cnt) {
							result = cnt;
						}
						while(!queue.isEmpty()) queue.poll();
						return;
					}
					
					isVisited[nexti][nextj] = true;
					queue.offer(new Point(nexti, nextj, direction));
				}
			}
			cnt++;
		}
	}
}
