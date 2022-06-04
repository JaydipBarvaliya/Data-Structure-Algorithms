package com.geeksforgeeks.linkedlist;

public class DoublyLinkedList {

    Node head;

    class Node {
        int data;
        DoublyLinkedList.Node next;
        int size;
        DoublyLinkedList.Node prev;

        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
    }

    public void insert(int data) {

        // Creating the new Node
        Node newNode = new Node(data);

        //Setting the next of newNode to head, as this is insert in front and prev = null;
        newNode.next = head;
        newNode.prev = null;

        if (head != null) {
            head.prev = newNode;
        }
        head = newNode;
    }

    public void append(int data) {
        // Creating the new Node
        Node newNode = new Node(data);
        newNode.next = null;

        if (head == null) {
            newNode.next = head;
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    public void printList(Node head) {
        Node temp = head;

        while (temp != null) {
            System.out.print(temp.data + ",");
            temp = temp.next;
        }
        System.out.println();
    }

    public void reverseDoublyLinkedList(Node head) {
        Node current = head;
        Node temp = null;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }

        //Check for the case when empty list, or list with 1 node
        if (temp != null) {
            head = temp.prev;
        }
        printList(head);
    }

    public Node reverseDLLRecursively(Node head) {
        if (head == null) return head; // Single node can't be reversed

        if (head.next == null) {
            head.prev = null;
            return head;
        }

        Node reversedHead = reverseDLLRecursively(head.next);

        // Creating the forward link to myself.... from the next node.
        head.next.next = head;
        // Current's previous will point to the next node.
        head.prev = head.next;

        head.next = null;
        return reversedHead;
    }

    public static void main(String[] args) {
        DoublyLinkedList util = new DoublyLinkedList();

        util.insert(5);
        util.insert(4);
        util.insert(3);
        util.insert(2);
        util.insert(1);

        util.printList(util.head);

        util = new DoublyLinkedList();
        util.append(1);
        util.append(2);
        util.append(3);
        util.append(4);
        util.append(5);

        util.printList(util.head);

        System.out.println("===========Reverse Doubly Linked List=================");
//        util.reverseDoublyLinkedList(util.head);


        util.head = util.reverseDLLRecursively(util.head);
        util.printList(util.head);
    }
}
