 // Recursive soltuion
class Solution {
    List<Integer> list = new ArrayList();
    public List<Integer> postorderTraversal(TreeNode root) {

        if(root == null ) return list;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        list.add(root.val);
        return list;
    }
}


// Using 2 stack
//one stack hold the Actual Nodes and another stack hold the value of each node

// push root value in stack 2
// push root.left int stack 1 
// push root.right int stack 1 

//repeat this process until stack1 is not empty

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {

        if(root == null) return new ArrayList();

        List<Integer> list = new ArrayList<Integer>();

        Stack<TreeNode> s1 = new Stack();
        Stack<Integer> s2 = new Stack();

        s1.push(root);

        while(!s1.isEmpty()){

           root = s1.pop();

            s2.push(root.val);

            if(root.left  != null ) s1.push(root.left);
            if(root.right != null ) s1.push(root.right);
        }

        while(!s2.isEmpty()){
            list.add(s2.pop());
        }
        
        return list;
    }
}

// pre-order traversal is root-left-right, and post order is left-right-root. modify the code for pre-order to make it root-right-left,  
// and then reverse the output so that we can get left-right-root.
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        
        Stack<TreeNode> stack = new Stack();
        List<Integer> list = new LinkedList<Integer>();

        while(root != null || !stack.isEmpty()){

            while(root!=null){
                stack.push(root);
                list.add(0, root.val);
                root = root.right;
            }

            root = stack.pop().left;
        }

        return list;
    }
}
