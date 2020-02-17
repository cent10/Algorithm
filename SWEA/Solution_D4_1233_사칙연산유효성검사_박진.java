// D4 - 1233 : [S/W 문제해결 기본] 9일차 - 사칙연산 유효성 검사

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int N;           // 트리가 갖는 정점의 총 수 N (1~200)
    static String[] tree;   // 트리 역할을 할 배열
    static Stack stack;     // 스택
    static int result;      // 계산이 가능하다면 “1”, 계산이 불가능할 경우 “0”
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        // 10개의 테스트 케이스
        for (int tc = 1; tc <= 10; tc++) {
            // 입력
            N = Integer.parseInt(br.readLine());
            tree = new String[N+1]; // 0번 인덱스는 사용x
            stack = new Stack();
            result = 1;
            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                st.nextToken();
                tree[i] = st.nextToken();
            }
//          for(int i = 0; i <=N; i++)
//              System.out.println(tree[i]);
             
            // 알고리즘
            checkWithBinaryTreePostOrder(1);
             
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        // 출력
        System.out.println(sb);
    }
 
     
    public static void checkWithBinaryTreePostOrder(int node) {
        if (node <= N) {
            checkWithBinaryTreePostOrder(node*2);
            checkWithBinaryTreePostOrder(node*2+1);
             
            if (tree[node].equals("+") || tree[node].equals("-") || tree[node].equals("*") || tree[node].equals("/")) {
                if (stack.isEmpty()) {  // 연산자인데 스택에 아무것도 없으면
                    result = 0;         // 계산이 불가능.
                    return;
                }
                else {                  // 연산자인데 스택이 비어있지 않을 때
                    if (stack.size() >= 2) {
                        stack.pop();        // 피연산자 두개를 빼고
                        stack.pop();
                        stack.push("" + 1); // 아무 숫자 1을 넣어줌. (유효성 검사이기 때문에, 진짜 계산은 필요하지 않으므로)
                    }
                    else {
                        result = 0;
                        return;
                    }
                }
            }
            else {                      // 피연산자이면
                stack.push(tree[node]); // 스택에 넣어줌.
            }
        }
    }
}
