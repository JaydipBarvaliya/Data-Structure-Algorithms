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




 



//This approach is correct, however in one testcase [0,1,2,3,4,5,6] it will create a binary Search Tree ...However tree is unnecessary tall..

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        
        int size = nums.length;
        int midIndex = size/2;
        TreeNode root = new TreeNode(nums[midIndex]);
        
        for(int i=0; i<midIndex; i++){
            root = insertIntoBST(root, nums[i]);
        }
        
        for(int i=midIndex+1; i<size; i++){
            root = insertIntoBST(root, nums[i]);
        }
        return root;
    }
    
    public TreeNode insertIntoBST(TreeNode root , int key) {
        
            if(root == null){
                return new TreeNode(key);
            }
            
            if(root.val < key){
                    if(root.right ==  null){
                        root.right = new TreeNode(key);
                        return root;
                    }         
                    root.right = insertIntoBST(root.right, key);
            }else{
                    if(root.left == null){
                        root.left = new TreeNode(key);
                        return root;
                    }
                    root.left = insertIntoBST(root.left, key);
            }
        return root;
    }
    
}



//-------------------------------------------------------------------
//In this approach we have use list to store all the index in specific manner so that afterwards we can create a expected low level's BST.




class Solution {
    
    
    List<Integer>  list = new ArrayList();
    
    public TreeNode sortedArrayToBST(int[] arr) {
    
        middleFinder(0, arr.length-1, arr);
        
        TreeNode defaultRoot = new TreeNode(list.get(0));
        
        for (int i = 1; i < list.size(); i++) {
            defaultRoot = insertIntoBST(defaultRoot, list.get(i));
        }
        
        return defaultRoot;
    }
    
    public TreeNode middleFinder(int start, int end, int[] arr){
        
        if(start == end){
            list.add(arr[start]);
            return null;
        }
        
        if(start > end){
            return null;
        }
        
        int MidIndex = start + ((end-start+1)/2);
        
        list.add(arr[MidIndex]);
        
        middleFinder(start, MidIndex-1, arr);
        middleFinder(MidIndex+1, end, arr);
        
        return null;
    }
    
    
    public TreeNode insertIntoBST(TreeNode root, int key) {
        
            if(root == null){
                return new TreeNode();
            }
        
            if(root.val < key){
                    if(root.right ==  null){
                        root.right = new TreeNode(key);
                        return root;
                    }         
                    root.right = insertIntoBST(root.right, key);
            }else{
                    if(root.left == null){
                        root.left = new TreeNode(key);
                        return root;
                    }
                    root.left = insertIntoBST(root.left, key);
            }
        return root;
    }
    
}



//-------------------------------------------------------------------
// we remove unnecessary use of list from previous approach


class Solution {
    
    TreeNode defaultRoot = null;
    int[] arr;
    
    public TreeNode sortedArrayToBST(int[] nums) {
       
       arr = nums;
       return  middleFinder(0, arr.length-1);
    }
    
    public TreeNode middleFinder(int start, int end){
        
        if(start > end) return null;
        
        int MidIndex = start + ((end-start+1)/2);
        
        defaultRoot = insertIntoBST(defaultRoot, arr[MidIndex]);
        
        middleFinder(start, MidIndex-1);
        middleFinder(MidIndex+1, end);
        
        return defaultRoot;
    }
    
    
    public TreeNode insertIntoBST(TreeNode root, int key) {
        
            if(root == null){
                return new TreeNode(key);
            }
        
            if(root.val < key){
                    if(root.right ==  null){
                        root.right = new TreeNode(key);
                        return root;
                    }         
                    root.right = insertIntoBST(root.right, key);
            }else{
                    if(root.left == null){
                        root.left = new TreeNode(key);
                        return root;
                    }
                    root.left = insertIntoBST(root.left, key);
            }
        return root;
    }
    
}
