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
    public List<Integer> preorderTraversal(TreeNode root) {

        if(root == null ) return list;
        list.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return list;
    }
}

//Iterating method using Stack
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new ArrayList();

        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()){

            while(curr!=null){
                stack.push(curr);
                list.add(curr.val);
                curr = curr.left;
            }

            curr = stack.pop().right;
        }

        return list;
    }
}


// Morris algoritham
class Solution {

    List<Integer> list = new ArrayList();

    public List<Integer> preorderTraversal(TreeNode tourist) {
        while(tourist!=null){

            TreeNode  guide = tourist.left;

            if(guide != null){

                    while(guide.right!=null && guide.right != tourist){
                        guide = guide.right;
                    }

                    if(guide.right == null){
                        guide.right = tourist.right;
                        list.add(tourist.val);
                        tourist = tourist.left;
                    }else{
                        guide.right = null;
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
