class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
            if(root == null) return null;
        
            if(p.val == root.val || q.val == root.val)  return root;
        
            TreeNode left  = lowestCommonAncestor(root.left,   p, q);
            TreeNode right = lowestCommonAncestor(root.right,  p, q);
            
            if(left == null) return right;
            if(right == null) return left;
    
            return root;
    }
        
}
