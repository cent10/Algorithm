// Beginner Coder - 1175 : 주사위 던지기2
 
import java.util.Scanner;
 
public class Main {
 
    // 주사위: {1, 2, 3, 4, 5, 6}
    final static int sizeOfDice = 6;
    static int N;               // 주사위를 던진 횟수 (즉, 뽑을 숫자의 수)
    static int M;               // 눈의 합
    static int[] numbers;       // 주사위를 N번 던졌을 때 나올 수 있는 경우의 수들을 저장할 배열
    static boolean[] selected;  // flag
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        numbers = new int[N];
        selected = new boolean[sizeOfDice+1];   // 인덱스를 0번은 안쓰고 1부터 쓰기 위해서 sizeOfDice에 +1
         
        permutation(0);
         
        sc.close();
    }
     
    private static void permutation(int index) {
        if (index == N) {   // 기저 조건
            int sum = 0;    // 눈들의 합을 저장할 변수
            for (int i = 0; i < N; i++) {
                sum = sum + numbers[i];     // 눈을 합함
            }
            if (sum == M) {     // 눈들의 합이 M과 같으면
                for (int i = 0; i < N; i++) {
                    System.out.print(numbers[i] + " "); // 그 경우의 수를 출력
                }
                System.out.println();
            }
            return;
        }
         
        for (int i = 1; i <= sizeOfDice; i++) {
//          if (selected[i] == true) continue;
             
            numbers[index] = i;
            selected[i] = true;
            permutation(index+1);
            selected[i] = false;
        }
    }
}
