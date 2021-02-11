
//Recursive 
public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        
        ListNode reversed = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversed; 
    }
    
    
    
//Looping    
public ListNode reverseList(ListNode head) {
     
        ListNode reversed = null;
        while (head != null){
             ListNode newNode = new ListNode(head.val); 
             newNode.next =   reversed;
             reversed  = newNode;
             head = head.next;
         }
        return reversed; 
    }
