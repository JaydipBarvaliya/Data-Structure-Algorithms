class Solution {
    
    int finaleMax;
    
    public int maxPathSum(TreeNode root) {
        finaleMax = Integer.MIN_VALUE; // if we initialize this variable here and declare as a global variable then it will reduce tons of time complexity 
        maxPath(root);
        return finaleMax;
    }
    
    int maxPath(TreeNode root){
        
        if(root==null) return 0;
        
        int left = maxPath(root.left);
        int right = maxPath(root.right);
        
        int leftWithRoot   = left  + root.val;
        int rightWithRoot  = right + root.val;
        int leftRightRoot  = left  + right + root.val;
        
        int tempMax = Math.max(Math.max(Math.max(leftWithRoot, rightWithRoot), leftRightRoot), root.val);
        
        finaleMax = Math.max(finaleMax, tempMax);
        
        return Math.max(Math.max(leftWithRoot, rightWithRoot), root.val);        
    }
}
