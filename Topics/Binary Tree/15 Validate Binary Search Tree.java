//Recursive
class Solution {
    public boolean validate(TreeNode root, Integer min, Integer max) {

        if (root == null) return true;


        // [5,4,6,null,null,3,7] this is best test case to understand everything

        if (max != null && root.val >= max) return false;
        if (min != null && root.val <= min) return false;

        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }

    public boolean isValidBST(TreeNode root) {
        return validate(root, null, null);
    }
}


//Iterative
public boolean isValidBST(TreeNode root) {
    if (root == null) return true;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode pre = null;
    while (root != null || !stack.isEmpty()) {
       while (root != null) {
          stack.push(root);
          root = root.left;
       }
       root = stack.pop();
       if(pre != null && root.val <= pre.val) return false;
       pre = root;
       root = root.right;
    }
    return true;
 }