// Gold IV - 1062 : 가르침

import java.io.*;
import java.util.*;

public class Main_B_1062_가르침_해답2_비트마스킹 {

	static int N;
	static int K;
	static int[] words;
	static int max;
	
	public static void main(String[] args) throws Exception {
		// 데이터 읽기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		//			    t     n    i     c a
		int letters = 0b10000010000100000101;	// anta tica
		words = new int[N];
		for (int i = 0; i < N; i++) {
			String word = br.readLine();
			int len = word.length();
			int bword = letters;
			for (int j = 4; j < len - 4; j++) {
				bword |= 1 << (word.charAt(j) - 'a');
			}
			words[i] = bword;
//			System.out.println(Integer.toBinaryString(bword));
		}
		combi('a', 5, letters);
		
		// 결과출력
		System.out.println(max);
	}// end of main

	private static void combi(int c, int cnt, int letters) {
		if (cnt == K) {
			// K개의 문자를 배웠을 때, 단어를 읽을 수 있는지 검사
			check(letters);
			return;
		}
		
		if(c > 'z') {
			return;
		}
		
		// 배우지 않았을 때
		combi(c+1, cnt, letters);
		
		// 배웠을 때
		if ((letters & (1 << c-'a')) == 0) {	// 안배운 글자
			combi(c+1, cnt+1, letters | (1 << c-'a'));
		}
	}

	private static void check(int letters) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			if ((letters & words[i]) == words[i]) {
				count++;
			}
		}
		max = Math.max(max, count);
	}

}
