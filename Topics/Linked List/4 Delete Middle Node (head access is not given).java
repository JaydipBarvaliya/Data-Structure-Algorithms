// You will not be given access to the head of the list, instead you will be given access to the node to be deleted directly.


public void deleteNode(ListNode node){
        
        node.val = node.next.val;
        node.next = node.next.next;
        
    }
