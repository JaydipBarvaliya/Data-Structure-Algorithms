package com.leetcode.year_2020.stack;

import java.util.Stack;

/**
 * @author neeraj on 22/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ImplementTextEditorUndoRedo {

    public static void main(String[] args) {
        performOperation(new String[][]{
                {"INSERT", "A"},
                {"DELETE"},
                {"UNDO"},
                {"UNDO"}
        });
    }

    private static void performOperation(String[][] actions) {
        System.out.println(performEditorActions(actions));
    }

    public static String performEditorActions(String[][] actions) {
        /**
         * We need 2 stack 1 for Insert and Delete operation
         * another one for UNDO and REDO cases.
         *
         */
        StringBuilder result = new StringBuilder();
        Stack<String> insertOrDeleteOperationStack = new Stack<>();
        Stack<String> undoOrRedoStack = new Stack<>();

        for (String[] action : actions) {
            if (action[0].equalsIgnoreCase("INSERT")) {
                insertOrDeleteOperationStack.push("INSERT-" + action[1]);
                result.append(action[1].charAt(0));
            } else if (action[0].equalsIgnoreCase("DELETE") && result.length() > 0) {
                insertOrDeleteOperationStack.push("DELETE-" + result.charAt(result.length() - 1));
                result.deleteCharAt(result.length() - 1);
            } else if (action[0].equalsIgnoreCase("UNDO")) {
                // We have to undo last operation in the stack.
                if (!insertOrDeleteOperationStack.isEmpty()) {
                    String[] lastOperation = insertOrDeleteOperationStack.pop().split("-");
                    if (lastOperation[0].equalsIgnoreCase("INSERT")) {
                        result.deleteCharAt(result.length() - 1);
                    } else { // If Last Operation was delete.
                        result.append(lastOperation[1].charAt(0));
                    }
                    // Update the Undo Stack.
                    undoOrRedoStack.push(lastOperation[0] + "-" + lastOperation[1]);
                }
            } else { // REDO case
                if (!undoOrRedoStack.isEmpty()) {
                    String[] lastUndoneOperation = undoOrRedoStack.pop().split("-");
                    if (lastUndoneOperation[0].equalsIgnoreCase("INSERT")) {
                        insertOrDeleteOperationStack.push("INSERT-" + lastUndoneOperation[1]);
                        result.append(lastUndoneOperation[1].charAt(0));
                    } else { // If Last Operation was delete.
                        insertOrDeleteOperationStack.push("DELETE-" + lastUndoneOperation[1]);
                        result.deleteCharAt(result.length() - 1);
                    }
                }
            }
        }
        return result.toString();
    }
}
