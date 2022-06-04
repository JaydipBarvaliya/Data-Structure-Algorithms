package com.datastructures.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by jaine03 on 12/07/17.
 */
public class StackUtil {

    public static int findMaxLen(String str) {
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '('){
                stack.push(i);
            }
            else {
                stack.pop();

                if(!stack.isEmpty()){
                    result = Math.max(result, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return result;
    }

    public static Boolean canRepresentBST(int [] pre){
        Stack<Integer> stack = new Stack<>();
        Integer ROOT = Integer.MIN_VALUE;

        for(int i=0;i<pre.length;i++){

            if(pre[i] < ROOT){
                return false;
            }

            while (!stack.isEmpty() && stack.peek() < pre[i]){
                ROOT = stack.pop();
            }
            stack.push(pre[i]);
        }
        return true;
    }

    /****************************** Queue using 2 Stack    ****************************************/
    private static Stack<Integer> inbox = new Stack<>();
    private static Stack<Integer> outbox = new Stack<>();
    public static void enque(int n){
        inbox.push(n);
    }

    public static void deque(){
        if(outbox.isEmpty()){
            while (!inbox.isEmpty()){
                outbox.push(inbox.pop());
            }
            System.out.println(outbox.pop());
        } else {
            System.out.println(outbox.pop());
        }
    }
    /****************************** Queue using 2 Stack [ENDS]    ****************************************/

    /****************************** Stack using 2 Queue    ****************************************/
    private static Queue<Integer> inboxQueue = new LinkedList<>();
    private static Queue<Integer> outboxQueue = new LinkedList<>();

    public static void push(int n){
        inboxQueue.add(n);
    }

    public static void pop(){
        while (inboxQueue.size() > 1){
            outboxQueue.add(inboxQueue.poll());
        }
        System.out.println(inboxQueue.poll());
        Queue<Integer> tempQueue = inboxQueue;
        inboxQueue = outboxQueue;
        outboxQueue = tempQueue;
    }
    /****************************** Stack using 2 Queue [ENDS]   ****************************************/

    public static void main(String[] args) {
        String str = "((()()";
        System.out.println(findMaxLen(str));

        str = "()(()))))";
        System.out.println(findMaxLen(str));

        //int []pre = new int[]{40, 30, 35, 20, 80, 100};
         int [] pre= new int[]{40, 30, 35, 80, 100};
        //int []pre = new int[]{7,4,2,1,5,6,9,40};

        System.out.println("Can represent BST ? "+canRepresentBST(pre));


//        enque(1);
//        enque(2);
//        enque(3);
//        enque(4);
//        enque(5);
//
//        deque();
//        deque();
//        deque();
//        deque();
//        deque();

        push(1);
        push(2);
        push(3);
        push(4);
        push(5);

        pop();
        pop();
        pop();
        pop();
        pop();
    }
}
