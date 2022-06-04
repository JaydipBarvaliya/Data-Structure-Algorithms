package com.datastructures.linkedlist;

/**
 * Created by jaine03 on 17/04/17.
 */
public class InsertNodeInList {

  public static void main( String[] args ) {

      LinkedListUtil linkedListUtil = new LinkedListUtil();

      linkedListUtil.append(3);
      linkedListUtil.append(4);
      linkedListUtil.append(5);
      linkedListUtil.append(6);
      linkedListUtil.append(7);

      // Length of List
      System.out.println("Length of List is "+linkedListUtil.length());

      //Traversing the List
      linkedListUtil.traverseList();

      linkedListUtil.push( 2 );
      linkedListUtil.push( 1 );
      linkedListUtil.push( 10 );

      linkedListUtil.traverseList();

      linkedListUtil.appendAfterGivenNode(3,8);
      linkedListUtil.traverseList();

      // Length of List
      System.out.println("Length of List is "+linkedListUtil.length());

      // Deleting a Node
      //linkedListUtil.deleteNode(7);
      linkedListUtil.deleteByPosistion(1);
      linkedListUtil.traverseList();

      System.out.println("Length of List"+linkedListUtil.getLengthRecursively(linkedListUtil.head));

      System.out.println("Do Element 8 exists ?"+linkedListUtil.findElement(linkedListUtil.head,8));

      // Find Data at particular index recursively

      System.out.println("Data at 2nd Index is :"+linkedListUtil.getNth(7,linkedListUtil.head));
  }
}
