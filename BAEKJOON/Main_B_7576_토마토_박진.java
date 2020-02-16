// Silver I - 7576 : 토마토

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main {

  static int M;	// 상자의 가로 칸의 수
	static int N;	// 상자의 세로 칸의 수
	static int[][] warehouse; // 창고
//	static Queue<Location> queue = new LinkedList<Location>();
	static Queue<int[]> queue = new LinkedList<int[]>();
	static int count = 0;
	
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	
//	static class Location {
//		int i;
//		int j;
//		
//		public Location(int i, int j) {
//			super();
//			this.i = i;
//			this.j = j;
//		}
//	}
	
//	static int[] location = new int[2];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		warehouse = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				warehouse[i][j] = Integer.parseInt(st.nextToken());
				if (warehouse[i][j] == 1) {
//					queue.offer(new Location(i, j));
					int[] location = {i, j};
					queue.offer(location);
				}
			}
		}
//		for(int[] a : warehouse)
//			System.out.println(Arrays.toString(a));
		
		while( !queue.isEmpty() ) {
			int size = queue.size();
			
			while (size-- > 0) {
//				int tempX = queue.peek()[0];
//				int tempY = queue.peek()[1];
				int[] temp = queue.poll();
				
				int nexti;
				int nextj;
				
//				warehouse[queue.peek().i][queue.peek().j] = 1;
				
				for (int k = 0; k < 4; k++) {
//					nexti = queue.peek().i + di[k];
//					nextj = queue.peek().j + dj[k];
					nexti = temp[0] + di[k];
					nextj = temp[1] + dj[k];
					
					if (nexti < 0 || nextj < 0 || nexti >= N || nextj >= M)
						continue;
					
					if (warehouse[nexti][nextj] == 0) {
						warehouse[nexti][nextj] = 1;
//						queue.offer(new Location(nexti, nextj));
						int[] location = {nexti, nextj};
						queue.offer(location);
					}
				}
			}
			count++;
		}
		
		count--;
		
	L:	for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (warehouse[i][j] == 0) {
					count = -1;
					break L;
				}
			}
		}
		
		System.out.println(count);
	}
}
