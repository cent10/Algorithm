// D5 - 4534 : 트리 흑백 색칠

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D5_4534_트리흑백색칠_박진_ING {

	static class Node {
		int index;
		boolean color;
		public Node(int index, boolean color) {
			this.index = index;
			this.color = color;
		}
	}
	
	static int T;
	static int N;	// 정점의 개수
	static ArrayList<Integer>[] arrList;
	static boolean[] isVisited;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			arrList = new ArrayList[N+1];
			for (int i = 1; i <= N; i++) {
				arrList[i] = new ArrayList<Integer>();
			}
			
			for (int i = 1; i <= N-1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				arrList[a].add(b);
				arrList[b].add(a);
			}
//			for (int i = 1; i <= N; i++) {
//				System.out.print("arrList[" + i + "] = ");
//				for (int j = 0; j < arrList[i].size(); j++) {
//					System.out.print(arrList[i].get(j) + " / ");
//				}
//			}
			
			isVisited = new boolean[N+1];
			bfs(1, false);
			isVisited = new boolean[N+1];
			bfs(1, true);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	static Queue<Node> queue = new LinkedList<Node>();
	
	private static void bfs(int start, boolean color) {
		queue.offer(new Node(start, color));
		isVisited[start] = true;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for (int i = 0; i < arrList[node.index].size(); i++) {
				if (isVisited[arrList[node.index].get(i)] == false) {
					isVisited[arrList[node.index].get(i)] = true;
					queue.offer(new Node(arrList[node.index].get(i), false));
					if (node.color == false) {
						queue.offer(new Node(arrList[node.index].get(i), true));
					}
				}
			}
		}
	}
}
