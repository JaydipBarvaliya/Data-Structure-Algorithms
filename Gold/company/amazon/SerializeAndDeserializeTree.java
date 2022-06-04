package com.company.amazon;

import com.company.amazon.BinaryTree.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem : 179 Amazon Interview Questions
 */
public class SerializeAndDeserializeTree {
    static int index = 0;

    public static void main(String[] args) {
        Node tree = ConstructBSTFromPreOrder.constructBST(new int[]{10, 5, 1, 7, 40, 50});
        System.out.println("Before Serializing.............");
        BinaryTree.inorder(tree, true);
        List<String> serializedData = new ArrayList<>();
        System.out.println("Doing Serializing.............");
        serialize(serializedData, tree);
        System.out.println(serializedData);
        System.out.println("While De-Serializing.............");
        tree = deserialize(serializedData);
        BinaryTree.inorder(tree, true);
    }

    private static Node deserialize(List<String> serializedData) {
        if (index >= serializedData.size() || serializedData.get(index).equalsIgnoreCase("#")) {
            index++; // Skip if Hash Encountered
            return null;
        }
        Node root = new Node(Integer.parseInt(serializedData.get(index)));
        index++;
        root.left = deserialize(serializedData);
        root.right = deserialize(serializedData);
        return root;
    }

    public static void serialize(List<String> list, Node root) {
        if (root == null) {
            list.add("#");
            return;
        }
        list.add(String.valueOf(root.data));
        serialize(list, root.left);
        serialize(list, root.right);
    }
}
