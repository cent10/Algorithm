import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class Main_B_17142_연구소3_해답2{
	static int[][] map, copy;
	static int N, M;
	static Point[] virus;
	static int[] select1;
	static boolean[] select2;
	static int virusCnt;
	static LinkedList<Point> queue;
	static int[] di = { 1, -1, 0, 0 };
	static int[] dj = { 0, 0, 1, -1 };
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][N];

		virusCnt = 0;
		virus = new Point[10];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 2) {
					virus[virusCnt++] = new Point(i, j);
				}
			}
		}

		ans = Integer.MAX_VALUE;
		copy = new int[N][N];
//		select1 = new int[M];
//		comb1(0, 0);
		
		select2 = new boolean[virusCnt];
		comb2(0,0);

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}

	static void comb1(int idx, int cnt) {
		if (cnt == M) {
			deepcopy();
			queue = new LinkedList<>();
			for (int i = 0; i < cnt; i++) { // 초기에 활성화시킨 바이러스들은 표시하고 큐에 담자.
				Point tmp = virus[select1[i]];
				queue.add(tmp);
				copy[tmp.i][tmp.j] = 9; 
			}

			bfs();

			return;
		}

		for (int i = idx; i < virusCnt; i++) {
			select1[cnt]=i;
			comb1(i + 1, cnt + 1);
		}
	}
	
	static void comb2(int idx, int cnt) {
		if (cnt == M) {

			deepcopy();
			queue = new LinkedList<>();
			for(int i=0; i<virusCnt; i++) {
				if(select2[i]) {
					Point tmp = virus[i];
					copy[tmp.i][tmp.j]=9;
					queue.add(tmp);
				}
			}

			bfs();

			return;
		}

		if (idx == virusCnt)
			return;

		select2[idx]=true;
		comb2(idx + 1, cnt + 1);
		select2[idx]=false;
		comb2(idx + 1, cnt);
	}

	static void bfs() {
		int time = 0;
		while (!queue.isEmpty()) {
			if (check()) { // 바이러스가 다 퍼졌으면 시간 봐서 갱신
				if (ans > time)
					ans = time;
				break;
			}

			int size = queue.size();

			for (int s = 0; s < size; s++) {
				Point now = queue.poll();
				for (int d = 0; d < 4; d++) {
					int ni = now.i + di[d];
					int nj = now.j + dj[d];

					// 비활성 바이러스도 빈칸이나 마찬가지므로 그냥 퍼뜨림. 활성 바이러스가 퍼진 칸은 0,1,2가 아닌 숫자로 채워버리기.
					if (ni >= 0 && ni < N && nj >= 0 && nj < N && (copy[ni][nj] == 0||copy[ni][nj]==2)) {
						copy[ni][nj]=9;
						queue.add(new Point(ni, nj));
					}
				}
			}
			time++;
		}

	}

	static void deepcopy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = map[i][j];
			}
		}
	}

	static boolean check() { // 빈칸 0이 남았으면 아직 다 안퍼진 거임.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copy[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	static class Point {
		int i, j;

		Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}