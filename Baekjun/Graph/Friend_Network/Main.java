package Graph.Friend_Network;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
문제
민혁이는 소셜 네트워크 사이트에서 친구를 만드는 것을 좋아하는 친구이다. 우표를 모으는 취미가 있듯이, 민혁이는 소셜 네트워크 사이트에서 친구를 모으는 것이 취미이다.
어떤 사이트의 친구 관계가 생긴 순서대로 주어졌을 때, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.
친구 네트워크란 친구 관계만으로 이동할 수 있는 사이를 말한다.

입력
첫째 줄에 테스트 케이스의 개수가 주어진다. 각 테스트 케이스의 첫째 줄에는 친구 관계의 수 F가 주어지며, 이 값은 100,000을 넘지 않는다. 다음 F개의 줄에는 친구 관계가 생긴 순서대로 주어진다. 친구 관계는 두 사용자의 아이디로 이루어져 있으며, 알파벳 대문자 또는 소문자로만 이루어진 길이 20 이하의 문자열이다.

출력
친구 관계가 생길 때마다, 두 사람의 친구 네트워크에 몇 명이 있는지 구하는 프로그램을 작성하시오.

주요 아이디어
1. union & find 를 통해 grouping
2. grouping 할 때 group 에 속한 인원 cnt 를 통해 갱신
 */
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
