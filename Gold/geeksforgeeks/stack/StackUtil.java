package com.geeksforgeeks.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUtil {

    final static int MAX = 3;
    int top;
    int[] arr = new int[MAX];
    boolean isLogEnabled;

    // Helper class to Implement Queue using Stack
    class QueueImpl {
        StackUtil pushStack;
        StackUtil popStack;
    }

    //Helper Class to Implement Stack using Queue
    class StackImpl<T> {
        Queue<T> queue1;
        Queue<T> queue2;

        StackImpl() {
            queue1 = new LinkedList<T>();
            queue2 = new LinkedList<T>();
        }
    }

    // Stack which will store the minimum Element
//    StackUtil minStack = new StackUtil(false);

    public static void letsDo(String task) {
        System.out.println("==================" + task + "======================");
    }

    public static void newLine() {
        System.out.println();
    }


    public StackUtil(boolean isLogEnabled) {
        top = -1;
        this.isLogEnabled = isLogEnabled;
    }

    public boolean isEmpty() {
        return top < 0;
    }

    public boolean isFull() {
        return top == MAX - 1;
    }

    public int peek() {
        return arr[top];
    }

    public boolean push(int x) {
        if (top + 1 >= MAX) {
            System.out.println("Stack Overflow");
            return false;
        }
        if (isLogEnabled)
            System.out.println("Pushing " + x + " into the Stack");
        arr[++top] = x;
        return false;
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return 0;
        }
        if (isLogEnabled)
            System.out.println("Popping " + peek());
        return arr[top--];
    }

    public void printStack(int[] arr) {
        System.out.print("Stack Contents are [");
        for (int i = top; i >= 0; i--) {
            System.out.print(arr[i] + ",");
        }
        System.out.println("]");
    }

    public void implementQueueUsingStack() {
        QueueImpl queue = new QueueImpl();
        queue.pushStack = new StackUtil(true);
        queue.popStack = new StackUtil(true);

        enqueue(queue, 1);
        enqueue(queue, 2);
        enqueue(queue, 3);
        enqueue(queue, 4);
        enqueue(queue, 5);

        dequeue(queue);
        dequeue(queue);
        dequeue(queue);
    }

    /**
     * Always pop from pop Stack, if popStack isEmpty() then push all content from pushStack to popStack
     * and then return(pop) from popstack
     */
    private void dequeue(final QueueImpl queue) {
        StackUtil popStack = queue.popStack;
        StackUtil pushStack = queue.pushStack;

        if (!popStack.isEmpty()) {
            System.out.println("Dequeue-ing...." + popStack.pop());
            return;
        }

        while (!pushStack.isEmpty()) {
            popStack.push(pushStack.pop());
        }
        if (popStack.isEmpty()) {
            System.out.println("Dequeue-ing...." + popStack.pop());
        } else {
            System.out.println("Nothing to deque");
        }
    }

    /**
     * Always push in PushStack
     */
    private void enqueue(final QueueImpl queue, final int item) {
        StackUtil pushStack = queue.pushStack;
        pushStack.push(item);
    }


    private void implementStackUsingQueue() {
        StackImpl<Integer> stack = new StackImpl<>();

        pushCostly(stack, 1);
        pushCostly(stack, 2);
        pushCostly(stack, 3);
        pushCostly(stack, 4);
        pushCostly(stack, 5);
        popCheaper(stack);
        popCheaper(stack);
        popCheaper(stack);

        letsDo("Implement Stack Using Queue Version 2");
        stack = new StackImpl<>();
        pushCheaper(stack, 10);
        pushCheaper(stack, 20);
        pushCheaper(stack, 30);
        pushCheaper(stack, 40);
        popCostly(stack);
        popCostly(stack);
        popCostly(stack);
    }

    private void pushCheaper(StackImpl<Integer> stack, int i) {
        if (isLogEnabled) {
            System.out.println("Push (cheaper) " + i + " into the Queue1");
        }
        stack.queue1.add(i);
    }

    private Integer popCostly(StackImpl<Integer> stack) {
        Integer lastPoppedElement = 0;
        if (stack.queue1.isEmpty()) {
            System.out.println("Queue underflow");
            return -1;
        }
        while (!stack.queue1.isEmpty()) {
            lastPoppedElement = stack.queue1.poll();
            if (!stack.queue1.isEmpty()) {
                stack.queue2.add(lastPoppedElement);
            }
        }
        if (isLogEnabled) {
            System.out.println(" Pop (costly) " + lastPoppedElement + " from queue1");
        }
        Queue<Integer> temp = null;
        temp = stack.queue1;
        stack.queue1 = stack.queue2;
        stack.queue2 = temp;
        return lastPoppedElement;
    }

    /**
     * While Queue1 is not empty, dequeue from Queue1 and Enqueue to Queue2
     * Now Queue1 is empty so enqueue X (new Value) into Queue1
     * Now Dequeue all values from Queue2 to Queue1
     *
     * @param stack
     * @param i
     */
    private void pushCostly(StackImpl<Integer> stack, int i) {
        while (!stack.queue1.isEmpty()) {
            stack.queue2.add(stack.queue1.poll());
        }
        if (isLogEnabled) {
            System.out.println(" Push (costly) " + i + " into queue1 ");
        }
        stack.queue1.add(i);
        while (!stack.queue2.isEmpty()) {
            stack.queue1.add(stack.queue2.poll());
        }
    }

    private Integer popCheaper(StackImpl<Integer> stack) {
        if (stack.queue1.isEmpty()) {
            System.out.println("Queue Underflow");
            return -1;
        }
        if (isLogEnabled) {
            System.out.println(" POP (cheaper) " + stack.queue1.peek() + " from Queue 1");
        }
        return stack.queue1.poll();
    }

    public static void main(String[] args) {
        StackUtil stack = new StackUtil(true);

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        stack.printStack(stack.arr);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.printStack(stack.arr);

        letsDo("Implement Queue Using Stack");
        stack.implementQueueUsingStack();

        letsDo("Implement Stack Using Queue");
        stack.implementStackUsingQueue();
    }
}
