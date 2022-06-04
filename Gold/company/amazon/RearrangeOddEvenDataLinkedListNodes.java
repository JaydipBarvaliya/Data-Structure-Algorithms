package com.company.amazon;

import com.company.amazon.LinkedListUtil.Node;

import static com.company.amazon.LinkedListUtil.printList;

public class RearrangeOddEvenDataLinkedListNodes {

    public static void main(String[] args) {
        LinkedListUtil util = new LinkedListUtil();
        util.append(1);
        util.append(2);
        util.append(3);
        util.append(4);
        util.append(5);
        util.append(6);

        System.out.println("Before Segregating");
        printList(util.head);


        segregate(util.head);

        System.out.println("After Segregating");
        printList(util.head);

    }

    public static void segregate(Node head) {
        Node prev = null;
        Node temp = head;
        int dataTemp = 0;

        while (temp != null) {
            if (temp.data % 2 == 0) { // Even Nodes then swap
                if (prev == null) {
                    prev = head;
                } else {
                    prev = prev.next;
                }
                dataTemp = temp.data;
                temp.data = prev.data;
                prev.data = dataTemp;
                temp = temp.next;
            } else {
                temp = temp.next;
            }
        }
    }
}
