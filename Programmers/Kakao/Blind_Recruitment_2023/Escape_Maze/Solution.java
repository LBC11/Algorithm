package Programmers.Kakao.Blind_Recruitment_2023.Escape_Maze;

import java.util.HashMap;

class Solution {

    // 순서는 사전순으로 배정
    char[] chars = {'d', 'l', 'r', 'u'};
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};

    int goal_x;
    int goal_y;

    // 움직일 수 있는 횟수내에 goal 에 도달할 수 있는지
    boolean possible(int x, int y, int k) {

        int p = k - (Math.abs(goal_x - x) + Math.abs(goal_y - y));

        // p 가 음수면 goal 을 향해 최단거리로 가도 닿을 수 없다.
        if (p < 0) return false;

        // p 가 홀수라면 goal 에서 한칸 떨어진 곳에 도착한다.
        else if (p % 2 == 1) return false;

        // p 가 양수면서 짝수여야지 goal 에 도착할 수 있다.
        else return true;
    }

    String search(int x, int y, int k, int n, int m) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // 이동할 수 있는 남은 횟수
        int left = k;

        // goal_x 가 x보다 크다면
        if (goal_x >= x) {

            // 0 은 위의 chars 에서 down 를 뜻하는 d의 idx
            map.put(0, map.getOrDefault(0, 0) + goal_x - x);
        } else {
            // 3 은 위의 chars 에서 up 를 뜻하는 u의 idx
            map.put(3, map.getOrDefault(3, 0) + x - goal_x);
        }

        left -= Math.abs(goal_x - x);

        // goal_x 가 x보다 크다면
        if (goal_y >= y) {

            // 2 은 위의 chars 에서 right 를 뜻하는 r의 idx
            map.put(2, map.getOrDefault(2, 0) + goal_y - y);
        } else {
            // 1 은 위의 chars 에서 left 를 뜻하는 l의 idx
            map.put(1, map.getOrDefault(1, 0) + y - goal_y);
        }

        left -= Math.abs(goal_y - y);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {

            if (map.containsKey(i)) {
                for (int j = 0; j < map.get(i); j++) {
                    sb.append(chars[i]);
                }
            }
        }

        // 나머지를 어떤 방
        int[] temp = new int[2];

        if (goal_x < n) {
            temp[0] = 0;
            temp[1] = 3;
        } else if (goal_y > 0) {
            temp[0] = 1;
            temp[1] = 2;
        } else {
            temp[0] = 2;
            temp[1] = 1;
        }

        while (left > 0) {

            int move_x = goal_x + dx[temp[0]];
            int move_y = goal_y + dy[temp[0]];

            int cnt = 0;

            while (cnt * 2 < left && move_x >= 0 && move_x <= n && move_y >= 0 && move_y <= m) {
                sb.append(chars[temp[0]]);

                move_x += dx[temp[0]];
                move_y += dy[temp[0]];
                cnt++;
            }

            System.out.println(left);
            System.out.println(cnt);

            sb.append(new String(new char[cnt]).replace('\u0000', chars[temp[1]]));

            left -= 2 * cnt;
        }

        return sb.toString();
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        goal_x = r - 1;
        goal_y = c - 1;

        // 탈출이 불가능한 경우
        if (!possible(x - 1, y - 1, k)) return "impossible";

        return search(x - 1, y - 1, k, n - 1, m - 1);
    }
}
