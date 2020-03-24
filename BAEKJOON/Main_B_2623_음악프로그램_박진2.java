// Gold II - 2623 : 음악프로그램

/*
 * <위상 정렬>
 *  13,656 kb
 *  96 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_2623_음악프로그램_박진2 {

	static int N, M;	// 가수의 수 N, 보조 PD의 수 M
	static int[] numOfEdge;	// 해당 노드로 들어오는 간선의 수
	static ArrayList<Integer>[] arrList;	// 인접리스트
	static Queue<Integer> queue = new LinkedList<Integer>();
	static int check = 0;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numOfEdge = new int[N+1];
		arrList = new ArrayList[N+1];
		numOfEdge = new int[N+1];
		
		for (int i = 1; i <= N; i++) {	// 인접리스트 초기화
			arrList[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());	// 보조 PD가 담당한 가수의 수
			int[] tempArr = new int[S];
			for (int j = 0; j < S; j++) {	// 입력받을 가수들의 순서 임시저장
				tempArr[j] = Integer.parseInt(st.nextToken());
			}
			for (int j = 0; j < S - 1; j++) {	// 인접리스트 저장
				arrList[tempArr[j]].add(tempArr[j+1]);
			}
			for (int j = 1; j < S; j++) {	// 간선정보 저장
				numOfEdge[tempArr[j]]++;
			}
		}// end input
		
		for (int i = 1; i <= N; i++) {
			if(numOfEdge[i] == 0)
				queue.offer(i);
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			check++;
			sb.append(cur).append("\n");
			
			int size = arrList[cur].size();
			for (int i = 0; i < size; i++) {
				numOfEdge[arrList[cur].get(i)]--;
				if(numOfEdge[arrList[cur].get(i)] == 0)
					queue.offer(arrList[cur].get(i));
			}
		}
		
		System.out.println(check == N ? sb : 0);
	}// end main
}
