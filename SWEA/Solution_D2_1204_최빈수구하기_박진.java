// D2 - 1204 : [S/W 문제해결 기본] 1일차 - 최빈수 구하기

import java.util.Scanner;
 
public class Solution {
 
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int[] score = new int[1000];
            int[] countScore = new int[101];
            int frqScore = 0;
            int result = 0;
             
            sc.nextInt();
             
            for (int i = 0; i < score.length; i++)
            {
                score[i] = sc.nextInt();
                countScore[score[i]]++;
            }
             
            for (int i = 0; i < countScore.length; i++)
            {
                if (countScore[i] >= frqScore)
                {
                    frqScore = countScore[i];
                    result = i;
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
        sc.close();
    }
}
