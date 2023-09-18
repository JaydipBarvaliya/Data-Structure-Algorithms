class Solution {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
       solve(root);    
       return max;
    }

    public int solve(TreeNode root){

        if(root == null) return 0;

        int left  = solve(root.left);
        int right = solve(root.right);
        
        int mid = root.val;
        int leftWithRoot  = left  + mid;
        int rightWithRoot = right + mid;
        int leftRightRoot = left  +right + mid;

        int temp = Math.max(Math.max(leftWithRoot, rightWithRoot), mid);
        int ans = Math.max(Math.max(Math.max(leftWithRoot, rightWithRoot), mid), leftRightRoot);

        
        max = Math.max(max, ans);

        return temp;
    }
}