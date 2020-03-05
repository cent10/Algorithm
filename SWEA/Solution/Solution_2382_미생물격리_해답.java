// 2382 : [모의 SW 역량테스트] 미생물 격리

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author 문제분석 미생물 덩어리들이 자기 방향과 마릿수를 가지고 한시간마다 한칸씩 이동함. M 시간이 지난 후에 남아있는 미생물의 총
 *         마릿수를 출력하시오.
 * 
 *         주의사항> 1시간이 지나면 모든 군집은 무조건 이동을 함. (1) 약품이 처리된 셀로 간 경우 (2) 그렇지 않은 내부
 *         셀에서 이동한 경우 >> 내부 셀에서 이동을 했는데 두개 이상의 군집이 모이는 경우.
 *
 */
public class Solution_2382_미생물격리_해답 {
    static int di[] = { 0, -1, 1, 0, 0 }; // 0:up, 1:down, 2:left, 3:right
    static int dj[] = { 0, 0, 0, -1, 1 };

    static int N;
    static int M;
    static int K;

    static List<Virus> list;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = Integer.parseInt(sc.nextLine());

        for (int tc = 1; tc <= TC; tc++) {
            N = sc.nextInt(); // map size
            M = sc.nextInt(); // time
            K = sc.nextInt(); // virus cnt

            list = new ArrayList();
            for (int k = 0; k < K; k++) {
                int i = sc.nextInt();
                int j = sc.nextInt();
                int cnt = sc.nextInt();
                int dir = sc.nextInt();
                list.add(new Virus(i * N + j, i, j, cnt, dir)); // 이차 배열의 한칸을 하나의 번호로 만드는 작업
            }

            for (int time = 0; time < M; time++) {
                for (int idx = 0; idx < list.size(); idx++) { // 모든 군집 한칸씩 이동해라.
                    Virus virus = list.get(idx);
                    virus.i = virus.i + di[virus.dir];
                    virus.j = virus.j + dj[virus.dir];
                    virus.num = (virus.i * N) + virus.j; // 이동한 칸번호 새로 계산해서 갱신

                    if (virus.i == 0 || virus.j == 0 || virus.i == N - 1 || virus.j == N - 1) {// 약품
                        virus.cnt /= 2;
                        virus.dir = changeDir(virus.dir);
                        if (virus.cnt == 0) {
                            list.remove(idx);
                            idx--;
                        }
                    }
                }

                // 위치 인덱스로 정렬시키고
                Collections.sort(list);
                for (int idx = 0; idx < list.size() - 1; idx++) {
                    Virus now = list.get(idx);
                    Virus next = list.get(idx + 1);
                    if (now.num == next.num) { // 위치가 같은 경우 미생물 수로 내림차순임. 다음놈을 걍 지우면 됨.
                        now.cnt += next.cnt; // next가 미생물 더 적으니까 흡수시키자.
                        list.remove(idx + 1);
                        idx--;
                    }
                }

            }

            int total = 0;
            for (int p = 0; p < list.size(); p++) {
                total += list.get(p).cnt;
            }
            System.out.println("#" + tc + " " + total);

        }
    }

    static int changeDir(int dir) {
        switch (dir) {
        case 1:
            return 2;
        case 2:
            return 1;
        case 3:
            return 4;
        case 4:
            return 3;
        }
        return -1;
    }

    static class Virus implements Comparable<Virus> {
        int num; // 어느 칸에 있는지를 하나의 칸번호로 만들어서 유지하도록.
        int i, j;
        int cnt;
        int dir;

        Virus(int num, int i, int j, int cnt, int dir) {
            this.num = num;
            this.i = i;
            this.j = j;
            this.cnt = cnt;
            this.dir = dir;
        }

        @Override
        public int compareTo(Virus o) {
            if (this.num == o.num) { // 같은 칸에 있는 군집은 마릿수로 내림차순
                return o.cnt - this.cnt;
            }
            return this.num - o.num;
        }

    }
}