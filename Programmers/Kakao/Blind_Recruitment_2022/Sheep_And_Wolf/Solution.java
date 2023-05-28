package Programmers.Kakao.Blind_Recruitment_2022.Sheep_And_Wolf;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
아이디어
1. 0번쨰 Node 부터 시작해 가능한 모든 순서를 탐색해봐야 한다. -> dfs 사용
2. 문제는 중복이 너무 많이 발생한다는 점 -> memoization 을 통해 해결
3. 탐색한 set 들을 ArrayList 등의 자료구조로 하기에는 너무 비효율적 -> bit masking 을 통해 해결

실패 분석
1. 각 Node 의 순서에만 집중한 나머지 중복을 해결하지 못했었다.
 */
public class Solution {

    Node[] nodes;

    public int solution(int[] info, int[][] edges) {

        nodes = new Node[info.length];

        for(int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i, info[i]);
        }

        for(int i = 0; i < edges.length; i++) {
            insert(nodes[edges[i][0]], nodes[edges[i][1]]);
        }

        return dfs(info.length);
    }

    private void insert(Node node1, Node node2) {

        if(node1.left == null) node1.left = node2;
        else node1.right = node2;
    }

    private int dfs(int n) {

        // 2^0 + 2^1 +... + 2^16 의 모든 숫자가 포함된 경우를 위해
        boolean[] visited = new boolean[1<<17];

        int ans = 1;

        Queue<Integer> queue = new LinkedList<>();

        // 0번쨰 node 만 추가된 상태
        queue.add(1);

        while(!queue.isEmpty()) {

            int set = queue.poll();

            // 이미 탐색한 적이 있다면 생략
            if(visited[set]) continue;

            visited[set] = true;

            int sheep = 0;
            int wolf = 0;

            ArrayList<Integer> elements = new ArrayList<>();

            // set 에 포함된 sheep, wolf 숫자 식별
            for(int i = 0; i < n; i++) {

                // i번째 숫자가 set 에 포함된 경우
                if((set & (1 << i)) != 0) {

                    elements.add(i);

                    if(nodes[i].val == 0) sheep++;
                    else wolf++;
                }
            }

            // 늑대의 수가 양의 수와 같거나 같을 경우
            if(wolf >= sheep) continue;

            // 탐색 결과 갱신
            ans = Math.max(sheep, ans);

            // 다음 탐색할 set 을 queue 에 추가
            for (Integer element : elements) {

                if(nodes[element].left != null) {
                    queue.add(set | (1 << nodes[element].left.idx));
                }

                if(nodes[element].right != null) {
                    queue.add(set | (1 << nodes[element].right.idx));
                }
            }
        }

        return ans;
    }
}

class Node {

    int idx;
    int val;
    Node left;
    Node right;

    public Node(int idx, int val) {
        this.idx = idx;
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
