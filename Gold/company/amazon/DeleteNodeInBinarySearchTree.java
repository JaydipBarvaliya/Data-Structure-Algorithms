package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

public class DeleteNodeInBinarySearchTree
{

  public static void main( String[] args )
  {
    Node root = new Node( 10 );
    root.left = new Node( 5 );
    root.right = new Node( 40 );
    root.left.left = new Node( 1 );
    root.left.right = new Node( 7 );
    root.right.right = new Node( 50 );

    BinaryTree.inorder( root, true );

    deleteNode( root, 10 );
    BinaryTree.inorder( root, true );

  }

  public static Node deleteNode( Node root, int nodeToBeDeleted )
  {
    if( root == null )
    { return null; }

    if( root.data > nodeToBeDeleted )
    {
      root.left = deleteNode( root.left, nodeToBeDeleted );
    }
    else if( root.data < nodeToBeDeleted )
    {
      root.right = deleteNode( root.right, nodeToBeDeleted );
    }
    else // this is the node to be deleted
    {
      if( root.left == null )
      { return root.right; }

      if( root.right == null )
      { return root.left; }

      // Now since we have reached here so that means we have to delete a Node which has 2 child
      root.data = findInorderSuccessor( root.right );

      root.right = deleteNode( root.right, root.data ); // this is so that the inorder successor shouldn't be duplicated
    }
    return root;
  }

  // Simple it's the smallest node in the right subtree, and since it's bst so it has to definitely reside in
  // the left most node in the right side
  // We are assuming the node which is to deleted has both child because that's when you have to actually find
  // inorder successor else we could have just replaced with left or right child respectively
  public  static Integer findInorderSuccessor( Node root )
  {
    Node temp = root;
    while( temp.left != null )
    {
      temp = temp.left;
    }
    return temp.data; // Our Inorder successor
  }
}
