package Programmers.Kakao.Blind_Recruitment_2023.Emoticon;

import java.util.Arrays;
/*
주요 아이디어
1. 최약의 경우에도 연산 횟수가 1억번 이하인 것을 통해 완전탐색 문제라는 것을 눈치채야 한다.
2. 중복순열은 dfs 로 구현할 수 있다.
3. 비교연산을 시행할 때 구간을 잘 설정해야 한다. (특히, 끝이 닫혔는지 열렸는지)
 */
public class Solution {

    int[] discount = {10, 20, 30, 40};
    int[] ratio;
    int[] emoticons;
    int[][] users;

    int[] answer;

    void dfs(int depth, int curr) {

        // ratio 끝까지 discount 를 정했다면
        if (depth == curr) {

            int memberNum = 0;
            int totalSales = 0;

            for (int[] user : users) {

                int sales = 0;
                for (int i = 0; i < emoticons.length; i++) {

                    // user 의 기준보다 할인율이 크다면
                    if (user[0] <= ratio[i]) {
                        sales += (emoticons[i] * (100 - ratio[i]) / 100);
                    }
                }

                // user 의 기준보다 총 금액이 크다면 서비스 가입
                if(user[1] <= sales) memberNum++;

                // 아니라면 각 이모티콘 구입
                else totalSales += sales;
            }

            // 목표 1. 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것
            if(answer[0] < memberNum) {
                answer[0] = memberNum;
                answer[1] = totalSales;
            }

            // 목표 2. 이모티콘 판매액을 최대한 늘리는 것.
            else if(answer[0] == memberNum && answer[1] < totalSales) {
                answer[1] = totalSales;
            }
        }

        // 아직 정해야할 discount 가 있는 경우
        else {
            for (int i = 0; i < 4; i++) {

                ratio[curr] = discount[i];
                dfs(depth, curr + 1);
            }
        }
    }

    public int[] solution(int[][] users, int[] emoticons) {

        ratio = new int[emoticons.length];
        this.users = users;
        this.emoticons = emoticons;

        answer = new int[2];

        dfs(emoticons.length, 0);

        return answer;
    }
}
