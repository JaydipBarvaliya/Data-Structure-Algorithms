package com.leetcode.linkedlist;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author neeraj on 2019-05-25
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("Duplicates")
public class LinkedListUtil {

    public Node head;

    public static class Node {
        public int data;
        public Node next;
        public Node down; // ===> for Flattening Linked List
        public Node prev; // ===> For Doubly Linked List

        public Node(int data) {
            this.data = data;
        }
    }

    public void appendAllInDoublyLinkedLinkedList(Integer... allData) {
        Stream.of(allData).forEach(data -> append(data, true));
    }

    public void appendAll(Integer... allData) {
        Stream.of(allData).forEach(data -> append(data, false));
    }

    public void appendAllToDown(Integer... allData) {
        Stream.of(allData).forEach(this::appendDown);
    }

    public void appendAllNodes(LinkedListUtil... allNodes) {
        Stream.of(allNodes).map(object -> object.head).forEach(this::appendNode);
    }

    public void pushAllToFront(Integer... numbers) {
        Stream.of(numbers).forEach(this::pushToFront);
    }

    public void pushToFront(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        Node nextOfNewHead = head;
        head = newNode;
        head.next = nextOfNewHead;
    }

    public void append(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }

        tempNode.next = newNode;
    }

    public void append(int data, Boolean shouldAttachPrev) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }

        tempNode.next = newNode;

        if (shouldAttachPrev) {
            newNode.prev = tempNode;
        }
    }

    public void appendDown(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }
        Node tempNode = head;
        while (tempNode.down != null) {
            tempNode = tempNode.down;
        }

        tempNode.down = newNode;
    }

    public void appendNode(Node nextNode) {
        if (head == null) {
            head = nextNode;
            return;
        }
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = nextNode;
    }

    public void printList() {
        Node tempNode = head;
        System.out.print("[ ");
        while (tempNode != null) {
            System.out.print(tempNode.data + " , ");
            tempNode = tempNode.next;
        }
        System.out.println("null ]");
    }

    public void swapNodesWithoutSwappingData(int first, int second) {
        Node tempA = head;
        Node tempB = head;
        Node prevA = null;
        Node prevB = null;
        Node nextOfA = null;
        Node nextOfB = null;
        Boolean foundA = false;
        Boolean foundB = false;

        while (tempA != null) {
            if (tempA.data == first) {
                nextOfA = tempA.next;
                foundA = true;
                break;
            } else {
                prevA = tempA;
                tempA = tempA.next;
            }
        }

        while (tempB != null) {
            if (tempB.data == second) {
                nextOfB = tempB.next;
                foundB = true;
                break;
            } else {
                prevB = tempB;
                tempB = tempB.next;
            }
        }

        if (foundA && foundB) {

            if (prevA == null) {  // If first element found at first index itself
                head = tempB;
            } else {
                prevA.next = tempB;
            }

            if (nextOfA == tempB) { // Adjacent Nodes
                tempB.next = tempA;
                tempA.next = nextOfB;
            } else {
                tempB.next = nextOfA;
                prevB.next = tempA;
                tempA.next = nextOfB;
            }

            printList();
        } else {
            LogUtil.logIt("Swapping is not possible since  one/both nodes are not  present in the list");
            printList();
        }
    }

    public int getMiddleElementOfList() {
        Node fastPointer = head;
        Node slowPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
        }
        return slowPointer.data;
    }

    public int getElementFromLast(int n) {
        Node firstPointer = head;
        Node secondPointer = head;

        int counter = 1;

        while (counter++ <= n) {
            firstPointer = firstPointer.next;
        }

        while (firstPointer != null) {
            firstPointer = firstPointer.next;
            secondPointer = secondPointer.next;
        }
        return secondPointer.data;
    }

    public void reverseList() {
        Node prev = null;
        Node temp = head;
        Node nextOfTemp = null;

        while (temp != null) {
            nextOfTemp = temp.next;
            temp.next = prev;
            prev = temp;
            temp = nextOfTemp;
        }
        head = prev;
        printList();
    }

    public Node reverseListRecursively(Node current, Node previous) {
        if (current == null) {
            return previous;
        }
        Node nextOfCurrent = current.next;
        current.next = previous;
        return reverseListRecursively(nextOfCurrent, current);
    }

    public int getSizeOfList(Node head) {
        Node temp = head;

        if (temp == null)
            return 0;
        int size = 1;
        while (temp.next != null) {
            size++;
            temp = temp.next;
        }
        return size;
    }

    public int getIntersectionOfList(Node list1, Node list2) {
        int sizeofList1 = getSizeOfList(list1);
        int sizeofList2 = getSizeOfList(list2);

        int difference = sizeofList1 - sizeofList2;
        int counter = 0;
        Node temp1 = list1;
        Node temp2 = list2;

        while (counter++ < difference) {
            temp1 = temp1.next;
        }

        while (temp1 != null && temp2 != null) {
            if (temp1.data == temp2.data) {
                return temp1.data;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        LogUtil.logIt("No Intersection exist");
        return 0;
    }

    public void removeDuplicatesFromSortedList() {
        LogUtil.logIt("Duplicate List is ", true);
        printList();
        Node temp = head;
        Boolean hasDuplicate = false;
        Node whoseDuplicate = null;

        while (temp != null) {
            if (temp.next != null) {

                // Checking for duplicate
                if (temp.data == temp.next.data) {
                    if (!hasDuplicate) {
                        hasDuplicate = true;
                        whoseDuplicate = temp;
                    }
                } else {
                    if (hasDuplicate) {
                        hasDuplicate = false;
                        whoseDuplicate.next = temp.next;
                    }
                }
            } else {
                if (hasDuplicate) {
                    whoseDuplicate.next = whoseDuplicate.data != temp.data ? temp : null;
                }
            }
            temp = temp.next;
        }
        LogUtil.logIt("After removing duplicates", true);
        printList();
    }

    public void reverseListInGroup(int groupSize) {
        printList();
        Node current = head;
        Node previous = null;
        Node thisSubListHead = head;
        Node previousSubListHead = null;
        int count = 0;
        Node nextOfCurrentNode;

        while (current != null) {
            // Reset the counter, so that we can reverse the next chunk/sublist
            count = 0;
            thisSubListHead = current;
            previous = null;

            while (current != null && count++ < groupSize) {
                nextOfCurrentNode = current.next;
                current.next = previous;
                previous = current;
                current = nextOfCurrentNode;
            }

            // Now the sublist is being reversed, we just have to fit it correctly within the list
            if (previousSubListHead != null) {
                // Why we are connecting headOfPreviousSubList to the previous node, instead of the current,
                // because it's  current has either reached the end of list or is present at the head of next sublist.
                previousSubListHead.next = previous;
            } else {
                // This can only be possible when we are dealing with the first sublist in the complete list
                // in this case since the HEAD is now changed to the last element of the first sublist
                // so let's do the same
                head = previous;
            }
            // Since we are moving on the next sublist, but in the next cycle we need to stitch these two sublist and
            // at that point previousSubListHead will help.
            previousSubListHead = thisSubListHead;
        }

        LogUtil.logIt("After Reversing list in group of size " + groupSize);
        printList();
    }

    public void reverseAlternativeKNode(Node head, int k) {
        k = 3;
        LogUtil.logIt("Reversing Alternative " + k + " nodes.....", true);
        printList();

        Node thisListHead = null;
        Node previousListHead = null;
        Node previous = null;
        Node curr = head;
        int count = 0;
        Boolean reverse = true;

        while (curr != null) {
            if (reverse) {
                thisListHead = curr;
                previous = null;
                count = 0;

                // Reverse the group
                while (count++ < k && curr != null) {
                    Node nextOfCurr = curr.next;
                    curr.next = previous;
                    previous = curr;
                    curr = nextOfCurr;
                }


                // Now tie up the list
                if (previousListHead == null) {
                    head = previous;
                } else {
                    previousListHead.next = previous;
                }
                previousListHead = thisListHead;
            } else {
                previousListHead.next = curr;
                count = 0;
                while (count++ < k && curr != null) {
                    previous = curr;
                    curr = curr.next;
                }
                previousListHead = previous;
            }
            reverse = !reverse;
        }

        LogUtil.logIt("After Reversing Alternative " + k + " nodes.....", true);
        this.head = head;
        printList();
    }

    public void deleteNodesWithGreaterValueOnRightSide(Node head) {
        LogUtil.logIt("Deleting Nodes on the Right hand side with the Greater-Value.......");
        // Step 1 : Reverse the list and keep track of the head pointer.
        Node headOfReversedList = reverseListRecursively(head, null);

        // Step 2 : Let's find out all the Smaller elements
        Node temp = headOfReversedList;
        int MAX_TILL_NOW = temp.data;
        Node nextOfTemp;
        temp = temp.next;  // We will start with the second last node in the original list, as last node will always be in result set, because there is no new greater node in the list.
        Node prev = headOfReversedList;

        while (temp != null) {
            if (MAX_TILL_NOW > temp.data) {
                nextOfTemp = temp.next;
                prev.next = nextOfTemp;
                temp = temp.next;
            } else {
                MAX_TILL_NOW = temp.data;
                prev = temp;
                temp = temp.next;
            }
        }

        this.head = reverseListRecursively(headOfReversedList, null);
        printList();
    }

    public void segregateEvenAndOddNodes() {
        LogUtil.logIt("Segregating Even and Odd Nodes from input list ");
        printList();
        Node temp = head;
        Node evenNodeHead = null;
        Node evenNodePrev = null;
        Node oddNodeHead = null;
        Node oddNodePrev = null;

        while (temp != null) {
            if (temp.data % 2 == 0) { // Even Node
                if (evenNodeHead == null) {
                    evenNodeHead = temp;
                    evenNodePrev = evenNodeHead;
                } else {
                    evenNodePrev.next = temp;
                    evenNodePrev = evenNodePrev.next;
                }

            } else { // Odd Node
                if (oddNodeHead == null) {
                    oddNodeHead = temp;
                    oddNodePrev = oddNodeHead;
                } else {
                    oddNodePrev.next = temp;
                    oddNodePrev = oddNodePrev.next;
                }
            }
            temp = temp.next;
        }
        evenNodePrev.next = null;
        oddNodePrev.next = null;
        evenNodePrev.next = oddNodeHead;

        this.head = evenNodeHead;
        LogUtil.logIt("After Segregation............");
        printList();
    }

    public static void addNumbers(LinkedListUtil list1, LinkedListUtil list2) {
        LogUtil.logIt("Adding these 2 Lists", true);

        list1.printList();
        list2.printList();
        Node temp1 = list1.head;
        Node temp2 = list2.head;
        int sum;
        int carry = 0;
        LinkedListUtil resultList = new LinkedListUtil();

        while (temp1 != null && temp2 != null) {
            sum = carry + temp1.data + temp2.data;
            carry = sum / 10;
            resultList.append(sum % 10);
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        while (temp1 != null) {
            sum = carry + temp1.data;
            carry = sum / 10;
            resultList.append(sum % 10);
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            sum = carry + temp2.data;
            carry = sum / 10;
            resultList.append(sum % 10);
            temp2 = temp2.next;
        }

        LogUtil.logIt("Result is ", true);
        resultList.printList();
    }

    public void findTriplet(LinkedListUtil list1, LinkedListUtil list2, LinkedListUtil list3, Integer sum) {
        LogUtil.logIt("Finding Sum " + sum + " in these lists", true);
        list1.printList();
        list2.printList();
        list3.printList();

        Boolean foundTriplet = false;
        Node temp1 = list1.head;
        Node temp2 = list2.head;
        Node temp3 = list3.head;
        Integer currentItem;
        Integer remainingSum;
        Map<Integer, Integer> pairSumMap;

        while (temp1 != null) {
            currentItem = temp1.data;
            remainingSum = sum - currentItem;
            pairSumMap = new HashMap<>();
            temp2 = list2.head;
            temp3 = list3.head;

            while (temp2 != null) {
                pairSumMap.put(remainingSum - temp2.data, temp2.data);
                temp2 = temp2.next;
            }
            while (temp3 != null) {
                if (pairSumMap.containsKey(temp3.data)) {
                    foundTriplet = true;
                    LogUtil.logIt("Triplet Found", true);
                    System.out.println(currentItem + "," + pairSumMap.get(temp3.data) + "," + temp3.data);
                    break;
                }
                temp3 = temp3.next;
            }
            if (foundTriplet) {
                break;
            }
            temp1 = temp1.next;
        }
        if (!foundTriplet) {
            LogUtil.logIt("No Triplet available which sums upto " + sum, true);
        }
    }

    public void flattenList(LinkedListUtil head) {
        LogUtil.logIt("Flattening the List", true);
        LinkedListUtil sortedList = head;
        Node next = head.head.next;
        while (next != null) {
            sortedList = sortedMerge(sortedList, next);
            next = next.next;
        }

        // Since we have sorted list, but appended down, let's fix it
        Node temp = sortedList.head;
        LinkedListUtil resultList = new LinkedListUtil();
        while (temp != null) {
            resultList.append(temp.data);
            temp = temp.down;
        }
        resultList.printList();
    }

    public LinkedListUtil sortedMerge(LinkedListUtil sortedMergeHead, Node next) {
        LinkedListUtil sortedList = new LinkedListUtil();
        Node pointer1 = sortedMergeHead.head;
        Node pointer2 = next;

        while (pointer1 != null && pointer2 != null) {
            if (pointer1.data <= pointer2.data) {
                sortedList.appendDown(pointer1.data);
                pointer1 = pointer1.down;
            } else {
                sortedList.appendDown(pointer2.data);
                pointer2 = pointer2.down;
            }
        }

        while (pointer1 != null) {
            sortedList.appendDown(pointer1.data);
            pointer1 = pointer1.down;
        }
        while (pointer2 != null) {
            sortedList.appendDown(pointer2.data);
            pointer2 = pointer2.down;
        }
        return sortedList;
    }

    public void incrementNumberRepresentedByListByOne(Node head) {
        LogUtil.logIt("Incrementing List by 1", true);
        printList();
        int carry = addWithCarry(head);
        if (carry > 0) {
            Node newNode = new Node(carry);
            newNode.next = this.head;
            this.head = newNode;
        }
        LogUtil.logIt("After incrementing", true);
        printList();
    }

    private int addWithCarry(Node head) {
        if (head == null) {
            return 1;
        }

        int sum = head.data + addWithCarry(head.next);

        head.data = sum % 10;
        return sum / 10;
    }

    public void reverseListWith2Pointers() {
        LogUtil.logIt("Reversing the list using 2 pointers");
        printList();
        Node curr = head;
        Node next;

        while (curr != null && curr.next != null) {
            next = curr.next;
            curr.next = next.next;
            next.next = head;
            head = next;
        }
        LogUtil.logIt("After reversing the list");
        printList();
    }

    public void quickSort(Node head, Node end) {

        if (end != null && head != end && head != end.next) {
            Node pivotRestingPosition = findPivotRestingPosition(head, end);
//            LogUtil.logIt("After sorting on this pass", true);
//            printList();
            quickSort(head, pivotRestingPosition.prev);
            quickSort(pivotRestingPosition.next, end);
        }
    }

    private Node findPivotRestingPosition(Node head, Node end) {
        Node tempi = head.prev;
        Node tempj;
        Node pivot = end;
        for (tempj = head; tempj != end; tempj = tempj.next) {
            if (tempj.data <= pivot.data) {
                tempi = tempi == null ? head : tempi.next; // Doing i++

                // Swap i and j
                int temp = tempi.data;
                tempi.data = tempj.data;
                tempj.data = temp;
            }
        }
        tempi = tempi == null ? head : tempi.next; // Doing i++
        // Swap i and j
        int temp = tempi.data;
        tempi.data = tempj.data;
        tempj.data = temp;

        return tempi;
    }

    public void reverseDoublyLinkedList() {
        LogUtil.logIt("Reversing Doubly Linked List");
        printList();
        Node curr = head;
        Node prev = null;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            curr.prev = next;
            prev = curr;
            curr = next;
        }
        this.head = prev;

        LogUtil.logIt("After reversing");
        printList();
    }

    public static void main(String[] args) {
        LinkedListUtil list = new LinkedListUtil();

        list.append(10);
        list.append(15);
        list.append(12);
        list.append(13);
        list.append(20);
        list.append(14);

        LogUtil.logIt("Current List ", true);

        list.printList();

        LogUtil.logIt("Swapping 12 and 20", true);
        list.swapNodesWithoutSwappingData(12, 20);

        list = new LinkedListUtil();
        list.appendAll(1, 2, 3);

        LogUtil.logIt("Current list", true);
        list.printList();
        LogUtil.logIt("Swapping 1 and 2", true);
        list.swapNodesWithoutSwappingData(1, 2);

        list = new LinkedListUtil();
        list.appendAll(1, 2, 3);

        LogUtil.logIt("Swapping 1 and 3", true);
        list.swapNodesWithoutSwappingData(1, 3);

        list = new LinkedListUtil();
        list.appendAll(1, 2, 3);
        LogUtil.logIt("Swapping 2 and 3", true);
        list.swapNodesWithoutSwappingData(2, 3);


        list = new LinkedListUtil();
        list.appendAll(1, 2, 3, 4, 5);

        LogUtil.logIt("Middle element of list is " + list.getMiddleElementOfList(), true);
        list.printList();

        list = new LinkedListUtil();
        list.appendAll(1, 2, 3, 4, 5, 6);

        LogUtil.logIt("Middle element of list is " + list.getMiddleElementOfList(), true);
        list.printList();

        LogUtil.logIt("Get 1st Element from last " + list.getElementFromLast(1), true);
        LogUtil.logIt("Get 2nd Element from last " + list.getElementFromLast(2), true);
        LogUtil.logIt("Get 3rd Element from last " + list.getElementFromLast(3), true);

        LogUtil.logIt("Reversing this list ", true);
        list.reverseList();

        LogUtil.logIt("Reversing it again recursively ", true);
        list.head = list.reverseListRecursively(list.head, null);

        list.printList();

        LogUtil.logIt("Size of the list is " + list.getSizeOfList(list.head), true);


        list = new LinkedListUtil();
        list.appendAll(3, 6, 9, 15, 30);

        LinkedListUtil list2 = new LinkedListUtil();
        list.appendAll(10, 15, 30);

        LogUtil.logIt("Intersection of 2 list is " + list.getIntersectionOfList(list.head, list2.head), true);

        LogUtil.logIt("Remove duplicates from Sorted List", true);
        list = new LinkedListUtil();
        list.appendAll(11, 11, 11, 21, 43, 43, 60);

        list.removeDuplicatesFromSortedList();

        list = new LinkedListUtil();
        list.appendAll(11, 11, 11, 13, 13, 20);

        list.removeDuplicatesFromSortedList();

        list = new LinkedListUtil();
        list.appendAll(1, 2, 3, 4, 5, 6);
        list.reverseListInGroup(4);

        list = new LinkedListUtil();
        list.appendAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        list.reverseAlternativeKNode(list.head, 9);


        list = new LinkedListUtil();
        list.appendAll(1, 2, 3, 4, 5, 6);
        list.deleteNodesWithGreaterValueOnRightSide(list.head);

        list = new LinkedListUtil();
        list.appendAll(12, 15, 10, 11, 5, 6, 2, 3);
        list.deleteNodesWithGreaterValueOnRightSide(list.head);

        list = new LinkedListUtil();
        list.appendAll(17, 15, 8, 12, 10, 5, 4, 1, 7, 6);
        list.segregateEvenAndOddNodes();

        LinkedListUtil firstList = new LinkedListUtil();
        firstList.pushAllToFront(3, 6, 5);

        LinkedListUtil secondList = new LinkedListUtil();
        secondList.pushAllToFront(2, 4, 8);

        addNumbers(firstList, secondList);

        firstList = new LinkedListUtil();
        secondList = new LinkedListUtil();

        firstList.pushAllToFront(6, 4, 9, 5, 7);
        secondList.pushAllToFront(4, 8);

        addNumbers(firstList, secondList);

        firstList = new LinkedListUtil();
        secondList = new LinkedListUtil();
        LinkedListUtil thirdList = new LinkedListUtil();

        firstList.appendAll(12, 6, 29);
        secondList.appendAll(23, 5, 8);
        thirdList.appendAll(90, 20, 59);

        firstList.findTriplet(firstList, secondList, thirdList, 101);


        // Flattening a List.
        firstList = new LinkedListUtil();
        secondList = new LinkedListUtil();
        thirdList = new LinkedListUtil();
        LinkedListUtil fourthList = new LinkedListUtil();

        firstList.appendAllToDown(5, 7, 8, 30);
        secondList.appendAllToDown(10, 20);
        thirdList.appendAllToDown(19, 22, 50);
        fourthList.appendAllToDown(28, 35, 40, 45);

        firstList.appendAllNodes(secondList, thirdList, fourthList);

        // Now we have a setup ready so we can do flattening of the list
        firstList.flattenList(firstList);

        firstList = new LinkedListUtil();
        firstList.appendAll(1, 9, 9, 9);
        firstList.incrementNumberRepresentedByListByOne(firstList.head);

        // Now let's reverse the list using 2 pointers
        firstList = new LinkedListUtil();
        firstList.appendAll(1, 2, 3, 4, 5, 6);
        firstList.reverseListWith2Pointers();


        // Doubly Linked List
        LogUtil.logIt("Appending Data in Doubly Linked List");

        firstList = new LinkedListUtil();
        firstList.appendAllInDoublyLinkedLinkedList(30, 3, 4, 20, 5);

        Node temp = firstList.head;
        while (temp.next != null) {
            temp = temp.next;
        }
        LogUtil.logIt("Let's do Quick Sort in Doubly Linked List");
        firstList.printList();
        firstList.quickSort(firstList.head, temp);
        LogUtil.logIt("After Sorting");
        firstList.printList();

        firstList.reverseDoublyLinkedList();

    }

}
