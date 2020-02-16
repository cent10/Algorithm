// Silver V - 2941 : 크로아티아 알파벳

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static String input;
	static int inputLength;
	static char[] croatiaAlphabet;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 입력
		input = br.readLine();					// 문자열
		inputLength = input.length();			// 문자열의 길이
		croatiaAlphabet = input.toCharArray();	// 문자열을 char배열에 저장
//		for (char a : croatiaAlphabet) {
//			System.out.print(a);
//		}
//		System.out.println("input = " + input);
//		System.out.println("inputLength = " + inputLength);
		
		// 알고리즘
		for (int i = 0; i < inputLength; i++) {
			if (croatiaAlphabet[i] == 'c') {
				if ( ((i+1) < inputLength) && (croatiaAlphabet[i+1] == '=') ) {
					result++;
					i+=1;
					continue;
				}
				else if ( ((i+1) < inputLength) && (croatiaAlphabet[i+1] == '-')) {
					result++;
					i+=1;
					continue;
				}
				else {
					result++;
					continue;
				}
			}
			else if (croatiaAlphabet[i] == 'd') {
				if ( ((i+2) < inputLength) && ((i+1) < inputLength) && (croatiaAlphabet[i+1] == 'z') && (croatiaAlphabet[i+2] == '=') ) {
					result++;
					i+=2;
					continue;
				}
				else if ( ((i+1) < inputLength) && (croatiaAlphabet[i+1] == '-') ) {
					result++;
					i+=1;
					continue;
				}
				else {
					result++;
					continue;
				}
			}
			else if (croatiaAlphabet[i] == 'l') {
				if ( ((i+1) < inputLength) && (croatiaAlphabet[i+1] == 'j')) {
					result++;
					i+=1;
					continue;
				}
				else {
					result++;
					continue;
				}
			}
			else if (croatiaAlphabet[i] == 'n') {
				if ( ((i+1) < inputLength) && (croatiaAlphabet[i+1] == 'j')) {
					result++;
					i+=1;
					continue;
				}
				else {
					result++;
					continue;
				}
			}
			else if (croatiaAlphabet[i] == 's') {
				if ( ((i+1) < inputLength) && (croatiaAlphabet[i+1] == '=')) {
					result++;
					i+=1;
					continue;
				}
				else {
					result++;
					continue;
				}
			}
			else if (croatiaAlphabet[i] == 'z') {
				if ( ((i+1) < inputLength) && (croatiaAlphabet[i+1] == '=')) {
					result++;
					i+=1;
					continue;
				}
				else {
					result++;
					continue;
				}
			}
			else {
				result++;
			}
		}
		
		// 출력
		System.out.println(result);
	}

}
