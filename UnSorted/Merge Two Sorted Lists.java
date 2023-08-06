
// Time : O(N)
// Space : O(1)

//This is recursive way in which we are holding the smaller element and send the rest of elements for merging.
// if first ele in l1 is smaller then first elem in l2 then we are holding first ele of l1 and sending rest of l1 and
//entire l2 ...whatever the answer will it give we connect behind the l1 and return the l1...

public ListNode mergeTwoLists(ListNode l1, ListNode l2){
    if(l1 == null) return l2;
    if(l2 == null) return l1;
    if(l1.val < l2.val){
        l1.next = mergeTwoLists(l1.next, l2);
        return l1;
    } else{
        l2.next = mergeTwoLists(l1, l2.next);
        return l2;
    }
}



//Create a Dummy Node and then store all the elements in sorted manner behind dummy node
//move l1 or l2 based on the it's value if l1 is smaller then store l1 and move it and vice verse

// Time : O(N + M)
// Space : O(N + M)
class Solution {
   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        

        while(l1!=null && l2!=null){

            if(l1.val <= l2.val){
                curr.next = l1;
                l1 = l1.next;
            }else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        if(l1 == null) curr.next = l2;
        if(l2 == null) curr.next = l1;
        return dummy.next;
    }
}