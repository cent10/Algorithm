// Gold II - 2252 : 줄 세우기

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_B_2252_줄세우기_해답_20200524 {

	static int N, M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		ArrayList<Integer>[] list = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		// 진입차수 관리
		int[] indegree = new int[N+1];
		for (int i = 0; i < M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			list[x].add(y);
			indegree[y]++;
		}
		// 진입차수가 0인 것들 큐에 삽입
		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(indegree[i] == 0) {
				q.offer(i);
			}
		}
		
		if(q.size() == 0) {	// 이 문제에서는 사이클이 존재하는 경우는 없기 때문에, 이 문제에서는 안써줘도 되기는 함.
			System.out.println("사이클 존재");
			return;
		}
		
		// 큐에서 하나 빼서 연결된 애들 진입차수 1씩 감소, 다시 0인 것들은 큐에 삽입
		// 큐에서 하나씩 뺄 때, 그 내용을 리스트로 정리
		ArrayList<Integer> result = new ArrayList<>();
		Integer cur;
		while(!q.isEmpty()) {
			cur = q.poll();
			result.add(cur);
			for(int idx : list[cur]) {
				indegree[idx]--;
				if(indegree[idx] == 0) {
					q.offer(idx);
				}
			}
		}
		
		if(result.size() != N) {	// 이 문제에서는 사이클이 존재하는 경우는 없기 때문에, 이 문제에서는 안써줘도 되기는 함.
			System.out.println("사이클 존재");
			return;
		}
		
		for (Integer idx : result) {
			System.out.print(idx + " ");
		}
		System.out.println();
	}

}
