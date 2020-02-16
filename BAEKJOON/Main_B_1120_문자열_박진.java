// Silver IV - 1120 : 문자열

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1120_문자열_박진 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String A = st.nextToken();	// 문자열A
		String B = st.nextToken();	// 문자열B
		int lengthOfA = A.length();	// 문자열A의 길이
		int indexOfB = 0;
		int gap = B.length() - A.length();	// 두 문자열의 글자수 차이
		int tempResult = 0;
		int result = Integer.MAX_VALUE;
		
		for (int i = 0; i <= gap; i++) {
			tempResult = 0;
			indexOfB = i;
			for (int j = 0; j < lengthOfA; j++) {
				if (A.charAt(j) != B.charAt(indexOfB)) {
					tempResult++;
				}
				indexOfB++;
			}
			if (result > tempResult)
				result = tempResult;
		}
		
		System.out.println(result);
	}
}