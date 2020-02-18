// Silver I - 1697 : 숨바꼭질

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_1697_숨바꼭질_박진 {

	static int N, K; // 수빈이가 있는 위치 N과 동생이 있는 위치 K
	static int[] memo = new int[100001];
	static int result = 0;
	
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Arrays.fill(memo, Integer.MAX_VALUE);
		
		bfs(N, 0);
		
		System.out.println(result);
	}

	private static void bfs(int n, int count) {
		queue.offer(n);
		memo[n] = 0;
		
		while ( !queue.isEmpty() ) {
			int size = queue.size();
			
			while ( size-- > 0) {
				int index = queue.peek();
				queue.poll();
				
				if (index == K) {
					result = count;
					return;
				}
				
				for (int i = 0; i < 3; i++) {
					int nexti = 0;
					
					switch (i) {
					case 0:
						nexti = index + 1;
						break;
					case 1:
						nexti = index - 1;
						break;
					case 2:
						nexti = index * 2;
						break;
					default:
						break;
					}
					
					if (nexti < 0 || nexti >= 100001)
						continue;
					
					if (nexti == K) {
						result = count+1;
						return;
					}
					
					if (memo[nexti] > count+1) {
						memo[nexti] = count+1;
					}
					else
						continue;
					
					queue.offer(nexti);
				}
			}
			count++;
		}
	}
}