package com.datastructures.linkedlist;

import java.util.Stack;

/**
 * Created by jaine03 on 17/04/17.
 */
public class LinkedListUtil {

    Node head;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public void append(int data) {

        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void push(int data) {
        //System.out.println("Head is at -----> "+head.data+" and next is "+head.next.data);
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    public void appendAfterGivenNode(int afterData, int data) {
        if (head == null) {
            System.out.println("After Node not present");
            return;
        }
        Node temp = head;

        while (temp.data != afterData) {
            temp = temp.next;
        }

        Node newNode = new Node(data);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    public void traverseList() {
        Node temp = head;
        if (temp != null) {
            while (temp != null) {
                System.out.print(temp.data + "-->");
                temp = temp.next;
            }
            System.out.println();
        }
    }

    public int length() {
        if (head == null)
            return 0;
        int counter = 0;
        Node temp = head;
        while (temp != null) {
            counter++;
            temp = temp.next;
        }
        return counter;
    }

    public void deleteNode(int data) {
        if (head == null) {
            System.out.println("Empty Linked List");
            return;
        }
        Node previous = null;
        Node current = head;

        while (current != null) {
            if (current.data == data) {
                if (previous == null) {
                    head = current.next;
                    return;
                }
                previous.next = current.next;
                current = null;
                return;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("Node Not Present");
    }

    public void deleteByPosistion(int position) {
        if (isEmptyLinkedList(head)) {
            System.out.println("Empty Linked List");
            return;
        }

        Node previous = null;
        Node current = head;

        for (int i = 0; i <= position; i++) {
            previous = current;
            current = current.next;
        }

        previous.next = current.next;
        return;
    }

    public boolean isEmptyLinkedList(Node head) {
        return head == null ? true : false;
    }

    public int getLengthRecursively(Node head) {
        if (head == null)
            return 0;
        return 1 + getLengthRecursively(head.next);
    }

    public boolean findElement(Node head, int data) {
        if (head == null)
            return false;
        if (head.data == data)
            return true;

        return findElement(head.next, data);
    }

    public int getNth(int index, Node head) {
        if (head == null)
            return -1;
        if (index == 0)
            return head.data;
        return getNth(index - 1, head.next);
    }

    public int getSize(Node head) {
        if (head == null)
            return 0;
        else
            return 1 + getSize(head.next);
    }

    public int getMiddleElement() {
        int count = 0;
        Node middle = head;
        Node temp = head;

        while (temp != null) {
            if (count % 2 != 0) {
                middle = middle.next;
            }
            ++count;
            temp = temp.next;
        }
        return middle.data;
    }

    public Node getMiddleNode(Node head) {

        if (head == null) {
            return head;
        }
        Node slowPointer = head;
        Node fastPointer = head;

        while (fastPointer != null) {

            fastPointer = fastPointer.next;
            if (fastPointer != null && fastPointer.next != null) {
                fastPointer = fastPointer.next;
                slowPointer = slowPointer.next;
            }

        }
        return slowPointer;
    }

    public int getNthNodeFromEnd(int nthNode) {
        Node referencePointer = head;
        Node mainPointer = head;
        for (int i = 0; i < nthNode; i++) {
            referencePointer = referencePointer.next;
        }
        while (referencePointer != null && referencePointer.next != null) {
            referencePointer = referencePointer.next;
            mainPointer = mainPointer.next;
        }

        return mainPointer.data;
    }

    public void reverseList() {
        Node prev = null;
        Node temp = null;
        Node current = head;

        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        head = prev;
    }

    public void reverseListRecursively(Node current, Node previous) {

        if (current.next == null) {
            head = current;
            current.next = previous;
            return;
        }

        Node temp = current.next;
        current.next = previous;

        reverseListRecursively(temp, current);
    }

    public Boolean checkLoopExistInListWithFloydAlgo() {
        Node fastPointer = head;
        Node slowPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            if (slowPointer.data == fastPointer.data) {
                System.out.println("Loop Exist");
                return true;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }
        System.out.println("No Loop Exist");
        return false;
    }

    public void sortedMerge(Node firstListHead, Node secondListHead, LinkedListUtil sortedList) {
        Node leftListBeg = firstListHead;
        Node rightListBeg = secondListHead;

        while (leftListBeg != null && rightListBeg != null) {
            if (leftListBeg.data < rightListBeg.data) {
                sortedList.append(leftListBeg.data);
                leftListBeg = leftListBeg.next;
            } else {
                sortedList.append(rightListBeg.data);
                rightListBeg = rightListBeg.next;
            }
        }

        while (leftListBeg != null) {
            sortedList.append(leftListBeg.data);
            leftListBeg = leftListBeg.next;
        }

        while (rightListBeg != null) {
            sortedList.append(rightListBeg.data);
            rightListBeg = rightListBeg.next;
        }
    }

    public Node sortedMergeRecursively(Node firstList, Node secondList) {
        Node result;
        if (firstList == null) {
            return secondList;
        } else if (secondList == null) {
            return firstList;
        }

        if (firstList.data < secondList.data) {
            result = firstList;
            result.next = sortedMergeRecursively(firstList.next, secondList);
        } else {
            result = secondList;
            result.next = sortedMergeRecursively(firstList, secondList.next);
        }
        return result;
    }

    public Boolean isPalindromicList() {
        Node slowPointer = head;
        Node fastPointer = head;
        Boolean evenList = false;
        Stack<Integer> stack = new Stack<>();

        while (fastPointer != null && fastPointer.next != null) {
            stack.push(slowPointer.data);
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        if (fastPointer == null) {
            evenList = true;
        }
        if (!evenList) {
            slowPointer = slowPointer.next;
        }

        while (slowPointer != null) {
            if (stack.pop() != slowPointer.data) {
                return false;
            }
            slowPointer = slowPointer.next;
        }
        return true;
    }

    /**
     * 1-->2-->3-->4-->5
     */
    public void swapPairsInList() {
        Node prev = head;
        Node curr = head.next;

        head = curr;

        while (true) {
            Node next = curr.next;
            curr.next = prev;
            if (next == null || next.next == null) {
                prev.next = next;
                break;
            }

            prev.next = next.next;


            prev = next;
            curr = prev.next;
        }
    }

    public void alternativeSplit(LinkedListUtil sublist1, LinkedListUtil sublist2) {
        Node curr = head;
        Integer counter = 0;
        while (curr != null) {

            if (counter % 2 == 0) {
                sublist1.append(curr.data);
            } else {
                sublist2.append(curr.data);
            }
            curr = curr.next;
            counter++;
        }
    }

    public Node mergeSort(Node head) {

        // If only one node is present return the same
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddleNode(head);
        Node nextOfMiddle = middle.next;

        middle.next = null; // Making 1st half of list until middle Node only

        // Sorting both halves
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        LinkedListUtil sortedList = new LinkedListUtil();
        sortedMerge(left, right, sortedList);

        return sortedList.head;
    }

//    public Node reverseEveryKNodeRecursively(Node head, int k) {
//        Node curr = head;
//        Node prev = null;
//        int count = 0;
//        Node temp = null;
//        while (curr != null && count < k) {
//            Node temp = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = temp;
//            count++;
//        }
//        if (temp != null)
//            head.next = reverseEveryKNodeRecursively(temp, k);
//        return prev;
//    }

     public void swapEveryTwoNodes(){
        Node curr = head;
        Node prev;
        Node temp;
        Node thisHead,prevHead = null;
        int count = 0;
        while (curr != null){
            thisHead = curr;
            prev = null;
            count = 0;

            while (curr != null && count < 2){
                temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
                count++;
            }

            if(prevHead != null)
                prevHead.next = prev;
            else
                head = prev;

            prevHead = thisHead;
        }
    }

    public void reverseEveryKNodeIteratively(int k) {
        int count = 0;
        Node curr = head;
        Node prev = null;
        Node next = null;
        Node thisHead = curr;
        Node prevHead = null;

        while (curr != null) {
            thisHead = curr;
            count = 0;
            prev = null;

            while (curr != null && count < k) {
                next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                count++;
            }

            if (prevHead != null)
                prevHead.next = prev;
            else
                head = prev;

            prevHead = thisHead;
        }
    }

    public void reverseEveryKAlternativeNodeIteratively(int k) {
        int count = 0;
        Node curr = head;
        Node prev = null;
        Node next = null;
        Node thisHead = curr;
        Node prevHead = null;
        int alternativeGroup = 0;

        while (curr != null) {
            thisHead = curr;
            count = 0;
            prev = null;

            if (alternativeGroup % 2 == 0) {
                while (curr != null && count < k) {
                    next = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = next;
                    count++;
                }

                if (prevHead != null)
                    prevHead.next = prev;
                else
                    head = prev;

                prevHead = thisHead;
            } else {
                prevHead.next = curr;
                while (curr != null && count < k) {
                    prev = curr;
                    curr = curr.next;
                    count++;
                }
                prevHead = prev;
            }
            alternativeGroup++;
        }
    }
}




