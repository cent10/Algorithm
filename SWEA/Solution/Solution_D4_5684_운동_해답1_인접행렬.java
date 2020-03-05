// D4 - 5684 : [Professional] 운동

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
public class Solution_D4_5684_운동_해답1_인접행렬 {
    static int[][] graph; // 방향성 있는 가중치 그래프를 인접 행렬을 써보자.
    static int ans;
    static boolean[] visit;
    static int N, M; // 정점 갯수, 간선 갯수
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();
        
        for(int tc=1; tc<=TC; tc++) {
            ans = Integer.MAX_VALUE; 
            N = sc.nextInt();
            M = sc.nextInt();
            
            graph = new int[N+1][N+1]; // 1~N까지 정점번호 그대로 쓸 예정
            for(int m=0; m<M; m++) {
            	int s = sc.nextInt(); // start 정점
                int e = sc.nextInt(); // end 정점
                int c = sc.nextInt(); // cost
                
                graph[s][e] = c;
            }
            
            for(int now=1; now<=N; now++) {
                visit = new boolean[N+1]; // 출발할때 이전의 방문 정보는 초기화 해버리자!
                dfs(now, now, 0); // now에서 출발!
            }
            
            System.out.println("#"+tc+" "+(ans==Integer.MAX_VALUE?-1:ans));
        }
        
    }
    
    static void dfs(int now, int start, int dist) { // 현재 서있는 정점, 출발지 정점 어디였는지
        if(now==start && visit[now]) { // 출발점으로 돌아왔네 ?! 사이클이당 ~~ 
            if(dist < ans)
                ans = dist;
            return;
        }
        
        if(visit[now]){ // 출발점에 대한 재방문이면 위의 조건문이 걸렸을 거지만, 출발이 아닌 정점의 재방문은 지금 나한테는 관심없는 사이클 더 진행하지 말아라 !
            return;
        }
        
        if(dist >= ans) // 최소 사이클을 찾고 싶은건데 이미 이전에 찾은 사이클보다 넘 많이 걸었어.. 이 길은 안가봐도 되겠다~
            return;
        
        visit[now] = true;
        for(int i=1; i<=N; i++) {
            if(graph[now][i]>0) { // 현재 정점에서 이동가능한 다음 next 정점 찾는 작업.
                dfs(i, start, dist+graph[now][i]);
            }
        }
    }
}