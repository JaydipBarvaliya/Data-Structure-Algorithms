// Intuition :
// Use a Floyd's Cycle-Finding algorithm to detect a cycle in a linked list and find the node where the cycle starts.



// What is Floyd's Cycle-Finding algorithm ?
// It is also called Hare-Tortoise algorithm
// The algorithm works by using two pointers, a slow pointer and a fast pointer.
// Initially, both pointers are set to the head of the linked list.
// The fast pointer moves twice as fast as the slow pointer.
// If there is a cycle in the linked list, eventually, the fast pointer will catch up with the slow pointer.
// If there is no cycle, the fast pointer will reach the end of the linked list.


// Approach :
// When the two pointers meet, we know that there is a cycle in the linked list.
// We then reset the slow pointer to the head of the linked list and move both pointers at the same pace, one step at a time, until they meet again.
// The node where they meet is the starting point of the cycle.
// If there is no cycle in the linked list, the algorithm will return null.




public class Solution {
    public ListNode detectCycle(ListNode head) {
        
        if(head == null || head.next == null){
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast !=null && fast.next != null){
            
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast){
                slow = head;
                
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
            
        }
        return null;
    }
}

// Time Complexity: O(N)

// Reason: We can take overall iterations and club them to O(N)

// Space Complexity: O(1)

// Reason: No extra data structure is used.

//------------------------------------------------------------------------------------------------------------------------
//Other Approach is that, we can store the data into the list and we can check if elem is present in the list or not