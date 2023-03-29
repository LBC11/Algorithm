package Graph.Friend_Network;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    static HashMap<String, Node> map;

    static void init() {
        map = new HashMap<>();
    }

    static void AddTheFriend(String s) {
        if(!map.containsKey(s)) {

            Node node = new Node(s);
            node.root = node;
            map.put(s, node);
        }
    }

    static Node find(Node node) {
        if(node == node.root) return node;

        else return find(node.root);
    }

    static void union(Node node1, Node node2) {

        Node root1 = find(node1);
        Node root2 = find(node2);

        if(root1 != root2) {

            root2.root = root1;
            root1.cnt += root2.cnt;
        }
    }

    static int GetTheNumber(StringTokenizer st) {

        String s1 = st.nextToken();
        String s2 = st.nextToken();

        AddTheFriend(s1);
        AddTheFriend(s2);

        union(map.get(s1), map.get(s2));

        return find(map.get(s1)).cnt;
    }


    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            init();

            int n = Integer.parseInt(br.readLine());

            for (int i = 0; i < n; i++) {
                sb.append(GetTheNumber(new StringTokenizer(br.readLine()))).append("\n");
            }

        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

    }
}

class Node {

    String name;
    Node root;
    int cnt;

    public Node(String name) {
        this.name = name;
        this.cnt = 1;
    }
}
