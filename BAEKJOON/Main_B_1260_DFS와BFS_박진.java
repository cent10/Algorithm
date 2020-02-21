// Silver I - 1260 : DFS와 BFS

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1260_DFS와BFS_박진 {

	static int N;	// 정점의 개수 N(1 ≤ N ≤ 1,000)
	static int M;	// 간선의 개수 M(1 ≤ M ≤ 10,000)
	static int V;	// 탐색을 시작할 정점의 번호 V
	
	static int[][] adjArr;	// 인접행렬
	
	static StringBuilder sbForDFS = new StringBuilder();
	static boolean[] isVisitedForDFS;
	static boolean[] isVisitedForBFS;
	
	static StringBuilder sbForBFS = new StringBuilder();
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		adjArr = new int[N+1][N+1];
		isVisitedForDFS = new boolean[N+1];
		isVisitedForBFS = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjArr[a][b] = 1;
			adjArr[b][a] = 1;
		}
		
		dfs(V, 0);
		System.out.println(sbForDFS);
		
		bfs(V);
		System.out.println(sbForBFS);
	}
	
	static private void dfs (int node, int cnt) {
		if (cnt == N) {
			sbForDFS.append("\n");
			return;
		}
		
		sbForDFS.append(node).append(" ");
		isVisitedForDFS[node] = true;
		
		for (int i = 1; i <= N; i++) {
			if (adjArr[node][i] == 0 || isVisitedForDFS[i] == true)
				continue;
			
			dfs(i, cnt+1);
		}
	}
	
	static private void bfs (int node) {
		queue.offer(node);
		isVisitedForBFS[node] = true;
		
		while ( !queue.isEmpty() ) {
			int temp = queue.peek();
			sbForBFS.append(queue.poll()).append(" ");
			
			for (int i = 1; i <= N; i++) {
				if (adjArr[temp][i] > 0 && isVisitedForBFS[i] == false) {
					isVisitedForBFS[i] = true;
					queue.offer(i);
				}
			}
		}
	}
}
