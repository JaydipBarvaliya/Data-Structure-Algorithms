/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

 //Recursive Solution
class Solution {
    public boolean validate(TreeNode root, Integer min, Integer max) {

        if (root == null) return true;
        if ((max != null && root.val >= max) || (min != null && root.val <= min)) return false;

        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
}


//As we know that when Tree is BST then when we do inorder traversal then resultant sequence will be in Sorted Manner.
//That Means if we somehow do inorder traversal and store the number into array then check if array is sorted or not.
//if it is sorted then Tree is balanced and if not sorted then Tree is not balanced.
//this solution only and only work if we know the exact size of the tree and based on that we can create array.
class Solution {
    int[]  arr = new int[7]; // this 7 should be replace with the actual size of the tree
    int index = 0;

    public boolean isValidBST(TreeNode root) {
        dfs(root);
        for(int i=1; i<arr.length; i++) if(arr[i-1]>=arr[i]) return false;
        return true;
    }

    void dfs(TreeNode root){
        if(root == null) return;
        dfs(root.left);
        arr[index] = root.val;
        index++;
        dfs(root.right);
    }
}


//just track the last element we saw and compare it as we go
class Solution {
    Integer pre = null;
    public boolean isValidBST(TreeNode root) {
        if(root ==  null) return true;
        if(!isValidBST(root.left)) return false;
        if(pre != null && root.val<=pre) return false;
        pre = root.val;
        if(!isValidBST(root.right)) return false;
        return true;
    }
}



