// D4 - 5684 : [Professional] 운동

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author 문제분석
 *  가중치 방향성 그래프의 간선 정보가 주어졌을 때 N개의 정점 사이에서 최소비용의 사이클 찾기
 *  
 *  분석>
 *  (1) 모든 정점에서 출발해 봐야겠다. 돌아오는 사이클이 만들어 지는지.
 *  (2) 디스조인트셋은 어떨까? 방향성때매 좀 곤란한듯 .. 
 *  (3) 방문처리 하는 visit을 어떤식으로 사용하면 좋을까.. 이미 방문했지만 가야하는 정점이 있는데!
 *  
 *  주의사항>
 *  (1) 두 마을의 왕복도 사이클로 들어가야함. 고려되는지 확인하자!
 *  (2) 간선의 길이는 자연수임.
 *  (3) 사이클이 없어서 -1 출력해야 되는 경우도 있음. 
 *
 */
public class Solution_D4_5684_운동_해답2_인접리스트 {
    static int ans;
    static List<Edge>[] edgeList;
    static int N, M;
    static boolean[] visit;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            ans = Integer.MAX_VALUE;
            N = sc.nextInt();
            M = sc.nextInt();
            edgeList = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                edgeList[i] = new ArrayList();
            }

            for (int i = 0; i < M; i++) {
                int s = sc.nextInt();
                int e = sc.nextInt();
                int c = sc.nextInt();
                edgeList[s].add(new Edge(e, c)); // 인접 리스트의 인덱스로 출발점을 처리함.
            }

            for (int i = 1; i <= N; i++) {
                visit = new boolean[N+1];
                dfs(i, i, 0); // 현재정점, 출발점, 누적거
            }
            System.out.println("#" + tc + " " + (ans==Integer.MAX_VALUE?-1:ans));
        }
    }

    public static void dfs(int now, int start, int sum) {
        
        if (visit[now] && now == start) { //출발하자 마자는 now == start 이므로 방문 처리 순서를 신경써야함.
            if (sum < ans)
                ans = sum;
            return;
        }
        if (visit[now] || sum>=ans) // 재귀를 호출할 때 방문에 대한 조건을 안걸었었음. 출발점으로 돌아오지 않았지만 이미 방문한 점이면 여기서 끊기.
            return;
        
        visit[now] = true;

        for (Edge next: edgeList[now]) { 
            // 보통 재귀 호출할 때 이미 방문했는지 체크해서 보내지만 우리는 그냥 보내기로 함. 이미 방문했던 출발점도 가야하니까. 
            dfs(next.num, start, sum+next.cost);
        }
    }

    static class Edge {
        int num, cost; //이 간선을 따라가면 있 정점번호, 비용.

        Edge(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
    }
}