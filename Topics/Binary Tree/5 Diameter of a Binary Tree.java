class Solution {
    
    int max = Integer.MIN_VALUE;
    
    public int diameterOfBinaryTree(TreeNode root) {
        fun(root);
        return max;
    }
    
    public int fun(TreeNode root) {

        if(root == null) return 0;

        int left  = fun(root.left);
        int right = fun(root.right);

        int temp  = Math.max(left, right);
        int ans   = left + right;
        max = Math.max(max, ans);

        return 1 + temp;
        
    }
}