// D3 - 2814 : 최장 경로

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D3_2814_최장경로_박진 {

	static int T;
	static int N, M;	// N개의 정점과 M개의 간선
	static boolean[][] adjArr;	// 인접행렬
	static boolean[] isSelected;
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int result;
	static int distance;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			result = 0;	// result 초기화
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			adjArr = new boolean[N+1][N+1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjArr[a][b] = true;
				adjArr[b][a] = true;
			}// end input
			
			// 알고리즘
			for (int i = 1; i <= N; i++) {
				isSelected = new boolean[N+1];
				distance = 0;
				
				isSelected[i] = true;
				dfs(i, 1);
				
				if (result < distance)
					result = distance;
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		
		System.out.println(sb);
	}// end main
	
	private static void dfs(int index, int cnt) {
		if (distance < cnt)
			distance = cnt;
		
		for (int i = 1; i <= N; i++) {
			if (adjArr[index][i] == true && isSelected[i] == false) {
				isSelected[i] = true;
				dfs(i, cnt+1);
				isSelected[i] = false;
			}
		}
		
	}
}
