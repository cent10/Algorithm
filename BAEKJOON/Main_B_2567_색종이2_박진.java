// Silver V - 2567 : 색종이 - 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2567_색종이2_박진 {

	static int N;
	static int[][] paper = new int[102][102];
	static int x, y;
	static int[] di = { 0, 0, -1, 1 };
	static int[] dj = { -1, 1, 0, 0 };
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			for (int j = x; j < x+10; j++) {
				for (int k = y; k < y+10; k++) {
					paper[j][k] = 1;
				}
			}
		}
		
		int result = 0;
		int iCheck = 0;
		int jCheck = 0;
		int cnt = 0;
		
		for (int i = 1; i <= 100; i++) {
			for (int j = 1; j <= 100; j++) {
				if (paper[i][j] == 1) {
					cnt = 0;
					for (int k = 0; k < 4; k++) {
						iCheck = i + di[k];
						jCheck = j + dj[k];
						if (paper[iCheck][jCheck] == 1)
							cnt++;
					}
					if (cnt == 3)
						result++;
					else if (cnt == 2)
						result += 2;
				}
			}
		}
		System.out.println(result);
	}
}