package com.company.amazon;

import com.company.amazon.LinkedListUtil.Node;

public class RearrangeOddEvenIndexedLinkedListNodesTogether {

    public static void main(String[] args) {
        LinkedListUtil util = new LinkedListUtil();
        util.append(1);
        util.append(2);
        util.append(3);
        util.append(4);
        util.append(5);
        util.append(6);

        rearrange(util.head);
    }

    public static void rearrange(Node head) {
        if (head == null) // Empty List
            return;
        if (head.next == null) // List with only 1 node
            return;

        Node currEven = head;
        Node currOdd = head.next;
        Node prevOdd = head.next;

        // First Group All Even Nodes
        while (currEven.next != null && currOdd.next != null) {
            currEven.next = currEven.next.next;
            currEven = currEven.next;

            currOdd.next = currOdd.next.next;
            currOdd = currOdd.next;
        }
        currEven.next = prevOdd;
        LinkedListUtil.printList(head);
    }
}
