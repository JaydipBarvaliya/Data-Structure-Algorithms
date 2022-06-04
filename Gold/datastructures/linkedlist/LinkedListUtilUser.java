package com.datastructures.linkedlist;

import static java.lang.System.*;

/**
 * Created by jaine03 on 29/06/17.
 */
public class LinkedListUtilUser {

    public static void main(String[] args) {

        LinkedListUtil list1 = new LinkedListUtil();

        // Inserting into first List
        list1.append(1);
        list1.append(2);
        list1.append(3);
        list1.append(6);
        list1.append(9);
        list1.append(15);
        list1.append(30);

        /*out.println("Before Reverse operation");
        list1.traverseList();
        list1.reverseList();
        out.println("After Reverse operation");
        list1.traverseList();
        out.println("After Recursive Reverse operation");
        list1.reverseListRecursively(list1.head, null);

        list1.traverseList();*/


//        list1 = new LinkedListUtil();
//        list1.append(1);
//        list1.append(2);
//        list1.append(3);
//        list1.append(2);
//        list1.append(1);
//
//        out.println(list1.isPalindromicList());
//
//        System.exit(0);
//
//        list1.checkLoopExistInListWithFloydAlgo();
//
//        LinkedListUtil firstSortedList = new LinkedListUtil();
//        LinkedListUtil secondSortedList = new LinkedListUtil();
//        LinkedListUtil sortedList = new LinkedListUtil();
//
//        firstSortedList.append(5);
//        firstSortedList.append(10);
//        firstSortedList.append(15);
//
//        secondSortedList.append(2);
//        secondSortedList.append(3);
//        secondSortedList.append(20);
//
//        firstSortedList.traverseList();
//        secondSortedList.traverseList();
//
//        out.println("After Sorted Merge");
//        //firstSortedList.sortedMerge(firstSortedList.head, secondSortedList.head, sortedList);
//        sortedList.head = firstSortedList.sortedMergeRecursively(firstSortedList.head,secondSortedList.head,sortedList.head);
//        sortedList.traverseList();


//        list1 = new LinkedListUtil();
//        list1.append(1);
//        list1.append(2);
//        list1.append(3);
//        list1.append(4);
//        list1.append(5);
//        list1.append(6);
//
//        //list1.swapPairsInList();
//
//        list1.traverseList();
//
//        LinkedListUtil sublist1 = new LinkedListUtil();
//        LinkedListUtil sublist2 = new LinkedListUtil();
//        list1.alternativeSplit(sublist1,sublist2);
//
//        sublist1.traverseList();
//        sublist2.traverseList();


        list1 = new LinkedListUtil();
        list1.append(5);
        list1.append(4);
        list1.append(3);
        list1.append(2);
        list1.append(1);

        list1.traverseList();

        System.out.println(list1.getMiddleNode(list1.head).data);

        out.println("Before Sorting");
        list1.traverseList();
        out.println("After Sorting");
        list1.head = list1.mergeSort(list1.head);
        list1.traverseList();

        list1 = new LinkedListUtil();
        list1.append(1);
        list1.append(2);
        list1.append(3);
        list1.append(4);
        list1.append(5);
        list1.append(6);
//        list1.append(7);
//        list1.append(8);
//        list1.append(9);
//        list1.append(10);
//        list1.append(11);
//        list1.append(12);

        list1.traverseList();
           // list1.reverseEveryKNodeIteratively(2);
//        list1.reverseEveryKNodeIteratively(2);
//        list1.traverseList();

//        list1.reverseEveryKAlternativeNodeIteratively(3);
//        list1.traverseList();

       list1.swapEveryTwoNodes();
        list1.traverseList();

    }
}
