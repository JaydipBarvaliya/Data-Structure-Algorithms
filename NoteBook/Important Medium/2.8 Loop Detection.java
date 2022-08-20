
//single and double pointer run parallely
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        if(head == null || head.next == null){
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast !=null && fast.next != null){
            
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast){
                return true;
            }
            
        }
        return false;
        
    }
}

//The ArrayList uses an array for storing the data. The ArrayList.contains will be of O(n) complexity. So essentially searching in array again and again will 
//have O(n^2) complexity.

//While HashSet uses hashing mechanism for storing the elements into their respective buckets. The operation of HashSet will be faster for long list of 
//values. It will reach the element in O(1).

//Iterate Linked List and also adding them into hashset, 
public class Solution {
    public boolean hasCycle(ListNode head) {
        
    Set<ListNode> list  = new HashSet();
        
        while(head != null){
            
            if(list.contains(head)){
                return true;
            }
            list.add(head);

            head = head.next;
        }
        
        return false;
        
    }
}
