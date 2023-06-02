package Softeer.Jiwoo_Like_Prime_Number;

import java.util.*;
import java.io.*;

/*
문제
지우는 현재 포켓몬 트레이너이다 그는 여러 체육관을 거쳐 체육관 배지를 얻은 후 마지막 포켓몬 리그에서 사천왕과 챔피언에게 도전해야 하는 임무가 주어져 있다.
각각의 체육관에는 체육관 관장들이 있고 그 관장들을 이겨야 체육관 배지를 얻을 수 있는 시스템이다.  그 관장들을 이기기 위해선 그 관장들이 갖고 있는
레벨(level)보다 높아야 한다. 하지만 너무 자주 찾아오는 지원자들 때문에 지친 체육관 관장들은 체육관 오는 길에 레벨 제한을 두었다. ‘X레벨 이하 지원자는 오지 마시오.’
지우는 포켓몬 리그를 나가기 위해 자신의 레벨을 올리면서 수련하고 있다. 수련을 하는 어느 날 지우는 자신이 포켓몬 리그에 나가기 위한 최소한의 레벨이 알고 싶어졌다.
하지만 지우는 소수(Prime Number)를 정말 좋아하기에 자신의 레벨도 소수에 맞춰서 포켓몬 리그에 참여하고 싶어 한다.
이러한 지우의 조건을 맞추어 지우에게 각각의 체육관을 넘어 마지막 장소인 포켓몬 리그에 참여할 수 있는 최소한의 레벨을 알려주는 프로그램을 작성해보자.

아이디어
1. 모든 path 의 가중치를 기준으로 정렬
2. kruskal algorithm 을 사용해서 마지막 node 의 parent 가 1이 될 때까지 진행
3. 그동안 연결한 path 의 가중치 중 최대값이 최소한의 레벨
4. 해당 레벨보다 커야 하므로 +1 한 후 해당 숫자가 소수인지 검증
5. 검증 과정은 Math.sqrt(n) 까지만 검사하면 된다.
+ Dijkstra algorithm 으로도 풀 수 있다.

실패 분석
1. n 보다 크거나 같아도 되는 줄 알고 한참을 헤멨다... 문제를 잘 읽자!!!
 */

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] parents;
    static PriorityQueue<Path> pq;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Path(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int max_cost = 0;
        while (find(n) != 1 && !pq.isEmpty()) {

            Path current = pq.poll();
            if (union(current.start, current.end)) {

                max_cost = Math.max(max_cost, current.cost);
            }
        }

        System.out.println(nextPrime(max_cost));
    }

    static int nextPrime(int n) {

        if(n < 2) return 2;

        while (true) {

            n++;

            boolean flag = true;

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) break;
        }

        return n;
    }

    static int find(int idx) {

        if (parents[idx] == idx) return idx;

        else return find(parents[idx]);
    }

    static boolean union(int idx1, int idx2) {

        int p1 = find(idx1);
        int p2 = find(idx2);

        if (p1 != p2) {

            if (p1 < p2) parents[p2] = p1;
            else parents[p1] = p2;

            return true;
        }

        return false;
    }
}

class Path {

    int start;
    int end;
    int cost;

    public Path(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }
}
