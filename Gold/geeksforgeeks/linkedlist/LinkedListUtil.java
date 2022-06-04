package com.geeksforgeeks.linkedlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LinkedListUtil {
    public Node head;

    public class Node {
        int data;
        Node next;
        int size;
        Node down; // ===> For Flattening Purpose


        public Node(int data) {
            this.data = data;
        }

        public Node() {
        }
    }

    public void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        newNode.next = head;
        head = newNode;
    }

    public void appendDown(Node nodeOnWhichDataToBeAppended, int data) {
        Node newNode = new Node(data);
        Node temp = nodeOnWhichDataToBeAppended;
        if (head == null) {
            head = nodeOnWhichDataToBeAppended;
            return;
        }
        while (temp.down != null) {
            temp = temp.down;
        }
        temp.down = newNode;
    }

    public void append(int data) {
        Node newNode = new Node(data);
        Node temp = head;
        if (head == null) {
            head = newNode;
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public void append(Node newNode) {
        Node temp = head;
        if (head == null) {
            head = newNode;
            return;
        }
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    public int getSize(Node head) {
        if (head == null) {
            return 0;
        }
        return 1 + getSize(head.next);
    }

    public void deleteKey(int dataToBeDeleted) {
        Node temp = head;
        Node prev = null;
        while (temp != null) {
            if (temp.data == dataToBeDeleted) {
                if (prev == null) { // List with only 1 node and we are deleting that node
                    if (temp.next != null) {
                        head = temp.next;
                    } else {
                        head = null;
                    }
                    break;
                } else {
                    prev.next = temp.next;
                }
            }
            prev = temp;
            temp = temp.next;
        }
        printList(head);
    }

    public static Node getMiddleElementOfList(Node head) {
        Node slowPointer = head;
        Node fastPointer = head;

        if (head == null) {
            return head;
        }

        while (fastPointer != null) {

            fastPointer = fastPointer.next;
            if (fastPointer != null && fastPointer.next != null) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
            }
        }
        System.out.println("===========================Finding the Middle element of list : ");
        printList(head);
        System.out.println("The Middle Element of the list is " + slowPointer.data);
        return slowPointer;
    }

    public static void printList(Node head) {
        List<Integer> items = new ArrayList<>();
        while (head != null && head != null) {
            items.add(head.data);
            head = head.next;
        }
        System.out.println(items);
    }

    public void printSize(Node head) {
        System.out.println("Size of List is " + getSize(head));
    }

    public void swapNodesWithoutSwappingData(int A, int B) {
        Node prevA = null;
        Node nodeA = head;
        Node prevB = null;
        Node nodeB = head;
        Boolean nodeAPresent = false;
        Boolean nodeBPresent = false;

        while (nodeA != null && nodeB != null && (!nodeAPresent || !nodeBPresent)) {

            if (!nodeAPresent) {
                if (nodeA.data == A) {
                    nodeAPresent = true;
                } else {
                    prevA = nodeA;
                    nodeA = nodeA.next;
                }
            }

            if (!nodeBPresent) {
                if (nodeB.data == B) {
                    nodeBPresent = true;
                } else {
                    prevB = nodeB;
                    nodeB = nodeB.next;
                }
            }
        }

        if (nodeAPresent && nodeBPresent) {

            if (prevA == null) {
                head = nodeB;
            } else {
                prevA.next = nodeB;
            }

            if (prevB == null) {
                head = nodeA;
            } else {
                prevB.next = nodeA;
            }

            Node temp = nodeA.next;
            nodeA.next = nodeB.next;
            nodeB.next = temp;

            System.out.println("Swap done and after swapping " + A + "," + B + " from the list is :");
            printList(head);
        } else {
            System.out.println("=====================Node(s) doesn't exist in the list=========================");
        }

    }

    public static void printKthElementFromLast(Node head, int k) {
        Node lazyPointer = head;
        Node fastPointer = head;
        int count = 0;
        while (fastPointer != null) {
            while (count++ < k) {
                fastPointer = fastPointer.next;
                if (fastPointer == null) {
                    System.out.println("Invalid value of " + k);
                    break;
                }
            }
            if (fastPointer != null) {
                lazyPointer = lazyPointer.next;
                fastPointer = fastPointer.next;
            }

        }
        System.out.println("=================Finding the kth :: " + k + " Element from Last================");
        printList(head);
        System.out.println(lazyPointer.data);
    }

    public static void reverseList(Node head) {
        Node prev = null;
        Node temp = head;
        Node next;
        while (temp != null) {
            next = temp.next;
            temp.next = prev;
            prev = temp;
            temp = next;
        }
        printList(prev);
    }

    public static Node reverseListRecursively(Node head, Node prev) {
        if (head == null) {
            return prev;
        }
        Node next = head.next;
        head.next = prev;
        return reverseListRecursively(next, head);
    }

    public static Boolean listHasLoop(Node head) {
        Boolean hasLoop = false;
        Node slowPointer = head;
        Node fastPointer = head;

        while (fastPointer != null && fastPointer.next != null) {
            fastPointer = fastPointer.next.next;
            slowPointer = slowPointer.next;
            if (fastPointer == slowPointer) {
                hasLoop = true;
                break;
            }
        }
        return hasLoop;
    }

    /**
     * SortedMerge() function that takes two lists, each of which is sorted in increasing order,
     * and merges the two together into one list which is in increasing order.
     * <p>
     * 5->10->15   +   2->3->20  = 2->3->5->10->15->20.
     *
     * @param headA
     * @param headB
     */
    public Node sortedMerge(Node headA, Node headB) {
        Node tempA = headA;
        Node tempB = headB;
        LinkedListUtil util = new LinkedListUtil();

        while (tempA != null && tempB != null) {
            if (tempA.data < tempB.data) {
                util.append(new Node(tempA.data));
                tempA = tempA.next;
            } else {
                util.append(new Node(tempB.data));
                tempB = tempB.next;
            }
        }

        while (tempA != null) {
            util.append(new Node(tempA.data));
            tempA = tempA.next;
        }
        while (tempB != null) {
            util.append(new Node(tempB.data));
            tempB = tempB.next;
        }
        return util.head;
    }

    /**
     * Given a singly linked list of characters, write a function that returns true if the given list is palindrome, else false.
     * <p>
     * R -> A -> D -> A -> R
     * <p>
     * M -> A -> D -> D -> A -> M
     *
     * @param head
     * @return
     */
    public Boolean isPalindromicList(Node head) {
        Boolean isPalindromicList = false;

        int size = getSize(head);
        Node firstHalf = head;
        firstHalf.size = size / 2;

        Node middleElement = getMiddleElementOfList(head);

        // Here taking care of odd and even list, if Odd length list then we should reverse the list after the middle element else start from the middle element itself
        Node secondHalf = reverseListRecursively(size % 2 == 0 ? middleElement : middleElement.next, null);
        if (compareLists(firstHalf, secondHalf)) {
            isPalindromicList = true;
        }
        return isPalindromicList;
    }

    public Boolean compareLists(Node headA, Node headB) {
        Boolean isEqual = true;
        int counterA = 0;
        int sizeA = headA.size;
        while (headA != null && counterA < sizeA && headB != null) {
            if (headA.data == headB.data) {
                headA = headA.next;
                headB = headB.next;
                counterA++;
                continue;
            }
            isEqual = false;
            break;
        }
        if (headA != null && headB != null) {
            isEqual = false;
        }
        return isEqual;
    }

    public static Boolean isPalindromicListUsingStack(Node head) {

        Boolean isPalindromicList = true;
        Node slowPointer = head;
        Node fastPointer = head;
        Stack<Integer> stack = new Stack<>();
        Boolean isEvenList = false;

        while (fastPointer != null && fastPointer.next != null) {
            stack.push(slowPointer.data);
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        if (fastPointer != null && fastPointer.next == null) {  // i.e Odd length List we compare from Next of middle item
            slowPointer = slowPointer.next;
        }

        while (!stack.isEmpty()) {
            if (slowPointer.data == stack.peek()) {
                slowPointer = slowPointer.next;
                stack.pop();
                continue;
            }
            isPalindromicList = false;
            break;
        }
        return isPalindromicList;
    }

    /**
     * 11->11->11->21->43->43->60
     * <p>
     * 11->21->43->60
     *
     * @param head
     */
    public void removeDuplicateFromSortedList(Node head) {
        Node current = head;
        Node nextOfCurrentNext;

        if (current == null) {
            System.out.println("List is Empty");
            return;
        }

        while (current.next != null) {
            if (current.data == current.next.data) {
                nextOfCurrentNext = current.next.next;
                current.next = null;
                current.next = nextOfCurrentNext;
            } else {
                current = current.next;
            }
        }
        printList(head);
    }


    /**
     * 12->11->12->21->41->43->21
     * <p>
     * 12->11->21->41->43
     *
     * @param head
     */
    public void removeDuplicateFromUnsortedList(Node head) {
        if (head == null) {
            System.out.println("List is Empty");
            return;
        }
        Node current = head;
        Node nextOfCurrentsNext;
        List<Integer> visitedNodes = new ArrayList<>();
        visitedNodes.add(head.data);

        while (current.next != null) {
            if (visitedNodes.contains(current.next.data)) {
                visitedNodes.add(current.data);
                nextOfCurrentsNext = current.next.next;
                current.next = null;
                current.next = nextOfCurrentsNext;
            } else {
                current = current.next;
                visitedNodes.add(current.data);
            }
        }
        printList(head);
    }

    /**
     * 1->2->3->4->5 ===>  2->1->4->3->5,
     * <p>
     * <p>
     * 1->2->3->4->5->6 ==> 2->1->4->3->6->5.
     *
     * @param head
     */
    public void reverseListInGroup(Node head, int groupSize) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node current = head;
        int count;
        Node prev;
        Node next;
        Node thisHead;
        Node prevHead = null;

        while (current != null) {
            count = 0;
            thisHead = current;
            prev = null;
            while (count++ < groupSize) {
                next = current.next;
                current.next = prev;
                prev = current;
                current = next;
            }

            if (prevHead != null) {
                prevHead.next = prev;
            } else {
                head = prev; // Since Initial swap the first element becomes last element of the first chunk
            }
            prevHead = thisHead;
        }

        printList(head);

    }


    /**
     * Delete alternate nodes of a Linked List
     * Given a Singly Linked List, starting from the second node delete all alternate nodes of it.
     * For example, if the given linked list is 1->2->3->4->5 then your function should convert it to 1->3->5,
     * and if the given linked list is 1->2->3->4 then convert it to 1->3.
     *
     * @param head
     */
    public void deleteAlternativeNode(Node head) {
        System.out.println("Let's delete Alternative Nodes of below list");
        printList(head);
        Node temp = head;
        Node next = null;
        while (temp != null && temp.next != null) {
            next = temp.next.next;
            temp.next = next;
            temp = next;
        }
        printList(head);
    }


    /**
     * Reverse alternate K nodes in a Singly Linked List  ------> Difficulty  3.3
     * Given a linked list, write a function to reverse every alternate k nodes (where k is an input to the function) in an efficient way. Give the complexity of your algorithm.
     * <p>
     * Example:
     * Inputs:   1->2->3->4->5->6->7->8->9->NULL and k = 3
     * Output:   3->2->1->4->5->6->9->8->7->NULL.
     *
     * @param head
     * @param k
     */
    public void reverseAlternateKNodes(Node head, int k) {
        Node current = head;
        Node next;
        Node prev = null;
        Node thisHead;
        Node prevHead = null;
        int counter;
        Boolean shouldReverse = true;

        while (current != null) {
            thisHead = current;
            counter = 0;
            prev = null;

            if (shouldReverse) {
                while (current != null && counter++ < k) {
                    next = current.next;
                    current.next = prev;
                    prev = current;
                    current = next;
                }
                shouldReverse = false;
                if (prevHead == null) {
                    head = prev;
                } else {
                    prevHead.next = prev;
                }
                prevHead = thisHead;
            } else {
                prevHead.next = current;
                while (current != null && counter++ < k) {
                    prev = current;
                    current = current.next;
                }
                shouldReverse = true;
                prevHead = prev;
            }
        }
        printList(head);
    }

    /**
     * 1) Reverse the list
     * 2) Identify Nodes to be deleted
     * 3) Reverse the list again to get the original answer
     *
     * @param head
     */
    public static void deleteNodesHavingGreaterValueAtRightSide(Node head) {
        Node reversedList = reverseListRecursively(head, null);
        int maxTillNow = 0;
        Node temp = reversedList;
        Node prev = null;
        Node next = null;

        while (temp != null) {
            if (temp.data > maxTillNow) {
                maxTillNow = temp.data;
                prev = temp;
                temp = temp.next;
            } else {
                next = temp.next;
                prev.next = next;
                temp = next;
            }
        }
        printList(reverseListRecursively(reversedList, null));
    }


    /**
     * Input: 17->15->8->12->10->5->4->1->7->6->NULL
     * Output: 8->12->10->4->6->17->15->5->1->7->NULL
     * <p>
     * Input: 8->12->10->5->4->1->6->NULL
     * Output: 8->12->10->4->6->5->1->NULL
     * <p>
     * // If all numbers are even then do not change the list
     * Input: 8->12->10->NULL
     * Output: 8->12->10->NULL
     * <p>
     * // If all numbers are odd then do not change the list
     * Input: 1->3->5->7->NULL
     * Output: 1->3->5->7->NULL
     *
     * @param head
     */
    public static void segregateEvenAndOddNumbers(Node head) {

        Node current = head;
        Node evenHead = null, oddHead = null, lastEvenNode = null, lastOddNode = null;

        while (current != null) {
            if (current.data % 2 == 0) {
                if (evenHead == null) {
                    evenHead = lastEvenNode = current;
                } else {
                    lastEvenNode.next = current;
                    lastEvenNode = current;
                }
            } else {
                if (oddHead == null) {
                    oddHead = lastOddNode = current;
                } else {
                    lastOddNode.next = current;
                    lastOddNode = current;
                }
            }
            current = current.next;
        }

        if (evenHead != null) {
            head = evenHead;
            if (oddHead != null) {
                lastEvenNode.next = oddHead;
                lastOddNode.next = null;
            }
        } else {
            head = oddHead;
        }
        printList(head);
    }


    /**
     * Using Extended Floydd Algorithm
     *
     * @param head
     */
    public void detectAndRemoveLoop(Node head) {
        Node slowPointer = head;
        Node fastPointer = head;

        slowPointer = slowPointer.next;
        fastPointer = fastPointer.next.next;

        while (fastPointer != null && fastPointer.next != null) {
            if (slowPointer == fastPointer) {
                break;
            }
            slowPointer = slowPointer.next;
            fastPointer = fastPointer.next.next;
        }

        // Loop Exist
        if (slowPointer == fastPointer) {
            System.out.println("Yes Loop Exist");
            slowPointer = head;

            while (slowPointer.next != fastPointer.next) {
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next;
            }

            fastPointer.next = null;
        } else {
            System.out.println("No Loop Exist");
        }

        printList(head);
    }


    /**
     * Input:
     * First List: 5->6->3  // represents number 365
     * Second List: 8->4->2 //  represents number 248
     * Output
     * Resultant list: 3->1->6  // represents number 613
     * <p>
     * Input:
     * First List: 7->5->9->4->6  // represents number 64957
     * Second List: 8->4 //  represents number 48
     * Output
     * Resultant list: 5->0->0->5->6  // represents number 65005
     * <p>
     * Problem Number : 41 Linked List
     *
     * @param head
     */
    public static void addNumbersRepresentedByList(Node first, Node second) {
        Node firstTemp = first;
        Node secondTemp = second;
        int result = 0, digitFromFirst = 0, digitFromSecond = 0, carry = 0;
        LinkedListUtil resultList = new LinkedListUtil();

        while (firstTemp != null && secondTemp != null) {
            digitFromFirst = firstTemp.data;
            digitFromSecond = secondTemp.data;
            result = digitFromFirst + digitFromSecond;
            if (carry == 1) {
                result += 1;
            }
            carry = result / 10;
            resultList.append(result >= 10 ? result % 10 : result);
            firstTemp = firstTemp.next;
            secondTemp = secondTemp.next;
        }

        while (firstTemp != null) {
            digitFromFirst = firstTemp.data;
            result = carry == 1 ? digitFromFirst + 1 : digitFromFirst;
            carry = result / 10;
            resultList.append(result >= 10 ? result % 10 : result);
            firstTemp = firstTemp.next;
        }

        while (secondTemp != null) {
            digitFromSecond = secondTemp.data;
            result = carry == 1 ? digitFromSecond + 1 : digitFromSecond;
            carry = result / 10;
            resultList.append(result >= 10 ? result % 10 : result);
            secondTemp = secondTemp.next;
        }

        printList(resultList.head);
    }

    /**
     * For example, if the three linked lists are 12->6->29, 23->5->8 and 90->20->59,
     * and the given number is 101, the output should be tripel “6 5 90”.
     * <p>
     * Problem Number : 42 Linked List
     */
    public void findTriplet(Node headA, Node headB, Node headC, int sumToBeTested) {
        Node A = headA;
        Node B = headB;
        Node C = headC;

        B = mergeSort(B, false);
        C = mergeSort(C, true);

        Node tempB = B;
        Node tempC = C;

        while (A != null) {
            tempB = B;
            tempC = C;
            while (tempB != null && tempC != null) {
                int result = A.data + tempB.data + tempC.data;

                if (result == sumToBeTested) {
                    System.out.println("Triplet found " + A.data + "+" + tempB.data + "+" + tempC.data);
                    return;
                }

                if (result < sumToBeTested) {
                    tempB = tempB.next;
                } else {
                    tempC = tempC.next;
                }
            }
            A = A.next;
        }

    }


    public Node mergeSort(Node head, Boolean isReverseSort) {
        //If No Nodes are present or only 1 node is present return the same as single node or no node is already sorted
        if (head == null || head.next == null) {
            return head;
        }

        Node middle = getMiddleElementOfList(head);
        Node nextOfMiddle = middle.next;

        middle.next = null;

        Node left = mergeSort(head, isReverseSort);
        Node right = mergeSort(nextOfMiddle, isReverseSort);

        LinkedListUtil sortedList = new LinkedListUtil();
        sortedMerge(left, right, sortedList, isReverseSort);
        return sortedList.head;

    }

    private void sortedMerge(Node headOfFirstPartOfList, Node headofSecondPartOfList, LinkedListUtil sortedList, Boolean isReverseSort) {
        Node t1 = headOfFirstPartOfList;
        Node t2 = headofSecondPartOfList;

        if (isReverseSort) {
            while (t1 != null && t2 != null) {
                if (t1.data > t2.data) {
                    sortedList.append(t1.data);
                    t1 = t1.next;
                } else {
                    sortedList.append(t2.data);
                    t2 = t2.next;
                }
            }
            while (t2 != null) {
                sortedList.append(t2.data);
                t2 = t2.next;
            }
            while (t1 != null) {
                sortedList.append(t1.data);
                t1 = t1.next;
            }
        } else {
            while (t1 != null && t2 != null) {
                if (t1.data < t2.data) {
                    sortedList.append(t1.data);
                    t1 = t1.next;
                } else {
                    sortedList.append(t2.data);
                    t2 = t2.next;
                }
            }

            while (t1 != null) {
                sortedList.append(t1.data);
                t1 = t1.next;
            }

            while (t2 != null) {
                sortedList.append(t2.data);
                t2 = t2.next;
            }
        }
    }

    /**
     * Left Rotate it is
     * For example, if the given linked list is 10->20->30->40->50->60 and k is 4,
     * the list should be modified to 50->60->10->20->30->40.
     */
    public static void rotateList(Node head, int k) {
        int count = 1;
        Node temp = head;
        Node next;
        Node toBeFirstNode;
        while (++count <= k) {
            temp = temp.next;
            if (temp == null) {
                temp = head;
            }
        }
        next = temp.next;
        temp.next = null;

        if (next == null) { // This situation is possible when (K is multiple of list size) k ==  (n * size) of the list [1,2,3] k = 3,6,9 ... etc;
            next = head; // So in that situation list will always come to it's original shape even after multiple rounds of rotation.
        }

        toBeFirstNode = next;
        temp = next;

        while (temp.next != null) {
            temp = temp.next;
        }
        if (next != head) { // This situation is similar to the comment above, So (k = n * size);
            temp.next = head; // if that is the case then we shouldn't be appending the second half to the first half, because then it will make go list round and round indefinitely.
        }
        head = toBeFirstNode;
        printList(head);
    }

    /**
     * 5 -> 10 -> 19 -> 28
     * |    |     |     |
     * V    V     V     V
     * 7    20    22    35
     * |          |     |
     * V          V     V
     * 8          50    40
     * |                |
     * V                V
     * 30               45
     * <p>
     * Final Result = 5->7->8->10->19->20->22->28->30->35->40->45->50.
     * <p>
     * <p>
     * We will use SortedMerge of Merge Sort to sort 2 list at a time and finally generate the final list
     *
     * @param head
     */
    public void flattenList(Node head) {
        Node temp = head;
        Node prev = null;
        Node current = null;
        while (temp != null) {
            if (prev == null) {
                prev = getFlatList(temp);
            } else {
                current = getFlatList(temp);
                prev = getSortedList(prev, current);
            }
            temp = temp.next;
        }
        printList(prev);
    }

    public Node getFlatList(Node head) {
        LinkedListUtil flatList = new LinkedListUtil();
        Node temp = head;

        while (temp != null) {
            flatList.append(temp.data);
            temp = temp.down;
        }
        return flatList.head;
    }

    public Node getSortedList(Node firstHead, Node secondHead) {
        LinkedListUtil sortedList = new LinkedListUtil();
        Node t1 = firstHead;
        Node t2 = secondHead;
        while (t1 != null && t2 != null) {
            if (t1.data < t2.data) {
                sortedList.append(t1.data);
                t1 = t1.next;
            } else {
                sortedList.append(t2.data);
                t2 = t2.next;
            }
        }

        while (t1 != null) {
            sortedList.append(t1);
            t1 = t1.next;
        }

        while (t2 != null) {
            sortedList.append(t2);
            t2 = t2.next;
        }
        return sortedList.head;
    }

    /**
     * Add 1 to a number represented as linked list             2.5
     * Number is represented in linked list such that each digit corresponds to a node in linked list.
     * Add 1 to it. For example 1999 is represented as (1-> 9-> 9 -> 9) and adding 1 to it should change it to (2->0->0->0)
     */
    public Node addOneToNumberRepresentedByListRecursively(Node head) {

        int carry = addWithCarry(head);

        if (carry > 0) {
            Node carryLatestNode = new Node(carry);
            carryLatestNode.next = head;
            head = carryLatestNode;
        }
        return head;
    }

    /**
     * This method is the utility method which will actually do the addition of 1 to the existing number and return
     * if the No of digit will change after addition of 1. i.e 9 + 1--> 10, 99 + 1 ==> 100, 98 + 1 ==> 99
     *
     * @param head
     * @return
     */
    private int addWithCarry(Node head) {

        if (head == null) {
            return 1;
        }

        int result = head.data + addWithCarry(head.next);
        head.data = result % 10;
        return result / 10;
    }


    /**
     * Iteratively Reverse a linked list using only 2 pointers (An Interesting Method)
     * <p>
     * Input : Head of following linked list
     * 1->2->3->4->NULL
     * Output : Linked list should be changed to,
     * 4->3->2->1->NULL
     * <p>
     * Input : Head of following linked list
     * 1->2->3->4->5->NULL
     * Output : Linked list should be changed to,
     * 5->4->3->2->1->NULL
     *
     * @param head
     */
    public void reverseListWith2Pointers(Node head) {
        Node current = head;
        Node next;

        while (current.next != null) {
            next = current.next;
            current.next = next.next;
            next.next = head;
            head = next;
        }

        printList(head);
    }

    /**
     * Input:
     * M = 2, N = 2
     * Linked List: 1->2->3->4->5->6->7->8
     * Output:
     * Linked List: 1->2->5->6
     *
     * @param head
     * @param N
     * @param M
     */
    public void delete_N_Nodes_After_M_Nodes(Node head, int N, int M) {
        Node temp = head;
        int initCounter = 0;
        Node prev = head;
        while (temp != null) {
            if (initCounter == M) {
                for (int i = 0; i < N && temp != null; i++) {
                    temp = temp.next;
                }
                if (temp == null) {
                    prev.next = null;
                    break;
                } else {
                    prev.next = temp;
                }
                initCounter = 0;
            }
            prev = temp;
            temp = temp.next;
            initCounter++;
        }
    }


    public static void main(String[] args) {
        LinkedListUtil util = new LinkedListUtil();
        util.insert(5);
        util.insert(4);
        util.insert(3);
        util.insert(2);
        util.insert(1);
//
//        util.append(6);
//        util.append(7);
//        util.append(8);
//        util.append(9);
//        util.append(10);


        printList(util.head);

        util.printSize(util.head);

//        util.deleteKey(10);

//        util.swapNodesWithoutSwappingData(2, 9);
//        util.swapNodesWithoutSwappingData(5, 5);
//        util.swapNodesWithoutSwappingData(5, 91);
//        util.swapNodesWithoutSwappingData(4, 1);

        getMiddleElementOfList(util.head);


        getMiddleElementOfList(util.head);

        printKthElementFromLast(util.head, 2);


//        Node head = new LinkedListUtil().new Node(4);
        System.out.println("-=============================Lets Reverse List-=============================");
        printList(util.head);
        //   reverseListRecursively(util.head, null);

//        util.printSize(util.head);

        util.head = reverseListRecursively(util.head, null);
        printList(util.head);


        // Check List has a loop.
        System.out.println("=======================Let's check List has a loop=========================");
        LinkedListUtil util2 = new LinkedListUtil();
        Node node2 = new LinkedListUtil().new Node(2);
        util2.append(new LinkedListUtil().new Node(1));
        util2.append(node2);
        util2.append(new LinkedListUtil().new Node(3));
        util2.append(new LinkedListUtil().new Node(4));
        util2.append(new LinkedListUtil().new Node(5));
        util2.append(node2);
        System.out.println("List has loop ::===> " + listHasLoop(util2.head));


        //=======================> Sorted Merge <========================
        System.out.println("=======================Let's do Sorted Merge=========================");
        LinkedListUtil sortedListA = new LinkedListUtil();
        LinkedListUtil sortedListB = new LinkedListUtil();
        LinkedListUtil sortedMergeUtil = new LinkedListUtil();

        sortedListA.append(5);
        sortedListA.append(10);
        sortedListA.append(15);

        sortedListB.append(2);
        sortedListB.append(3);
        sortedListB.append(20);

        printList(sortedListA.head);
        printList(sortedListB.head);
        sortedMergeUtil.head = sortedMergeUtil.sortedMerge(sortedListA.head, sortedListB.head);

        printList(sortedMergeUtil.head);

        //=======================> Palindromic List <========================
        System.out.println("=======================Let's play with Palindrome=========================");
        LinkedListUtil palindromeList = new LinkedListUtil();

        palindromeList.append(1);
        palindromeList.append(2);
        palindromeList.append(4);
        palindromeList.append(4);
        palindromeList.append(2);
        palindromeList.append(1);

        printList(palindromeList.head);

        System.out.println("Is List palindromic ? " + util.isPalindromicListUsingStack(palindromeList.head));


        // Lets remove the duplicates
        System.out.println("=======================Let's remove duplicate from the sorted list=========================");
        LinkedListUtil duplicateSortedList = new LinkedListUtil();
        duplicateSortedList.append(11);
        duplicateSortedList.append(11);
        duplicateSortedList.append(11);
        duplicateSortedList.append(21);
        duplicateSortedList.append(43);
        duplicateSortedList.append(43);
        duplicateSortedList.append(60);

        printList(duplicateSortedList.head);
        duplicateSortedList.removeDuplicateFromSortedList(duplicateSortedList.head);

        System.out.println("=======================Let's remove duplicate from the unsorted list=========================");
        LinkedListUtil duplicateUnsortedList = new LinkedListUtil();
        duplicateUnsortedList.append(12);
        duplicateUnsortedList.append(11);
        duplicateUnsortedList.append(12);
        duplicateUnsortedList.append(21);
        duplicateUnsortedList.append(41);
        duplicateUnsortedList.append(43);
        duplicateUnsortedList.append(21);


        printList(duplicateUnsortedList.head);
        duplicateUnsortedList.removeDuplicateFromUnsortedList(duplicateUnsortedList.head);


        LinkedListUtil pairWiseSwap = new LinkedListUtil();
        pairWiseSwap.append(1);
        pairWiseSwap.append(2);
        pairWiseSwap.append(3);
        pairWiseSwap.append(4);
        pairWiseSwap.append(5);
        pairWiseSwap.append(6);
        pairWiseSwap.append(7);
        pairWiseSwap.append(8);
        pairWiseSwap.append(9);
        pairWiseSwap.append(10);
        pairWiseSwap.append(11);
        pairWiseSwap.append(12);

        pairWiseSwap.reverseListInGroup(pairWiseSwap.head, 3);

        LinkedListUtil deleteAlternativeNodes = new LinkedListUtil();
        deleteAlternativeNodes.append(1);
        deleteAlternativeNodes.append(2);
        deleteAlternativeNodes.append(3);
        deleteAlternativeNodes.append(4);
//        deleteAlternativeNodes.append(5);

//        deleteAlternativeNodes.deleteAlternativeNode(deleteAlternativeNodes.head);


        System.out.println("=================deleteNodesHavingGreaterValueAtRightSide=========================");
        LinkedListUtil deleteNodesGreaterValueRightSide = new LinkedListUtil();
        deleteNodesGreaterValueRightSide.append(12);
        deleteNodesGreaterValueRightSide.append(15);
        deleteNodesGreaterValueRightSide.append(10);
        deleteNodesGreaterValueRightSide.append(11);
        deleteNodesGreaterValueRightSide.append(5);
        deleteNodesGreaterValueRightSide.append(6);
        deleteNodesGreaterValueRightSide.append(2);
        deleteNodesGreaterValueRightSide.append(3);

        printList(deleteNodesGreaterValueRightSide.head);
        deleteNodesGreaterValueRightSide.deleteNodesHavingGreaterValueAtRightSide(deleteNodesGreaterValueRightSide.head);


        System.out.println("=================SEGREGATE ODD AND EVEN NODES=========================");
        LinkedListUtil segregateOddEven = new LinkedListUtil();
        segregateOddEven.append(17);
        segregateOddEven.append(15);
        segregateOddEven.append(8);
        segregateOddEven.append(12);
        segregateOddEven.append(10);
        segregateOddEven.append(5);
        segregateOddEven.append(4);
        segregateOddEven.append(1);
        segregateOddEven.append(7);
        segregateOddEven.append(6);

        printList(segregateOddEven.head);
        segregateOddEven.segregateEvenAndOddNumbers(segregateOddEven.head);


        System.out.println("=============Detect and Remove Loop");
        LinkedListUtil detectAndRemoveLoop = new LinkedListUtil();
        detectAndRemoveLoop.head = new LinkedListUtil().new Node(1);
        detectAndRemoveLoop.head.next = new LinkedListUtil().new Node(2);
        detectAndRemoveLoop.head.next.next = new LinkedListUtil().new Node(3);
        detectAndRemoveLoop.head.next.next.next = new LinkedListUtil().new Node(4);
        detectAndRemoveLoop.head.next.next.next.next = new LinkedListUtil().new Node(5);

        // Adding the loop for testing
        detectAndRemoveLoop.head.next.next.next.next.next = detectAndRemoveLoop.head.next;

        detectAndRemoveLoop.detectAndRemoveLoop(detectAndRemoveLoop.head);


        System.out.println("======================addNumbersRepresentedByList==================");
        LinkedListUtil firstList = new LinkedListUtil();
        firstList.append(7);
        firstList.append(5);
        firstList.append(9);
        firstList.append(4);
        firstList.append(6);

        LinkedListUtil secondList = new LinkedListUtil();
        secondList.append(8);
        secondList.append(4);

        firstList.addNumbersRepresentedByList(firstList.head, secondList.head);


        System.out.println("===================== let's do Sorting=========================");
        LinkedListUtil mergeSortUtil = new LinkedListUtil();
        mergeSortUtil.append(5);
        mergeSortUtil.append(4);
        mergeSortUtil.append(3);
        mergeSortUtil.append(2);
        mergeSortUtil.append(1);

        printList(mergeSortUtil.head);
        mergeSortUtil.head = mergeSortUtil.mergeSort(mergeSortUtil.head, true);
        printList(mergeSortUtil.head);

        System.out.println("================Let's find a triplet=======================");
        LinkedListUtil A = new LinkedListUtil();
        A.append(12);
        A.append(6);
        A.append(29);

        LinkedListUtil B = new LinkedListUtil();
        B.append(23);
        B.append(5);
        B.append(8);

        LinkedListUtil C = new LinkedListUtil();
        C.append(90);
        C.append(20);
        C.append(59);

        A.findTriplet(A.head, B.head, C.head, 101);

        System.out.println("================Let's Rotate the Node====================");
        LinkedListUtil rotateList = new LinkedListUtil();
        rotateList.append(10);
        rotateList.append(20);
        rotateList.append(30);
        rotateList.append(40);
        rotateList.append(50);
        rotateList.append(60);
        printList(rotateList.head);
        rotateList.rotateList(rotateList.head, 13);


        // =======================Let's Flatten the list =================================
        System.out.println("=======================Let's Flatten the list =================================");
        LinkedListUtil mainList = new LinkedListUtil();

        mainList.append(5);
        mainList.append(10);
        mainList.append(19);
        mainList.append(28);

        mainList.appendDown(mainList.head, 7);
        mainList.appendDown(mainList.head, 8);
        mainList.appendDown(mainList.head, 30);

        mainList.appendDown(mainList.head.next, 20);

        mainList.appendDown(mainList.head.next.next, 22);
        mainList.appendDown(mainList.head.next.next, 50);

        mainList.appendDown(mainList.head.next.next.next, 35);
        mainList.appendDown(mainList.head.next.next.next, 40);
        mainList.appendDown(mainList.head.next.next.next, 45);

        mainList.flattenList(mainList.head);


        System.out.println("===================Add One to the carry ===============================");
        LinkedListUtil addOneUtil = new LinkedListUtil();

        addOneUtil.append(9);
        addOneUtil.append(6);
        addOneUtil.append(9);
        addOneUtil.append(9);
        addOneUtil.append(9);

        addOneUtil.head = addOneUtil.addOneToNumberRepresentedByListRecursively(addOneUtil.head);

        printList(addOneUtil.head);


        addOneUtil = new LinkedListUtil();
        addOneUtil.append(9);
        addOneUtil.append(9);
        addOneUtil.append(9);
        addOneUtil.append(9);

        addOneUtil.head = addOneUtil.addOneToNumberRepresentedByListRecursively(addOneUtil.head);

        printList(addOneUtil.head);

        System.out.println("================Reverse the list using 2 pointers=========================");

        LinkedListUtil reverseUtil = new LinkedListUtil();
        reverseUtil.append(1);
        reverseUtil.append(2);
        reverseUtil.append(3);
        reverseUtil.append(4);
        reverseUtil.append(5);

        reverseUtil.reverseListWith2Pointers(reverseUtil.head);


        System.out.println("==================Delete N nodes after M nodes of a linked list=====================");
        LinkedListUtil deleteNodesUtil = new LinkedListUtil();
        deleteNodesUtil.append(1);
        deleteNodesUtil.append(2);
        deleteNodesUtil.append(3);
        deleteNodesUtil.append(4);
        deleteNodesUtil.append(5);
        deleteNodesUtil.append(6);
        deleteNodesUtil.append(7);
        deleteNodesUtil.append(8);

        printList(deleteNodesUtil.head);
        deleteNodesUtil.delete_N_Nodes_After_M_Nodes(deleteNodesUtil.head, 2, 2);
        printList(deleteNodesUtil.head);

    }
}
