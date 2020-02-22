// Silver IV - 14501 : 퇴사

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Work {
	int start;
	int end;
	int income;
	
	public Work(int start, int end, int income) {
		this.start = start;
		this.end = end;
		this.income = income;
	}
}

public class Main_B_14501_퇴사_박진 {

	static int N;
	static Work[] schedule;
	
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		schedule = new Work[N+1];	// 인덱스 0은 사용x
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			schedule[i] = new Work(i, i+Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()));
//			System.out.println(schedule[i].start + " " + schedule[i].end + " " + schedule[i].income);
		}
		
		// 알고리즘
		for (int i = 1; i <= N; i++) {
			
			dfs(schedule[i], 0);
			
		}
		
		// 출력
		System.out.println(result);
		
	}

	private static void dfs(Work s, int income) {
		if (s.end <= N) {
			income += s.income;
			if (result < income)
				result = income;
		}
		
		for (int i = s.end+1; i <= N; i++) {
			if (schedule[i].end > N)
				continue;
			dfs(schedule[i], income);
		}
	}
	
}
