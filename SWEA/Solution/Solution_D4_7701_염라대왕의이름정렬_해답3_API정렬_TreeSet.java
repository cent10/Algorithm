// D4 - 7701 : 염라대왕의 이름 정렬

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 정렬 - 우선순위
 * 중복을 제거
 * 
 * API 정렬 O[NlogN] & TreeSet을 이용하여 중복제거 : 2900ms
 */

public class Solution_D4_7701_염라대왕의이름정렬_해답3_API정렬_TreeSet {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();	// 1 ~ 50
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();	// 이름의 개수 (1 ~ 20,000)
			
			TreeSet<String> name = new TreeSet<String>(new Comparator<String>() {
				@Override
				public int compare(String pre, String next) {
					if (pre.length() != next.length()) {	// 글자의 길이 짧은순서
						return pre.length() - next.length();
					} else {	// 길이가 같으면, 사전순 정렬
						return pre.compareTo(next);	// 오름차순
					}
				}
			});
			for (int i = 0; i < N; i++) {
				name.add(sc.next());	// 중복 제거
			}
			
//			System.out.println(name);
			System.out.println("#" + tc);
			for (String string : name) {
				System.out.println(string);
			}
		}// end of testCase
	}// end of main

}
