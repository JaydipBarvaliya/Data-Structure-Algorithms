public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        
        while(pA != pB){
            pA = pA == null? pA = headB : pA.next;
            pB = pB == null? pB = headA : pB.next;
        }
        
        return pA;
    }
}




------------------------------------------------------------------------------------------



/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        
        int len1 = 0;
        while(pA != null){
            pA = pA.next;
            len1++;
        }
        
        int len2 = 0;
        while(pB != null){
            pB = pB.next;
            len2++;
        }
        
        int count = 0;
        
        if(len1> len2){
            
            while(count!= len1 - len2){
                headA = headA.next;
                count++;
            }
            
        }else{
            
            while(count!= len2 - len1){
                headB = headB.next;
                count++;
            }
        }

        while(headA!=null){
            if(headA == headB){
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        
        return null;
    }
}


----------------------------------------------------------------------------
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        
        ListNode pA = headA;
        ListNode pB = headB;
        
        Set<ListNode> listOfA = new HashSet<>();
        
        while(pA != null){
            listOfA.add(pA);
            pA = pA.next;
        }
        
        while(pB!=null){
            if(listOfA.contains(pB)){
                return pB;
            }
            pB = pB.next;
        }
    
        return null;
    }
}
