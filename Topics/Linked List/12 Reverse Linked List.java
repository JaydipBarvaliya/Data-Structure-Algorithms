//Recursive -- Pointing Back
public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
                
        ListNode reversed = reverseList(head.next);

        head.next.next = head;
        head.next = null;
        return reversed; 
    }
    
    
    
//Iterative Approch -- Create a newNode and link rest with them
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

//changing internal links without creating newNode
public ListNode reverseList(ListNode cur) {

        ListNode pre = null;
        ListNode forw;

        while(cur!=null){
            forw = cur.next;
            cur.next = pre;
            pre = cur;
            cur = forw;
        }
      return pre;
}
