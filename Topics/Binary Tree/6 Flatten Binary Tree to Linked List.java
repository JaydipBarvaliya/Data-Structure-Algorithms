
//Using single stack
 class Solution {
     public void flatten(TreeNode root) {
         
         if (root == null) return;
         
         Stack<TreeNode> st = new Stack();
         st.push(root);

         while (!st.empty()){

             TreeNode curr = st.pop();

             if (curr.right != null) st.push(curr.right);

             if (curr.left != null)  st.push(curr.left);

             if (!st.empty()) curr.right = st.peek();

             curr.left = null;
         }
     }
 }




//Recursive
 class Solution {
     TreeNode pre = null;
     public void flatten(TreeNode root) {
         
         if(root==null) return;
         
         flatten(root.right);
         flatten(root.left);
         
         root.right = pre;
         root.left = null;
         pre = root;
     }
 }




//Simple one
 class Solution {
     public void flatten(TreeNode root) {
          
             if(root == null) return;
         
             if(root.left  !=null) flatten(root.left);
             
             if(root.left!=null){
                 TreeNode temp = root.right;
                 root.right = root.left;
                 root.left = null;
                 
                 if( temp != null){
                     TreeNode iterator = root;
                     while(iterator.right!=null) iterator = iterator.right;
                     iterator.right = temp;
                 }
             }
         if(root.right !=null) flatten(root.right);
         
         }
 }


 //Similar to Morris Algoritham but slide changes. 
 class Solution {
     public void flatten(TreeNode visitor) {
         
         while(visitor!=null){

             if(visitor.left != null) {
             TreeNode guider = visitor.left;
             
             while(guider.right != null) guider = guider.right;
             
             guider.right = visitor.right;
             visitor.right = visitor.left;
             visitor.left = null;
            }
             
            visitor = visitor.right;
             
         }
     }
 }
