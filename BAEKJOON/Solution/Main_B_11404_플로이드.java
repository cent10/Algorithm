import java.io.*;
import java.util.*;

public class 백준_Main_11404_플로이드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 도시의 개수 n(1 ≤ n ≤ 100)
		int M = Integer.parseInt(br.readLine()); // 버스의 개수 m(1 ≤ m ≤ 100,000)
		final int INF = 987654321;
		int[][] D = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(D[i], INF);
			D[i][i] = 0; // 자기자신 정점
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()); // 비용
			D[a][b] = Math.min(c, D[a][b]);
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					D[i][j] = Math.min(D[i][j], D[i][k] + D[k][j]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (D[i][j] == INF) {
					D[i][j] = 0;
				}
				sb.append(D[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	} // end of main
} // end of class