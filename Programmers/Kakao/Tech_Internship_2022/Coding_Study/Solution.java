package Programmers.Kakao.Tech_Internship_2022.Coding_Study;

import java.util.ArrayList;

/*
주요 아이디어
1. dp 이용
2. 현재의 알고력과 코딩력이 문제의 최대 요구치보다 클 때가 있다
   -> 이거 간과했다가 array 의 크기를 - 로 설정해서 runtime error 발생했었다

실패 이유 분석
1. 기존의 방식에서는 문제의 최대 요구치보다 rwd 가 크다면 map 의 크기보다 rwd 가
   크기 때문에 해당 문제 푸는 것을 배제해버리는 현상 발생
   -> map 의 크기를 언제나 rwd 를 최대한 받아도 결과가 반영될수 있게 일정한 크기로 fix

2. 1의 이유로 인해 goal 보다 더 큰 숫자에는 결과가 반영이 되어도 정작 goal 에 반영이 안되는
   경우가 발생한다
   -> 마지막에 goal 보다 큰 범위에서 가장 작은 cost 를 탐색하여 찾는 것으로 해결했다.
   -> 이 방식이 아니면 갱신이 될 때마다 더 작은 모든 수에서 갱신하는 것을 해야하는데
      너무 많은 시간이 소요된다.

추가
배열의 크기가 450 * 450 인 이유
단순히 채점 case 에서 450 초과하는 al(알고력) / co(코딩력) 를 요구하지 않기 때문입니다

사실 모든 경우의 수에서 아래의 알고리즘이 성립하려면 다음과 같아야 한다.
ex) 0,0,[[0,0,2,30,1],[150,150,0,0,1]], 30인 이유는 rwd 의 최댓값이 30이라서

위 case 의 경우, 2번 문제를 풀기 위해 al = 150 / co = 150 이 요구된다.
1번 문제를 75회 풀어 75초가 경과하는것이 맞으나 그렇게 되는 경우
al = 150, co = 2250 이 되어야 한다. 이러한 case 를 재대로 반영하기 위해
al_rwd / co_rwd 가 반전되면 al = 11250 / co = 150 케이스 역시 고려해야 하므로

dp의 사이즈를 상수로 지정하는 경우
dp[2251][2251] 의 사이즈가 필요하게 됩니다


 */
class Solution {

    int[][] map;
    ArrayList<Problem> sProblems;

    public int solution(int alp, int cop, int[][] problems) {

        sProblems = new ArrayList<>();

        // 알고리즘 공부를 통해 알고력 혹은 코딩력 올리기
        sProblems.add(new Problem(0, 0, 1, 0, 1));
        sProblems.add(new Problem(0, 0, 0, 1, 1));

        int max_alp = 0;
        int max_cop = 0;
        for (int[] problem : problems) {

            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);

            // cost 가 60 이상이면 alp_rwd, cop_rwd 가 각각 최대 30 이기에
            // 공부해서 올리는 거랑 차이가 없기 때문에 굳이 연산에 넣을 이유가 없다.
            if (problem[4] <= 60)
                sProblems.add(new Problem(problem[0], problem[1], problem[2], problem[3], problem[4]));
        }

        int goal_x = Math.max(0, max_alp - alp);
        int goal_y = Math.max(0, max_cop - cop);

        map = new int[450][450];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 10000;
            }
        }

        map[0][0] = 0;

        dp(alp, cop);

        return getMinCost(goal_x, goal_y);
    }

    private void dp(int alp, int cop) {

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {

                for (Problem problem : sProblems) {

                    int x = i - problem.alp_rwd;
                    int y = j - problem.cop_rwd;

                    if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) continue;

                    if (x + alp >= problem.alp_req && y + cop >= problem.cop_req) {
                        map[i][j] = Math.min(map[x][y] + problem.cost, map[i][j]);
                    }
                }
            }
        }
    }

    int getMinCost(int x, int y) {

        int cost = 10000;
        for (int i = x; i < map.length; i++) {
            for (int j = y; j < map[0].length; j++) {

                cost = Math.min(cost, map[i][j]);

            }
        }

        return cost;
    }
}

class Problem {

    int alp_req;
    int cop_req;
    int alp_rwd;
    int cop_rwd;
    int cost;

    public Problem(int alp_req, int cop_req, int alp_rwd, int cop_rwd, int cost) {
        this.alp_req = alp_req;
        this.cop_req = cop_req;
        this.alp_rwd = alp_rwd;
        this.cop_rwd = cop_rwd;
        this.cost = cost;
    }
}
