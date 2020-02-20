// Silver III - 2630 : 색종이 만들기

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_B_2630_색종이만들기_박진 {

	static int N;
	static int[][] paper;
	
	static int cntWhite = 0;	// 햐얀색 색종이의 개수
	static int cntBlue = 0;		// 파란색 색종이의 개수
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		paper = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
//				System.out.print(paper[i][j] + " ");
			}
//			System.out.println();
		}
		
		dividePaper(0, 0, N, N);
		
		// 하얀색 색종이와 파란색 색종이의 개수 출력
		System.out.println(cntWhite);
		System.out.println(cntBlue);
	}


	private static void dividePaper(int startRow, int startCol, int endRow, int endCol) {
		if (endRow == 0 || endCol == 0)
			return;
		
		int numW = 0;
		int numB = 0;
		
		// 색이 섞여있는지 확인
	L:	for (int i = startRow; i < endRow; i++) {
			for (int j = startCol; j < endCol; j++) {
				if (numW > 0 && numB > 0) {	// 색이 하나라도 섞여있으면 더이상 비교안하고 바로 빠져나감.
					break L;
				}
				if (paper[i][j] == 0)
					numW++;
				else
					numB++;
			}
		}
		
		if (numW != 0 && numB != 0) {	// 다른 색이 하나라도 섞여있으면.
			dividePaper(startRow, startCol, (startRow+endRow)/2, (startCol+endCol)/2);	// 좌측상단
			dividePaper(startRow, (startCol+endCol)/2, (startRow+endRow)/2, endCol);	// 우측상단
			dividePaper((startRow+endRow)/2, startCol, endRow, (startCol+endCol)/2);	// 좌측하단
			dividePaper((startRow+endRow)/2, (startCol+endCol)/2, endRow, endCol);		// 우측하단
		}
		else {	// 다른 색이 하나라도 섞여있지 않으면.
			if (numW == 0)	// 흰색 종이가 하나도 없으면.
				cntBlue++;
			else			// 파란색 종이가 하나도 없으면.
				cntWhite++;
			return;
		}
	}

}
