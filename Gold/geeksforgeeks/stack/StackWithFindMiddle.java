package com.geeksforgeeks.stack;

import static com.util.LogUtil.logIt;

@SuppressWarnings("Duplicates")
public class StackWithFindMiddle {

    DLLNode top;
    DLLNode middle;
    int size;

    public StackWithFindMiddle() {
        size = 0;
    }

    // Helper class for Stack with Find Middle and Delete Middle operation
    class DLLNode {
        DLLNode prev;
        DLLNode next;
        int data;

        public DLLNode(int data) {
            this.data = data;
        }
    }

    /**
     * 1) Simple Rule for Pushing , the first element entered will be out last so for the first element there is no nextElement
     * but only previous element, think of this as a Stack of Trays placed, the last plate has no next and first plate has no previous
     * <p>
     * |  |
     * |77| <--- = Top Element
     * |66|
     * |55|
     * |44|
     * |33|
     * |22|
     * |11| <---- = First Element Inserted
     * ----
     * <p>
     * So how we will push after first element.
     *
     * @param data
     */
    public void push(int data) {
        logIt("Pushing Element " + data);
        DLLNode newNode = new DLLNode(data);

        if (top == null) {
            top = newNode;  // Since this is the first element so the TOP point to it.
            size++;
            middle = top; // If only 1 element hence middle element point to it.
        } else {
            DLLNode temp = top;
            temp.prev = newNode;
            temp.prev.next = temp;
            top = temp.prev;
            size++;
        }

        if (size > 1 && size % 2 != 0) {
            middle = middle.prev; // Since new node is added in the front of DLL, hence the middle is moving backwards
        }
    }

    public int pop() {
        logIt("Popping Element " + top.data);
        // If No Item in stack
        if (top == null) {
            System.out.println("Stack Underflow");
            return 0;
        }

        // If only 1 item in stack
        if (top.next == null) {
            DLLNode temp = top;
            top = middle = null;
            --size;
            return temp.data;
        }

        DLLNode temp = top;
        temp.next.prev = null;
        size--;
        top = temp.next;

        if (size % 2 == 0) {
            middle = middle.next;
        }
        return temp.data;
    }

    public void printStack() {
        logIt("Middle Element in Stack of size [" + size + "] is ");
        DLLNode temp = top;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.data == middle.data) {
                System.out.println(" <========== middle element");
            } else {
                System.out.println();
            }
            temp = temp.next;
        }
    }

    public DLLNode findMiddle() {
        printStack();
        return middle;
    }

    public void deleteMiddle() {
        logIt("Deleting the current middle Element ==>  " + middle.data, true);
        if (size == 1) {
            logIt("This is the only node present in the Stack which is " + top.data);
            --size;
            top = middle = null;
        } else {
            DLLNode prev = middle.prev;
            DLLNode next = middle.next;

            prev.next = next;
            next.prev = prev;

            --size;

            if (size % 2 == 0) { // After deletion total items inside Stack is Even.
                middle = next;
            } else {
                middle = prev;
            }

        }
    }

    public static void main(String[] args) {
        StackWithFindMiddle stack = new StackWithFindMiddle();

        // Middle Element [Assuming 0 based index]
        // int mid = firstElementIndex + (lastElementIndex - firstElementIndex) / 2;

        stack.push(11);
        stack.push(22);
        stack.push(33);
        stack.push(44);
        stack.push(55);
        stack.push(66);
        stack.push(77);

        logIt("Middle Element is " + stack.findMiddle().data, true);

        stack.deleteMiddle();
        logIt("Middle Element is " + stack.findMiddle().data, true);

        stack.deleteMiddle();
        stack.findMiddle();

        stack.push(88);
        stack.findMiddle();

        stack.pop();
        stack.findMiddle();

        stack.pop();
        stack.findMiddle();


//        logIt("Middle Element is " + stack.findMiddle().data, true);
//        System.out.println("Item popped is " + stack.pop());
//
//        logIt("Middle Element is " + stack.findMiddle().data, true);
//        System.out.println("Item popped is " + stack.pop());
//
//        logIt("Middle Element is " + stack.findMiddle().data, true);
    }
}
