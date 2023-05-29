package Softeer.Jiwoo_Like_Prime_Number;

import java.util.*;
import java.io.*;


public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] parents;
    static PriorityQueue<Path> pq;

    public static void main(String args[]) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        parents = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);

        int m = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new Path(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int max_cost = 0;
        while (find(n) != 1 && !pq.isEmpty()) {

            Path current = pq.poll();
            if (union(current.start, current.end)) max_cost = Math.max(max_cost, current.cost);
        }

        System.out.println(nextPrime(max_cost));
    }

    static int nextPrime(int n) {

        n--;

        while (true) {

            n++;

            boolean flag = true;

            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) break;
        }

        return n;
    }

    static int find(int idx) {

        if (parents[idx] == idx) return idx;

        else return find(parents[idx]);
    }

    static boolean union(int i1, int i2) {

        int idx1, idx2;

        if (i1 > i2) {
            idx1 = i1;
            idx2 = i2;
        } else {
            idx1 = i2;
            idx2 = i1;
        }

        int p1 = find(idx1);
        int p2 = find(idx2);

        if (p1 != p2) {
            parents[p2] = p1;
            return true;
        }

        return false;
    }
}

class Path {

    int start;
    int end;
    int cost;

    public Path(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

}
