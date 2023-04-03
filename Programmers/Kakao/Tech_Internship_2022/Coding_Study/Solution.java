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

        map = new int[200][200];

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
