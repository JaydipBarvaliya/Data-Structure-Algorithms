
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
      int carry = 0;
        int sum   = 0;

        
        ListNode head = new ListNode(0);
        ListNode tail = head;
        
        while(l1 != null ||  l2 != null){
            
            sum = carry;
            if(l1 != null){
                sum = sum + l1.val;
            }
            
            if(l2 != null){
                sum = sum + l2.val;
            }
            
            carry = sum / 10;
		
            ListNode newNode = new ListNode(sum%10);

            tail.next = newNode;
            tail = tail.next;
            
            if(l1 != null){
                l1 = l1.next;
            }
            
            if(l2 != null){
                 l2 = l2.next;
            }
        }
      
        if(carry > 0){
           tail.next = new ListNode(carry);
        }
        return head.next;
}
}
