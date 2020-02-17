// D3 - 1873 : 상호의 배틀필드
 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
/*
 
문자  의미
.   평지(전차가 들어갈 수 있다.)
*   벽돌로 만들어진 벽
#   강철로 만들어진 벽
-   물(전차는 들어갈 수 없다.)
^   위쪽을 바라보는 전차(아래는 평지이다.)
v   아래쪽을 바라보는 전차(아래는 평지이다.)
<    왼쪽을 바라보는 전차(아래는 평지이다.)
>    오른쪽을 바라보는 전차(아래는 평지이다.)
 
*/
/*
 
문자  동작
U   Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
D   Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
L   Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
R   Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
S   Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 
*/
 
public class Solution {
 
    static int T;               // 테스트 케이스의 수 T
    static int H, W;            // 게임 맵의 높이가 H, 너비가 W
    static char[][] map;        // 게임 맵 (H x W 크기의 격자판)
    static int N;               // 사용자가 넣을 입력의 개수
    static char[] userInput;    // 사용자가 넣을 입력
     
    static int[] di = { -1, 1, 0, 0 };
    static int[] dj = { 0, 0, -1, 1 };
     
    static int tank_i;      // 전차의 i 좌표
    static int tank_j;      // 전차의 j 좌표
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
         
        for (int tc = 1; tc <= T; tc++) {
            // 게임 맵의 상태 입력
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for (int i = 0; i < H; i++) {
                map[i] = br.readLine().toCharArray();
            }
//          for (int i = 0; i < H; i++) {
//              for (int j = 0; j < W; j++) {
//                  System.out.print(map[i][j]);
//              }
//              System.out.println();
//          }
//          System.out.println();
             
            // 전차의 위치 파악
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
                        tank_i = i;
                        tank_j = j;
                    }
                }
            }
//          System.out.println(tank_i + " , " + tank_j);
             
            // 사용자의 입력 입력
            N = Integer.parseInt(br.readLine());
            userInput = br.readLine().toCharArray();
