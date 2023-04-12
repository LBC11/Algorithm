package HackerRank.Kruskal;

import java.io.*;
import java.util.*;
import java.util.stream.*;

class Result {

    /*
     * Complete the 'kruskal' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
     */

    /*
     * For the weighted graph, <name>:
     *
     * 1. The number of nodes is <name>Nodes.
     * 2. The number of edges is <name>Edges.
     * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
     *
     */

    static Node[] nodes;
    static PriorityQueue<Path> paths;

    static void init(int n) {

        nodes = new Node[n+1];
        for(int i=1; i<= n; i++) {
            Node node = new Node(i);
            node.root = node;
            nodes[i] = node;
        }

        paths = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
    }

    static Node find(Node node) {
        if(node.idx == node.root.idx) return node;
        else return find(node.root);
    }

    static boolean union(Node node1, Node node2) {

        Node root1 = find(node1);
        Node root2 = find(node2);

        if(root1 != root2) {
            root2.root = root1;
            return true;
        }

        return false;
    }

    public static int kruskal(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {

        init(gNodes);

        for(int i=0; i<gFrom.size(); i++) {
           paths.add(new Path(gFrom.get(i), gTo.get(i), gWeight.get(i)));
        }

        int cnt = 0;
        int sum = 0;
        while(!paths.isEmpty() && cnt < gNodes - 1) {

            Path currPath = paths.poll();
            if(union(nodes[currPath.start], nodes[currPath.end])) {
                sum += currPath.weight;
                cnt++;
            }
        }

        return sum;
    }

}

class Node {
    int idx;
    Node root;

    public Node(int idx) {
        this.idx = idx;
    }
}

class Path {
    int start;
    int end;
    int weight;

    public Path(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int gNodes = Integer.parseInt(gNodesEdges[0]);
        int gEdges = Integer.parseInt(gNodesEdges[1]);

        List<Integer> gFrom = new ArrayList<>();
        List<Integer> gTo = new ArrayList<>();
        List<Integer> gWeight = new ArrayList<>();

        IntStream.range(0, gEdges).forEach(i -> {
            try {
                String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                gFrom.add(Integer.parseInt(gFromToWeight[0]));
                gTo.add(Integer.parseInt(gFromToWeight[1]));
                gWeight.add(Integer.parseInt(gFromToWeight[2]));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Result.kruskal(gNodes, gFrom, gTo, gWeight);

        // Write your code here.
        bufferedWriter.write(String.valueOf(res));

        bufferedReader.close();
        bufferedWriter.close();
    }
}

