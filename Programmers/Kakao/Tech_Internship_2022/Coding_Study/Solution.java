package Programmers.Kakao.Tech_Internship_2022.Coding_Study;

import java.util.ArrayList;

class Solution {

    int[][] map;
    ArrayList<Problem> sProblems;

    public int solution(int alp, int cop, int[][] problems) {

        sProblems = new ArrayList<>();

        // 알고리즘 공부를 통해 알고력 혹은 코딩력 올리기
        sProblems.add(new Problem(0,0,1,0,1));
        sProblems.add(new Problem(0,0,0,1,1));

        int max_alp = 0;
        int max_cop = 0;
        for (int[] problem : problems) {

            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);

            // cost 가 60 이상이면 alp_rwd, cop_rwd 가 각각 최대 30 이기에
            // 공부해서 올리는 거랑 차이가 없기 때문에 굳이 연산에 넣을 이유가 없다.
            if(problem[4] <= 60) sProblems.add(new Problem(problem[0], problem[1], problem[2], problem[3], problem[4]));
        }

        map = new int[max_alp - alp + 1][max_cop - cop + 1];

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] = 10000;
            }
        }

        map[0][0] = 0;

        dp(alp, cop);

        return map[max_alp - alp][max_cop - cop];
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
//
//                if (i >= 1) map[i][j] = Math.min(map[i - 1][j] + 1, map[i][j]);
//                if (j >= 1) map[i][j] = Math.min(map[i][j - 1] + 1, map[i][j]);
//
//                for (int[] problem : problems) {
////
//                    int x = i - problem[2];
//                    int y = j - problem[3];
////
//                    if (x < 0 || x >= map.length || y < 0 || y >= map[0].length) continue;
//
//                    if (x + alp >= problem[0] && y + cop >= problem[1]) {
//                        map[i][j] = Math.min(map[x][y] + problem[4], map[i][j]);
//                    }
//                }
            }
        }
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
