// Silver I - 2667 : 단지번호붙이기 (BFS로 풀기)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
	int x, y;
	
	Point() {};
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int N;		// 지도의 크기
	static int[][] map;	// 지도
	static boolean[][] visited;
	
	// 동 서 남 북
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	
	static int numOfDanzi = 0;	// 총 단지수
	static int numOfHouse = 0;	// 각 단지내 집의 수
	static int[] arrNumOfHouse;	// 각 단지내 집의 수
	
	static Queue<Integer> queue = new LinkedList<Integer>();
	static Queue<Point> queueForBFS = new LinkedList<Point>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		// 입력
		for (int i = 0; i < N; i++) {
			String S = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(""+S.charAt(j));
//				System.out.print(map[i][j]);
			}
//			System.out.println();
		}
		
		// 알고리즘
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1 && visited[i][j] == false) {
					numOfDanzi++;
					numOfHouse = 0;
					bfs(i, j);
//					System.out.println("numOfHouse= " + numOfHouse);
					queue.offer(numOfHouse);
				}
			}
		}
		
		// 총 단지수 출력
		System.out.println(numOfDanzi);
		// 각 단지내 집의 수를 오름차순으로 정렬하여 출력
		arrNumOfHouse = new int[numOfDanzi];
		for (int i = 0; i < numOfDanzi; i++) {
			arrNumOfHouse[i] = queue.poll();
		}
		Arrays.sort(arrNumOfHouse);
		for (int i = 0; i < numOfDanzi; i++) {
			System.out.println(arrNumOfHouse[i]);
		}
	}

	private static void bfs(int x, int y) {
		visited[x][y] = true;
		numOfHouse++;
		queueForBFS.offer(new Point(x, y));
		
		while (!queueForBFS.isEmpty()) {
			for (int i = 0; i < 4; i++) {
				int nextx = queueForBFS.peek().x + dx[i];
				int nexty = queueForBFS.peek().y + dy[i];
				
				if (nextx < 0 || nextx >= N || nexty < 0 || nexty >= N)
					continue;
				
				if (map[nextx][nexty] == 1 && visited[nextx][nexty] == false) {
					queueForBFS.offer(new Point(nextx, nexty));
					visited[nextx][nexty] = true;
					numOfHouse++;
				}
			}
			queueForBFS.poll();
		}
		
	}
}
