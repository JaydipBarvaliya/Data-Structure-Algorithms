/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {

    public List<ListNode> binaryTreeToLists(TreeNode root) {
        
        List<ListNode> mainList = new ArrayList();
        Queue<TreeNode> q = new LinkedList<>();
        
        if(root == null) return mainList;
        
        ListNode sub = null;
        
        q.add(root);
        q.add(null);
        
        while(!q.isEmpty()){
            
            TreeNode node = q.remove();
            
            if( node != null){
                
                if(sub == null){
                    sub = new ListNode(node.val);
                }else{

                    while(sub.next != null){
                        sub = sub.next;
                    }
                    sub.next = new ListNode(node.val); 
                }
                
                if(node.left  != null)  q.add(node.left);
                if(node.right != null)  q.add(node.right);
                
            }else{
                mainList.add(sub);
                sub = null;
                
                if(q.isEmpty()) return mainList;
                q.add(null);
            }
        }

        return mainList;
    }
}
