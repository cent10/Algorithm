// Beginner Coder - 1490 : 다음 조합(next combination)
 
import java.util.Scanner;
 
public class Main {
 
    static int N, K;                // N개의 정수 중에서 K개를 뽑아냄
    static int[] combination;       // 가능한 조합을 저장할 배열
    static int[] combinationInput;  // 주어진 조합
    static boolean[] seleceted;
    static boolean isEqual_flag;    // 가능한 조합과 주어진 조합이 일치하는지 확인하는 변수
    static boolean endMethod_flag;  // 재귀함수를 종료할지말지 결정할 변수
     
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        combination = new int[K];
        seleceted = new boolean[N+1];   // 인덱스 0은 쓰지 않음
        combinationInput = new int[K];
        for (int i = 0; i < K; i++) {
            combinationInput[i] = sc.nextInt();
        }
         
        getCombination(0);
         
        if(endMethod_flag == false) // 만약 가능한 다음 조합이 없을 경우 'NONE'을 출력
            System.out.print("NONE");
         
        sc.close();
    }
     
    private static void getCombination(int index) {
        if (endMethod_flag == true) // 다음으로 나오는 조합을 출력했으므로 재귀 함수를 종료
            return;
         
        if (index == K) {   // 기저조건
            if (isEqual_flag == true) {
                for (int i = 0; i < K; i++) {
                    System.out.print(combination[i] + " "); // 다음으로 나오는 조합 출력
                    endMethod_flag = true;  // 다음으로 나오는 조합을 출력했으므로 재귀 함수를 종료시키기 위해 endMethod_flag를 true로 변경
                }
                return;
            }
             
            for (int i = 0; i < K; i++) {
                if (combination[i] != combinationInput[i])  // 가능한 조합이 주어진 조합과 일치하지 않으면 isEqual_flag가 true가 되지 않도록 리턴시킴
                    return;
            }
             
            isEqual_flag = true;    // 가능한 조합이 주어진 조합과 일치하면 isEqual_flag를 true로 변경
            return;
        }
         
        for (int i = 1; i <= N; i++) {
            if (seleceted[i] == true)
                continue;
 
            if (index > 0) {
                if (combination[index - 1] < i) {
                    combination[index] = i;
                    seleceted[i] = true;
                    getCombination(index + 1);
                    seleceted[i] = false;
                }
            } else {
                combination[index] = i;
                seleceted[i] = true;
                getCombination(index + 1);
                seleceted[i] = false;
            }
        }
    }
}
