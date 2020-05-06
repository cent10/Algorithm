// D4 - 4366 : 정식이의 은행업무

import java.util.*;
import java.io.*;

public class Solution_D4_4366_정식이의은행업무_해답 {

	static int T;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long answer = 0;
			String wrong2 = br.readLine();
			String wrong3 = br.readLine();
			char[] char2 = wrong2.toCharArray();
			char[] char3 = wrong3.toCharArray();
			
			// 2진수의 각 자리수를 하나씩 바꿔가며 10진수로 변경해서 관리
			List<Long> candidates = new ArrayList<Long>();
			for (int i = 0; i < wrong2.length(); i++) {
				// 변경할 자리수를 저장해뒀다가 원복때 사용
				char2[i] = char2[i] == '0' ? '1' : '0';
				// 2진수를 10진수로 바꿔주자..
				candidates.add(toDigit10(char2, 2));
				// 원복
				char2[i] = char2[i] == '0' ? '1' : '0';
			}
			
		L:	for (int i = 0; i < wrong3.length(); i++) {
				// 변경할 자리수를 저장해뒀다가 원복때 사용
				for (char j = '0'; j < '3'; j++) {
					char old = char3[i];
					if(char3[i] != j) {
						char3[i] = j;
					}
					long digit10 = toDigit10(char3, 3);
					if(candidates.contains(digit10)) {
						answer = digit10;
						break L;
					}
					// 변수 원복
					char3[i] = old;
				}
			}
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}// end of tc
		System.out.println(sb);
	}// end of main

	static long toDigit10(char[] chars, int digits) {
		long num = 0;
		for (int i = 0, j = chars.length - 1; i < chars.length; i++, j--) {
			num += (chars[i] - '0') * Math.pow(digits, j);
		}
		return num;
	}
}