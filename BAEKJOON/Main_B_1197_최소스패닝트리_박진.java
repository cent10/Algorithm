// Gold IV - 1197 : 최소 스패닝 트리

import java.util.*;
import java.io.*;

public class Main_B_1197_최소스패닝트리_박진 {

	static int V, E;
	static int[][] edge;
	static int[] parents;
	static long result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edge = new int[E][3];
		parents = new int[V+1];
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			edge[i][0] = Integer.parseInt(st.nextToken());
			edge[i][1] = Integer.parseInt(st.nextToken());
			edge[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(edge, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		makeSet();
		int cnt = 0;
		for (int i = 0; i < E; i++) {
			int aRoot = findSet(edge[i][0]);
			int bRoot = findSet(edge[i][1]);
			
			if (aRoot == bRoot)
				continue;
			
			union(edge[i][0], edge[i][1]);
			cnt++;
			result += edge[i][2];
			
			if (cnt == V - 1)
				break;
		}
		
		System.out.println(result);
	}// end main

	public static void makeSet() {
		Arrays.fill(parents, -1);
	}
	
	public static int findSet(int a) {
		if (parents[a] == -1)
			return a;
		return parents[a] = findSet(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if (aRoot == bRoot)
			return false;
		parents[bRoot] = aRoot;
		return true;
	}
}
