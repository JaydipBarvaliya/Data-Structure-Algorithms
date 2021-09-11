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

//Reverse the second half and then compare it to first..here we have made many mistake while doing reversing ...
//there are many other ways to do revering in easy manner


class Solution {
    public boolean isPalindrome(ListNode head) {
        
        if (head.next == null) return true;
        
        ListNode mid = middle(head);
        ListNode rev = reversing(mid.next);

        boolean flag = true;
        while(flag && rev != null){
            if(head.val != rev.val) flag = false;
            head = head.next;
            rev = rev.next;
        }
            return flag;
    }

    
    public ListNode middle(ListNode head) {
            
            ListNode slow = head;
            ListNode fast = head;
            
            while(fast.next!=null && fast.next.next!=null){
                fast = fast.next.next;
                slow = slow.next;
            }
            
            return slow;
        }
    
    public ListNode reversing(ListNode curr) {
         
             ListNode pre = null;
            while(curr != null){
                ListNode temp = curr.next;
                curr.next = pre;
                pre = curr;
                curr = temp;
            }
        return pre;
        
        }
}
