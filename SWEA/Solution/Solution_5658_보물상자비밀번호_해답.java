// 5658. [모의 SW 역량테스트] 보물상자 비밀번호

import java.util.*;
import java.io.*;

public class Solution_5658_보물상자비밀번호_해답 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();	// 4의 배수, 8 <= N <= 28
			int K = sc.nextInt();	// 크기 순서 K
			String s = sc.next();
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < N / 4; i++) {
				int start = 0;
				int end = N / 4;
				for (int j = 0; j < 4; j++) {
					String hex = s.substring(start, end);
					int num = Integer.parseInt(hex, 16);
					if (!list.contains(num))
						list.add(num);
					start = end;
					end += N / 4;
				}
				char c = s.charAt(N - 1);
				s = c + s.substring(0, N - 1);
			}
//			Collections.sort(list);	// 오름차순
//			System.out.println("#" + tc + " " + list.get(list.size() - K));
			
			Collections.sort(list, Collections.reverseOrder());	// 내림차순
			System.out.println("#" + tc + " " + list.get(K - 1));
		}
		sc.close();
	}
}
