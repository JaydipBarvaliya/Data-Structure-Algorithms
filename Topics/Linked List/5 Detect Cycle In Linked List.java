//Slow pointer and fast pointer collide or not
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast !=null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
                if(slow == fast) return true;
        }
        return false;
    }
}

// Time Complexity: O(N)

// Reason: In the worst case, all the nodes of the list are visited.

// Space Complexity: O(1)

// Reason: No extra data structure is used.


// --------------------------------------------------------------------------------
//Store data in list and check if list contains the elem
public class Solution {
    public boolean hasCycle(ListNode head) {
        
        List<ListNode> list  = new ArrayList();
        
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


