package Programmers.Kakao.Hiking_Route;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

class Solution {

    HashMap<Integer, Place> map;
    HashSet<Integer> ban;

    void init(int n, int[][] paths, int[] gates, int[] summits) {

        map = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            map.put(i, new Place(i));
        }

        for(int[] path : paths) {
            map.get(path[0]).paths.add(new Path(path[1], path[2]));
            map.get(path[1]).paths.add(new Path(path[0], path[2]));
        }

        ban = new HashSet<>();
        for (int gate : gates) {
            ban.add(gate);
        }
        for (int summit : summits) {
            ban.add(summit);
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {

        init(n, paths, gates, summits);

        int[] answer = {0, 10000001};

        for(int summit : summits) {

            int min_intensity = 10000001;
            ban.remove(summit);

            for(int gate : gates) {

                ban.remove(gate);

                min_intensity = Math.min(min_intensity, Dijkstra(gate, summit));

                ban.add(gate);
            }

            if(min_intensity < answer[1]) {
                answer[0] = summit;
                answer[1] = min_intensity;
            }

            ban.add(summit);
        }

        return answer;
    }

    private int Dijkstra(int gate, int summit) {

        int intensity = 0;

        HashMap<Integer, Integer> min_intensity = new HashMap<>();

        PriorityQueue<Path> pq = new PriorityQueue<>(((o1, o2) -> {return o1.cost - o2.cost;}));

        for (Path path : map.get(gate).paths) {
            min_intensity.put(path.idx, path.cost);
            pq.add(path);
        }

        while(!pq.isEmpty()) {

            Path curr_path = pq.poll();
            int place = curr_path.idx;
            int cost =
        }
    }
}

class Place {

    int idx;
    ArrayList<Path> paths;

    public Place(int idx) {
        this.idx = idx;
        this.paths = new ArrayList<>();
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
