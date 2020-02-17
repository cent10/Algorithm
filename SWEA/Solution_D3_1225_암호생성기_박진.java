// D4 - 1225 : [S/W 문제해결 기본] 7일차 - 암호생성기
 
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
 
public class Solution {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
         
        for (int i = 1; i <= 10; i++) {
            int minus = 1;  // 감소할 숫자
            StringBuilder sb = new StringBuilder();
            sc.nextInt();
            Queue<Integer> queue = new LinkedList<Integer>();
            for (int j = 0; j < 8; j++) {
                queue.offer(sc.nextInt());
            }
//          System.out.println(queue.toString());
             
            while (true) {
                if (queue.peek() - minus > 0) {
                    queue.offer(queue.poll() - minus);
                    if (minus == 5)
                        minus = 1;
                    else
                        minus++;
                }
                else {
                    queue.poll();
                    queue.offer(0);
                    break;
                }
            }
             
            sb.append("#");
            sb.append(i);
            sb.append(" ");
            for (int j = 0; j < 8; j++) {
                sb.append(queue.poll());
                sb.append(" ");
            }
            System.out.println(sb);
        }
         
        sc.close();
    }
 
}
