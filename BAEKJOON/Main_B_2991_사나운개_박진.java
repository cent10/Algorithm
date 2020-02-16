// Bronze III - 2991 : 사나운개

import java.util.Scanner;

public class Main_B_2991_사나운개_박진 {

	static int A, B, C, D;
	static int P, M, N;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		A = sc.nextInt();	// 첫번째 개: A분동안 공격적
		B = sc.nextInt();	// 첫번째 개: B분동안 휴식
		C = sc.nextInt();	// 두번째 개: C분동안 공격적
		D = sc.nextInt();	// 두번째 개: D분동안 휴식
		P = sc.nextInt();	// 우체부의 도착 시간
		M = sc.nextInt();	// 우유배달원의 도착 시간
		N = sc.nextInt();	// 신문배달원의 도착 시간
		
		int dog1_start = 0;
		int dog1_end = A;
		int dog2_start = 0;
		int dog2_end = C;
		
		int lastTime = Math.max(P, Math.max(M, N));
		
		int[] attacked = new int[3];
		
		while (true) {
			if (dog1_start < P && dog1_end >= P) {
				attacked[0]++;
			}
			if (dog1_start < M && dog1_end >= M) {
				attacked[1]++;
			}
			if (dog1_start < N && dog1_end >= N) {
				attacked[2]++;
			}
			dog1_start += A+B;
			dog1_end += A+B;
			
			if (dog2_start < P && dog2_end >= P) {
				attacked[0]++;
			}
			if (dog2_start < M && dog2_end >= M) {
				attacked[1]++;
			}
			if (dog2_start < N && dog2_end >= N) {
				attacked[2]++;
			}
			dog2_start += C+D;
			dog2_end += C+D;
			
			if (lastTime < dog1_start && lastTime < dog2_start)
				break;
		}

		System.out.println(attacked[0]);
		System.out.println(attacked[1]);
		System.out.println(attacked[2]);
		
		sc.close();
	}
}