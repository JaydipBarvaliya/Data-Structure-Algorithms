package com.company.amazon;

import java.util.ArrayList;
import java.util.List;

public class StackWithFindMiddle {

    DLL top;
    DLL middle;
    Integer size = 0;

    static class DLL {
        int data;
        DLL prev;
        DLL next;

        DLL(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        StackWithFindMiddle middle = new StackWithFindMiddle();

        middle.push(11);
        middle.push(22);
        middle.push(33);
        middle.push(44);
        middle.push(55);
        middle.push(66);
        middle.push(77);

        middle.printStack();

//        middle.findMiddle();
//        middle.pop();
//        middle.findMiddle();
//        middle.pop();
//        middle.findMiddle();

        middle.deleteMiddle();
        middle.findMiddle();
        middle.deleteMiddle();
    }

    public void push(int data) {
        if (top == null) {
            top = new DLL(data);
            middle = top;
            size++;
        } else {
            DLL temp = new DLL(data);
            temp.prev = top;
            top.next = temp;
            top = top.next;
            size++;
        }

        if (size % 2 == 0) {
            middle = middle.next;
        }
    }

    public void pop() {
        if (top == null) {
            System.out.println("Underflow error");
            return;
        }
        System.out.println("Item Popped is " + top.data);
        top = top.prev;
        top.next = null;
        size--;

        if (size % 2 != 0) { // Reduce middle only in case of odd
            middle = middle.prev;
        }
    }

    public DLL findMiddle() {
        System.out.println("Middle Element is " + middle.data);
        return middle;
    }

    public void deleteMiddle() {
        if (middle == null) {
            System.out.println("Stack is empty");
            return;
        }

        if (size == 1) {
            System.out.println("Deleting " + middle.data + " and since this is the only element so list is empty now");
            middle = null;
            top = null;
            size = 0;
        } else {
            System.out.println("Deleting " + middle.data);
            DLL nextOfMiddle = middle.next;
            middle = middle.prev;
            middle.next = nextOfMiddle;
            nextOfMiddle.prev = middle;
            size--;

            if (size % 2 == 0) {
                middle = middle.next;
            }
        }
        System.out.println("After deletion ");
        printStack();
    }

    public void printStack() {
        DLL temp = top;
        List<Integer> data = new ArrayList<>();

        while (temp != null) {
            data.add(temp.data);
            temp = temp.prev;
        }
        System.out.print(data);
        System.out.println(" and top is " + top.data + " and middle element is " + middle.data);
    }
}
