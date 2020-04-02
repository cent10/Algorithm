// D4 - 9760 : Poker Game

import java.io.*;
import java.util.*;

public class Solution_D4_9760_PokerGame_박진 {

	static class Card implements Comparable<Card> {
		char s;	// 슈트
		int r;	// 랭크

		public Card(char s, int r) {
			this.s = s;
			this.r = r;
		}

		@Override
		public int compareTo(Card o) {
			return this.r - o.r;
		}
	}
	
	static int T;
	static Card[] cards;
	static int result;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			cards = new Card[5];
			StringTokenizer st = null;
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < 5; i++) {
				String str = st.nextToken();
				char temp1 = str.charAt(0);
				int temp2 = changeChar(str.charAt(1));
				cards[i] = new Card(temp1, temp2);
//				System.out.print(cards[i].s + ", " + cards[i].r + "\t");
			}// end input
//			System.out.println();
			
			Arrays.sort(cards);
//			for (int i = 0; i < 5; i++) {
//				System.out.print(cards[i].s + ", " + cards[i].r + "\t");
//			}
//			System.out.println();
//			System.out.println();
			
			sb.append("#").append(tc).append(" ");
			
			if (isStraightFlush()) result = 1;		// 1
			else if(isFourOfAKind()) result = 2;	// 2
			else if(isFullHouse()) result = 3;		// 3
			else if(isFlush()) result = 4;			// 4
			else if(isStraight()) result = 5;		// 5
			else if(isThreeOfAKind()) result = 6;	// 6
			else if(isTwoPair()) result = 7;		// 7
			else if(isOnePair()) result = 8;		// 8
			else result = 9;
			
			switch (result) {
				case 1: sb.append("Straight Flush"); break;
				case 2: sb.append("Four of a Kind"); break;
				case 3: sb.append("Full House"); break;
				case 4: sb.append("Flush"); break;
				case 5: sb.append("Straight"); break;
				case 6: sb.append("Three of a kind"); break;
				case 7: sb.append("Two pair"); break;
				case 8: sb.append("One pair"); break;
				case 9: sb.append("High card"); break;
			}
			
			sb.append("\n");
		}// end tc
		System.out.println(sb);
	}// end main
	
	// 1. Straight Flush : 모두 동일한 슈트에 랭크(값)가 모두 연속적이다(여기서는 로얄 플러쉬를 포함한다. 로얄 플러쉬는 모두 동일한 슈트에 T, J, Q, K, A를 갖는다).
	static public boolean isStraightFlush() {
		for (int i = 0; i < 4; i++) {	// 모두 동일한 슈트인지 확인
			if (cards[i].s != cards[i+1].s) {
				return false;
			}
		}
		for (int i = 0; i < 4; i++) {	// 랭크(값)가 모두 연속적인지 확인
			if (cards[i].r - cards[i+1].r != -1) {
				return false;
			}
		}
		
		return true;
	}

	// 2. Four of a Kind : 5장 중 4개의 랭크(값)가 모두 같다.
	static public boolean isFourOfAKind() {
		int[] cnt = new int[14];
		for (int i = 0; i < 5; i++) {
			cnt[cards[i].r]++;
		}
		for (int i = 1; i <= 13; i++) {
			if (cnt[i] == 4) {
				return true;
			}
		}
		return false;
	}

	// 3. Full House : 3장의 동일한 랭크(값)와 다른 랭크(값)의 동일한 2장으로 구성된다.
	static public boolean isFullHouse() {
		int[] cnt = new int[14];
		for (int i = 0; i < 5; i++) {
			cnt[cards[i].r]++;
		}
		boolean flag1 = false;
		boolean flag2 = false;
		for (int i = 1; i <= 13; i++) {
			if (cnt[i] == 3) {
				flag1 = true;
			} else if (cnt[i] == 2) {
				flag2 = true;
			}
		}
		if (flag1 && flag2)
			return true;
		
		return false;
	}

	// 4. Flush : 5장이 모두 동일한 슈트다.
	static public boolean isFlush() {
		for (int i = 0; i < 4; i++) {	// 모두 동일한 슈트인지 확인
			if (cards[i].s != cards[i+1].s) {
				return false;
			}
		}
		return true;
	}

	// 5. Straight : 다른 슈트가 섞여있으며 랭크(값)가 모두 연속적이다.
	static public boolean isStraight() {
		for (int i = 0; i < 4; i++) {	// 랭크(값)가 모두 연속적인지 확인
			if (cards[i].r - cards[i+1].r != -1) {
				return false;
			}
		}
		
		return true;
	}

	// 6. Three of a kind : 동일한 랭크(값)가 3장이다(1,2,3,3,3).
	static public boolean isThreeOfAKind() {
		int[] cnt = new int[14];
		for (int i = 0; i < 5; i++) {
			cnt[cards[i].r]++;
		}
		for (int i = 1; i <= 13; i++) {
			if (cnt[i] == 3) {
				return true;
			}
		}
		return false;
	}

	// 7. Two pair : 동일한 랭크(값) 2장씩 두개의 랭크다(2,6,6,3,3).
	static public boolean isTwoPair() {
		int[] cnt = new int[14];
		int setCnt = 0;
		for (int i = 0; i < 5; i++) {
			cnt[cards[i].r]++;
		}
		for (int i = 1; i <= 13; i++) {
			if (cnt[i] == 2) {
				setCnt++;
			}
		}
		if (setCnt == 2)
			return true;
		
		return false;
	}

	// 8. One pair : 동일한 랭크가 2장이다(2,4,5,5,7).
	static public boolean isOnePair() {
		int[] cnt = new int[14];
		for (int i = 0; i < 5; i++) {
			cnt[cards[i].r]++;
		}
		for (int i = 1; i <= 13; i++) {
			if (cnt[i] == 2) {
				return true;
			}
		}
		return false;
	}
	
	static public int changeChar(char c) {
		if (c == 'A') return 1;
		else if (c == 'T') return 10;
		else if (c == 'J') return 11;
		else if (c == 'Q') return 12;
		else if (c == 'K') return 13;
		else return c - '0';
	}

}
