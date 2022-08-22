class Solution {
    public ArrayList<Integer> inorder(TreeNode root, ArrayList<Integer> arr) {

      if (root == null) return arr;

      inorder(root.left, arr);

      arr.add(root.val);

      inorder(root.right, arr);

      return arr;
    }
  
    public int kthSmallest(TreeNode root, int k) {
      
        ArrayList<Integer> nums = inorder(root, new ArrayList<Integer>());

        return nums.get(k - 1);
    }
  }


  //Iterative

  class Solution {
    public int kthSmallest(TreeNode root, int k) {
        
        Stack<TreeNode> stack = new Stack<>();
        
        while(true){
            
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            k--;
            if(k==0) return root.val;
            root=root.right;
        }
        
    }
}