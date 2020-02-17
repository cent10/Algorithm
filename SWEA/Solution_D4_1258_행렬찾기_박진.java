// D4 - 1258 : [S/W 문제해결 응용] 7일차 - 행렬찾기
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T;       // 테스트 케이스의 개수
    static int N;       // 배열의 크기
    static int[][] arr; // 화학 물질 용기 n2개가 n x n으로 배열
    static boolean[][] isChecked;   // 해당 용기가 부분행렬에 포함되어있는지 파악하는 flag
    static int[][] colAndRow = new int[21][3];  // 부분 행렬의 행의 크기, 열의 크기, 행과 열을 곱한 값을 저장할 배열 (21개 중에 0번 인덱스는 사용x)
     
    // 아래방향, 오른쪽방향
    static int[] di = { 1, 0 };
    static int[] dj = { 0, 1 };
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
             
            // 입력
            N = Integer.parseInt(br.readLine());
            arr = new int[N][N];
            isChecked = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
//                  System.out.print(arr[i][j] + " ");
                }
//              System.out.println();
            }
             
            // 알고리즘
            int nexti;
            int nextj;
            int numOfSubset = 0;    // 추출된 부분 행렬의 개수
            int colCnt = 1;         // 추출된 부분 행렬의 행의 크기
            int rowCnt = 1;         // 추출된 부분 행렬의 열의 크기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if ( (arr[i][j] != 0) && (isChecked[i][j] == false) ) { // 화학 물질이 담긴 용기를 찾으면.
                        nexti = i;
                        nextj = j;
                        colCnt = 1;
                        rowCnt = 1;
                        numOfSubset++;
                         
                        while ( !(nexti + di[0] < 0) && !(nexti + di[0] >= N) ) { // 아래 방향으로 이동하면서 체크
                            nexti = nexti + di[0];
                             
                            if (arr[nexti][j] == 0) break;
                            else colCnt++;
                        }
                         
                        while ( !(nextj + dj[1] < 0) && !(nextj + dj[1] >= N) ) { // 오른쪽 방향으로 이동하면서 체크
                            nextj = nextj + dj[1];
                             
                            if (arr[i][nextj] == 0) break;
                            else rowCnt++;
                        }
                         
                        for (int k = i; k < i+colCnt; k++) { // 해당 부분 행렬의 isChecked를 true로 바꿔줌.
                            for (int s = j; s < j+rowCnt; s++) {
                                isChecked[k][s] = true;
                            }
                        }
                         
                        colAndRow[numOfSubset][0] = colCnt;
                        colAndRow[numOfSubset][1] = rowCnt;
                        colAndRow[numOfSubset][2] = colCnt*rowCnt;
                    }
                }
            }
             
            // 행과 열을 곱한 값을 기준으로 오름차순 정렬 (같을 경우 행이 작은 순으로 정렬)
            for (int i = 1; i <= numOfSubset-1; i++) {
                for (int j = i+1; j <= numOfSubset; j++) {
                    if (colAndRow[i][2] > colAndRow[j][2]) { // 행과 열을 곱한 값을 기준으로 오름차순 정렬
                        int temp = colAndRow[i][2];
                        colAndRow[i][2] = colAndRow[j][2];
                        colAndRow[j][2] = temp;
                         
                        temp = colAndRow[i][1];
                        colAndRow[i][1] = colAndRow[j][1];
                        colAndRow[j][1] = temp;
                         
                        temp = colAndRow[i][0];
                        colAndRow[i][0] = colAndRow[j][0];
                        colAndRow[j][0] = temp;
                    }
                    else if (colAndRow[i][2] == colAndRow[j][2]) {  // 같을 경우 행이 작은 순으로 정렬
                        if (colAndRow[i][0] > colAndRow[j][0]) {
                            int temp = colAndRow[i][2];
                            colAndRow[i][2] = colAndRow[j][2];
                            colAndRow[j][2] = temp;
                             
                            temp = colAndRow[i][1];
                            colAndRow[i][1] = colAndRow[j][1];
                            colAndRow[j][1] = temp;
                             
                            temp = colAndRow[i][0];
                            colAndRow[i][0] = colAndRow[j][0];
                            colAndRow[j][0] = temp;
                        }
                    }
                }
            }
//          for (int[] a : colAndRow) {
//              System.out.println(Arrays.toString(a));
//          }
//          System.out.println("부분행렬 개수 =" + numOfSubset);
             
            // 출력을 위해 StringBuilder에 저장
            sb.append("#").append(tc).append(" ").append(numOfSubset);
            for (int i = 1; i <= numOfSubset; i++) {
                sb.append(" ").
                append(colAndRow[i][0]).append(" ").append(colAndRow[i][1]);
            }
            sb.append("\n");
        }
        // 출력
        System.out.println(sb);
    }
}
