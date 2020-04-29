// 5658. [모의 SW 역량테스트] 보물상자 비밀번호

import java.util.*;
import java.io.*;

public class Solution_5658_보물상자비밀번호_박진 {

	static int T;
	static int N, K;	// 숫자의 개수 N과 크기 순서 K
	static ArrayList<Character> inputNum;
	static int result;
	
	static ArrayList<Integer> arrList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			String s = br.readLine();
			ArrayList<Character> inputNum = new ArrayList<Character>();
			ArrayList<Integer> arrList = new ArrayList<Integer>();
			for (int i = 0; i < s.length(); i++) {
				inputNum.add(s.charAt(i));
			}
			for (int i = 0; i < N / 4; i++) { // 각 변에 위치한 숫자의 개수만큼 회전
				for (int j = 0; j < 4; j++) { // 가능한 숫자 만들기
					StringBuilder sb2 = new StringBuilder();
					for (int k = 0; k < N / 4; k++) {
						sb2.append(inputNum.get((j * (N / 4)) + k));
					}
					String createdNum = sb2.toString();
					// System.out.println(createdNum);
					int temp = decimal(createdNum);
					// System.out.println(temp);
					if (!arrList.contains(temp)) { // 중복되는 수가 아니면 큐에 넣어줌.
						arrList.add(temp);
					}
				}
				// 숫자 회전
				char temp = inputNum.get(0);
				inputNum.remove(0);
				inputNum.add(temp);
			}
				
			
//			System.out.println("개수  = " + queue.size());
			
			Collections.sort(arrList);
			result = arrList.get(arrList.size() - K);
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	// 16진수를 10진수로 변환
	private static int decimal(String createdNum) {
		int length = createdNum.length();
		int n = 0;
		int result = 0;
		for (int i = length - 1; i >= 0; i--) {
			if (createdNum.charAt(i) >= 65) {	// A ~ F 일경우
				result += (createdNum.charAt(i) - 55) * (int)Math.pow(16, n);
			} else {	// 0 ~ 9 일 경우
				result += (createdNum.charAt(i) - 48) * (int)Math.pow(16, n);
			}
			n++;
		}
		return result;
	}

}
