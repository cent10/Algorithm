// D4 - 7701 : 염라대왕의 이름 정렬

import java.util.*;
import java.io.*;

public class Solution_D4_7701_염라대왕의이름정렬_박진 {

	static class Name implements Comparable<Name>{
		String name;
		int length;
		public Name(String name, int length) {
			this.name = name;
			this.length = length;
		}
		@Override
		public int compareTo(Name o) {
			if (this.length == o.length)
				return this.name.compareTo(o.name);
			return this.length - o.length;
		}
	}
	
	static int N;	// 이승 명부의 이름 개수 N(1 ≤ N ≤ 20,000)
	static Set<String> set = new HashSet<>();
	static ArrayList<Name> arrList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			for (int n = 0; n < N; n++) {
				String str = br.readLine();
				if(!set.contains(str)) {
					set.add(str);
					arrList.add(new Name(str, str.length()));
				}
			}
			sb.append("#").append(tc).append(" ").append("\n");
			Collections.sort(arrList);
			for (int i = 0; i < arrList.size(); i++) {
				sb.append(arrList.get(i).name).append("\n");
//				System.out.println(arrList.get(i).name + " / " + arrList.get(i).length);
			}
			set.clear();
			arrList.clear();
		}// end tc
		System.out.println(sb);
	}// end main
}
