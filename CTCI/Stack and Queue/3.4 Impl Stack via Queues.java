//-------------Using 2 Queue ------------------------------

class MyStack {

    
    Queue<Integer> q1 = new LinkedList();
    Queue<Integer> q2 = new LinkedList();
    int top;
    
    public MyStack() {}
    
    public void push(int x) {
        q1.add(x);
        top = x;
    }
    
    
    public int pop() {
        
        if(q1.isEmpty())
            return -1;
    
        while(q1.size() != 1){
             top = q1.remove();
             q2.add(top);
        }
        
        int x =  q1.remove();
        
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
        
        return x;
    }
    
    
    public int top() {
       return top;
    }
    
    
    
    public boolean empty() {
        return q1.isEmpty();
    }
    
}




//-------------Using 1 Queue ------------------------------

class MyStack {

    
    Queue<Integer> q1 = new LinkedList();
    
    public MyStack() {}
    
    public void push(int x) {
        q1.add(x);
        int sz = q1.size();
       
        while (sz > 1) {
            q1.add(q1.remove());
            sz--;
        }
    }
    
    
    public int pop() {
        return q1.remove();
    }
    
    
    public int top() {
        return q1.peek();
    }
    
    
    
    public boolean empty() {
        return q1.isEmpty();
    }
    
}
