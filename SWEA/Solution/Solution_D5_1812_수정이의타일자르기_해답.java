// D5 - 1812 : 수정이의 타일 자르기

package com.ssafy.step06.priorityqueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_D5_1812_수정이의타일자르기_해답 {

	static class Rectangle implements Comparable<Rectangle> {
		int min, max;

		public Rectangle(int width, int height) {
			if (width < height) {
				min = width;
				max = height;
			} else {
				min = height;
				max = width;
			}
		}

		@Override
		public int compareTo(Rectangle o) {
			return o.min - this.min;
		}
	}

	static int N, M, size[], cnt;
	static PriorityQueue<Rectangle> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			size = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				size[i] = Integer.parseInt(st.nextToken());
			} // 만들고자하는 정사각형의 크기 (2^k) 입력 (k)

			cnt = 0;
			cut();
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}

	private static void cut() {
		// 가장 큰 크기의 정사각형부터 만들기
		Arrays.sort(size);
		queue = new PriorityQueue<Rectangle>();
		queue.offer(new Rectangle(M, M));
		cnt++;

		for (int i = N - 1; i >= 0; i--) {
			go(1 << size[i]); // 1 << 2 = 2^2 = 4
		}
	}

	private static void go(int size) {
		// queue에서 poll() : min값이 최대인 직사각형이 뽑아짐.
		Rectangle r = queue.poll();
		if (r.min >= size) {	// 직사각형의 최소변의 길이가 size보다 같거나 크면 : 원하는 크기의 정사각형을 만들 수 있음
			queue.offer(new Rectangle(r.min - size, size));
			queue.offer(new Rectangle(r.min, r.max - size));
		} else {	// 직사각형의 최소변의 길이가 size보다 작으면: 원하는 크기의 정사각형을 만들 수 없음
			queue.offer(r);	// 사용하지 못한 타일은 다시 넣어줌.
			queue.offer(new Rectangle(M - size, size));
			queue.offer(new Rectangle(M, M - size));
			cnt++;
		}
	}

}
