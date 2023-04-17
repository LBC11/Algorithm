package Programmers;

import java.util.*;

public class TwoDDoublyLinkedList {

    class Node {
        int data;
        Node next;
        Node prev;
        Node up;
        Node down;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
            this.up = null;
            this.down = null;
        }
    }

    Node head;

    public TwoDDoublyLinkedList() {
        this.head = null;
    }

    public Node insertRecursively(Node node, int data, int row, int col) {
        if (row == 0 && col == 0) {
            if (node == null) {
                return new Node(data);
            } else {
                node.data = data;
                return node;
            }
        }

        if (node == null) {
            node = new Node(0);
        }

        if (row > 0) {
            node.down = insertRecursively(node.down, data, row - 1, col);
            node.down.up = node;
        } else {
            node.next = insertRecursively(node.next, data, row, col - 1);
            node.next.prev = node;
        }

        return node;
    }

    public void insert(int data, int row, int col) {
        head = insertRecursively(head, data, row, col);
    }

    public void print2DListRecursively(Node node, boolean newLine) {
        if (node != null) {
            System.out.print(node.data + " ");
            print2DListRecursively(node.next, false);

            if (newLine) {
                System.out.println();
                print2DListRecursively(node.down, true);
            }
        }
    }

    public void print2DList() {
        print2DListRecursively(head, true);
    }

    public static void main(String[] args) {
        TwoDDoublyLinkedList list = new TwoDDoublyLinkedList();

        list.insert(1, 0, 0);
        list.insert(2, 0, 1);
        list.insert(3, 0, 2);
        list.insert(4, 0, 3);

        list.insert(5, 1, 0);
        list.insert(6, 1, 1);
        list.insert(7, 1, 2);
        list.insert(8, 1, 3);

        list.insert(9, 2, 0);
        list.insert(10, 2, 1);
        list.insert(11, 2, 2);
        list.insert(12, 2, 3);

        list.print2DList();

        Queue<Integer> a = new LinkedList<>();
        Stack<Integer> b = new Stack<>();
    }
}