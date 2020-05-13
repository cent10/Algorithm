// D5 - 1798 : 범준이의 제주도 여행 계획

import java.util.*;
import java.io.*;

public class Solution_D5_1798_범준이의제주도여행계획_해답 {

	static class Point {
		String type; // A, H, P;
		int idx;
		int playTime;
		int satis;
		Point nearH;

		public Point(String type, int idx) {
			this(type, idx, 0, 0);
		}

		public Point(String type, int idx, int playTime, int satis) {
			super();
			this.type = type;
			this.idx = idx;
			this.playTime = playTime;
			this.satis = satis;
		}

		@Override
		public String toString() {
			return "Point [type=" + type + ", idx=" + idx + ", playTime=" + playTime + ", satis=" + satis + "]";
		}
	}

	static int T;
	static int N, M; // 지점 개수 N, 휴가 기간 M

	static int[][] graph; // 각 정점을 연결할 그래프

	// 관리할 지점들
	static Point airport;
	static List<Point> hotels;
	static List<Point> tourSpots;

	// 최대 만족도
	static int maxSatis;
	static List<Integer> maxSatisPath;

	// 탐색에 사용할 임시 경로
	static Stack<Integer> tempPath;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			graph = new int[N + 1][N + 1];
			for (int r = 1; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = r + 1; c < graph.length; c++) {
					graph[r][c] = graph[c][r] = Integer.parseInt(st.nextToken());
				}
			}
			// System.out.println("그래프확인");
			// for(int[] row : graph) {
			// System.out.println(Arrays.toString(row));
			// }

			// 2. 지점 정보 가져오기
			hotels = new ArrayList<>();
			tourSpots = new ArrayList<>();

			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine(), " ");
				String type = st.nextToken();
				if (type.equals("H")) {
					hotels.add(new Point(type, n));
				} else if (type.equals("A")) {
					airport = new Point(type, n);
				} else {
					int playTime = Integer.parseInt(st.nextToken());
					int satis = Integer.parseInt(st.nextToken());
					tourSpots.add(new Point(type, n, playTime, satis));
				}
			}
			// System.out.println(hotels);
			// System.out.println(airport);
			// System.out.println(tourSpots);

			// 3. 관광지에 가장 가까운 호텔 정보 설정해주기. - graph
			for (Point p : tourSpots) {
				int min = Integer.MAX_VALUE;
				for (Point h : hotels) {
					if (graph[p.idx][h.idx] < min) {
						min = graph[p.idx][h.idx];
						p.nearH = h;
					}
				}
			}
			// System.out.println("가장 가까운 호텔은?");
			// for(Point p : tourSpots) {
			// System.out.println(p + " :" + p.nearH);
			// }

			// 4. 여행 준비
			maxSatis = Integer.MIN_VALUE;
			tempPath = new Stack<>();

			solve(0, airport, 0, 0, new boolean[N + 1]);

			// 5. 결과 출력
			sb.append("#").append(tc).append(" ");
			if (maxSatis <= 0) {
				sb.append(0);
			} else {
				sb.append(maxSatis).append(" ");
				for (int i : maxSatisPath) {
					sb.append(i).append(" ");
				}
			}
			sb.append("\n");
		} // end of tc
		System.out.println(sb);
	}// end of main

	private static void solve(int day, Point start, int satis, int time, boolean[] visited) {
		// 종료조건
		if (day == M) {
			if (satis > maxSatis) {
				maxSatis = satis;
				// 최대 만족도에서의 경로 전달
				maxSatisPath = new ArrayList<>(tempPath);
			}
			return;
		}

		// 일반 탐색
		boolean canGoNext = false;
		for (Point point : tourSpots) {
			// 갈 수 있는 지점들을 찾아서 계속 돌아다닌다.
			if (!visited[point.idx]) {
				// 미방문이라면.. 그 지점을 갈 수 있는지 시간을 계산해볼 필요가 있다.
				int tempTime = time + graph[start.idx][point.idx] + point.playTime;
				if (day == M - 1) {
					tempTime += graph[point.idx][airport.idx];
				} else {
					tempTime += graph[point.idx][point.nearH.idx];
				}

				if (tempTime > 540) {
					continue;
				}

				canGoNext = true;
				visited[point.idx] = true;
				tempPath.push(point.idx);
				solve(day, point, satis + point.satis, time + graph[start.idx][point.idx] + point.playTime, visited);
				tempPath.pop();
				visited[point.idx] = false;
			}
		}

		// 관강지로 못가면 날짜에 따라서 공항/호텔로 이동
		if (!canGoNext) {
			if (day == M - 1) {
				tempPath.push(airport.idx);
				solve(day + 1, airport, satis, 0, visited);
				tempPath.pop();
			} else {
				for (Point hotel : hotels) {
					if (time + graph[start.idx][hotel.idx] <= 540) {
						tempPath.push(hotel.idx);
						solve(day + 1, hotel, satis, 0, visited);
						tempPath.pop();
					}
				}
			}
		}
	}

}
