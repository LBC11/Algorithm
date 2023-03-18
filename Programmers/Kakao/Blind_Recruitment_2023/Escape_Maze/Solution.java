package Programmers.Kakao.Blind_Recruitment_2023.Escape_Maze;

import java.util.*;

class Solution {

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
