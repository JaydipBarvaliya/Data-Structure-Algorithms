// In this solution we are making the making the average of 2 path..
// If first path has total 5 ele and second has 6th and....Swapping those pointer with another path will
// average out the total path
// 1st pointer ( 5 it's own elements +  6 another path elements = 11 elements)
// 2nd pointer ( 6 it's own elements +  5 another path elements = 11 elements)
//anyhow we are making the total elements to visit as same for both the pointer so there are chances that they collide at the elem which is shared by both.



//Simple version

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





------------------------------------------------------------------------------------------
 //Short Version
    
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
