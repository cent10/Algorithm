import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_17472_다리만들기2_해답 {
	static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int INF = 987654321;

	static int R, C, islandIdx;
	static int[][] map, graph;

	public static void main(String[] args) throws IOException {
		// 입력 처리
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(br.readLine());

		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());

		map = new int[R][C];

		for (int r = 0; r < R; r++) {
			tokens = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}

		/*
		 * System.out.println("지도 확인"); for (int[] row : map) {
		 * System.out.println(Arrays.toString(row)); }
		 */

		// 각 섬 구별
		islandIdx = 2;
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] == 1) {
					bfs(r, c);
					islandIdx++;
				}
			}
		}
		/*
		 * System.out.println("섬 확인: " + islandIdx); for (int[] row : map) {
		 * System.out.println(Arrays.toString(row)); }
		 */

		// graph 구성하기 - 각 섬간에 최단 거리 찾아서 업데이트 - 최대값으로 초기화
		graph = new int[islandIdx][islandIdx];
		for (int r = 2; r < islandIdx; r++) {
			Arrays.fill(graph[r], INF);
		}

		// 각 섬별로 거리 찾아보기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] > 1) {
					makeGraph(r, c);
				}
			}
		}

		/*
		 * System.out.println("그래프 확인"); for (int[] row : graph) {
		 * System.out.println(Arrays.toString(row)); }
		 */

		// MST 구하기
		System.out.println(prim());
	}

	static int prim() {
		PriorityQueue<Vertex> pq = new PriorityQueue<>();

		Vertex[] vertexes = new Vertex[islandIdx];
		for (int i = 2; i < islandIdx; i++) {
			if (i == 2) {
				vertexes[i] = new Vertex(i, 0);
			} else {
				vertexes[i] = new Vertex(i, INF);
			}
			pq.offer(vertexes[i]);
		}

		int sum = 0;
		while (!pq.isEmpty()) {
			Vertex front = pq.poll();
			if (front.cost == INF) {
				return -1;
			}
			sum += front.cost;
			for (int i = 2; i < islandIdx; i++) {
				Vertex child = vertexes[i];
				if (pq.contains(child)) {
					if (child.cost > graph[front.idx][i]) {
						child.cost = graph[front.idx][i];
						pq.remove(child);
						pq.offer(child);
					}
				}
			}
		}
		return sum;
	}

	static void makeGraph(int row, int col) {
		int base = map[row][col];
		for (int d = 0; d < dirs.length; d++) {
			for (int l = 1;; l++) {
				int nr = row + dirs[d][0] * l;
				int nc = col + dirs[d][1] * l;
				if (isIn(nr, nc)) {
					if (map[nr][nc] == 0) {
						continue;
					} else if (map[nr][nc] == base) {
						break;
					} else {
						if (l > 2) {
							graph[map[nr][nc]][base] = graph[base][map[nr][nc]] = Math.min(graph[base][map[nr][nc]],
									l - 1);
						}
						break;
					}
				} else {
					break;
				}
			}
		}
	}

	static void bfs(int row, int col) {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(row, col));

		map[row][col] = islandIdx;

		while (!q.isEmpty()) {
			Pair front = q.poll();

			for (int d = 0; d < dirs.length; d++) {
				int nr = front.row + dirs[d][0];
				int nc = front.col + dirs[d][1];

				if (isIn(nr, nc) && map[nr][nc] == 1) {
					map[nr][nc] = islandIdx;
					q.offer(new Pair(nr, nc));
				}
			}
		}
	}

	static boolean isIn(int row, int col) {
		return 0 <= row && row < R && 0 <= col && col < C;
	}

	static class Vertex implements Comparable<Vertex> {
		int idx, cost;

		public Vertex(int idx, int cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	static class Pair {
		int row, col;

		public Pair(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
}