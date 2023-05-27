package Programmers.Kakao.Blind_Recruitment_2022.Sheep_And_Wolf;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    Node[] nodes;

    public int solution(int[] info, int[][] edges) {

        nodes = new Node[info.length];

        for(int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(info[i]);
        }

        for(int i = 0; i < edges.length; i++) {
            insert(nodes[edges[i][0]], nodes[edges[i][1]]);
        }

        return bfs();
    }

    private void insert(Node node1, Node node2) {

        if(node1.left == null) node1.left = node2;
        else node1.right = node2;
    }

    private int bfs() {

        Queue<Node> queue = new LinkedList<>();

        nodes[0].sheep = 1;
        nodes[0].wolf = 0;

        queue.add(nodes[0]);

        while(!queue.isEmpty()) {

            Node current = queue.poll();

            int diff = current.sheep - current.wolf;

            if(current.left != null) {

                diff -= current.left.val;

                if(diff > 0) {
                    current.
                }

            }

            if(current.left != null) {

            }
        }
    }
}

class Node {

    int val;
    int sheep;
    int wolf;
    Node left;
    Node right;

    public Node(int val) {
        this.val = val;
        this.sheep = 0;
        this.wolf = 0;
        this.left = null;
        this.right = null;
    }
}
