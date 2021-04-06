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

//level by level traversar -- BFS
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

 // Recursive Solution -- DFS
public class Solution {

    List<ListNode> result = new ArrayList();

    public List<ListNode> binaryTreeToLists(TreeNode root) {
           return dfs(root, 0);
    }

    List<ListNode> dfs(TreeNode root, int level){

    if(root==null) return result;

    if(level == result.size()){
        result.add(new ListNode(root.val));
    }else{
        ListNode head = result.get(level);

        while(head.next != null){
            head = head.next;
        }
        head.next = new ListNode(root.val);
    }

    dfs(root.left, level+1);
    dfs(root.right, level+1);

    return result;
  }
}
