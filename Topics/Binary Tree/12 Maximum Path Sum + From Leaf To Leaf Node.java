class Solution {
    private int sum;
    
    public Solution() {
        this.sum = Integer.MIN_VALUE;
    }
    
     int findMaxPathSum(Node root) {
        
        if (root == null) return 0;
        
        int left = findMaxPathSum(root.left);
        int right = findMaxPathSum(root.right);
        
        if (root.left == null && root.right == null) return root.data;
        if (root.left == null) return root.data + right;
        if (root.right == null) return root.data + left;
        
        
        //until and unless there is any node among left or right is null we are
        //not calculating the sum because it will gonna calculated at the top.
        //Think of left skewed tree. 
        this.sum = Math.max(this.sum, root.data + left + right);
        return Math.max(root.data + left, root.data + right);
    }
    
     int maxPathSum(Node root) {
        int ans = findMaxPathSum(root);
        
        //Checking at top root
        // i.e left skewed tree or right skewed tree
        if (root.left == null || root.right == null) {
            this.sum = Math.max(this.sum, ans);
        }
        
        return this.sum;
    }
}