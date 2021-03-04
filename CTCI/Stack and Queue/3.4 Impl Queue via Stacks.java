class StackQueue
{
    Stack<Integer> s1 = new Stack<Integer>();
    Stack<Integer> s2 = new Stack<Integer>();

    void Push(int x)
    {
        
        while (!s1.isEmpty())
            s2.push(s1.pop());  
  
        s1.push(x);  
  
        while (!s2.isEmpty())
            s1.push(s2.pop());  
        }
    }
    
    
    int Pop(){
        
        return  s1.isEmpty() ? -1: s1.pop();
        
    }
}


---------------------------------------------


class MyQueue {


    Stack<Integer> s1 = new Stack();
    Stack<Integer> s2 = new Stack();
    int front;
    
    public MyQueue() {}
    
    
    
    public void push(int x) {
        front = s1.isEmpty() ? x: front;
        s1.push(x);
    }

    
    
    public int pop() {
        
        if(s1.isEmpty() && s2.isEmpty()) {
            return -1;
        }
        
        if(s2.isEmpty()){
            while(!s1.isEmpty()){
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }
    
    
    
    public int peek() {
        return !s2.isEmpty()  ? s2.peek() : front;
    }
    
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
