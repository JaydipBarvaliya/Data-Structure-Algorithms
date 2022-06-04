package com.datastructures.linkedlist;

/**
 * Created by jaine03 on 08/07/17.
 */
public class DoublyCircularLinkedList {

    Node head;

    public static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public void append(Node newNode){
        if(head == null){
            head = newNode;
            return;
        }
        Node curr = head;
        while (curr.next!= null && curr.next != head){
            curr = curr.next;
        }
            curr.next = newNode;
            newNode.prev = curr;
            newNode.next = head;
            head.prev = newNode;
    }

    public void traverseList(){
        if(head == null){
            return;
        }
        System.out.println("Traversing a list");
        Node curr = head;
        while (curr.next!=head){
            System.out.print(curr.data+"--->");
            curr = curr.next;
        }
        if(curr.next == head)
            System.out.print(curr.data);
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyCircularLinkedList list = new DoublyCircularLinkedList();

        Node first = new Node(1);
        list.append(first);
        list.append(new Node(2));
        list.append(new Node(3));
        list.append(new Node(4));
        Node lastNode = new Node(5);
        lastNode.next = first;
        list.append(lastNode);

        list.traverseList();
    }
}
