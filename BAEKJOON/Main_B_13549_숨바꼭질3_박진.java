// Gold V - 13549 : 숨바꼭질 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_13549_숨바꼭질3_박진 {

	static int N, K;						// 수빈이가 있는 위치 N과 동생이 있는 위치 K
	static int[] memo = new int[100001];	// 걸리는 시간 기록
	static int result = Integer.MAX_VALUE;	// 출력값
	
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Arrays.fill(memo, Integer.MAX_VALUE);
		
		bfs(N);
		
		System.out.println(result);
	}
	
	private static void bfs(int n) {
		queue.offer(n);
		memo[n] = 0;
		
		if (n == K) {
			result = 0;
			return;
		}
		
		while ( !queue.isEmpty() ) {
			int current = queue.peek();
			queue.poll();
				
			for (int i = 0; i < 3; i++) {
				int nexti = current;

				switch (i) {
				case 0:
					nexti = current + 1;
					break;
				case 1:
					nexti = current - 1;
					break;
				case 2:
					nexti = current * 2;
					break;
				default:
					break;
				}
				
				if (nexti < 0 || nexti >= 100001)
					continue;
				
				if (i == 0 || i == 1) {
					if (memo[nexti] <= memo[current] + 1)
						continue;
					memo[nexti] = memo[current] + 1;
				}
				else {	// i == 2 일때 (2*X의 위치로 순간이동을 하는 경우)
					if (memo[nexti] <= memo[current])
						continue;
					memo[nexti] = memo[current];
				}
				
				queue.offer(nexti);
				
				if (nexti == K) {
					if (result > memo[nexti])
						result = memo[nexti];
				}
			}
		}
	}
}
