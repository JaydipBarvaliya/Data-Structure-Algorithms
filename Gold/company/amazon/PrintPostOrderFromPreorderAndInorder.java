package com.company.amazon;

import java.util.Arrays;
import java.util.stream.IntStream;

public class PrintPostOrderFromPreorderAndInorder {

    public static void main(String[] args) {
        int in[] = {4, 2, 5, 1, 3, 6};
        int pre[] = {1, 2, 4, 5, 3, 6};

        printPostOrderFromPreOrderAndInorder(in, pre, in.length);
        System.out.println("\nPrint in Easy Way");
        printPostOrderInEasyWay(in, pre, 0, in.length - 1);
    }

    public static int getIndexOfRootFromInorder(int[] inorder, int root) {
        return IntStream.range(0, inorder.length)
                .filter(i -> root == inorder[i])
                .findFirst()
                .orElse(-1);
    }


    /**
     * Much Easier way
     * <p>
     * Since in Post Order we have to first traverse left subtree and then right subtree....in the last root
     * that is what we are doing here
     */
    static int preOrderIndex = 0;

    public static void printPostOrderInEasyWay(int[] inorder, int[] preorder, int inorderStart, int inorderEnd) {
        if (inorderStart > inorderEnd)
            return;

        int indexOfRoot = getIndexOfRootFromInorder(inorder, preorder[preOrderIndex++]);

        // Traverse left tree
        printPostOrderInEasyWay(inorder, preorder, inorderStart, indexOfRoot - 1);

        // Traverse right tree
        printPostOrderInEasyWay(inorder, preorder, indexOfRoot + 1, inorderEnd);

        System.out.print(inorder[indexOfRoot] + "\t");
    }

    public static void printPostOrderFromPreOrderAndInorder(int[] inorder, int[] preorder, int size) {
        int indexOfRoot = getIndexOfRootFromInorder(inorder, preorder[0]);

        // If Index of root is not 0 then print the left subtree
        if (indexOfRoot != 0) {
            printPostOrderFromPreOrderAndInorder(inorder, Arrays.copyOfRange(preorder, 1, preorder.length), indexOfRoot);
        }

        // If index is not size the print the right subtree
        if (indexOfRoot != size - 1) {
            printPostOrderFromPreOrderAndInorder(Arrays.copyOfRange(inorder, indexOfRoot + 1, inorder.length),
                    Arrays.copyOfRange(preorder, indexOfRoot + 1, preorder.length), size - indexOfRoot - 1);
        }

        System.out.print(preorder[0] + ",");
    }
}
