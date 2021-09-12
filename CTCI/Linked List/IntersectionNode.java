
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        ListNode hA = headA;
        ListNode hB = headB;
        
        while(hA!=hB){
            
            hA = hA.next;
            hB = hB.next;
            
            if(hA == null && hB != null) hA = headB;
            if(hB == null && hA != null) hB = headA;
            if(hA == null && hB == null) return null;
        }
        
        return hA;
    }
}
