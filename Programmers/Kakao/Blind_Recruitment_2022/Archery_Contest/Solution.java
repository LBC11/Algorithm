package Programmers.Kakao.Blind_Recruitment_2022.Archery_Contest;

import java.util.PriorityQueue;

class Solution {

    // array[i][0]: i번째 점수를 얻기 위해 필요한 화살 개수
    // array[i][1]: i번째 점수를 얻었을 때 기대값
    int[][] array;
    PriorityQueue<Node> pq;
    boolean[] candidate;

    public int[] solution(int n, int[] info) {

        pq = new PriorityQueue<>((o1, o2) -> o2.score - o1.score);
        array = new int[11][2];
        candidate = new boolean[11];

        int score = 0;

        for (int i = 0; i < info.length; i++) {

            array[i][0] = info[i] + 1;

            // 아피치가 맞힌 화살이 있는 경우
            if (info[i] > 0) {
                array[i][1] = (10 - i) * 2;
                score += (10 - i);
            }

            // 없는 경우
            else array[i][1] = 10 - i;
        }

        dfs(10, n, score);

        if(pq.isEmpty()) return new int[]{-1};

        if(pq.peek().score <= 0) return new int[]{-1};

        return pq.peek().hits;
    }

    void dfs(int l, int n, int score) {
        if(l == -1) {

            int[] temp = new int[11];

            int need = 0;
            int current_score = score * -1;
            for(int i = 0; i<temp.length; i++) {
                if(candidate[i]) {
                    temp[i] = array[i][0];
                    need += array[i][0];
                    current_score += array[i][1];
                }
            }

            if(n >= need) {
                temp[10] += n - need;
                pq.add(new Node(current_score, temp));
            }
        }

        else {

            // l 번쨰가 true 인 경우
            candidate[l] = true;
            dfs(l - 1, n, score);

            candidate[l] = false;
            // l 번째가 false 인 경우
            dfs(l - 1, n, score);

            candidate[l] = true;
        }
    }
}

class Node {

    int score;
    int[] hits;

    public Node(int score, int[] hits) {
        this.score = score;
        this.hits = hits;
    }
}
