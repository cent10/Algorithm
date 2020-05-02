// D4 - 4366 : 정식이의 은행업무

import java.util.*;
import java.io.*;

public class Solution_D4_4366_정식이의은행업무_박진 {

	static int T;
	static String input2;
	static String input3;
	static char[] two;
	static char[] three;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		T = sc.nextInt();
	TC:	for (int tc = 1; tc <= T; tc++) {
			input2 = sc.next();
			input3 = sc.next();
			two = new char[input2.length()];
			three = new char[input3.length()];
			
			for (int i = 0; i < input2.length(); i++) { // 2진수
				two = input2.toCharArray();
				if (two[i] == '0') {
					two[i] = '1';
				} else {
					two[i] = '0';
				}
				for (int j = 0; j < input3.length(); j++) { // 3진수
					three = input3.toCharArray();
					for (int k = 0; k < 2; k++) {
						three[j] += 1;
						if (three[j] == '3') {
							three[j] = '0';
						}
						StringBuilder sb2 = new StringBuilder();
						for (int l = 0; l < input2.length(); l++) {
							sb2.append(two[l]);
						}
						StringBuilder sb3 = new StringBuilder();
						for (int l = 0; l < input3.length(); l++) {
							sb3.append(three[l]);
						}
						if (Integer.parseInt(sb2.toString(), 2) == Integer.parseInt(sb3.toString(), 3)) {
							sb.append("#").append(tc).append(" ").append(Integer.parseInt(sb2.toString(), 2)).append("\n");
							continue TC;
						}
					}
				}
			}
		}// end of tc
		System.out.println(sb);
	}// end of main

}
