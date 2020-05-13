// Gold V - 16236 : 아기 상어

import java.io.*;
import java.util.*;

public class Main_B_16236_아기상어_해답 {

	static class Shark {
		int row, col;
		int size; // 크기
		int depth; // bfs의 탐색 너비
		int eatCnt; // 먹은 마리수

		public Shark(int row, int col, int size, int depth, int eatCnt) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.depth = depth;
			this.eatCnt = eatCnt;
		}

		public void eat() {
			eatCnt++;
			if (eatCnt == size) {
				size++;
				eatCnt = 0;
			}
		}
	}

	static class Fish implements Comparable<Fish> {
		int row, col;
		int size;
		int dist;

		public Fish(int row, int col, int size, int dist) {
			this.row = row;
			this.col = col;
			this.size = size;
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			if (this.dist == o.dist) {
				if (this.row == o.row) {
					return Integer.compare(this.col, o.col);
				} else {
					return Integer.compare(this.row, o.row);
				}
			} else {
				return Integer.compare(this.dist, o.dist);
			}
		}
	}

	static int N; // 공간의 크기 N
	static int[][] map;

	// 상어의 이동거리
	static int sharkMoveCnt;

	// 사방 탐색
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		Shark shark = null;

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == 9) {
					shark = new Shark(r, c, 2, 0, 0);
					map[r][c] = 0;
				}
			}
		}

		bfs(shark);

		System.out.println(sharkMoveCnt);

	}// end of main

	private static void bfs(Shark shark) {
		Queue<Shark> queue = new LinkedList<>();
		queue.offer(shark);

		boolean[][] visited = new boolean[N][N];
		visited[shark.row][shark.col] = true;

		// 먹을 수 있는 물고기를 저장할 PQ
		PriorityQueue<Fish> targetFishes = new PriorityQueue<>();

		Shark front = null;
	L:	while (!queue.isEmpty()) {
			front = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = front.row + dirs[d][0];
				int nc = front.col + dirs[d][1];

				if (isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					// 이동한 점의 값에 따라서 분기 처리
					// 이동 가능
					if (map[nr][nc] == 0 || map[nr][nc] == front.size) {
						queue.offer(new Shark(nr, nc, front.size, front.depth + 1, front.eatCnt));
					}
					// 먹을 수 있다.
					else if (map[nr][nc] < front.size) {
						if (targetFishes.isEmpty()) {
							targetFishes.offer(new Fish(nr, nc, map[nr][nc], front.depth + 1));
						}
						// 비어있지 않다면....
						else {
							Fish first = targetFishes.peek();
							if (first.dist < front.depth + 1) {
								break L;
							} else {
								targetFishes.offer(new Fish(nr, nc, map[nr][nc], front.depth + 1));
							}
						}
					}
				}
			}
		}
		
		// 먹을게 없다.
		if (targetFishes.isEmpty()) {
			return;
		}
		// 맨 처음 녀석을 먹자.
		else {
			Fish food = targetFishes.poll();
			front.eat();
			map[food.row][food.col] = 0;
			
			sharkMoveCnt += food.dist;
			
			bfs(new Shark(food.row, food.col, front.size, 0, front.eatCnt));;
		}
	}

	private static boolean isIn(int row, int col) {
		return 0 <= row && row < N && 0 <= col && col < N;
	}

}
