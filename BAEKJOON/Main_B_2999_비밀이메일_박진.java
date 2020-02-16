// Bronze I - 2999 : 비밀 이메일

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class Main_B_2999_비밀이메일_박진 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String message = br.readLine();	// 원래 메시지
		int N = message.length();		// 메시지의 글자 수
//		System.out.println("N = " + N);
		int R = 0;
		int C = 0;
		
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if ( (r <= c) && (r*c == N) ) {
					R = r;
					C = c;
				}
			}
		}
//		System.out.println("R = " + R);
//		System.out.println("C = " + C);
		
		char[][] arr = new char[R][C];	// 메시지를 저장할 R*C 크기의 행렬
		int index = 0;	// arr 배열의 인덱스
		
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				arr[j][i] = message.charAt(index);
				index++;
			}
		}
//		for(char[] a : arr) {
//			System.out.println(Arrays.toString(a));
//		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(arr[i][j]);
			}
		}
		System.out.println(sb);
	}
}