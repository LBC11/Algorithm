package Programmers.Kakao.Blind_Recruitment_2023.Merge_Tables;

import java.util.ArrayList;

class Solution {

    Node[][] table;
    ArrayList<String> answer;


    // node 의 root node 를 return
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

    void update(Node node, String value) {

        find(node).value = value;
    }

    void update(String value1, String value2) {

        for (Node[] nodes : table) {
            for (Node node : nodes) {

                Node root = find(node);
                if (root.value.equals(value1)) root.value = value2;
            }
        }
    }

    void unmerge(Node node) {

        Node root = find(node);

        String root_value = root.value;

        for (Node[] nodes : table) {
            for (Node n : nodes) {

                if (find(n) == root) {
                    n.root = n;
                    n.value = "EMPTY";
                }
            }
        }

        node.value = root_value;
    }

    void print(Node node) {
        answer.add(find(node).value);
    }

    boolean isInteger(String s) {

        try {
            Integer.parseInt(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    void init() {
        table = new Node[50][50];

        for(int i=0; i<50; i++) {
            for(int j=0; j<50; j++) {
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
                    if (command[1].length() == 1 && isInteger(command[1])) {
                        update(table[Integer.parseInt(command[1]) - 1][Integer.parseInt(command[2]) - 1], command[3]);
                    }

                    else {
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