//          System.out.println(Arrays.toString(userInput));
             
            // 알고리즘
            for (int i = 0; i < N; i++) {
                switch(userInput[i]) {
                case 'U':   // Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다
                    move('U');
                    break;
                     
                case 'D':   // Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
                    move('D');
                    break;
                     
                case 'L':   // Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
                    move('L');
                    break;
                     
                case 'R':   // Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
                    move('R');
                    break;
                     
                case 'S':   // Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
                    shot();
                    break;
                     
                default:
                    break;
                }
            }
             
            // 출력을 위해 StringBuilder에 저장
            sb.append("#").append(tc).append(" ");
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        // 출력
        System.out.println(sb);
    }
     
    // 포탄 발사
    private static void shot() {
        int direction = 0;
        int next_i = tank_i;
        int next_j = tank_j;
         
        switch(map[tank_i][tank_j]) {
        case '^':
            direction = 0;
            break;
        case 'v':
            direction = 1;
            break;
        case '<':
            direction = 2;
            break;
        case '>':
            direction = 3;
            break;
        default:
            break;
        }
         
        while (true) {
            next_i = next_i + di[direction];
            next_j = next_j + dj[direction];
             
            if (next_i < 0 || next_j < 0 || next_i >= H || next_j >= W) { // 포탄이 게임 맵 밖으로 나가면, 맵에는 아무런 변화가 없다.
                return;
            }
             
            if (map[next_i][next_j] == '#') {   // 포탄이 강철로 만들어진 벽에 충돌하면, 맵에는 아무런 변화가 없다.
                return;
            }
             
            if (map[next_i][next_j] == '*') {   // 포탄이 벽돌로 만들어진 벽에 충돌하면,
                map[next_i][next_j] = '.';      // 이 벽은 파괴되어 칸은 평지가 된다.
                return;
            }
        }
    }
 
    // 전차 이동
    private static void move(char userInput) {
        int direction = 0;
        int next_i = 0;
        int next_j = 0;
         
        switch(userInput) {
        case 'U':
            direction = 0;
            next_i = tank_i + di[direction];
            next_j = tank_j + dj[direction];
             
            if (next_i < 0 || next_j < 0 || next_i >= H || next_j >= W) { // 전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 이동하지 않는다.
                map[tank_i][tank_j] = '^';      // 전차는 이동하지 않고 방향만 바꾼다.
                return;
            }
            if (map[next_i][next_j] != '.') {   // 전차가 이동을 하려고 할 때, 만약 평지가 아니라면,
                map[tank_i][tank_j] = '^';      // 전차는 이동하지 않고 방향만 바꾼다.
            }
            else {  // 전차가 이동을 하려고 할 때, 만약 평지라면,
                map[next_i][next_j] = '^';  // 평지로 전차가 이동한다.
                map[tank_i][tank_j] = '.';  // 전차가 있던 자리는 평지로 바꿔준다.
                tank_i = next_i;
                tank_j = next_j;
            }
            break;
             
        case 'D':
            direction = 1;
            next_i = tank_i + di[direction];
            next_j = tank_j + dj[direction];
             
            if (next_i < 0 || next_j < 0 || next_i >= H || next_j >= W) { // 전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 이동하지 않는다.
                map[tank_i][tank_j] = 'v';      // 전차는 이동하지 않고 방향만 바꾼다.
                return;
            }
            if (map[next_i][next_j] != '.') {   // 전차가 이동을 하려고 할 때, 만약 평지가 아니라면,
                map[tank_i][tank_j] = 'v';      // 전차는 이동하지 않고 방향만 바꾼다.
            }
            else {  // 전차가 이동을 하려고 할 때, 만약 평지라면,
                map[next_i][next_j] = 'v';  // 평지로 전차가 이동한다.
                map[tank_i][tank_j] = '.';  // 전차가 있던 자리는 평지로 바꿔준다.
                tank_i = next_i;
                tank_j = next_j;
            }
            break;
             
        case 'L':
            direction = 2;
            next_i = tank_i + di[direction];
            next_j = tank_j + dj[direction];
             
            if (next_i < 0 || next_j < 0 || next_i >= H || next_j >= W) { // 전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 이동하지 않는다.
                map[tank_i][tank_j] = '<';       // 전차는 이동하지 않고 방향만 바꾼다.
                return;
            }
            if (map[next_i][next_j] != '.') {   // 전차가 이동을 하려고 할 때, 만약 평지가 아니라면,
                map[tank_i][tank_j] = '<';       // 전차는 이동하지 않고 방향만 바꾼다.
            }
            else {  // 전차가 이동을 하려고 할 때, 만약 평지라면,
                map[next_i][next_j] = '<';   // 평지로 전차가 이동한다.
                map[tank_i][tank_j] = '.';  // 전차가 있던 자리는 평지로 바꿔준다.
                tank_i = next_i;
                tank_j = next_j;
            }
            break;
             
        case 'R':
            direction = 3;
            next_i = tank_i + di[direction];
            next_j = tank_j + dj[direction];
             
            if (next_i < 0 || next_j < 0 || next_i >= H || next_j >= W) { // 전차가 이동을 하려고 할 때, 만약 게임 맵 밖이라면 전차는 이동하지 않는다.
                map[tank_i][tank_j] = '>';       // 전차는 이동하지 않고 방향만 바꾼다.
                return;
            }
            if (map[next_i][next_j] != '.') {   // 전차가 이동을 하려고 할 때, 만약 평지가 아니라면,
                map[tank_i][tank_j] = '>';       // 전차는 이동하지 않고 방향만 바꾼다.
            }
            else {  // 전차가 이동을 하려고 할 때, 만약 평지라면,
                map[next_i][next_j] = '>';   // 평지로 전차가 이동한다.
                map[tank_i][tank_j] = '.';  // 전차가 있던 자리는 평지로 바꿔준다.
                tank_i = next_i;
                tank_j = next_j;
            }
            break;
             
        default:
            break;
        }
    }
}
