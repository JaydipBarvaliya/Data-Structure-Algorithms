class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        
            if(root == null) return null;
        
            if(p.val == root.val || q.val == root.val)  return root;
        
            TreeNode left  = lowestCommonAncestor(root.left,   p, q);
            TreeNode right = lowestCommonAncestor(root.right,  p, q);
        
            
            // System.out.println("Root " + root.val);
            // if(left ==  null && right ==  null ) System.out.println("null null");    
            // if(left ==  null && right !=  null ) System.out.println("null " + right.val);    
            // if(right == null && left !=  null) System.out.println(left.val + " null ");  
            // if(right != null && left !=  null) System.out.println(left.val + " " + right.val );   
            // System.out.println();   
            
        
            if(left == null) return right;
            if(right == null) return left;
    
            return root;
    }
        
}
