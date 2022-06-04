package com.leetcode.year_2020;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author neeraj on 27/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SerializeAndDeserializeBinaryTree {

    private static final String NULL_SYMBOL = "X";
    private static final String DELIMITER = ",";

    public static void main(String[] args) {
        testSerializationAndDeserialization("1,2,3,X,X,X,X");
        testSerializationAndDeserialization("1,2,3,X,X,4,5,X,X,X,X");
//        testSerializationAndDeserialization("5,2,3,X,X,2,4,3,1");
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) return NULL_SYMBOL + DELIMITER; // Empty Node represented by X
        String leftSerialized = serialize(root.left);
        String rightSerialized = serialize(root.right);
        return root.val + DELIMITER + leftSerialized + rightSerialized;
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(DELIMITER)));
        return deserializeHelper(queue);
    }

    private static TreeNode deserializeHelper(Queue<String> queue) {
        String polledItem = queue.poll();
        if (polledItem.equalsIgnoreCase(NULL_SYMBOL)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(polledItem));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }


    private static void testSerializationAndDeserialization(String input) {
        LogUtil.logIt("Testing Serialization and Deserialization of ===> " + input);
        TreeNode root = deserialize(input);
        LogUtil.logIt("Inorder of Same is ");
        TreeNode.inorder(root);
        LogUtil.newLine();
        LogUtil.logIt("Serializing the same now  ===> " + serialize(root));
        TreeNode.inorder(deserialize(serialize(root)));
    }
}
