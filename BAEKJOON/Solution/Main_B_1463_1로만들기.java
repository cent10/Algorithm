package algo.dp;

import java.util.Scanner;
// 2부터 시작하여 N을 만들 수 있는 최소연산 수로 접근한 방식
//1부터 시작하면 안됨.(문제가 1을 만드는 최소연산수인데 1을 /3,/2,-1 로는 1을 만들수 없으므로 )
/**
 * 
 * @author taeheekim
 *
 */
public class Main_B1463_1로만들기 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		int count[] = new int[N+1];
		int temp = 0,min = Integer.MAX_VALUE;
		for(int i=2; i<=N; ++i) {
			min = Integer.MAX_VALUE;
			if(i % 3 == 0) {
				temp = i/3;
				if(min>count[temp]+1) min = count[temp]+1;
			}
			if(i % 2 == 0) {
				temp = i/2;
				if(min>count[temp]+1) min = count[temp]+1;
			}
			temp = i-1;
			if(min>count[temp]+1) min = count[temp]+1;
			count[i] = min;
		}
		System.out.println(count[N]);
	}

}