// Gold IV - 1062 : 가르침

import java.io.*;
import java.util.*;

public class Main_B_1062_가르침_해답 {

	static int N;
	static int K;
	static char[][] words;
	static boolean[] letters = {	// anta tica는 꼭 배워야하는 필수 문자
			true, false, true, false, false, false, false, false, true, false,
			false, false, false, true, false, false, false, false, false, true,
			false, false, false, false, false, false
	};
	static int max;
	
	public static void main(String[] args) throws Exception {
		// 데이터 읽기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		words = new char[N][];
		for (int i = 0; i < N; i++) {
			words[i] = br.readLine().toCharArray();
//			System.out.println(Arrays.toString(words[i]));
		}
		
		// 조합
		combi('a', 5);
		
		// 결과출력
		System.out.println(max);
	}// end of main

	private static void combi(int c, int cnt) {
		if (cnt == K) {
			check();
			return;
		}
		
		if(c > 'z')
			return;
		// 조합
		// 문자(c)를 안배울 때
		combi(c + 1, cnt);
		// 문자(c)를 배울 때
		int idx = c - 'a';	// 0 ~ 25 (a ~ z)
		if (!letters[idx]) {
			letters[idx] = true;
			combi(c + 1, cnt + 1);
			letters[idx] = false;
		}
	}

	private static void check() {
		int count = 0;	// 읽은 단어 수 세기
		for (int i = 0; i < N; i++) {
			boolean isRead = true;
			for (int j = 0, size = words[i].length; j < size; j++) {
				int letter = words[i][j] - 'a';
				if(!letters[letter]) {
					isRead = false;
					break;
				}
			}
			if(isRead) {
				count++;
			}
		}
		max = Math.max(max, count);
	}

}
