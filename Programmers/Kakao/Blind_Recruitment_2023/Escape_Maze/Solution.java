package Programmers.Kakao.Blind_Recruitment_2023.Escape_Maze;

import java.util.HashMap;

/*

실패 분석
1. 항상 목적지에 도착한 후 남은 k 를 사용하는 경우는 사전적으로 가장 앞선 route 라는 것을 보장하지 못한다.
2. 우선순위가 가장 낮은 u 를 가장 마지막에 해야 하는 데 d 와 묶어서 생각하는 바람에 헤맸다.
3. 우선순위가 낮은 애들은 최대한 뒤에 배치해야 하는데 이것을 간과했다.

주요 아이디어
1. 목적지에 도착하기까지의 거리보다 k 가 작거나 둘을 뺀 값이 홀수라면 impossible
2. 가능한 모든 route 중 가장 아래면서 왼쪽인 지점을 거치는 것이 정답이다.
3. 2를 달성하기 위해 d 와 l 방향으로 최대한 움직인 후 남은 횟수는 rl 을 반복하면서 소모한다.
 */
class Solution {

    // 순서는 사전순으로 배정
    char[] chars = {'d', 'l', 'r', 'u'};

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

    String search(int n, int m, int x, int y, int k) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<4; i++) {
            map.put(i, 0);
        }

        // 이동할 수 있는 남은 횟수
        int left = k;

        // goal_x 가 x보다 크다면
        if (goal_x >= x) {

            // 0 은 위의 chars 에서 down 를 뜻하는 d의 idx
            map.put(0, map.get(0) + goal_x - x);
        } else {
            // 3 은 위의 chars 에서 up 를 뜻하는 u의 idx
            map.put(3, map.get(3) + x - goal_x);
        }

        left -= Math.abs(goal_x - x);

        // goal_x 가 x보다 크다면
        if (goal_y >= y) {

            // 2 은 위의 chars 에서 right 를 뜻하는 r의 idx
            map.put(2, map.get(2) + goal_y - y);
        } else {
            // 1 은 위의 chars 에서 left 를 뜻하는 l의 idx
            map.put(1, map.get(1) + y - goal_y);
        }

        left -= Math.abs(goal_y - y);

        StringBuilder sb = new StringBuilder();

        // 가능한 가장 아래쪽으로 이동
        int left_d = Math.min(left / 2, n - (x + map.get(0)));
        map.put(0, map.get(0) + left_d);

        // d 로 움직인 만큼 나중에 u 로도 움직여야 한다.
        map.put(3, map.get(3) + left_d);

        // d 로 이동 실행
        sb.append(new String(new char[map.get(0)]).replace('\u0000', chars[0]));

        // 움직일 수 있는 횟수 차감
        left -= (left_d * 2);

        // 가능한 가장 왼쪽으로 이동
        int left_l = Math.min(left / 2, y - map.get(1));
        map.put(1, map.get(1) + left_l);

        // l 로 움직인 만큼 나중에 r 로도 움직여야 한다.
        map.put(2, map.get(2) + left_l);

        sb.append(new String(new char[map.get(1)]).replace('\u0000', chars[1]));

        left -= (left_l * 2);

        // 그래도 남은 움직일 수 있는 횟수는 rl 로 처리한다.
        // 이미 가장 아래쪽 왼쪽에 위치해 있는 상황이기에 r 보다
        // 우선순위가 높은 d 혹은 u 로 움직일 수 없다.
        // u 는 r 보다 우선순위가 낮기 때문에 제외한다.
        for (int i = 0; i < left / 2; i++) {
            sb.append("rl");
        }

        // r 로 이동 실행
        sb.append(new String(new char[map.get(2)]).replace('\u0000', chars[2]));

        // l 로 이동 실행
        sb.append(new String(new char[map.get(3)]).replace('\u0000', chars[3]));

        return sb.toString();
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        goal_x = r - 1;
        goal_y = c - 1;

        // 탈출이 불가능한 경우
        if (!possible(x - 1, y - 1, k)) return "impossible";

        return search(n - 1, m - 1, x - 1, y - 1, k);
    }
}
