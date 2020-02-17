// Beginner Coder - 1719 : 별삼각형2
 
import java.util.Scanner;
 
public class Main {
     
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, m;   // n:높이, m:종류
        n = sc.nextInt();
        m = sc.nextInt();
         
        if ((n%2)==0)
        {
            System.out.println("INPUT ERROR!");
            sc.close();
            return;
        }
         
        switch(m)
        {
            case 1 :
                for (int i = 0; i < n/2; i++)
                {
                    for (int j = 0; j < i + 1; j++)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                for (int i = (n/2); i < n; i++)
                {
                    for (int j = 0; j < n - i; j++)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                break;
            case 2 :
                for (int i = 0; i < n/2; i++)
                {
                    for (int j = 0; j < n/2 - i; j++)
                    {
                        printSpace();
                    }
                    for (int j = 0; j < i + 1; j++)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                for (int i = (n/2); i < n; i++)
                {
                    for (int j = 0; j < i - (n/2); j++) //미완
                    {
                        printSpace();
                    }
                    for (int j = n - i; j > 0; j--)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                 
                break;
            case 3 :
                for (int i = 0; i < n/2; i++)
                {
                    for (int j = 0; j < i; j++)
                    {
                        printSpace();
                    }
                    for (int j = i; j < n - i; j++)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                for (int i = (n/2); i < n; i++)
                {
                    for (int j = i - n/2; j < n/2; j++)
                    {
                        printSpace();
                    }
                    for (int j = (n/2) - i; j < i - n/2 + 1; j++)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                break;
            case 4 :
                for (int i = 0; i < n/2; i++)
                {
                    for (int j = 0; j < i; j++)
                    {
                        printSpace();
                    }
                    for (int j = n/2 - i; j >= 0; j--)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                for (int i = (n/2); i < n; i++)
                {
                    for (int j = 0; j < n/2; j++)
                    {
                        printSpace();
                    }
                    for (int j = 0; j < i - n/2 + 1; j++)
                    {
                        printStar();
                    }
                    System.out.println();
                }
                break;
            default :
                System.out.println("INPUT ERROR!");
                break;
        }
         
        sc.close();
    }
     
    private static void printStar()
    {
        System.out.printf("*");
    }
     
    private static void printSpace()
    {
        System.out.print(" ");
    }
}
