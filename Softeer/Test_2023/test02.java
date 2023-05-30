package Softeer.Test_2023;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class test02 {

    static PriorityQueue<Node> pq;

    static Node[] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        nodes = new Node[26];
        for(int i = 0; i < 26; i++) {
            nodes[i] = new Node(i);
        }

        pq = new PriorityQueue<>((o1, o2) -> {
            if(o1.priority.size() == o2.priority.size()) return o1.priority.peek() - o2.priority.peek();
            else return o2.priority.size() - o1.priority.size();
        });

        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String cmd = st.nextToken();

            if(cmd.equals("enqueue")) {

                int idx = st.nextToken().charAt(0) - 'A';

                pq.remove(nodes[idx]);
                nodes[idx].priority.add(i);

                pq.add(nodes[idx]);
            }

            else {
                if(pq.isEmpty()) sb.append("*").append(" ");

                else {
                    Node node = pq.poll();

                    sb.append((char) (node.value + 'A')).append(" ");
                    node.priority.poll();

                    if(node.priority.size() > 0) pq.add(node);
                }
            }
        }
        System.out.println(sb);
    }
}

class Node {

    int value;
    PriorityQueue<Integer> priority;

    public Node(int value) {
        this.value = value;
        this.priority = new PriorityQueue<>();
    }
}
