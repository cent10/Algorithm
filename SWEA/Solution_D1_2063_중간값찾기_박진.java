// D1 - 2063 : 중간값 찾기

import java.util.Scanner;
 
public class Solution {
 
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
         
        int[] intArray = new int[T];        // N개의 점수 저장할 배열
        int temp;
         
        for(int test_case = 0; test_case < T; test_case++)
        {
            intArray[test_case] = sc.nextInt();
             
//          System.out.print(intArray[test_case] + " ");
        }
         
//      System.out.println();
         
        for(int i = 0; i < T - 1; i++)
        {
            for(int j = i + 1; j < T; j++)
            {
                if (intArray[i] > intArray[j])
                {
                    temp = intArray[j];
                    intArray[j] = intArray[i];
                    intArray[i] = temp;
                }
            }
        }
         
//      for(int i = 0; i < T; i++)
//          System.out.print(intArray[i] + " ");
//      System.out.println();
         
        System.out.print(intArray[(T/2)]);
         
    }
}
