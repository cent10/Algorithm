
// Gold II - 2252 : 줄 세우기

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_B_2252_줄세우기_해답 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		List<Integer>[] adj = new List[N];
		for (int i = 0; i < N; i++) {
			adj[i] = new ArrayList<>();
		}
		int[] cnt = new int[N]; // 각 학생별로 나보다 작은 녀석의 수
		for (int i = 0; i < M; i++) {
			int a = sc.nextInt() - 1;
			int b = sc.nextInt() - 1;
			adj[a].add(b);
			cnt[b]++;
		}
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < N; i++) {
			if (cnt[i] == 0)
				queue.add(i);
		}
		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			int n = queue.poll();
			// 출력
			sb.append(n+1).append(" ");
			// n번 친구가 갖고 있는 다음 친구들(나보다 큰놈들)에 대해서 cnt를 1씩 줄여주자.
			for (int i = 0; i < adj[n].size(); i++) {
				// adj[n].get(i)인 친구에 대해서 cnt를 하나 줄여주고
				cnt[adj[n].get(i)] -= 1;
				// 그 줄인 값이 0이 됐는지 확인
				if(cnt[adj[n].get(i)] == 0) {
					queue.add(adj[n].get(i));
				}
			}
		}
	}

}
