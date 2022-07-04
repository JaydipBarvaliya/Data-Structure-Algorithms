
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        if(lists.length==0) return null;
        
        int interval = 1;
        
        while(interval < lists.length){
            
            for(int i=0; i+interval<lists.length; i=i+interval*2){
                lists[i] = mergeTwoLL(lists[i], lists[i+interval]);
            }
            interval = interval*2;
            
        }
        return lists[0];
    }
    
    ListNode mergeTwoLL(ListNode l1, ListNode l2){
        
        ListNode preHead = new ListNode(-1);
        ListNode pre = preHead;
        
        while(l1!=null && l2!=null){
            
            if(l1.val<=l2.val){
                pre.next = l1;
                l1 = l1.next;
            }else{
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        
        if(l1==null) pre.next=l2;
        if(l2==null) pre.next=l1;
        
        return preHead.next;
    }
}
