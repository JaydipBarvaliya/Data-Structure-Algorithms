public class Main {
    public static void main(String[] args) {
        
        Node node = new Node(1);
        node.appendToTail(2);
        node.appendToTail(3);
        node.appendToTail(4);
        node.appendToTail(5);
        // node.genericShow();
        // System.out.println(" FINALE " + node.reverse(node));
       // Node reversedLL =  node.reverse(node);
       Node reversedLL = node.reverseAndClone(node);
       // System.out.println(reversedLL.next.data);
       // boolean ispalindrom = node.palindrome(node, reversedLL);
       // System.out.println(ispalindrom);
        
       // node.staticShow(reversedLL);
        
        
        // node.removeDuplicates();
        // node.removeIntialDuplicate();
        
        // node.removeDuplicates1();
        // System.out.println(node.lengthOfTheList(1));
        
        
    }
}

class Node {
    
      int data;
      Node head;
      Node next;
    Stack<Integer> stack= new Stack<Integer>(); 
    
     public Node(){
     }

    public Node(int d){
        data = d;
     }
    
    Node reverseAndClone(Node head) { 
        Node reversed = null;
        Node temp = head;
        while (temp != null){
             Node n = new Node(temp.data); 
             n.next =   reversed;
             reversed  = n;
             temp = temp.next;
         }
        return reversed; 
        
//         if (head == null || head.next == null){
//             return head;
//         } 
//             Node reversed = reverseAndClone(head.next);
//             System.out.println(head.data);
        
//             head.next.next = head;
        
//             head.next = null;
//         return reversed;
     } 

    boolean palindrome(Node original, Node reversed){
        
        Node fast = original.next;
        
        while (fast.next != null && fast.next.next != null){
            if(original.data !=  reversed.data){
                return false;
            }
            original = original.next;
            reversed = reversed.next;
            fast = fast.next.next;
        }
        if(original.data !=  reversed.data){
                return false;
        }else{
        return true;
        }
    }
    
    
    Node reverse(Node currentNode){
        
        if(currentNode.next == null){
         return currentNode;
        }
        
        Node returned = reverse(currentNode.next);
        Node pointer = returned;
        
        while (pointer.next !=  null) { 
          pointer = pointer.next; 
        }
        
        Node newNode = new Node(currentNode.data);
        pointer.next = newNode;
        return returned;
    }
    
     void appendToTail(int d) {
        
         Node current_Node = this;
         Node newNode =  new Node(d);

        while (current_Node.next !=  null) { 
          current_Node  = current_Node.next; 
        }
        current_Node.next = newNode;
     }
    
    void staticShow(Node n){
        while (n !=  null) { 
            System.out.println(n.data);
            n  = n.next; 
        }
   }
    void genericShow(){
        Node n =  this;
        while (n !=  null) { 
            System.out.println(n.data);
            n  = n.next; 
        }
   }
    
    
    
    int lengthOfTheList(int index){
        
        int length = 1;
        Node head = this; 
        while(head.next!=null){
            head = head.next;
            length++;
        }
       
        if(index>length){
            return -1;
        }
        Node cn = this;
        int count = 1;
        int position = length - index + 1;
        
        while(count != position){
            cn = cn.next;
            count++;
        }
        return cn.data;
    }

    
    
     void removeIntialDuplicate(){
        
        Node head = this; 
        if(head == null){
             return;
         }
         Node currentNode = head;
         
         while(currentNode.data == currentNode.next.data){
             currentNode = currentNode.next;
         }
         currentNode = currentNode.next;
    }
    
    
    
    void removeDuplicates(){
        
        Node head = this; 
        
        if(head == null){
             return;
         }

         boolean[] arr = new boolean[65535];
         Node currentNode = head;
         arr[currentNode.data] = true;
         
         while(currentNode.next != null){
             
                 if(!arr[currentNode.next.data]){
                 arr[currentNode.next.data] = true;
                 currentNode = currentNode.next;    
             }else{
                 currentNode.next = currentNode.next.next;
             }
         }
    }
    
    
    
 
    
    
    
    
    void deleteNode(int index) {
  
        Node current_Node = this;
        
        if(index == 0){
            current_Node  = current_Node.next;
        }else{

            int i = 0;
            while (i !=  index-1) { 
              current_Node  = current_Node.next; 
                i++;
            }
            current_Node.next  = current_Node.next.next;
        }
     }
    
    
    
    

}
