// D4 - 1861 : 정사각형 방
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Solution {
 
    static int T;           // 테스트 케이스의 수 T
    static int N;           // 정수 N
    static int[][] room;    // N2개의 방 (N×N형태)
     
    // 상 하 좌 우
    static int[] di = { -1, 1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };
     
    static int startRoomNum = 0;    // 처음에 출발해야하는 방 번호
    static int cntRoom = 0; // 몇 개의 방을 이동할 수 있는지
    static int maxRoom = 0; // 최대 몇 개의 방을 이동할 수 있는지
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
             
            // 입력
            N = Integer.parseInt(br.readLine());
            room = new int[N][N];
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    room[i][j] = Integer.parseInt(st.nextToken());
//                  System.out.print(room[i][j] + " ");
                }
//              System.out.println();
            }
             
            // 알고리즘
            startRoomNum = 0;
            findRoom();
             
            sb.append("#").append(tc).append(" ").append(startRoomNum).append(" ").append(maxRoom).append("\n");
        }
        System.out.println(sb);
    }
     
    private static void findRoom() {
        maxRoom = 0;
         
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int start_i = i;
                int start_j = j;
                cntRoom = 1;
                int current_i = i;
                int current_j = j;
            L:  while (true) {
                    int k = 0;
                    for (k = 0; k < 4; k++) {
                        int next_i = current_i + di[k];
                        int next_j = current_j + dj[k];
 
                        if (next_i < 0 || next_j < 0 || next_i >= N || next_j >= N)
                            continue;
                        else {
                            if (room[next_i][next_j] == (room[current_i][current_j] + 1)) {
                                current_i = next_i;
                                current_j = next_j;
                                cntRoom++;
                                continue L;
                            }
                        }
                    }
                    if (maxRoom < cntRoom) {
                        maxRoom = cntRoom;
                        startRoomNum = room[start_i][start_j];
                    } else if (maxRoom == cntRoom) {
                        if (startRoomNum > room[start_i][start_j]) {
                            startRoomNum = room[start_i][start_j];
                        }
                    }
                    break;
                }
             
            }
        }
    }
}
