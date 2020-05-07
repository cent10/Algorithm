import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_B_2146_다리만들기_해답 {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    static int N, A, islandIdx;
    static int[][] map;
    static boolean[][] visited;

    static List<Point> points; // 육지들

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());// N(100이하의 자연수)
        map = new int[N][N];
        visited = new boolean[N][N];

        StringTokenizer tokens = null;
        for (int r = 0; r < N; r++) {
            tokens = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

        points = new LinkedList<>();
        islandIdx = 2;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] == 1) {
                    bfsMarkIslandNum(r, c);
                    islandIdx++;
                }
            }
        }

        /*
        System.out.println("섬 정보 확인");
        for (int[] row : map) {
            System.out.println(Arrays.toString(row));
        }*/

        // 각 섬에서 다른 섬까지 최단 거리 찾아보기.
        A = Integer.MAX_VALUE;
        for (Point p : points) {
            bfsGetShortBridgeLength(p);
        }
        System.out.println(A);
    }

    static void bfsGetShortBridgeLength(Point p) {
        Queue<Point> q = new LinkedList<>();
        q.offer(p);
        // visited 배열 초기화
        for (boolean[] row : visited) {
            Arrays.fill(row, false);
        }

        while (!q.isEmpty()) {
            Point front = q.poll();
            if (front.d >= A) {
                break;
            }

            for (int d = 0; d < dirs.length; d++) {
                int nr = front.r + dirs[d][0];
                int nc = front.c + dirs[d][1];

                if (isIn(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (map[nr][nc] == front.idx) {
                        continue;
                    } else if (map[nr][nc] == 0) {
                        q.offer(new Point(nr, nc, front.d + 1, front.idx));
                    } else if (map[nr][nc] != front.idx) {
                        A = Math.min(A, front.d);
                        return;
                    }
                }
            }
        }
    }

    static void bfsMarkIslandNum(int r, int c) {
        Queue<Point> q = new LinkedList<>();
        Point p = new Point(r, c, 0, islandIdx);
        q.offer(p);
        map[r][c] = islandIdx;

        points.add(p);

        while (!q.isEmpty()) {
            Point front = q.poll();

            for (int d = 0; d < dirs.length; d++) {
                int nr = front.r + dirs[d][0];
                int nc = front.c + dirs[d][1];

                if (isIn(nr, nc)) {
                    if (map[nr][nc] == 1) {
                        map[nr][nc] = islandIdx;
                        p = new Point(nr, nc, 0, islandIdx);
                        q.offer(p);
                        points.add(p);
                    }
                }
            }
        }
    }

    static boolean isIn(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }

    static class Point {
        int r, c;
        int d;// 다리를 연결할 경우 다리의 길이
        int idx;// 섬의 index

        public Point(int r, int c, int d, int idx) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.idx = idx;
        }
    }

}