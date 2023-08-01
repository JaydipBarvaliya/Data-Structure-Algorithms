//Using Queue

class Solution {
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> mainList = new ArrayList();
        Queue<TreeNode> q = new LinkedList<>();
        
        if(root == null) return mainList;
        
        List<Integer> subList = new ArrayList();
        
        q.add(root);
        q.add(null);
        
        while(!q.isEmpty()){
            
            TreeNode node = q.remove();
            
            if( node != null){
                
                subList.add(node.val);
                
                if(node.left  != null)  q.add(node.left);
                if(node.right != null)  q.add(node.right);
                
            }else{
                mainList.add(subList);
                subList = new ArrayList();
                
                //This is must, otherwise q will never get empty, as we are adding null value at every end.
                if(q.isEmpty()) return mainList;
                
                
                q.add(null);
            }
        }

        return mainList;
    }
}





//Using in-Order

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> ans=new ArrayList<>();
        compute(ans,root,0);
        return ans;
    }
    
    public void compute(List<List<Integer>> ans,TreeNode curr,int level)
    {
        if(curr==null) return;
        
        if(ans.size()==level) 
            ans.add(0,new ArrayList<Integer>());
        
        ans.get(ans.size()-level-1).add(curr.val);
        
        compute(ans,curr.left,level+1);
        compute(ans,curr.right,level+1);
    }
}