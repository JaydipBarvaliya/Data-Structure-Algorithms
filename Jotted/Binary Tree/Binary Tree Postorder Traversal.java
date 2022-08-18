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

// pre-order traversal is root-left-right, and post order is left-right-root. modify the code for pre-order to make it root-right-left,  
// and then reverse the output so that we can get left-right-root.
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList<Integer>();

        while(root != null || !stack.isEmpty()){

            while(root!=null){
                stack.push(root);
                list.add(0, root.val);
                root = root.right;
            }

            root = stack.pop().left;
        }

        return list;
    }
}
