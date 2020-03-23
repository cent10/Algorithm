// Gold II - 2623 : 음악프로그램

/*
 * <위상 정렬>
 * 15,168 kb
 * 132 ms
 */

import java.io.*;
import java.util.*;

public class Main_B_2623_음악프로그램_박진 {

	static int N, M;	// 가수의 수 N, 보조 PD의 수 M
	static int[] numOfEdge;	// 해당 노드로 들어오는 간선의 수
	static ArrayList<Integer>[] arrList;	// 인접리스트
	static Queue<Integer> queue = new LinkedList<Integer>();
	static boolean[] isChecked;
	static int[] result;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numOfEdge = new int[N+1];
		arrList = new ArrayList[N+1];
		isChecked = new boolean[N+1];
		result = new int[N+1];
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
		}

		int temp = -1;
		for (int i = 1; i <= N; i++) {
			if (numOfEdge[i] == 0) {
				temp = i;
				break;
			}
		}
		if (temp == -1) {
			System.out.print("0");
			return;
		}
		queue.offer(temp);
		isChecked[temp] = true;
		int cnt = 0;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			cnt++;
			result[cnt] = cur;
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
		
		if (cnt == N) {
			for (int i = 1; i <= N; i++) {
				sb.append(result[i]).append("\n");
			}
			System.out.print(sb);
		} else {
			System.out.print("0");
		}
	}

}
