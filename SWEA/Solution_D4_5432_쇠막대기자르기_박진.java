// D4 - 5432 : 쇠막대기 자르기
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            String S = br.readLine();
            int lengthOfS = S.length();
            char[] gwalho = new char[lengthOfS];    // 괄호를 입력받을 배열
            int result = 0;                         // 결과값
            int stackedIron = 0;                    // 쌓인 쇠막대기의 수
 
            // 입력
            for (int i = 0; i < lengthOfS; i++) {
                gwalho[i] = S.charAt(i);
//              System.out.print(input[i]);
            }
//          System.out.println();
             
            for (int i = 0; i < lengthOfS; i++) {
                // '('일 경우
                if (gwalho[i] == '(') {
                    if (gwalho[i+1] == ')') {   // 레이저일 경우
                        i++;                    // 레이저일 경우 ')' 괄호는 건너뛰어버림
                        result += stackedIron;
                    }
                    else {  // 쇠막대기일 경우
                        stackedIron++;  // 쌓인 쇠막대기의 수 증가
                    }
                }
                // ')'일 경우 (레이져의 닫히는 괄호는 if문에서 건너뛰므로 쇠막대기의 닫히는 괄호일 경우임)
                else { 
                    stackedIron--;  // 쌓인 쇠막대기의 수 감소
                    result++;       // 잘린 쇠막대기의 끝부분을 결과값에 합쳐줌.
                }
            }
            System.out.println("#" + tc + " " + result);
        }
    }
}
