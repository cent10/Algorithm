// Intermediate Coder - 3334 : 회문

/*
Accepted(87)
*/
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main_J_3334_회문_박진_ING {
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " ");
 
        int T = Integer.parseInt(st1.nextToken()); // 문자열의 개수
        String[] strArr = new String[T];
 
        for (int i = 0; i < T; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " ");
            strArr[i] = st2.nextToken();
 
            if (isPalindrome(strArr[i])) {
                System.out.println("0");
            } else if (isPseudoPalindrome(strArr[i])) {
                System.out.println("1");
            } else {
                System.out.println("2");
            }
        }
    }
 
    // 회문(palindrome)인지 아닌지 확인하는 메소드
    public static boolean isPalindrome(String str) {
        int lengthOfString = str.length();
        int startIndex = 0;
        int endIndex = lengthOfString - 1;
 
        // 문자를 하나는 맨 앞부터, 다른 하나는 맨 뒤부터 뽑아 두 개의 문자를 비교
        while (startIndex != endIndex) {
            if (startIndex > endIndex || startIndex == endIndex)
                break;
 
            if (str.charAt(startIndex) != str.charAt(endIndex)) {
                return false; // 하나라도 다르면 false를 리턴
            }
 
            startIndex++;
            endIndex--;
        }
        return true; // 다른 문자가 하나도 없으면 true 리턴
    }
 
    // 유사회문(pseudo palindrome)인지 아닌지 확인하는 메소드
    public static boolean isPseudoPalindrome(String str) {
        int lengthOfString = str.length();
        int startIndex = 0;
        int endIndex = lengthOfString - 1;
        boolean flag = false;
 
        while (true) {
            if (startIndex >= endIndex)
                break;
 
            if (str.charAt(startIndex) != str.charAt(endIndex)) {
                if (flag == true)
                    return false;
                 
                flag = true;
 
                if ( (startIndex < (endIndex-1)) && (str.charAt(startIndex) == str.charAt(endIndex - 1)) ) {
                    endIndex--;
                    continue;
                }
                else if ( ((startIndex+1) < endIndex) && (str.charAt(startIndex + 1) == str.charAt(endIndex)) ) {
                    startIndex++;
                    continue;
                }
                else
                    return false;
            }
             
            startIndex++;
            endIndex--;
        }
 
        return true;
    }
}
