// Gold IV - 13913 : 숨바꼭질 4

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_13913_숨바꼭질4_박진 {

	static int N, K;						// 수빈이가 있는 위치 N과 동생이 있는 위치 K
	static int result = Integer.MAX_VALUE;	// 출력값
	static int count = 0;
	
	static Queue<Integer> queue = new LinkedList<Integer>();	// bfs를 위한 큐
	static boolean[] isVisited = new boolean[100001];			// 방문 여부 확인 flag
	static int[] memo = new int[100001];						// 전에 어디에서 왔는지 기록할 배열
	static ArrayList<Integer> order = new ArrayList<Integer>();	// 어떻게 이동해야 하는지 저장할 큐
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bfs(N);
		
//		for (int i = 0; i < result; i++) {
//			System.out.print(order.get(i) + " ");
//		}
//		System.out.println();
		
		sb.append(N).append(" ");
		for (int i = result-1; i >= 0; i--) {
			sb.append(order.get(i)).append(" ");
		}
		
		System.out.println(result);
		System.out.println(sb);
	}
	
	private static void bfs(int n) {
		queue.offer(n);
		isVisited[n] = true;
		
		if (n == K) {	// N과 K가 같을 때는 0 출력.
			result = 0;
			return;
		}
		
		while ( !queue.isEmpty() ) {
			int size = queue.size();
			
			while ( size-- > 0 ) {
				int current = queue.peek();
				queue.poll();
				
				if (current == K) {		// 기저 조건
					result = count;
					
					order.add(current);
					for (int i = 0; i < result-1; i++) {
						order.add(memo[current]);
						current = memo[current];
					}
					return;
				}
				
				for (int i = 0; i < 3; i++) {
					int nexti = current;
					
					switch (i) {
					case 0:
						nexti = current - 1;
						break;
					case 1:
						nexti = current + 1;
						break;
					case 2:
						nexti = current * 2;
						break;
					default:
						break;
					}
					
					if (nexti < 0 || nexti >= 100001)
						continue;
					
					if (isVisited[nexti] == true)
						continue;
					
					isVisited[nexti] = true;
					
					memo[nexti] = current;
					
					queue.offer(nexti);
				}
			}
			count++;
		}
	}
}