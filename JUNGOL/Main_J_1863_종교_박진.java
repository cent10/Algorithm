// Advanced Corder - 1863 : 종교
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main {
 
    static int n;   // 학교에는 n (0 < n ≤ 50,000)명의 학생이 있다.
    static int m;   // 같은 종교를 가지는 쌍의 수
    static int[] set;
    static int count = 0;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        set = new int[n+1];
         
        makeSet();
         
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int iInput = Integer.parseInt(st.nextToken());
            int jInput = Integer.parseInt(st.nextToken());
             
            union(iInput, jInput);
        }
         
        for (int i = 1; i <= n; i++) {
            if (set[i] < 0)
                count++;
        }
         
        System.out.println(count);
    }
 
    private static void makeSet() {
        Arrays.fill(set, -1);
    }
     
    private static int findSet(int a) {
        if (set[a] < 0) {
            return a;
        }
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
}
