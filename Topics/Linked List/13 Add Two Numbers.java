
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode curr  = dummy;
        int carry = 0;
        while( l1!=null || l2!=null || carry == 1){

            int sum = 0;

            if(l1 != null){
                sum = sum + l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                sum = sum + l2.val;
                l2 = l2.next;
            }

            sum = sum + carry;
            carry = sum / 10;

            ListNode newNode = new ListNode(sum % 10);
            curr.next = newNode;
            curr = curr.next;

        }

        return dummy.next;
        
    }
}


// ANALYSIS :-

// Time Complexity :- BigO(max(N, M)) where N is length of l1 & M is length of l2

// Space Complexity :- BigO(max(N,M))