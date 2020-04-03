package algo.comsa;

import java.util.Scanner;

// long 형
// 제시된 시작 층, 종류 층 숫자는 4가 없는 층 표현으로 1, 2, 3은 정확하나, 5, 6, 7, 8, 9는 하나 실제로 하나 작은 수
// #1 제시된 수를 실제 수로 표현 123 그대로, 56789는 하나 작게 <== 9진수
// #2 10진수로 변환 처리

public class Solution_D4_4530_극한의청소작업2 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc=1;tc<=T;tc++) {
			
			long A = sc.nextLong();
			long B = sc.nextLong();
			
			long nineA = toNine(A);
			long nineB = toNine(B);
			
			long tenA = toDigit(nineA);
			long tenB = toDigit(nineB);
			
			long res = tenB-tenA;
			
			if( (tenA<0 && tenB>0) || (tenA>0 && tenB<0) ) res = res - 1;
			System.out.println("#"+tc+" "+res);
		}
		
	}
	
	public static long toNine(long A) {
		boolean minus = false;; 
		if( A < 0 ) {
			minus = true;
		}
		String s = Math.abs(A)+"";
		StringBuilder sb = new StringBuilder("");
		if ( minus ) {
			sb.append("-");
		}
		for(int i=0; i<s.length();i++) {
			if( (s.charAt(i)-'0') > 4 ) {
				sb.append(s.charAt(i)-'1');
			} else {
				sb.append(s.charAt(i)-'0');
			}
		}
		return Long.parseLong(sb.toString());
	}
	
	public static long toDigit(long A) {
		boolean minus = false;; 
		if( A < 0 ) {
			minus = true;
		}
		String s = A+"";
		Long res = (long) 0;
		Long cipher = (long) 0;
		
		for(int i=s.length()-1; i>=0;i--) {
			if ( s.charAt(i) == '-') continue;
			res += (s.charAt(i)-'0') * (long)Math.pow(9, cipher++);
		}
		if ( minus ) {
			res = res * -1;
		}
		return res;
	}
}