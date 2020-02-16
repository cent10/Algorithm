// Bronze II - 8958 : OX퀴즈

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_B_8958_OX퀴즈_박진 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());

		for (int i = 0; i < TC; i++) {
			String s = br.readLine();
			int sLen = s.length();
			char[] ox = new char[sLen];		// OX를 저장할 배열
			int[] score = new int[sLen];	// 점수를 저장할 배열
			ox = s.toCharArray();
			int totalScore = 0;
			
			for (int j = 0; j < sLen; j++) {
				if (ox[j] == 'O') {
					score[j] = 1;
					if (j > 0 && ox[j-1] == 'O') {
						score[j] += score[j-1]; 
					}
				}
			}
			
			for (int j = 0; j < sLen; j++) {
				totalScore += score[j];
			}
			
			System.out.println(totalScore);
		}
	}
}