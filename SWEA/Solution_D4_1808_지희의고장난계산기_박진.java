// D4 - 1808 : 지희의 고장난 계산기

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_D4_1808_지희의고장난계산기_박진 {

	static class Node {
		int num;
		int cnt;
		public Node(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}
	}
	
	static int[] number = new int[10];
	static int X;
	static int sizeX;	// X가 몇자리숫자인지
	static int result;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
	TC:	for (int tc = 1; tc <= T; tc++) {
			result = Integer.MAX_VALUE;
			arrList = new ArrayList<Node>();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 10; i++) {
				number[i] = Integer.parseInt(st.nextToken());
			}
			X = Integer.parseInt(br.readLine());
			sizeX = what(X);
			
//			makeNum(0, 0);
			makeNum("");
			int size = arrList.size();
			for (int i = 0; i < size; i++) {
				if (arrList.get(i).num == X) {
					if (result > arrList.get(i).cnt + 1)
						result = arrList.get(i).cnt + 1;
					sb.append("#").append(tc).append(" ").append(result).append("\n");
//					System.out.println("#" + tc + " " + result);
					continue TC;
				}
			}
			for (int i = 0; i < size; i++) {
				queue = new LinkedList<Node>();
//				System.out.println("***** i = " + i + " *****");
//				System.out.println("num = " + arrList.get(i).num);
//				System.out.println("cnt = " + arrList.get(i).cnt);
				bfs(arrList.get(i));
//				System.out.println("result = " + result);
			}
			
			if (result == Integer.MAX_VALUE)
				result = -1;
			sb.append("#").append(tc).append(" ").append(result).append("\n");
//			System.out.println("#" + tc + " " + result);
		}// end tc
		System.out.println(sb);
		
	}// end main
	
	static Queue<Node> queue;
	private static void bfs(Node start) {
		queue.offer(start);
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for (int i = 0; i < arrList.size(); i++) {
				if (arrList.get(i).num == 0 || arrList.get(i).num == 1)	// 0이나 1은 곱해봤자 소용없기 때문에 곱하지 않음
					continue;
				if (node.num * arrList.get(i).num > X) {
					continue;
				}
				else if (node.num * arrList.get(i).num < X) {
//					System.out.println("queue.offer => " + node.num * arrList.get(i).num);
					if (X % (node.num * arrList.get(i).num) == 0)
						queue.offer(new Node(node.num * arrList.get(i).num, node.cnt + arrList.get(i).cnt + 1));
				}
				else {
					while(!queue.isEmpty())
						queue.poll();
					int tempAns = node.cnt + arrList.get(i).cnt + 2;
//					System.out.println("tempAns = " + tempAns);
					if (result > tempAns)
						result = tempAns;
					return;
				}
			}
		}
	}

 	// 만들 수 있는 숫자들 저장
	static ArrayList<Node> arrList;
	private static void makeNum(String str) {
		if (str.length() > 0) {
			int tempNum = Integer.parseInt(str);
//			System.out.println("num = " + tempNum);
//			System.out.println("cnt = " + what(tempNum));
			if (tempNum > 0 && tempNum <= X) {
				if (X % tempNum == 0)
					arrList.add(new Node(tempNum, what(tempNum)));
			}
		}

		if (sizeX <= str.length())
			return;
			
		for (int i = 0; i < 10; i++) {
			if (number[i] == 1) {
				String tempStr = str.concat(""+i);
				makeNum(tempStr);
			}
		}
	}
	
	// 몇자리 숫자인지 알아내는 함수
	private static int what(int n) {
		int count = 0;
		int temp = n;
		while(temp != 0) {
			temp = temp / 10;
			count++;
		}
		return count;
	}
}
