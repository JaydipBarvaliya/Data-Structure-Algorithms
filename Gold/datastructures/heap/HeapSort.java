package com.datastructures.heap;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by jaine03 on 15/07/17.
 */
public class HeapSort {
	String traverse = "" ;		//to store inorder traversal
	Node root;
	public class Node{
		int data;
		Node left,right;
		Node(int d){
			data = d;
			left = right = null;
		}
	}

//this function will create a balanced tree
	public void createheap(Node node, int d){
		Node n = new Node(d);
		if (root == null ){
			root = n;
			return;
		}
		Queue<Node> q = new LinkedList<>();
		q.add(root);
		while(!q.isEmpty()){
			node = q.remove();
			if (node.left == null){
				node.left = n;
				return;
			}
			else q.add(node.left);

			if (node.right == null)	{
				node.right = n;
				return;
			}
			else q.add(node.right);
		}
	}


	public void BFS(Node node){
		int counter =0;

		Queue<Node> q = new LinkedList<>();
		q.add(node);
		System.out.print("\ninitial tree BFS traversal: 	");
		while(!q.isEmpty()){
			node = q.remove();
			if (node.left != null)		q.add(node.left);
			if (node.right != null)		q.add(node.right);
			System.out.print(node.data + " ");
			//storing inorder traversal in string traverse
			traverse = traverse + node.data + " ";
			counter++;
		}
		System.out.println();

		//creating array from the stored inorder traversal//

		int arr[] = new int[counter];
		StringTokenizer st = new StringTokenizer(traverse);
		counter = 0;
		while(st.hasMoreTokens()){
			arr[counter] = Integer.parseInt(st.nextToken());
			counter ++ ;
		}

		//sorting array - bubble sort
		int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }

        //reprint tree while simultaneously changing it
        q.add(root);
        System.out.print("Heap sorted tree:  		");
		while(!q.isEmpty()){
			n--;
			node = q.remove();
			if (node.left != null)		q.add(node.left);
			if (node.right != null)		q.add(node.right);
			node.data = arr[n];
			System.out.print(node.data + " ");
		}

	}

	public void inorder(Node node){
		if(node == null)
			return;
		inorder(node.left);
		System.out.print(node.data + " ");
		inorder(node.right);
	}

	public static void main(String[] args) {
		HeapSort hs = new HeapSort();
		hs.createheap(hs.root,10);
		hs.createheap(hs.root,15);
		hs.createheap(hs.root,30);
		hs.createheap(hs.root,40);
		hs.createheap(hs.root,50);
		hs.createheap(hs.root,100);
		hs.createheap(hs.root,40);
		System.out.print("Inorder traversal :		");
		hs.inorder(hs.root);
		hs.BFS(hs.root);
	}

}
