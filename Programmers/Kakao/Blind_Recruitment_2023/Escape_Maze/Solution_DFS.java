package Programmers.Kakao.Blind_Recruitment_2023.Escape_Maze;

import java.util.Stack;

/*
방법
1. 시작 지점에서 dfs 로 탐색 시작
2. 움직일 수 있는 기회보다 현 시점에서 goal 까지의 거리가 멀다면 탐색 x
3. dfs 로 탐색할 때 경로를 일일이 clone 하면 시간 복잡도가 factorial 이 된다.
   -> Path class 선언 후 parent 라는 간선을 이어주는 것으로 경로를 이어주는 것으로 해결
4. K 번 움직이고 goal 에 도착했다면 결과를 return.
5. 사전 순으로 가장 빠른 경로는 chars 의 idx 순서가 사전순으로 함으로써 보장된다.
 */
class Solution_DFS {

    char[] chars = {'d', 'l', 'r', 'u'};
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, -0};

    int goal_x;
    int goal_y;
    boolean[][] map;

    String ans;

    // 움직일 수 있는 횟수내에 goal 에 도달할 수 있는지
    boolean possible(int x, int y,int cnt, int k) {

        int p = k - cnt - (Math.abs(goal_x - x) + Math.abs(goal_y - y));

        // p 가 음수면 goal 을 향해 최단거리로 가도 닿을 수 없다.
        if (p < 0) return true;

            // p 가 홀수라면 goal 에서 한칸 떨어진 곳에 도착한다.
        else if (p % 2 == 1) return true;

            // p 가 양수면서 짝수여야지 goal 에 도착할 수 있다.
        else return false;
    }

    private void dfs(int sx, int sy, int k) {

        Stack<Node> stack = new Stack<>();
        stack.add(new Node(0, sx, sy, new Path()));

        while (!stack.isEmpty()) {

            Node curr = stack.pop();
            int curr_x = curr.x;
            int curr_y = curr.y;
            int curr_cnt = curr.cnt;
            Path curr_path = curr.path;

            // k 번만큼 움직였다면
            if (curr_cnt == k) {

                // 목적지에 도착한 경우
                if (map[curr_x][curr_y]) {

                    StringBuilder sb = new StringBuilder();

                    for (int i = 0; i < k; i++) {
                        sb.append(curr_path.c);
                        curr_path = curr_path.parent;
                    }

                    // 순서를 재대로 하기위해
                    sb.reverse();
                    ans = sb.toString();
                }

                break;
            }


            for (int i = 3; i >= 0; i--) {
                int x = curr_x + dx[i];
                int y = curr_y + dy[i];

                // 남은 횟수로 goal 에 도달할 수 없다면 굳이 탐색할 필요가 없다.
                if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || possible(x, y, curr_cnt + 1, k)) continue;

                stack.add(new Node(curr_cnt + 1, x, y, new Path(chars[i], curr_path)));
            }
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        goal_x = r - 1;
        goal_y = c - 1;

        map = new boolean[n][m];
        map[r - 1][c - 1] = true;

        // 탈출이 불가능한 경우
        if (possible(x - 1, y - 1, 0, k)) return "impossible";

        dfs(x - 1, y - 1, k);

        return ans;
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
