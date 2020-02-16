// Silver V - 3985 : 롤 케이크

import java.util.Scanner;

public class Main {

	static int L;	// 롤 케이크의 길이
	static int N;	// 방청객의 수
	// P번 조각부터 K번 조각을 원한다는 뜻
	static int[] P;
	static int[] K;
	
	static int[] cake;		// 케이크
	static int[] cntCake;	// 각 방청객이 몇개의 케이크를 받는지 저장할 배열
	
	static int result1;		// 가장 많은 조각을 받을 것으로 기대하고 있던 방청객의 번호
	static int result2;		// 실제로 가장 많은 조각을 받은 방청객의 번호
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		N = sc.nextInt();
		P = new int[N];
		K = new int[N];
		cake = new int[L+1];	// 0번 인덱스는 사용하지 않음
		cntCake = new int[N];
		
		for (int i = 0; i < N; i++) {
			P[i] = sc.nextInt();
			K[i] = sc.nextInt();
//			System.out.print(P[i] + " ");
//			System.out.println(K[i]);
		}
		
		int temp1 = 0;
		for (int i = 0; i < N; i++) {
			if (temp1 < K[i]-P[i]) {
				temp1 = K[i]-P[i];
				result1 = i + 1;
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = P[i]; j <= K[i]; j++) {
				if (cake[j] == 0) {
					cake[j] = i+1;
					cntCake[i]++;
				}
			}
		}
		
		
		int temp2 = 0;
		for (int i = 0; i < N; i++) {
			if (temp2 < cntCake[i]) {
				temp2 = cntCake[i];
				result2 = i + 1;
			}
		}
		
		System.out.println(result1);
		System.out.println(result2);
		
		sc.close();
	}

}
