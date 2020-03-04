// D4 - 8275 : 햄스터

import java.util.*;

/**
 * 
 * @author Park
 * N : 우리 갯수 1~6
 * X : 하나의 우리에 있을 수 있는 마릿수
 * M : 경근이가 체크한 횟수.. 햄스터를 배치해서 이 M번의 체크를 다 통과하는지 봐야함!
 * 
 * 우리 갯수 최대 6개라네.. 그 때 각 우리에 마릿수는 1~11까지. 그러면?! 최악의 경우 11*11*11*... = 11^6 경우의수 만들어봐도 되겠다.
 *
 * 출력 형식 >
 * #1 0 5 5
 * 
 * 주의 사항 >
 * (1) M번을 다 만족하는 배치가 여러 경우일 때는 합계가 많은 거!
 * (2) 합계가 다 같은 경우도 있다면? 오름차순 빠른거
 * (3) 만족하는 배치가 없다면? -1 출력..
 */

public class Solution_D4_8275_햄스터_해답 {

	static int[] cage;	// 가능한 모든 햄스터 배치를 해볼 배열 (중복순열)
	static int N, X, M;
	static int[][] input;
	static int max;	// 햄스터 배치 여러가지 종류 가능하다면 ? 합이 최대가 되는 경우를 선택!
	static String ans;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			max = -1;
			ans = "";
			N = sc.nextInt();	// 총 우리 갯수
			X = sc.nextInt();	// 각 우리 가능 마릿수
			M = sc.nextInt();	// 체크 횟수
			
			cage = new int[N+1];	// 케이지 번호를 1부터 N까지 인덱스로 사용 예정.
			input = new int[M][3];	// left, right, sum
			
			for (int i = 0; i < M; i++) {
				input[i][0] = sc.nextInt();	// left
				input[i][1] = sc.nextInt();	// right
				input[i][2] = sc.nextInt();	// sum
			}
			permutation(1, 0);	// 1번 케이지부터 가능한 모든 마릿수 채워서
			System.out.println("#" + tc + " " + ((max == -1) ? max : ans));
		}
	}
	
	static void permutation(int index, int sum) {
		if (index == cage.length) {	// 경근이의 체크와 부합하는지 ?!
			if(check()) {	// 만족한다?!
				if(max < sum) {	// 등호 없는 조건은 ? 똑같은 합계가 나온 사전순 나중에 나온 애는 무시~~~
					max = sum;	// 오 합계가 크다 ? 그러면 지금 케이지 순서 일단 저장하자~~
					ans = makeAns();
				}
			}
			return;
		}
		
		for (int i = 0; i <= X; i++) {	// 알아서 0부터 채우기 때문에 다음 경우의 수는 점점 커짐!
			cage[index] = i;
			permutation(index+1, sum+i);
		}
	}
	
	static String makeAns() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {	// 1번 케이지부터 ~ N번 케이지까지
			sb.append(cage[i]).append(" ");
		}
		return sb.toString();
	}
	
	static boolean check() {
		for (int i = 0; i < M; i++) {	// M번의 체크 다 되나?
			int tmp = 0;
			for(int j = input[i][0]; j <= input[i][1]; j++) {	// j: left ~ right까지 돌면서 누적
				tmp += cage[j];
			}
			if (tmp != input[i][2])	// 경근이가 센거랑 달라?
				return false;
		}
		return true;
	}
}
