// D4 - 1224 : 계산기3

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
 
public class Solution {
 
    public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("intput.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        for (int i = 1; i <= 10; i++) {
            int N = Integer.parseInt(br.readLine());
            char[] infix = new char[N];     // 중위연산식을 저장할 배열
            infix = br.readLine().toCharArray();
             
//          for (int j = 0; j < infix.length; j++) {
//              System.out.print(infix[j]);
//          }
//          System.out.println();
             
            int isp = 0;    // in-stack priority
            int icp = 0;    // in-coming priority
             
            Stack<Character> st1 = new Stack<Character>();  // 연산자들을 저장할 스택
             
            for (int j = 0; j < N; j++) {
                // 연산자일 경우
                if (infix[j] == '+' || infix[j] == '-' || infix[j] == '*' || infix[j] == '/' || infix[j] == '(') {
                    if (st1.isEmpty()) {        // 스택이 비어있으면
                        st1.push(infix[j]);     // 그냥 넣는다.
                    }
                    else {  // 스택이 비어있지 않으면
                        // in-coming priority 설정
                        if (infix[j] == '+' || infix[j] == '-') {
                            icp = 1;
                        }
                        else if (infix[j] == '*' || infix[j] == '/') {
                            icp = 2;
                        }
                        else if (infix[j] == '(') {
                            icp = 3;
                        }
                        // in-stack priority 설정
                        if (st1.peek() == '+' || st1.peek() == '-') {
                            isp = 1;
                        }
                        else if (st1.peek() == '*' || st1.peek() == '/') {
                            isp = 2;
                        }
                        else if (st1.peek() == '(') {
                            isp = 0;
                        }
                        // 우선순위를 비교
                        if (isp < icp) { // 토큰의 우선순위가 스택의 맨 위에 있는 연산자의 우선순위보다 크면 push
                            st1.push(infix[j]);
                        }
                        else {  // 그렇지 않다면 스택의 맨 위에 있는 연산자의 우선순위가 토큰의 우선순위보다 작아질 때 까지 pop한 후에 push
                            sb.append(st1.pop());
                            j--;
                            continue;
                        }
                    }
                }
                // 오른쪽 괄호 ')'일 경우
                else if (infix[j] == ')') {
                    while (st1.peek() != '(') {
                        sb.append(st1.pop());   //  왼쪽 괄호 '('를 만날 때까지 pop
                    }
                    st1.pop();  // 왼쪽 괄호 '('는 버림
                }
                // 피연산자일 경우
                else {
                    sb.append(infix[j]); 
                }
            }
             
            while (!st1.isEmpty()) {
                if (st1.peek() == '(') {
                    st1.pop();
                } else {
                    sb.append(st1.pop());   // 스택에 아직 연산자가 남아있으면 모두 pop
                }
            }
             
//          System.out.println(sb);
             
//          char[] postfix = new char[sb.toString().length()];  // 후위연산식을 저장할 배열
//          Stack<Character> st2 = new Stack<Character>();      // 후위연산식을 계산할 스택
//          postfix = sb.toString().toCharArray();
             
            Stack<Integer> st2 = new Stack<Integer>();      // 후위연산식을 계산할 스택
             
            for (int k = 0; k < sb.length(); k++) {
                // 토큰이 연산자이면 계산
                if (sb.charAt(k) == '+' || sb.charAt(k) == '-' || sb.charAt(k) == '*' || sb.charAt(k) == '/') {
                    int tempA;
                    int tempB;
                    tempB = st2.pop();
                    tempA = st2.pop();
                     
                    if(sb.charAt(k) == '+') {
                        st2.push(tempA+tempB);
                    }
                    else if(sb.charAt(k) == '-') {
                        st2.push(tempA-tempB);
                    }
                    else if(sb.charAt(k) == '*') {
                        st2.push(tempA*tempB);
                    }
                    else {
                        st2.push(tempA/tempB);
                    }
                     
                }
                // 토큰이 피연산자이면 스택에 push
                else {
                    st2.push(Integer.parseInt(""+sb.charAt(k)));
                }
            }
             
             
            System.out.println("#" + i + " " + st2.pop());
        }
    }
 
}
