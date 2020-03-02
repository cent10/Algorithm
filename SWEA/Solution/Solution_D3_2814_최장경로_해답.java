import java.util.Scanner;

public class Solution_D3_2814_최장경로_해답 {
	static int N;
	static int M;
	static int[][] adj;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // 정점의 갯수 1 ≤ N ≤ 10
			M = sc.nextInt(); // 간선의 갯수 <= 20
			adj = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				adj[a][b] = 1;
				adj[b][a] = 1;
			}
			ans = 0;
			for (int i = 1; i <= N; i++) {
				visited[i] = true;
				dfs(i, 1);
				visited[i] = false;
			}
			System.out.println("#" + tc + " " + ans);
		}
	}

	static boolean[] visited;
	static int ans = 0;

	static void dfs(int v, int cnt) {
		// 더이상 방문할 노드가 없음 : 기저파트
		// cnt이 가장 큰 값
		ans = Math.max(ans, cnt);
		// 모든 노드 중에서 나와 간선이 존재하고, 아직 방문하지 않은 노드라면 방문
		for (int i = 1; i <= N; i++) {
			if (adj[v][i] == 1 && !visited[i]) {
				visited[i] = true;
				dfs(i, cnt + 1);
				visited[i] = false;
			}
		}
	}
}