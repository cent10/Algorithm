// Gold V - 16234 : 인구 이동

import java.io.*;
import java.util.*;

public class Main_B_16234_인구이동_해답 {
	
	static int N;
	static int L;
	static int R;
	static int[][] m;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// N*N 땅의 크기
		L = Integer.parseInt(st.nextToken());	// L ~ R
		R = Integer.parseInt(st.nextToken());	// L ~ R
		m = new int[N][N];	// 인구 저장
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				m[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int cnt = 0;	// 인구이동 횟수
		while(true) {	// 인구이동
			boolean change = false;	// 인구이동 여부 체크
			// 모든 칸 순회
			// BFS 탐색 인접한 국가와 L ~ R 범위 내의 인구차
			visited = new boolean[N][N];	// 방문 체크
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visited[i][j] && search(i, j)) {
						change = true;
					}
				}
			}
			
			
			if (!change) {
				break;
			}
			cnt++;
		}
		System.out.println(cnt);
	}

	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	private static boolean search(int r, int c) {
		ArrayList<LOC> al = new ArrayList<LOC>();
		Queue<LOC> q = new LinkedList<LOC>();
		LOC l = new LOC(r,c);
		al.add(l);
		q.offer(l);
		visited[r][c] = true;
		while(!q.isEmpty()) {
			l = q.poll();
			r = l.r;
			c = l.c;
			for (int d = 0; d < 4; d++) {
				int nr = r +dr[d];
				int nc = c +dc[d];
				if (0 <= nr && nr < N && 0 <= nc && nc < N
						&& !visited[nr][nc]
						&& diff(m[nr][nc], m[r][c])) {
					LOC nl = new LOC(nr, nc);
					al.add(nl);
					q.offer(nl);
					visited[nr][nc] = true;
				}
			}
		}
		
		// 인접국가 개수가 2개 이상이면, 인구이동 발생
		if (al.size() >= 2) {
			int total = 0;
			for (LOC loc : al) {
				total += m[loc.r][loc.c];
			}
			int avg = total/al.size();
			for (LOC loc : al) {
				m[loc.r][loc.c] = avg;;
			}
			return true;
		}
		
		return false;	// 인구이동 없으면
	}
	
	private static boolean diff(int i, int j) {	// L ~ R 범위 내에 있는지 확인
		int sub = i >= j ? i-j : j-i;
		return L <= sub && sub <=R;
	}

	public static class LOC {
		int r, c;
		public LOC(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "LOC [r=" + r + ", c=" + c + "]";
		}
	}
}
