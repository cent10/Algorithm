//Intermediate Coder - 1328 : 빌딩
 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
 
public class Main {
 
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st1 = new StringTokenizer(br.readLine(), " "); //StringTokenizer인자값에 입력 문자열 넣음
        int N = Integer.parseInt(st1.nextToken());          // 빌딩의 개수
        int[] buildingArr = new int[N]; // 빌딩의 높이를 저장할 배열
        boolean buildingCheck;          // 보이는 빌딩이 있는지 없는지 체크하는 변수 (true: 보이는 빌딩 있음, false: 보이는 빌딩 없음)
        for (int i = 0; i < N; i++)
        {
            StringTokenizer st2 = new StringTokenizer(br.readLine(), " "); //StringTokenizer인자값에 입력 문자열 넣음
            buildingArr[i] = Integer.parseInt(st2.nextToken());
//          System.out.println("buildArr[" + i + "] = " + buildingArr[i]);
        }
         
        for (int i = 0; i < N - 1; i++)
        {
            buildingCheck = false;
            for (int j = i + 1; j < N; j++)  // i번 빌딩에서 보이는 가장 가까운 빌딩의 번호를 출력
            {
                if (buildingArr[i] < buildingArr[j])
                {
                    bw.write( (j+1) + "\n" );
                    buildingCheck = true;
                    break;
                }
            }
            if (buildingCheck==false)   // 만약에 보이는 빌딩이 없을 경우에는 0을 출력
            {
                bw.write("0\n");
            }
        }
        bw.write("0");  // 마지막 빌딩은 보이는 빌딩이 없으므로 0을 출력
         
        bw.flush(); //남아있는 데이터를 모두 출력시킴
        bw.close(); //스트림을 닫음
    }
}
