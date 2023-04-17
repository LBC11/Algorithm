package Programmers.Kakao.Tech_Internship_2022.Hiking_Route;

import java.util.*;

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

        int[] answer = {0, Integer.MAX_VALUE};

        for(int summit : summits) {

            int min_intensity = Integer.MAX_VALUE;

            // 해당 summit 을 들려야 하니 ban 목록에서 삭제
            ban.remove(summit);

            for(int gate : gates) {

                // gate 는 다시 갈 일이 없으니 ban 목록에서 굳이 삭제하지 않는다.
                min_intensity = Math.min(min_intensity, Dijkstra(gate, summit));
            }

            if(min_intensity < answer[1]) {
                answer[0] = summit;
                answer[1] = min_intensity;
            }

            // 다시 ban 목록에 추가
            ban.add(summit);
        }

        return answer;
    }

    private int Dijkstra(int gate, int summit) {

        // key: place_idx, value: 해당 place 까지 route 의 max_intensity 중 min value
        int[] max = new int[map.size()];
        Arrays.fill(max, Integer.MAX_VALUE);

        // intensity 가 낮은 순서대로 탐색을 하는
        PriorityQueue<Path> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.cost)));

        pq.add(new Path(gate, 0));

        while(!pq.isEmpty()) {

            Path curr_path = pq.poll();

            // 현재 위치
            int place = curr_path.idx;
            int cost = curr_path.cost;

            // summit 까지의 min_intensity 보다 cost 가 크다면 탐색할 필요가 없다.
            if(cost >= max[summit]) continue;

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
