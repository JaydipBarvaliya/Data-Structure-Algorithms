package com.company.amazon;

public class LinkedListUtil {

    public Node head;

    public static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public void append(int data) {
        Node temp = head;
        Node newNode = new Node(data);
        if (temp == null) {
            this.head = newNode;
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " , ");
            temp = temp.next;
        }
        System.out.println();
    }

    public Node reverseListRecursively(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node headOfReversedList = reverseListRecursively(head.next);

        head.next.next = head;
        head.next = null;
        return headOfReversedList;
    }

    public Node reverse(Node head) {
        Node temp = head;
        Node tempNext = null;
        Node prev = null;

        while (temp != null) {
            tempNext = temp.next;
            temp.next = prev;
            prev = temp;
            temp = tempNext;
        }

        head = prev;
        return head;
    }

    public Node getMiddleNode(Node head) {
        Node slowPointer = head;
        Node fastPointer = head;

        while (fastPointer != null) {
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
            if (fastPointer != null && fastPointer.next == null) {
                fastPointer = fastPointer.next;
            }
        }
        return slowPointer;
    }

    public static boolean isEvenList(Node head) {
        int counter = 0;
        Node temp = head;
        while (temp != null) {
            counter++;
            temp = temp.next;
        }
        return counter % 2 == 0 ? true : false;
    }

    /**
     * 1) Find Middle of the List
     * 2) Reverse the Second Half
     * 3) Compare both 1st half and 2nd Half
     * 4) Re-Reverse the Original List
     *
     * @param head
     * @return
     */
    public boolean isListPalindrome(Node head) {
        Node middleNode = getMiddleNode(head);
        Node secondHalfReversedHead = reverse(middleNode);
        printList(secondHalfReversedHead);

        // This can be calculated as a part of finding the middle node
        Boolean isEvenList = isEvenList(head);
        Node temp = head;
        return compareList(temp, middleNode, secondHalfReversedHead, middleNode, isEvenList);
    }

    public boolean compareList(Node firstList, Node firstListEnd, Node secondList, Node secondListEnd, Boolean isEvenList) {
        Node tempA = firstList;
        Node tempB = secondList;

        while (tempA != firstListEnd && tempB != null) {
            if (tempA.data != tempB.data) {
                return false;
            }
            tempA = tempA.next;
            tempB = tempB.next;
            if (isEvenList) {
                if (tempA == firstListEnd) {
                    break;
                }
            }

        }
        return true;
    }

    public void swapListInGroup(Node head, int groupCount) {
        Node prevHead = null;
        Node thisHead = null;
        Node prev = null;
        Node curr = head;
        Node currNext = null;
        int count = 0;

        while (curr != null) {
            thisHead = curr;
            count = 0;
            prev = null;
            while (curr != null && count++ < groupCount) {
                currNext = curr.next;
                curr.next = prev;
                prev = curr;
                curr = currNext;
            }

            if (prevHead == null) {
                head = prev;
            } else {
                prevHead.next = prev;
            }
            prevHead = thisHead;
        }
        printList(head);
    }

    public void removeEveryKNode(Node head, int k) {
        int count = 1;
        Node prev = null;

        while (head != null) {
            if (count == k) {
                count = 1;
                if (prev != null) {
                    prev.next = head.next;
                    prev = prev.next;
                    head = head.next;
                } else {
                    this.head = head.next;
                }
            } else {
                prev = head;
                head = head.next;
                count++;
            }
        }
    }

    public static void main(String[] args) {
        LinkedListUtil util = new LinkedListUtil();

        util.append(1);
        util.append(2);
        util.append(3);
        util.append(4);
        util.append(5);

        util.printList(util.head);
        util.head = util.reverseListRecursively(util.head);

        util.printList(util.head);

//        util.append(1);
//        util.append(2);
//        util.append(3);
//        util.append(2);
//        util.append(1);
//
//        util.printList(util.head);
//
////        util.head = util.reverse(util.head);
//
////        util.printList();
//
//        System.out.println("Is List Palindrome ? " + util.isListPalindrome(util.head));
//
//        util = new LinkedListUtil();
//        util.append(1);
//        util.append(2);
//        util.append(3);
//        util.append(4);
//        util.append(5);
//        util.append(6);
//        util.append(7);
//        util.append(8);
//        util.append(9);
//        util.append(10);
//        util.append(11);
//
//        util.printList(util.head);
////        util.swapListInGroup(util.head, 3);
//
//
//        System.out.println("Removing every kth Node");
//        util.removeEveryKNode(util.head, 3);
//        util.printList(util.head);
    }
}
