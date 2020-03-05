// 2382 : [모의 SW 역량테스트] 미생물 격리

import java.io.*;
import java.util.*;

public class Solution_2382_미생물격리_박진 {

	static class Virus implements Comparable<Virus> {
		int loc;
		int row,col;
		int num;
		int dir;
		public Virus(int loc, int row, int col, int num, int dir) {
			this.loc = loc;
			this.row = row;
			this.col = col;
			this.num = num;
			this.dir = dir;
		}
		@Override
		public int compareTo(Virus o) {
			if (this.loc == o.loc)
				return o.num - this.num;
			return this.loc - o.loc;
		}
	}
	
	static int N;	// 한 변에 있는 셀의 개수 N
	static int M;	// 격리 시간 M
	static int K;	// 미생물 군집의 개수 K
	static ArrayList<Virus> arrList;
	static int result;
	
	static int[] di = { 0, -1, 1, 0, 0 };
	static int[] dj = { 0, 0, 0, -1, 1 };
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arrList = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				arrList.add(new Virus(r * N + c, r, c, n, d));
			}
			for (int m = 0; m < M; m++) {	// M번 반복
				for (int i = 0; i < arrList.size(); i++) {	// 이동
					arrList.get(i).row += di[arrList.get(i).dir];
					arrList.get(i).col += dj[arrList.get(i).dir];
					arrList.get(i).loc = arrList.get(i).row * N + arrList.get(i).col;
					
					// 약품에 닿아 죽음
					if(arrList.get(i).col == 0 || arrList.get(i).row == 0 || arrList.get(i).col == N-1 || arrList.get(i).row == N-1) {
						arrList.get(i).num /= 2;
						arrList.get(i).dir = changeDir(arrList.get(i).dir);
						
						if (arrList.get(i).num == 0) {
							arrList.remove(i);
							i--;
						}
					}
				}
				Collections.sort(arrList);
				for (int i = 1; i < arrList.size(); i++) {	// 미생물 합치기
					if (arrList.size() >= 2 && arrList.get(i).loc == arrList.get(i-1).loc) {
						arrList.get(i-1).num += arrList.get(i).num;
						arrList.remove(i);
						i--;
					}
				}
			}
			for (int i = 0; i < arrList.size(); i++) {
				result += arrList.get(i).num;
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}// end tc
		System.out.println(sb);
	}// end main

	private static int changeDir(int d) {
		switch(d) {
		case 1:
			return 2;
		case 2:
			return 1;
		case 3:
			return 4;
		case 4:
			return 3;
		default:
			return 0;
		}
	}
}
