// D4 - 1251 : [S/W 문제해결 응용] 4일차 - 하나로

import java.util.*;
import java.io.*;

public class Solution_D4_1251_하나로_해답 {
	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static long[][] islands;
	static double E;
	static long[][] graph;	// 정점들간의 거리 그래프
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			
			/////////////////////////////////////////////////////////////
			N = Integer.parseInt(br.readLine());
			StringTokenizer xLine = new StringTokenizer(br.readLine(), " ");
			StringTokenizer yLine = new StringTokenizer(br.readLine(), " ");
			
			islands = new long[N][2];
			for (int n = 0; n < N; n++) {
				islands[n] = new long[] {Integer.parseInt(xLine.nextToken()), Integer.parseInt(yLine.nextToken())};
			}
			E = Double.parseDouble(br.readLine());
			// 입력된 자료를 기반으로 섬 간의 가중치 그래프를 구해봅시다.!!
			graph = new long[N][N];
			long[] from, to;
			for (int r = 0; r < N; r++) {
				from = islands[r];
				for (int c = r + 1; c < N; c++) {
					to = islands[c];
					graph[c][r] = graph[r][c] = (from[0] - to[0]) * (from[0] - to[0]) + (from[1] - to[1]) * (from[1] - to[1]);
				}
			}
			
			double cost = prim(0) * E;
			sb.append(Math.round(cost));
			/////////////////////////////////////////////////////////////
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static long prim(int start) {
		// pq에 MST에 들어가지 않은 녀석들이 들어갈거임.
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		// 모든 정점들을 다 관리
		Edge[] dynamicGraph = new Edge[N];
		
		for (int n = 0; n < dynamicGraph.length; n++) {
			dynamicGraph[n] = new Edge(n, Long.MAX_VALUE);
			if(n==start) {
				dynamicGraph[n].cost = 0;
			}
			pq.add(dynamicGraph[n]);
		}
		
		long cost = 0;
		
		while(!pq.isEmpty()) {
			Edge front = pq.poll();	// MST에 포함되는 녀석
			cost += front.cost;
			
			// 자식 탐색
			for (int i = 0; i < dynamicGraph.length; i++) {
				Edge child = dynamicGraph[i];
				// pq: 비 MST
				if (pq.contains(child)) {
					long tempCost = graph[front.idx][child.idx];
					if(tempCost < child.cost) {
						child.cost = tempCost;
						pq.remove(child);
						pq.add(child);
					}
				}
			}
		}
		return cost;
	}
	
	static class Edge implements Comparable<Edge> {
		int idx;
		long cost;
		
		public Edge(int idx, long cost) {
			super();
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "Edge [idx=" + idx + ", cost=" + cost + "]";
		}
		
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
		
	}
}
