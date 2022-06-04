package com.interviewbit.linked_list;

import com.interviewbit.linked_list.LinkedNodeUtil.ListNode;

import java.util.Arrays;

/**
 * @author neeraj on 2019-08-11
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class AddTwoNumbersInList {

    public static void main(String[] args) {
        LinkedNodeUtil A = new LinkedNodeUtil();
        LinkedNodeUtil B = new LinkedNodeUtil();

        A.addData(Arrays.asList(9,9,1));
        B.addData(Arrays.asList(1));

        A.head = addTwoNumbers(A.head, B.head);
        A.printList();
    }

    public static ListNode addTwoNumbers(ListNode A, ListNode B) {
        int carry = 0;
        ListNode tempA = A;
        ListNode tempB = B;
        ListNode result = null;
        ListNode head = null;

        while (tempA != null && tempB != null) {
            int sum = carry + tempA.val + tempB.val;
            if (sum > 9) {
                carry = 1;
            } else {
                carry = 0;
            }
            int valueToStore = sum % 10;
            if (result == null) {
                result = new ListNode(valueToStore);
                head = result;
            } else {
                result.next = new ListNode(valueToStore);
                result = result.next;

            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        while (tempA != null) {
            int sum = carry + tempA.val;
            if (sum > 9) {
                carry = 1;
            } else {
                carry = 0;
            }
            result.next = new ListNode(sum % 10);
            result = result.next;
            tempA = tempA.next;
        }

        while (tempB != null) {
            int sum = carry + tempB.val;
            if (sum > 9) {
                carry = 1;
            } else {
                carry = 0;
            }
            result.next = new ListNode(sum % 10);
            result = result.next;
            tempB = tempB.next;
        }

        if(carry > 0) {
            result.next = new ListNode(1);
        }
        return head;
    }
}
