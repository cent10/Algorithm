// D3 - 5215 : 햄버거 다이어트

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
     
    static int N, L;
    static int[][] arr;
    static int maxScore;
     
    public static void main(String[] args) throws IOException {
//      System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         
        int T = Integer.parseInt(st.nextToken());   // 테스트 케이스의 수
         
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());   // 재료의 수
            L = Integer.parseInt(st.nextToken());   // 제한 칼로리
            arr = new int[N][2];                    // 맛에 대한 점수와 칼로리
            maxScore = 0;
             
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int k = 0; k < 2; k++) {
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }
             
            subset(0, 0, 0);
            System.out.println("#" + (i+1) + " " + maxScore);
        }
         
    }
     
    private static void subset(int index, int sumOfCal, int sumOfScore) {
        if (sumOfCal > L) {
            return;
        }
 
        if (index == N) { // 기저조건
            if (sumOfCal <= L)
                if (maxScore < sumOfScore) {
                    maxScore = sumOfScore;
                }
            return;
        }
         
        subset(index + 1, sumOfCal + arr[index][1], sumOfScore + arr[index][0]);
        subset(index + 1, sumOfCal, sumOfScore);
    }
}
