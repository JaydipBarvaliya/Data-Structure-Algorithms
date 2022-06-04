package com.geeksforgeeks.stack;

public class TwoStacks {

    int top1;
    int top2;
    int size;
    int[] arr;

    public TwoStacks(int size) {
        arr = new int[size];
        top1 = -1;
        top2 = size;
        this.size = size;
    }

    public void push1(int x) {

        // There is a place for atleast 1 element, so we push
        if (top1 < top2 - 1) {
            arr[++top1] = x;
        } else {
            System.out.println("Stack1 Overflow");
            return;
        }
    }

    public void push2(int x) {
        if (top1 + 1 < top2) {
            arr[--top2] = x;
        } else {
            System.out.println("Stack2 Overflow");
            return;
        }
    }

    public int pop1() {
        if (top1 < 0) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top1--];
    }

    public int pop2() {
        if (top2 >= size) {
            System.out.println("Stack Underflow");
            return -1;
        }
        return arr[top2++];
    }

    public static void main(String[] args) {
        TwoStacks twoStacks = new TwoStacks(5);

        twoStacks.push1(5);
        twoStacks.push2(10);
        twoStacks.push2(15);
        twoStacks.push1(11);
        twoStacks.push2(7);

        System.out.println("Popped element from" + " stack1 is " + twoStacks.pop1());
        twoStacks.push2(40);
        System.out.println("Popped element from" + " stack2 is " + twoStacks.pop2());
        System.out.println("Popped element from" + " stack2 is " + twoStacks.pop2());
        System.out.println("Popped element from" + " stack2 is " + twoStacks.pop2());
        System.out.println("Popped element from" + " stack2 is " + twoStacks.pop2());
    }
}
