package com.company.amazon;

import java.util.Arrays;
import java.util.List;

public class EvaluationOfExpression {

    static List<String> supportedOperations = Arrays.asList("+", "/", "*", "-");

    public static class Node {
        Node left;
        Node right;
        String data;

        Node(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Node head = new Node("+");
        head.left = new Node("*");
        head.right = new Node("-");
        head.left.left = new Node("5");
        head.left.right = new Node("4");

        head.right.left = new Node("100");
        head.right.right = new Node("20");

        System.out.println(evaluateExpression(head));

        head = new Node("+");
        head.left = new Node("*");
        head.right = new Node("-");
        head.left.left = new Node("5");
        head.left.right = new Node("4");

        head.right.left = new Node("100");
        head.right.right = new Node("/");
        head.right.right.left = new Node("20");
        head.right.right.right = new Node("2");
        System.out.println(evaluateExpression(head));
    }

    public static int evaluateExpression(Node root) {
        if (root == null) {
            return 0;
        }

        if (supportedOperations.contains(root.data)) {
            int left = evaluateExpression(root.left);
            int right = evaluateExpression(root.right);
            root.data = String.valueOf(evaluate(left, right, root.data));
        }
        return Integer.parseInt(root.data);
    }

    public static int evaluate(int operand1, int operand2, String operator) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "*":
                return operand1 * operand2;
            case "-":
                return operand1 - operand2;
            case "/":
                return operand1 / operand2;
            default:
                return 0;
        }
    }


}
