// D3 - 4615 : 재미있는 오셀로 게임

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D3_4615_재미있는오셀로게임_박진 {

	static class Point {
		int i, j;
		
		public Point (int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	static int T;	// 테스트 케이스의 수 T
	static int N;	// 보드의 한 변의 길이 N (N은 4, 6, 8 중 하나)
	static int M;	// 플레이어가 돌을 놓는 횟수 M
	static int[][] board;	// 게임판
	
	static int[] di = { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] dj = { 0, 0, -1, 1, -1, 1, 1, -1 };
	
	static Queue<Point> queue = new LinkedList<Point>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int numOfB = 0;
			int numOfW = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			board = new int[N+2][N+2];
			board[N/2][N/2] = 2;
			board[N/2][N/2+1] = 1;
			board[N/2+1][N/2] = 1;
			board[N/2+1][N/2+1] = 2;
//			for (int[] a : board) {
//				System.out.println(Arrays.toString(a));
//			}
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int stone = Integer.parseInt(st.nextToken());
				
				board[y][x] = stone;
				for (int d = 0; d < 8; d++) {
					int nexti = y + di[d];
					int nextj = x + dj[d];
					boolean flag = false;	// 상대 돌들을 바꿀 수 있으면 true, 바꿀 수 없으면 false
					
					if (board[y][x] == 1) {
						if (board[nexti][nextj] == 2) {
							while (true) {
								queue.offer(new Point(nexti, nextj));
								nexti = nexti + di[d];
								nextj = nextj + dj[d];
								
								if (board[nexti][nextj] != 2) {
									if (board[nexti][nextj] == 1) {
										flag = true;
									}
									break;
								}
							}
						}
						else
							continue;
					}
					else if (board[y][x] == 2) {
						if (board[nexti][nextj] == 1) {
							while (true) {
								queue.offer(new Point(nexti, nextj));
								nexti = nexti + di[d];
								nextj = nextj + dj[d];
								
								if (board[nexti][nextj] != 1) {
									if (board[nexti][nextj] == 2) {
										flag = true;
									}
									break;
								}
							}
						}
						else
							continue;
					}
					else
						continue;
					
					Point point;
					if (flag == true) {
						while(!queue.isEmpty()) {
							point = queue.poll();
							if (board[point.i][point.j] == 1)
								board[point.i][point.j] = 2;
							else if (board[point.i][point.j] == 2)
								board[point.i][point.j] = 1;
						}
					} else {
						while(!queue.isEmpty()) {
							queue.poll();
						}
					}
				}
			}
			
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (board[i][j] == 1)
						numOfB++;
					else if (board[i][j] == 2)
						numOfW++;
				}
			}
			
			sb.append("#").append(tc).append(" ").append(numOfB).append(" ").append(numOfW).append("\n");
		}
		System.out.println(sb);
	}
}
