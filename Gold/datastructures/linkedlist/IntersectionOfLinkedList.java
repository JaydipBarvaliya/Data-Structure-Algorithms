package com.datastructures.linkedlist;

/**
 * Created by jaine03 on 17/04/17.
 */
public class IntersectionOfLinkedList {


    /**
     * First we will find the difference b/w the length of 2 list
     * then after that traverse the bigger list upto the difference number of times,
     * afterwards start traversing both the list and compare the element, return data if intersected else return -1
     * @param list1
     * @param list2
     * @return
     */
    public int findIntersection(LinkedListUtil list1,LinkedListUtil list2){
        int difference = Math.abs(list1.length() - list2.length());
        return findIntersection(difference, list1.head, list2.head);
    }

    public int findIntersection(int difference,LinkedListUtil.Node bigList,LinkedListUtil.Node smallList){

        // Traversing upto the list difference
        for(int i=0;i<difference;i++){
            bigList = bigList.next;
        }

        while (bigList != null && smallList != null){
            if(bigList.data == smallList.data)
                return bigList.data;
            bigList = bigList.next;
            smallList = smallList.next;
        }

        return -1;
    }

    public static void main(String[] args) {

        LinkedListUtil list1 = new LinkedListUtil();
        LinkedListUtil list2 = new LinkedListUtil();

        // Inserting into first List
        list1.append(1);
        list1.append(2);
        list1.append(3);
        list1.append(6);
        list1.append(9);
        list1.append(15);
        list1.append(30);

        list1.traverseList();

        // Inserting into Second List
        list2.append(10);
        list2.append(15);
        list2.append(30);

        list2.traverseList();

//        IntersectionOfLinkedList linkedList = new IntersectionOfLinkedList();
//        System.out.println("Intersection of List is "+linkedList.findIntersection(list1,list2));

        System.out.println("Middle Element of first list "+ list1.getMiddleElement());
        System.out.println("Nth Node from the End "+ list1.getNthNodeFromEnd(5));
    }
}
