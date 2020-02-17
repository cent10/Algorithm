// D4 - 3289 : 서로소 집합
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T;   // 테스트 케이스의 수
    static int n;   // 초기 집합의 수
    static int m;   // 입력으로 주어지는 연산의 개수
     
    static int[] set;
     
    static int result;
    /*
     * 각 테스트 케이스마다 1로 시작하는 입력에 대해서 같은 집합에 속해있다면 1을, 아니면 0을 순서대로 한줄에 연속하여 출력
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
         
        T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            StringBuilder sb2 = new StringBuilder();
            st = new StringTokenizer(br.readLine(), " ");
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            set = new int[n+1];
             
            makeSet();
             
            for (int i = 0; i < m; i++) {
                result = -1;
                st = new StringTokenizer(br.readLine(), " ");
                int operate = Integer.parseInt(st.nextToken()); // 합집합은 0, 두 원소가 같은 집합에 포함되어 있는지를 확인하는 연산은 1
                int num1 = Integer.parseInt(st.nextToken());
                int num2 = Integer.parseInt(st.nextToken());
                 
                switch (operate) {
                case 0:
                    union(num1, num2);
                    break;
                     
                case 1:
                    if (checkSet(num1, num2))
                        result = 0;
                    else
                        result = 1;
                    sb2.append(result);
                    break;
 
                default:
                    break;
                }
            }
            sb.append("#").append(tc).append(" ").append(sb2).append("\n");
        }
        System.out.println(sb);
    }
 
    private static void makeSet() {
        Arrays.fill(set, -1);
    }
     
    private static int findSet(int a) {
        if (set[a] < 0)
            return a;
        return set[a] = findSet(set[a]);
    }
     
    private static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
         
        if (aRoot != bRoot) {
            set[bRoot] = aRoot;
            return true;
        }
        return false;
    }
     
    private static boolean checkSet(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
         
        if (aRoot != bRoot) {
            return true;    // 다른 집합
        }
        return false;       // 같은 집합
    }
}
