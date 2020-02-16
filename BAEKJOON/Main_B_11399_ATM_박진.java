// Silver III - 11399 : ATM

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_11399_ATM_박진 {

	static int N;
	static int[] P;
	static int[] time;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		P = new int[N];
		time = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(P);
		
		time[0] = P[0];
		for (int i = 1; i < N; i++) {
			time[i] = time[i-1] + P[i];
		}
		
		int result = 0;
		for(int a : time) {
			result = result + a;
		}
		
		System.out.println(result);
	}

}
