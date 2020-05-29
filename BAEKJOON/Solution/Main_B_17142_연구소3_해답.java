import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main_B_17142_연구소3_해답 {
	static int[][] map, copy;
	static int N, M;
	static Virus[] virus; // 바이러스 따로 모아놓자!
	static boolean[] select; // 활성화 시키는 바이러스 골라놓을 배열
	static int virusCnt; // 바이러스가 총 몇개인지
	
	static int[] di = {0,0,1,-1};
	static int[] dj = {1,-1,0,0};
	static int ans;
	//////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt(); // N*N 배열 크기 
		M = sc.nextInt(); // 활성화 시킬 바이러스 수
		
		map = new int[N][N];
		copy = new int[N][N];
		
		virus = new Virus[10]; // 바이러스가 총 몇개인지는 문제에서 안줘서.. 걍 넉넉히
		virusCnt=0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==2) {
					virus[virusCnt++] = new Virus(i, j);
				}
			}
		}
		
		select = new boolean[virusCnt];
		ans = Integer.MAX_VALUE;
		
		comb(0,0);
		
		System.out.println(ans == Integer.MAX_VALUE? -1: ans);
	}
////////////////////////////////////////////////////////////////////////
	static void comb(int idx, int cnt) {
		if(cnt == M) {
			// 자 이제 활성화 바이러스로 시뮬레이션 돌려보자! 원본 유지 필수!
			deepcopy();
			
			Queue<Virus> queue = new LinkedList<>();
			for(int i=0; i<virusCnt; i++) { // 모든 바이러스 중에서
				if(select[i]) { // 활성화 하기로 한 바이러스는 큐에 담자.
					Virus seed = virus[i];
					queue.add(seed);
					copy[seed.i][seed.j] = 9; // 현재 바이러스가 활성화로 퍼진곳을 9로 덮어버리기.
				}
			}
			
			bfs(queue);
			
			return;
		}
		
		if(idx == virusCnt) {
			return;
		}
		
		select[idx] = true;
		comb(idx+1, cnt+1);
		select[idx] = false;
		comb(idx+1, cnt);
	}
	
	static void bfs(Queue<Virus> queue) {
		int time=0;
		
		while(!queue.isEmpty()) {
			if(check()) {
				if(ans>time)
					ans = time;
				break; // 바이러스로 가득 채워졌다함(활성이든 비활성이든~) 그러면 시뮬은 그만해도 됨.
			}
			
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				Virus now = queue.poll(); // 활성화 바이러스를 하나 꺼내서
				
				for(int d=0; d<4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					
					if(ni>=0 && ni<N && nj>=0 && nj<N && (copy[ni][nj]==0 || copy[ni][nj]==2)) {
						copy[ni][nj] = 9;
						queue.add(new Virus(ni, nj));
					}
				}
			}
			time++;
		}
	}
	
	static boolean check() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(copy[i][j]==0) // 아직 덜퍼진 빈칸이 있으면 
					return false;
			}
		}
		return true;
	}
	
	static void deepcopy() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}
	////////////////////////////////////////////////////////////////////////
	static class Virus{
		int i, j;
		Virus(int i, int j){
			this.i = i;
			this.j = j;
		}
	}
}
