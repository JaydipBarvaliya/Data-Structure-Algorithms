package com.leetcode.year_2020;

/**
 * @author neeraj on 27/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ConstructTreeFromLevelOrderTraversal {

    public static void main(String[] args) {
        TreeNode.inorder(build("1,2,3"));
        TreeNode.inorder(build("1,2,3,null,null,4,5"));
        TreeNode.inorder(build("1,2,3,4,5,6"));
        TreeNode.inorder(build("1,2,3,4,5,6,6,6,6,6"));
    }

    public static TreeNode build(String input) {
        System.out.println("\n======Building Tree From " + input + " =========");
        String[] traversal = input.split(",");
        if (traversal.length == 0) return null;
        if (traversal[0] == null) return null;
        return insertIntoLevelOrder(traversal, 0);
    }

    private static TreeNode insertIntoLevelOrder(String[] traversal, int traversalPointer) {
        if (traversalPointer < traversal.length &&
                !traversal[traversalPointer].equalsIgnoreCase("X")) {
            TreeNode root = new TreeNode(Integer.parseInt(traversal[traversalPointer]));
            root.left = insertIntoLevelOrder(traversal, traversalPointer * 2 + 1);
            root.right = insertIntoLevelOrder(traversal, traversalPointer * 2 + 2);
            return root;
        }
        return null;
    }
}
