
//Iterative way

//Approch is start copying the next node value until you are about to reach end ...and when you reach at end
//then just mark the last node as a null...
class Solution {
    public void deleteNode(ListNode node) {

        while(node.next.next != null){
            node.val = node.next.val;
            node = node.next;
        }

        node.val = node.next.val;
        node.next = null;
        
    }
}



//Fucking Briliant Solution


// Approch is just copy the next node value and delete the next node....that's all
class Solution {
    public void deleteNode(ListNode node) {

      node.val = node.next.val;
      node.next = node.next.next;
        
    }
}

// Time complexity:
// O(1)

// Space complexity:
// O(1)