Node deleteMid(Node head) {
      
      if(head.next == null){
            return null;
        }
       
        Node slow = head;
        Node fast = head.next;
        
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        slow.next = slow.next.next;
        
        return head;
    }
