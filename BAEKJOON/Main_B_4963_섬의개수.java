// Silver II - 4963 : 섬의 개수

/*
 * BFS
 * 15796 kb, 136 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_4963_섬의개수 {

	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int w, h;	// 지도의 너비 w와 높이 h
	static int[][] map;	// 지도
	static int result;
	
	static int[] di = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dj = {0, 0, -1, 1, -1, 1, -1, 1};
	
	static Queue<Point> queue;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {	// tc
			String str = br.readLine();
			if (str.equals("0 0")) {
				break;
			}
			StringTokenizer st = new StringTokenizer(str, " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			for (int i = 0; i < h; i++) {
//				for (int j = 0; j < w; j++) {
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			// BFS
			result = 0;
			queue = new LinkedList<Point>();
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1) {
						result++;
						queue.offer(new Point(i, j));
						map[i][j] = 2;
						
						while(!queue.isEmpty()) {
							Point cur = queue.poll();
							for (int d = 0; d < 8; d++) {
								int nexti = cur.i + di[d];
								int nextj = cur.j + dj[d];
								
								if (nexti < 0 || nextj < 0 || nexti >= h || nextj >= w)
									continue;
								if (map[nexti][nextj] != 1)
									continue;
								
								map[nexti][nextj] = 2;
								queue.offer(new Point(nexti, nextj));
							}
						}
					}
				}
			}
			sb.append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main
}
