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

 // Recursive soltuion
class Solution {
    List<Integer> list = new ArrayList();
    public List<Integer> postorderTraversal(TreeNode root) {

        if(root == null ) return list;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);
        return list;
    }
}


// Using 2 stack
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {

        if(root == null) return new ArrayList();

        List<Integer> list = new ArrayList();
        Stack<TreeNode> s1 = new Stack();
        Stack<Integer> s2 = new Stack();

        s1.push(root);

        while(!s1.isEmpty()){

            TreeNode curr = s1.pop();
            s2.push(curr.val);

            if(curr.left  != null) s1.push(curr.left);
            if(curr.right != null) s1.push(curr.right);
        }

        while(!s2.isEmpty()){
            list.add(s2.pop());
        }

        return list;
    }
}
