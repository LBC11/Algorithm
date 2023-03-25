package Programmers.Kakao.Blind_Recruitment_2023.Escape_Maze;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
방법
1. 시작 지점에서 bfs 로 탐색 시작
2. 움직일 수 있는 기회보다 현 시점에서 goal 까지의 거리가 멀다면 탐색 x
3. bfs 로 탐색할 때 경로를 일일이 clone 하면 시간 복잡도가 factorial 이 된다.
   -> Path class 선언 후 parent 라는 간선을 이어주는 것으로 경로를 이어주는 것으로 해결
4. K 번 움직이고 goal 에 도착했다면 priority queue 에 넣는다.
5. 최종적으로 pq 에서 poll 하는 것으로 가장 우선순위가 높은 경로 return

실패 분석
1. 경로 탐색의 경우 자동적으로 bfs 을 써야 한다는 고정 관념
2. 이 문제의 경우 다른 경로 탐색 문제와 다르게 장애물이 없고
   방문한 장소를 다시 방문할 수 있기에 굳이 bfs 의 방식처럼
   하나하나 방문하면서 경로를 탐색할 필요가 없다.

-> 결론: 이 방식으로는 시간 초과 발생
 */
class Solution_DFS {

    char[] chars = {'u', 'd', 'r', 'l'};
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    int goal_x;
    int goal_y;
    boolean[][] map;

    PriorityQueue<String> pq;

    // 움직일 수 있는 횟수내에 goal 에 도달할 수 있는지
    boolean possible(int x, int y, int cnt, int k) {
        return Math.abs(goal_x - x) + Math.abs(goal_y - y) <= k - cnt;
    }

    private void bfs(int sx, int sy, int k) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, sx, sy, new Path()));

        while (!queue.isEmpty()) {

            Node curr = queue.poll();
            int curr_x = curr.x;
            int curr_y = curr.y;
            int curr_cnt = curr.cnt;
            Path curr_path = curr.path;

            // k 번만큼 움직였다면
            if (curr_cnt == k) {

                // 목적지에 도착한 경우
                if (map[curr_x][curr_y]) {

                    // add the route to pq
                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < k; i++) {
                        sb.append(curr_path.c);
                        curr_path = curr_path.parent;
                    }

                    // 순서를 재대로 하기위해
                    sb.reverse();
                    pq.add(sb.toString());
                }

                continue;
            }

            // 남은 횟수로 goal 에 도달할 수 없다면 굳이 탐색할 필요가 없다.
            if (!possible(curr_x, curr_y, curr_cnt, k)) continue;

            for (int i = 0; i < 4; i++) {
                int x = curr_x + dx[i];
                int y = curr_y + dy[i];

                if (x < 0 || y < 0 || x >= map.length || y >= map[0].length) continue;

                queue.add(new Node(curr_cnt + 1, x, y, new Path(chars[i], curr_path)));
            }
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        pq = new PriorityQueue<>((o1, o2) -> {
            for (int i = 0; i < o1.length(); i++) {
                if (o1.charAt(i) != o2.charAt(i)) return o1.charAt(i) - o2.charAt(i);
            }

            return 0;
        });

        goal_x = r - 1;
        goal_y = c - 1;

        map = new boolean[n][m];
        map[r - 1][c - 1] = true;

        bfs(x - 1, y - 1, k);

        // 탈출이 불가능한 경우
        if (pq.isEmpty()) return "impossible";

        return pq.peek();
    }
}

class Node {

    int cnt;
    int x;
    int y;
    Path path;

    public Node(int cnt, int x, int y, Path path) {
        this.cnt = cnt;
        this.x = x;
        this.y = y;
        this.path = path;
    }
}

class Path {

    char c;
    Path parent;

    public Path() {
    }

    public Path(char c, Path parent) {
        this.c = c;
        this.parent = parent;
    }
}
