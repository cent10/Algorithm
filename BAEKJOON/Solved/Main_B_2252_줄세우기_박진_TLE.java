// Gold II - 2252 : 줄 세우기

import java.util.*;
import java.io.*;

public class Main_B_2252_줄세우기_박진_TLE {

	static int N, M;
	static ArrayList<Integer>[] arrList;
	static int[] numOfEdge;
	
	static Queue<Integer> queue = new LinkedList<Integer>();
	static boolean[] isChecked;
	static boolean isPossible;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		arrList = new ArrayList[N+1];
		numOfEdge = new int[N+1];
		isChecked = new boolean[N+1];
		for (int i = 1; i <= N; i++) {
			arrList[i] = new ArrayList<Integer>();
		}
		int A, B;
		for (int i = 0; i < M; i++) {
			A = sc.nextInt();
			B = sc.nextInt();
			arrList[A].add(B);
			numOfEdge[B]++;
		}
		
		for (int i = 1; i <= N; i++) {
			if (numOfEdge[i] == 0 && isChecked[i] == false) {
				queue.offer(i);
				isChecked[i] = true;
				isPossible = true;
			}
		}
		
		if (!isPossible) {
			for (int i = 1; i <= N; i++) {
				sb.append(i).append(" ");
			}
			System.out.println(sb);
			return;
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
			
			for (int i = 0; i < arrList[cur].size(); i++) {
				numOfEdge[arrList[cur].get(i)]--;
			}
			
			for (int i = 1; i <= N; i++) {
				if (numOfEdge[i] == 0 && isChecked[i] == false) {
					queue.offer(i);
					isChecked[i] = true;
				}
			}
		}
		
		System.out.print(sb);
	}

}
