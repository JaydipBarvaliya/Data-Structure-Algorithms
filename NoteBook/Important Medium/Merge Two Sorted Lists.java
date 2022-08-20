
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

class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
       
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        
        ListNode temp = null;
        ListNode result = l1;
        
        
        if(l1.val > l2.val){
            temp = l1;
            l1 = l2;
            l2 = temp;
            result = l1;
        } 
        
        while(l1!=null && l2!=null){
            while(l1!=null && l1.val <= l2.val){
                temp = l1;
                l1 = l1.next;
            }
            temp.next = l2;
            temp = l1;
            l1 = l2;
            l2 = temp;
        }
        return result;
    }
}