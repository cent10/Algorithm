// D4 - 1251 : [S/W 문제해결 응용] 4일차 - 하나로

import java.io.*;
import java.util.*;

public class Solution_D4_1251_하나로_박진 {

	static class Edge implements Comparable<Edge> {
		int from, to;
		long value;
		public Edge(int from, int to, long value) {
			this.from = from;
			this.to = to;
			this.value = value;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.value, o.value);
		}
	}
	
	static int T;
	static int N;
	static long[] X;
	static long[] Y;
	static double E;
	static ArrayList<Edge> edges;
	static int[] parents;
	static long result;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			edges = new ArrayList<Edge>();
			N = Integer.parseInt(br.readLine());
			X = new long[N];
			Y = new long[N];
			parents = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				X[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				Y[i] = Integer.parseInt(st.nextToken());
			}
			E = Double.parseDouble(br.readLine());
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i < j)
						edges.add(new Edge (i, j, L2(X[i], Y[i], X[j], Y[j])));
				}
			}
			//end input
			
			Collections.sort(edges);
//			System.out.println(edges);
			
			makeSet();
			int cnt = 0;
			int size = edges.size();
			for (int i = 0; i < size; i++) {
				int aRoot = findSet(edges.get(i).from);
				int bRoot = findSet(edges.get(i).to);
				
				if (aRoot == bRoot)
					continue;
				
				union(edges.get(i).from, edges.get(i).to);
				result += edges.get(i).value;
				
				if (cnt == N - 1)
					break;
			}
			
			
			sb.append("#").append(tc).append(" ").append(Math.round(result*E)).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main
	
	public static long L2(long x1, long y1, long x2, long y2) {
		return (x1-x2)*(x1-x2) + (y1-y2)*(y1-y2);
	}

	public static void makeSet() {
		Arrays.fill(parents, -1);
	}
	
	public static int findSet(int a) {
		if (parents[a] == -1) {
			return a;
		} else {
			return parents[a] = findSet(parents[a]);
		}
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot != bRoot) {
			parents[bRoot] = aRoot;
			return true;
		}
		return false;
	}
	
}
