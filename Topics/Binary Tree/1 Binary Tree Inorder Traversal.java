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
class Solution {
    List<Integer> list = new ArrayList();
    public List<Integer> inorderTraversal(TreeNode root) {

        if(root == null ) return list;
        inorderTraversal(root.left);
        list.add(root.val);
        inorderTraversal(root.right);
        return list;
    }

}



//Iterating method using Stack

class Solution {
    
    public List<Integer> inorderTraversal(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new ArrayList();
        
        // curr != null --> this condition added so that we can enter in the while loop first time, then there is no use
        // of this condition 
        
        while(root != null || !stack.isEmpty()){
            
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}


// Morris algoritham

class Solution {

    List<Integer> list = new ArrayList();

    public List<Integer> inorderTraversal(TreeNode tourist) {

        while(tourist!=null){

            TreeNode guide = tourist.left;

            if(guide != null){

                    while(guide.right!=null && guide.right != tourist){
                        guide = guide.right;
                    }

                    if(guide.right == null){
                        guide.right = tourist;
                        tourist = tourist.left;
                    }else{
                        guide.right = null;
                        list.add(tourist.val);
                        tourist = tourist.right;
                    }
            }else{
                list.add(tourist.val);
                tourist = tourist.right;
            }
        }

        return list;
    }
}
