
//recursive method which work O(N)
class Solution
{
    Node pre = null;
    Node result = null;
	public Node inorderSuccessor(Node root, Node finder){

    if(root.left!=null) inorderSuccessor(root.left, finder);
    if(pre!=null && pre.data == finder.data) result = root;
    pre = root;
    if(result == null & root.right!=null)  inorderSuccessor(root.right, finder);
    return result;
 }
}


//this approch will work O(h) h is height of the tree, because here we are not checking all the node and then find successor, here we directly goes to that node and by ancestor method
// directly find the method.
class Solution
{
	public Node inorderSuccessor(Node root, Node finder){

             Node curr = findNodeInTree(root, finder);

             if(curr == null) return null;

             if(curr.right!=null){
                 return findMin(curr.right);
             }else{

                 Node ancestor = root;
                 Node successor = null;

                 while(ancestor != curr){
                     if(curr.data < ancestor.data){
                         successor = ancestor;
                         ancestor = ancestor.left;
                     }else{
                         ancestor = ancestor.right;
                     }
                 }
                 return successor;
             }
         }

         public Node findMin(Node curr){
             while(curr.left != null) curr = curr.left;
             return curr;
         }

         public Node findNodeInTree(Node cNode, Node finder){

             while(cNode != finder) cNode = finder.data < cNode.data ? cNode.left : cNode.right;
             return cNode == finder ? cNode: null;
         }
}
