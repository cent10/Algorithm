// D3 - 1289 : 원재의 메모리 복구하기

import java.util.Scanner;
 
class Solution
{
    public static void main(String args[]) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
 
        for(int test_case = 1; test_case <= T; test_case++)
        {
            String s;   // 입력받을 테스트케이스
            s = sc.next();
             
            int cnt = 0;    // 수정 횟수
            char[] testCase = new char[s.length()];
             
            for (int i = 0; i < s.length(); i++)
            {
                testCase[i] = s.toCharArray()[i];
            }
             
            for (int i = 0; i < testCase.length; i++)
            {
                if (testCase[i] == '0')
                {
                    continue;
                }
                else if (testCase[i] == '1')
                {
                    for (int j = i; j < testCase.length; j++)
                    {
                        if (testCase[j] == '0')
                        {
                            testCase[j] = '1';
                        }
                        else if (testCase[j] == '1')
                        {
                            testCase[j] = '0';
                        }
                    }
                    cnt++;
                }
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }
}
