// Silver III - 6987 : 월드컵

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_B_6987_월드컵_해답 {
    static StringBuilder sb = new StringBuilder();
    static int[][] gameResult = null;

    static int valid;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gameResult = new int[4][18];
        StringTokenizer tokens;
        for (int r = 0; r < 4; r++) {
            tokens = new StringTokenizer(br.readLine());
            for (int c = 0; c < 18; c++) {
                gameResult[r][c] = Integer.parseInt(tokens.nextToken());
            }
            valid = 0;
            dfs(0, 1, gameResult[r]);
            sb.append(valid).append(" ");
        }

        System.out.println(sb);
    }


    static void dfs(int teamA, int teamB, int[] score) {
        // 상대팀이 6을 넘어가면 우리 팀이 변경되어야 한다.
        if (teamB == 6) {
            dfs(teamA + 1, teamA + 2, score);
            return;
        }
        // System.out.println("next team: " + (char) (teamA + 'A') + "," + teamA + " : " + (char) (teamB + 'A') + "," + teamB);
        if (teamA > 4) {
            // 솔루션 실행 - 하나라도 0이 아닌게 있으면 0 아니면 1
            for (int c = 0; c < score.length; c++) {
                if (score[c] > 0) {
                    valid = 0;
                    return;
                }
            }
            valid = 1;
            return;
        }

        // 각 팀별로 이기고, 비기고 질 때 체크 i=0, j=2 --> teamB 승, i=1, j=1 --> 비김, i=2, j=0 --> teamA 승
        for (int i = 0, j = 2; i < 3; i++, j--) {
            if (score[teamA * 3 + i] > 0 && score[teamB * 3 + j] > 0) {
                score[teamA * 3 + i]--;
                score[teamB * 3 + j]--;
                dfs(teamA, teamB + 1, score);
                score[teamA * 3 + i]++;
                score[teamB * 3 + j]++;
            }
        }
        /*
        // teamA가 이기고 teamB가 지는 경우
        if (temp[teamA * 3 + 0] > 0 && temp[teamB * 3 + 2] > 0) {
            temp[teamA * 3 + 0]--;
            temp[teamB * 3 + 2]--;
            dfs(teamA, teamB + 1, temp);
            temp[teamA * 3 + 0]++;
            temp[teamB * 3 + 2]++;
        }
        
        // teamA와 teamB가 비기는 경우
        if (temp[teamA * 3 + 1] > 0 && temp[teamB * 3 + 1] > 0) {
            temp[teamA * 3 + 1]--;
            temp[teamB * 3 + 1]--;
            dfs(teamA, teamB + 1, temp);
            temp[teamA * 3 + 1]++;
            temp[teamB * 3 + 1]++;
        }
        
        if (temp[teamA * 3 + 2] > 0 && temp[teamB * 3 + 0] > 0) {
            // teamA가 지고 teamB가 이기는 경우
            temp[teamA * 3 + 2]--;
            temp[teamB * 3 + 0]--;
            dfs(teamA, teamB + 1, temp);
            temp[teamA * 3 + 2]++;
            temp[teamB * 3 + 0]++;
        }*/
    }
}