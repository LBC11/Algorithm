package Programmers.Kakao.Tech_Internship_2022.Hiking_Route;

import java.util.*;

/*
아이디어
1. 각 산의 지점, 그 지점으로 가는 통로 등을 map 에 저장
2. PriorityQueue 에 gate 을 시작점으로 주고
3. Dijkstra 를 통해 각 summit 까지 걸리는 min intensity 계산
    단, Dijkstra 도중 summit 은 pq에 더하지 않는다.
    -> 하나의 summit 으로 가는 데 다른 summit 을 거칠 수 없다는 조건 때문에
4. 모든 summits 중 조건을 가장 만족하는 결과 return

실패 분석
1. 굳이 gate 1개 - summit 1개 이럴 필요가 없었다.
2. ||, && -> 이거 잘못 사용하고 원인 못 찾아서 한 시간을 헤맸다...
 */

class Solution {

    ArrayList<ArrayList<Path>> paths;
    int[] mountains;
    boolean[] isSummit;

    void init(int n, int[][] paths, int[] gates, int[] summits) {

        this.paths = new ArrayList<>(n + 1);

        mountains = new int[n + 1];
        isSummit = new boolean[n + 1];

        Arrays.fill(mountains, 987654321);

        for (int gate : gates) {
            mountains[gate] = 0;
        }

        for(int summit : summits) {
            isSummit[summit] = true;
        }

        for (int i = 0; i <= n; i++) {
            this.paths.add(new ArrayList<>());
        }

        // 시작 도시에서 해당 도시까지의 경로정보
        for (int[] path : paths) {
            this.paths.get(path[0]).add(new Path(path[1], path[2]));
            this.paths.get(path[1]).add(new Path(path[0], path[2]));
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        // 필요한 자료구조 init
        init(n, paths, gates, summits);

        // 같은 min_intensity 여도 처음 return 하는 값의 산봉우리
        // idx 가 더 작다는 것을 보장하기 위해
        return Dijkstra(gates, summits);
    }

    private int[] Dijkstra(int[] gates, int[] summits) {

        // intensity 가 낮은 순서대로 탐색을 하는
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.min != o2.min) return o1.min - o2.min;
            else return o1.idx - o2.idx;
        });

        for (int gate : gates) {
            pq.add(new Node(gate, 0));
        }

        while (!pq.isEmpty()) {
            Node curr_Node = pq.poll();

            // 현재 위치
            int idx = curr_Node.idx;
            int cost = curr_Node.min;

            if(isSummit[idx] || cost >= mountains[idx]) {
                continue;
            }

            for (Path path : paths.get(idx)) {

                int max_cost = Math.max(cost, path.cost);

                // min_cost 가 기존의 min 보다 작다면 pq 에 추가해준다.
                if (max_cost < mountains[path.idx]) {
                    mountains[path.idx] = max_cost;

                    // 중복되는 node 가 pq 에 들어가는 것을 방지하기 위해
                    pq.add(new Node(path.idx, max_cost));
                }
            }
        }

        Arrays.sort(summits);

        int idx = 0;
        int min = 987654321;

        for (int summit : summits) {
            if (mountains[summit] < min) {

                idx = summit;
                min = mountains[summit];
            }
        }

        return new int[]{idx, min};
    }
}

class Path {

    int idx;
    int cost;

    public Path(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

class Node {
    int idx;
    int min;

    public Node(int idx, int min) {
        this.idx = idx;
        this.min = min;
    }
}