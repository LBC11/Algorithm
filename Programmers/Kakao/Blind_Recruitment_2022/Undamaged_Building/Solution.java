package Programmers.Kakao.Blind_Recruitment_2022.Undamaged_Building;

/*
아이디어
1. n*m 의 연산을 일일이 더하고 빼는 것이 아니라, 누적합을 이용해 4개의 mark 를 남긴다.
2. array[n][m] += num, array[n+i+1][m] -= num,
   array[n][m+i+1] -= num, array[n+i+1][m+i+1] += num
3. 그 후 좌우, 상하로 누접합을 진행하면 그동안의 연산 결과가 나온다.

소감
-> hash, dp, dfs 등 기존에 아는 방법으로 아무리 고민해도 답이 나오지 않아서 해설을 봤다.
   그런데 진짜 너무 대단한다. n*m 의 시간을 4개의 mark 를 남기는 것으로 해결하고 이를
   반복해 나갈 수 있다는게, 또 누접합이라는 간단한 방법으로 가능하다는 게 너무 신기했다.
 */
class Solution {

    int[][] sum;

    public int solution(int[][] board, int[][] skill) {

        sum = new int[board.length + 1][board[0].length + 1];

        for (int[] array : skill) {

            int sign;

            if (array[0] == 2) sign = 1;
            else sign = -1;

            int number = array[5] * sign;

            sum[array[1]][array[2]] += number;
            sum[array[3] + 1][array[2]] -= number;
            sum[array[1]][array[4] + 1] -= number;
            sum[array[3] + 1][array[4] + 1] += number;
        }

        for (int i = 0; i < sum.length; i++) {
            for (int j = 1; j < sum[0].length; j++) {
                sum[i][j] += sum[i][j - 1];
            }
        }

        for (int i = 1; i < sum.length; i++) {
            for (int j = 0; j < sum[0].length; j++) {
                sum[i][j] += sum[i - 1][j];
            }
        }

        int answer = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                if(sum[i][j] + board[i][j] > 0) answer++;
            }
        }

        return answer;
    }
}
