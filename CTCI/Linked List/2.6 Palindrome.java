/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        
    ListNode fast = head;
    ListNode slow = head;
    
    Stack <Integer> stack = new Stack <Integer> ();
    
    while (fast != null && fast.next != null) {
         stack.push(slow.val);
         slow = slow.next;
         fast = fast.next.next;
    }
    
    if (fast != null) {
         slow = slow.next;
    }
    
    while (slow != null) {
        int top = stack.pop().intValue();
        if (top != slow.val) {
            return false;
        }
     slow = slow.next;
    }
        
    return true;
    }
}
