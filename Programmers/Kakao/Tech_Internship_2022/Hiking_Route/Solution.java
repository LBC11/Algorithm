package Programmers.Kakao.Tech_Internship_2022.Hiking_Route;

import java.util.*;

/*
실패 분석
1. 굳이 gate 1개 - summit 1개 이럴 필요가 없었다. summit 1개를 탐색할 때
   gate 를 모두 pq에 더해버리면 된다. pq가 가장 낮은 cost 먼저 살핀다는
   것이 보장되기에 summit 에 도착하자마자 return 해버리면 된다.
 */
class Solution {

    ArrayList<ArrayList<Path>> map;
    HashSet<Integer> ban;

    void init(int n, int[][] paths, int[] gates, int[] summits) {

        map = new ArrayList<>(n + 1);

        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        // 시작 도시에서 해당 도시까지의 경로정보
        for(int[] path : paths) {
            map.get(path[0]).add(new Path(path[1], path[2]));
            map.get(path[1]).add(new Path(path[0], path[2]));
        }

        // 순회중 gates 와 summits 은 하나씩만 방문해야 하기에
        // 나머지 지점은 갈 수 없게 하기 위한 ban 목록
        ban = new HashSet<>();
        Collections.addAll(ban, Arrays.stream(gates).boxed().toArray(Integer[]::new));
        Collections.addAll(ban, Arrays.stream(summits).boxed().toArray(Integer[]::new));
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        // 필요한 자료구조 init
        init(n, paths, gates, summits);

        // 같은 min_intensity 여도 처음 return 하는 값의 산봉우리
        // idx 가 더 작다는 것을 보장하기 위해해

        int[] answer = {0, Integer.MAX_VALUE};

        for(int summit : summits) {

            // 해당 summit 을 들려야 하니 ban 목록에서 삭제
            ban.remove(summit);

            int min = Dijkstra(gates, summit);

            if(min < answer[1]) {
                answer[0] = summit;
                answer[1] = min;
            }

            // 다시 ban 목록에 추가
            ban.add(summit);
        }

        return answer;
    }

    private int Dijkstra(int[] gates, int summit) {

        // key: place_idx, value: 해당 place 까지 route 의 max_intensity 중 min value
        int[] max = new int[map.size()];
        Arrays.fill(max, Integer.MAX_VALUE);

        // intensity 가 낮은 순서대로 탐색을 하는
        PriorityQueue<Path> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.cost != o2.cost) return o1.cost - o2.cost;
            else return o1.idx - o2.idx;
        });

        for (int gate : gates) {
            pq.addAll(map.get(gate));
        }

        while(!pq.isEmpty()) {

            Path curr_path = pq.poll();

            // 현재 위치
            int place = curr_path.idx;
            int cost = curr_path.cost;

            // summit 에 도착했다면 pq로 인해 현재의 route 가 min_intensity 임이 증명된다.
            if(curr_path.idx == summit) {
                break;
            }

            for(Path path : map.get(place)) {

                // path 가 ban 목록에 있다면
                if(ban.contains(path.idx)) continue;

                int max_cost;
                if(cost != 0) max_cost = Math.max(cost, path.cost);
                else max_cost = path.cost;

                // min_cost 가 기존의 min 보다 작다면 pq 에 추가해준다.
                if(max_cost < max[path.idx]) {
                    max[path.idx] = max_cost;
                    pq.add(new Path(path.idx, max_cost));
                }
            }
        }

        return max[summit];
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
