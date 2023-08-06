// The naive approach here would be to run through the linked list and create an array of its values, then compare the array
// to its reverse to find out if it's a palindrome. Though this is easy enough to accomplish, we're challenged to find an
// approach with a space complexity of only O(1) while maintaining a time complexity of O(N).

// Time O(N) 
// Space O(N) 


// (1) find the middle using slow and fast pointer...slow will stop exactly at middle
// (2) start reversing from the slow till end of the list.. ( reversing second half)
// (3) compare the first and the second reversed half...if any mismatch then it is not palindrome..

// Time O(N) 
// Space O(1) 


class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head; 
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }


        ListNode prev = null;
        ListNode curr = slow;

        while(curr != null){

            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }

        fast = head;
        slow = prev;


        while(slow!=null){
            if(slow.val != fast.val ) return false;
            slow = slow.next;
            fast = fast.next;
        }

    return true;
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------


//Using Stack -- Instead of reversing we can store first / second half into the stack and when we do stack.pop() then we get all those
// variable in reverse manner so we can easily compare to the other half.

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


---------------------------------------------------------------------------------------------------------------------------------------

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


// In this way we are directly compare both end and increasing them in respective order as forward and backward
class Solution {
    ListNode front;
    public boolean isPalindrome(ListNode head) {
        
        front = head;
        return recursion(head);
    }
    
    public boolean recursion(ListNode current){
        
        if(current == null) return true;
        
        if(recursion(current.next)){
            if(current.val != front.val) return false;
            front = front.next;
            return true;
        }else{
            return false;
        }
    }
}
