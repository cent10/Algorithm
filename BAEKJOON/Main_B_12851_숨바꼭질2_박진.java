// Gold V - 12851 : 숨바꼭질2

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_12851_숨바꼭질2_박진 {

	static int N, K; // 수빈이가 있는 위치 N과 동생이 있는 위치 K
	static int result = Integer.MAX_VALUE;
	static int num;
	static boolean flag;
	static boolean[] isVisited = new boolean[100001];
	
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		bfs(N, 0);
		
		System.out.println(result);
		System.out.println(num);
	}

	private static void bfs(int n, int count) {
		queue.offer(n);
		
		while ( !queue.isEmpty() ) {
			int size = queue.size();
			
			while ( size-- > 0) {
				int index = queue.peek();
				queue.poll();
				isVisited[index] = true;
				
				if (index == K && flag == false) {
					result = count;
					flag = true;
				}
				
				if (index == K && result == count) {
					num++;
				}
				
				if (result < count)
					return;
				
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
					
					if (isVisited[nexti] == true)
						continue;
					
					queue.offer(nexti);
				}
			}
			count++;
		}
	}
}