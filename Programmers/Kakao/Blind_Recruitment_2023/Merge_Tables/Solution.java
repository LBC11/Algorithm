package Programmers.Kakao.Blind_Recruitment_2023.Merge_Tables;

import java.util.ArrayList;

/*
주요 아이디어
1. union & find
2. unmerge 하는 동안 바로바로 root 를 갱신하면 중간 다리가 끊기는 경우 발생
 */
class Solution {

    Node[][] table;
    ArrayList<String> answer;

    // node 의 root node 를 찾아서 반환
    Node find(Node node) {

        if (node == node.root) return node;

        else return find(node.root);
    }

    // node1, node2 를 그룹으로 묶는다.
    void merge(Node node1, Node node2) {

        Node root1 = find(node1);
        Node root2 = find(node2);

        if (root1 != root2) {

            root2.root = root1;

            // root1 이 empty 고 root2 가 다른 value 를 가지고 있는 경우는 root1 의 value 갱신
            if (root1.value.equals("EMPTY") && !root2.value.equals("EMPTY")) {
                root1.value = root2.value;
            }
        }
    }

    // 해당 node 의 root node value 갱신
    void update(Node node, String value) {

        find(node).value = value;
    }

    // value1 을 가지는 모든 node 의 value 를 value2 로 갱신한다.
    void update(String value1, String value2) {

        for (Node[] nodes : table) {
            for (Node node : nodes) {

                Node root = find(node);
                if (root.value.equals(value1)) root.value = value2;
            }
        }
    }

    // node 가 속한 group 해제
    void unmerge(Node node) {

        Node root = find(node);

        String root_value = root.value;

        // 이렇게 따로 group 에 속한 node 를 list 화 한 후 나중에 root 를 갱신하는 이유
        // 이 그룹에 속해있지만 중간다리가 되는 node 의 root 가 갱신되면서 연결이 끊기게 되어
        // 갱신이 되지 않는 경우가 생긴다.
        ArrayList<Node> list = new ArrayList<>();

        for (Node[] nodes : table) {
            for (Node n : nodes) {

                if (find(n) == root) {
                    list.add(n);
                }
            }
        }

        for (Node n : list) {
            n.root = n;
            n.value = "EMPTY";
        }

        // root node 의 value 는 현재 node 가 가지게 된다.
        node.value = root_value;
    }

    // answer 에 해당 node 의 root node 의 value 더한다.
    void print(Node node) {
        answer.add(find(node).value);
    }

    void init() {
        table = new Node[50][50];

        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
                Node node = new Node();
                node.root = node;
                table[i][j] = node;
            }
        }

        answer = new ArrayList<>();
    }

    public String[] solution(String[] commands) {

        init();

        for (int i = 0; i < commands.length; i++) {
            String[] command = commands[i].split(" ");

            switch (command[0]) {

                case "UPDATE":
                    if (command.length == 4) {
                        update(table[Integer.parseInt(command[1]) - 1][Integer.parseInt(command[2]) - 1], command[3]);
                    } else {
                        update(command[1], command[2]);
                    }
                    break;

                case "MERGE":
                    merge(table[Integer.parseInt(command[1]) - 1][Integer.parseInt(command[2]) - 1], table[Integer.parseInt(command[3]) - 1][Integer.parseInt(command[4]) - 1]);

                    break;

                case "UNMERGE":

                    unmerge(table[Integer.parseInt(command[1]) - 1][Integer.parseInt(command[2]) - 1]);
                    break;

                case "PRINT":
                    print(table[Integer.parseInt(command[1]) - 1][Integer.parseInt(command[2]) - 1]);
                    break;
            }
        }

        String[] temp = new String[answer.size()];
        for (int i = 0; i < answer.size(); i++) {
            temp[i] = answer.get(i);
        }
        return temp;
    }
}

class Node {

    String value;
    Node root;

    public Node() {
        this.value = "EMPTY";
    }
}
