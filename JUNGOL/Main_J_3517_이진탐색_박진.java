//Intermediate Coder - 3517 : Tutorial : 이진탐색(Binary Search-이진검색)
 
import java.util.Scanner;
 
public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
 
        int N = sc.nextInt();
        int[] ai = new int[N];
        for (int i = 0; i < N; i++) {
            ai[i] = sc.nextInt();
        }
         
        int low = 0;
        int high = N - 1;
 
        int Q = sc.nextInt();
        int[] bi = new int[Q];
        for (int i = 0; i < Q; i++) {
            bi[i] = sc.nextInt();
            System.out.print(binarySearch(ai, low, high, bi[i]) + " ");
        }
 
        sc.close();
    }
    // 이진탐색 (for문)
    public static int binarySearch(int[] arr, int low, int high, int target) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > target) {
                high = mid - 1;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
