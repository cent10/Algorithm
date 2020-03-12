// D4 - 7701 : 염라대왕의 이름 정렬

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 정렬 - 우선순위
 * 중복을 제거
 * 
 * API 정렬 O[NlogN] & TreeSet을 이용하여 중복제거 & 입출력 최적화 & 분할정복 : 500~600ms
 * 
 * 분할정복 아이디어: 글자 개수에 해당하는 배열의 글자길이가 동일한 이름들을 TreeSet으로 사전순 정렬해보자
 * --> 글자 개수가 작은 TreeSet부터 출력하면 됨.
 * 
 * ==> 이름 글자의 개수가 골고루 포진되어 있을 때 시간을 많이 줄일 수 있다.
 */

public class Solution_D4_7701_염라대왕의이름정렬_해답5_API정렬_TreeSet_입출력최적화_분할정복 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());	// 1 ~ 50
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());	// 이름의 개수 (1 ~ 20,000)
			
			TreeSet<String>[] tsrr = new TreeSet[51];
			for (int i = 0; i < tsrr.length; i++) {
				tsrr[i] = new TreeSet<String>();	// 배열 각 칸에 생성해서 넣기
			}
			
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				tsrr[str.length()].add(str);
			}
			
//			for (int i = 0; i < tsrr.length; i++) {
//				System.out.println(tsrr[i]);
//			}
			sb.append("#").append(tc).append("\n");
			for (int i = 1; i < tsrr.length; i++) {
				for (String string : tsrr[i]) {
					sb.append(string).append("\n");
				}
			}
			
		}// end of testCase
		System.out.print(sb);
	}// end of main

}
