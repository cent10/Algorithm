// D4 - 1210 : [S/W 문제해결 기본] 2일차 - Ladder1

import java.util.Scanner;
 
public class Solution {
 
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = 10; // 테스트케이스 수
        int[][] ladderArr = new int[100][100]; // 사다리
        int result = 0; // 결과값
 
        for (int test_case = 1; test_case <= T; test_case++) {
            sc.nextInt(); // 입력받는 테스트케이스 번호 버리기
 
            // 사다리 입력 받기
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    ladderArr[i][j] = sc.nextInt();
                }
            }
 
            for (int j = 0; j < 100; j++) {
                int i = 0;
                int temp_j = j; // 현재 내려가고 있는 사다리의 j값
                if (ladderArr[0][temp_j] == 1) {
                    i++;
                    while (i < 99) // 맨 밑에 도작할 때 까지 반복
                    {
                        if (((temp_j - 1) >= 0) && (ladderArr[i][temp_j - 1] == 1)) // 왼쪽에 이동가능한 통로가 있으면
                        {
                            temp_j--; // 왼쪽으로 한칸 이동
                            while (ladderArr[i + 1][temp_j] != 1) {
                                temp_j--; // 아래로 내려가는 통로를 만날 때 까지 왼쪽으로 이동
                            }
                            i++; // 아래로 한칸 이동
                        } else if (((temp_j + 1) <= 99) && (ladderArr[i][temp_j + 1] == 1)) // 오른쪽에 이동가능한 통로가 있으면
                        {
                            temp_j++; // 오른쪽으로 한칸 이동
                            while (ladderArr[i + 1][temp_j] != 1) {
                                temp_j++; // 아래로 내려가는 통로를 만날 때 까지 오른쪽으로 이동
                            }
                            i++; // 아래로 한칸 이동
                        } else // 옆으로 이동가능한 통로가 없으면
                        {
                            i++; // 아래로 한칸 이동
                        }
                    }
                    if (ladderArr[i][temp_j] == 2) {
                        result = j;
                        break;
                    }
                }
            }
            System.out.println("#" + test_case + " " + result);
        }
 
        sc.close();
    }
 
}
