// D4 - 8382 : 방향 전환

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution_D4_8382_방향전환_해답1_BFS {

	static class Point {
		int x, y, cnt;
		boolean dir;
		
		Point (int x, int y, int cnt, boolean dir) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}
	}
	
	static int sx;
	static int sy;
	static int fx;
	static int fy;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			sx = sc.nextInt() + 100;
			sy = sc.nextInt() + 100;
			fx = sc.nextInt() + 100;
			fy = sc.nextInt() + 100;
			
			// BFS탐색을 위한 Queue자료구조를 준비
			Queue<Point> queue = new LinkedList<>();
			// 방문체크를 위한 방문체크 변수를 준비
			boolean[][][] visited = new boolean[201][201][2];
			// 시작점을 큐에 삽입
			queue.add(new Point(sx, sy, 0, true));
			queue.add(new Point(sx, sy, 0, false));
			// 		첫 시작점을 현재 위치로, 다음 이동을 가로로 이동할 수 있는 상태
			// 		첫 시작점을 현재 위치로, 다음 이동을 세로로 이동할 수 있는 상태
			// 큐에 이미 삽입된 상태에 대해서는 방문체크
			visited[sx][sy][0] = true;
			visited[sx][sy][1] = true;
			
			// 큐가 빌때까지 상태를 탐색
			while(!queue.isEmpty()) {
				Point point = queue.poll();
				// 현재 도달한 상태의 좌표가 목적지라면 종료.
				if (point.x == fx && point.y == fy) {
					System.out.println("#" + tc + " " + point.cnt);
					break;
				}
				if (point.dir) {
					// 		현재 자신의 방향이 가로로 허용된 경우
					//			두가지 세로이동에 대해서 유효하다면(범위체크, 방문체크), 상태노드를 큐에 추가 후 방문 체크
					for (int d = 0; d < 2; d++) {
						int nx = point.x + dx[d];
						int ny = point.y + dy[d];
						if (nx < 0 || ny < 0 || nx >= 201 || ny >= 201)
							continue;
						if (visited[nx][ny][0])
							continue;
						queue.add(new Point(nx, ny, point.cnt+1, !point.dir));
						visited[nx][ny][0] = true;
					}
				}
				else {
					// 		현재 자신의 방향이 세로로 허용된 경우
					//			두가지 가로이동에 대해서 유효하다면, 상태노드를 큐에 추가 후 방문 체크
					for (int d = 2; d < 4; d++) {
						int nx = point.x + dx[d];
						int ny = point.y + dy[d];
						if (nx < 0 || ny < 0 || nx >= 201 || ny >= 201)
							continue;
						if (visited[nx][ny][1])
							continue;
						queue.add(new Point(nx, ny, point.cnt+1, !point.dir));
						visited[nx][ny][1] = true;
					}
				}
			}
		}
	}
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
}
