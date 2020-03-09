// D5 - 7793 : 오! 나의 여신님

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 
 * @author Park
 * - 안전지역으로 가기 위한 최소시간: BFS
 * - 악마와 수연이가 각각 별도의 Queue에서 동작
 * 
 * - 악마의 *: 1초마다 사방으로 퍼져나간다, (., S를 대상으로)
 * - 수연 S: 1초마다 사방탐색으로 이동, .을 대상으로 이동, D를 만나면 종료
 * - 수연이 없어지면 - 종료
 *
 */

public class Solution_D5_7793_오나의여신님_해답2 {
	static class Point {
		int row, col;
		int depth;
		boolean isDevil;
		public Point(int row, int col, int depth, boolean isDevil) {
			super();
			this.row = row;
			this.col = col;
			this.depth = depth;
			this.isDevil = isDevil;
		}
		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + ", cnt=" + depth + "]";
		}
		
	}
	
	// 출력을 모아서!!
	static StringBuilder sb = new StringBuilder();
	// 사방 탐색
	static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	
	static int T, N, M, A;
	static char[][] map;
	static Queue<Point> points;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			////////////////////////////////////////////////////////////////////////
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			A = Integer.MAX_VALUE;
			map = new char[N][M];
			points = new LinkedList<>();
			Point player = null;
			for (int n = 0; n < N; n++) {
				map[n] = br.readLine().toCharArray();
				for (int m = 0; m < M; m++) {
					if(map[n][m] == '*') {
						points.offer(new Point(n, m, 0, true));
					}
					else if (map[n][m] == 'S') {
						player = new Point(n, m, 0, false);
					}
				}
			}
			points.offer(player);	// *****S
			
			// 이제 탐험 시작!!
			// 끝나는 시점: 모든 수연이 소멸할때까지 or 수연이가 여신에게 도달할때까지
outer:		while(!points.isEmpty()) {
				Point front = points.poll();
				
				for (int d = 0; d < dirs.length; d++) {
					int nr = front.row + dirs[d][0];
					int nc = front.col + dirs[d][1];
					
					if(isIn(nr, nc)) {	// 범위에 있다면 수연이는 .으로 이동, D를 만나면 끝
						if(front.isDevil) {
							if(map[nr][nc]=='.' || map[nr][nc]=='S') {
								// 지도의 상태를 변경한것 자체가 방문처리.
								map[nr][nc]='*';
								// 큐에 새로 들어가긴 하지만 빠지지는 않는다.
								points.offer(new Point(nr, nc, front.depth+1, true));
							}
						} else {
							if(map[nr][nc]=='D') {
								A = front.depth + 1;
								break outer;
							} else if(map[nr][nc]=='.') {
								map[nr][nc]='S';
								points.offer(new Point(nr, nc, front.depth+1, false));
							}
						}
					}
				}
			}
			sb.append((A==Integer.MAX_VALUE ? "GAME OVER" : A));
			////////////////////////////////////////////////////////////////////////
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean isIn(int r, int c) {
		return 0 <= r && 0 <= c && r < N && c < M;
	}
	
}
