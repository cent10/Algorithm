// Gold II - 2252 : 줄 세우기

/*
 * 163,600 kb
 * 1,008 ms
 */

import java.util.*;
import java.io.*;

public class Main_B_2252_줄세우기_박진 {

	static int N, M;
	static ArrayList<Integer>[] arrList;
	static int[] numOfEdge;
	
	static Queue<Integer> queue = new LinkedList<Integer>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		N = sc.nextInt();
		M = sc.nextInt();
		arrList = new ArrayList[N+1];
		numOfEdge = new int[N+1];
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
			if (numOfEdge[i] == 0) {
				queue.offer(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
			
			for (int i = 0; i < arrList[cur].size(); i++) {
				numOfEdge[arrList[cur].get(i)]--;
				if (numOfEdge[arrList[cur].get(i)] == 0) {
					queue.offer(arrList[cur].get(i));
				}
			}
			
		}
		
		System.out.print(sb);
	}

}
