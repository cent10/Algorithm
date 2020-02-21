// Gold V - 1527 : 금민수의 개수

/*  
 13232KB
 2508ms
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_1527_금민수의개수_박진 {

	static int A, B;
	static int result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		
		result = B - A + 1;
		
		for (int i = A; i <= B; i++) {
			boolean isGeumMinSoo = true;
			int num = i;
			
			do {
				if ( (num%10 != 4) && (num%10 != 7) ) {
					isGeumMinSoo = false;
					break;
				}
				num /= 10;
			} while(( num != 0 ));
			
			if (isGeumMinSoo == false)
				result--;
		}
		
		System.out.println(result);
	}
}
