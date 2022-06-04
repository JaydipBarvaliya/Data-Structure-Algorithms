package com.stackoverflow;

class IdeOne {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    static Node head = null;
    static Node prev = null;

    public static void InOrder(Node root) {
        if (root == null) {
            return;
        }
        InOrder(root.left);
        System.out.println(root.data);
        if (head == null) {
            head = root;
            prev = head;
        } else {
            prev.right = root;
            root.left = prev;
            prev = root;
        }
        InOrder(root.right);
    }

    public static void PrintCDLL() {
        Node temp = head;
        System.out.println("Printing the CDLL!");
        while (temp.right != head) {
            System.out.print(temp.data + " ");
            temp = temp.right;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(12);
        root.right = new Node(15);
        root.left.left = new Node(25);
        root.left.right = new Node(30);
        root.right.left = new Node(36);
        InOrder(root);
        head.left = prev;
        prev.right = head;
//        System.out.println(head.data);
//        System.out.println(prev.data);
        PrintCDLL();
    }
}
