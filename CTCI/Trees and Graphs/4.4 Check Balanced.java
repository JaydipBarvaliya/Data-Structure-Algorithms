// This code check dippest height of left subtree & dippest height of right subtree.. and then do calculation. However, as per the leetcode question,
// we have to check the every node of tree and we have to check that whether it is balanced or not, if any of the sub-node defined it is unbalanced
// then we declare entire tree as unbalanced  
class Solution {
    int lefter  = 0;
    int righter = 0;
    
    public boolean isBalanced(TreeNode root) {
        
        if(root == null) return true;
        
        int LL = dfs(root.left);
        lefter = righter = 0;
        int RR = dfs(root.right);
        
        if(root.left != null)  LL++;
        if(root.right != null) RR++;
        
        return Math.abs(LL-RR)>1 ? false : true;
    }
    
    public int dfs(TreeNode root){
        
        if(root==null) return 0;
        if(root.left !=null){
            lefter++;
            dfs(root.left);
        }
        
        if(root.right !=null){
            righter++;
            dfs(root.right);
        }
        
        return lefter>righter ? lefter : righter;
    }
}

//------------------------------------------------
// Below code will perfectly work and check tree and sub tree which is balanced or not

class Solution {
    
    public boolean isBalanced(TreeNode root) {
        return dfs(root)!=-1;
    }
    
    public int dfs(TreeNode root){
        
        if(root==null) return 0;
        
        int left  = dfs(root.left);
        int right = dfs(root.right);
        
        if(left == -1 || right == -1 || Math.abs(left - right) > 1) return -1;
        
        return Math.max(left, right) + 1;
    }
}
