package com.leetcode.year_2020.stack;

import java.util.Stack;

/**
 * https://backtobackswe.com/platform/content/implement-text-editor-undo-redo
 */
public class TextEditorUndoRedo {

    public static void main(String[] args) {
        performEditorActions(new String[][]{
                {"INSERT", "A"},
                {"DELETE"},
                {"UNDO"}
        });

        performEditorActions(new String[][]{
                {"INSERT", "A"},
                {"INSERT", "B"}
        });

        performEditorActions(new String[][]{
                {"INSERT", "A"},
                {"INSERT", "B"},
                {"UNDO"}
        });

        performEditorActions(new String[][]{
                {"INSERT", "A"},
                {"INSERT", "B"},
                {"UNDO"},
                {"REDO"}
        });

        performEditorActions(new String[][]{
                {"INSERT", "A"},
                {"INSERT", "B"},
                {"UNDO"},
                {"REDO"},
                {"REDO"}
        });
    }

    public static String performEditorActions(String[][] actions) {
        /**
         * The main gist is for insert or delete operation we push opposite action in UNDO stack
         * so that it become convenient while performing undo operations
         *
         * Also for undo operations, we will store the opposite of undo operation in REDO stack.
         */
        final Stack<Action> undoStack = new Stack<>();
        final Stack<Action> redoStack = new Stack<>();
        final StringBuilder stringBuilder = new StringBuilder();


        for (String[] action : actions) {
            switch (action[0]) {
                case "INSERT": {
                    insertCharacter(action[1].charAt(0), undoStack, stringBuilder);
                    redoStack.clear(); // Any insert or delete operation should clear the redo
                    break;
                }
                case "DELETE": {
                    deleteCharacter(undoStack, stringBuilder);
                    redoStack.clear();
                    break;
                }
                case "UNDO": {
                    if (!undoStack.isEmpty()) {
                        final Action undoAction = undoStack.pop();
                        if (undoAction.type == ActionType.INSERT) {
                            insertCharacter(undoAction.item, redoStack, stringBuilder);
                        } else {
                            deleteCharacter(redoStack, stringBuilder);
                        }
                    }
                    break;
                }
                default: {
                    // Redo
                    if (!redoStack.isEmpty()) {
                        final Action redoAction = redoStack.pop();
                        if (redoAction.type == ActionType.INSERT) {
                            insertCharacter(redoAction.item, redoStack, stringBuilder);
                        } else {
                            deleteCharacter(redoStack, stringBuilder);
                        }
                    }
                    break;
                }
            }
        }
        System.out.println("Final State " + stringBuilder);
        return stringBuilder.toString();
    }

    private static void deleteCharacter(final Stack<Action> destinationStack, final StringBuilder stringBuilder) {
        final int charToBeDeletedIndex = stringBuilder.length() - 1;
        if (charToBeDeletedIndex != -1) {
            final Character charToBeDeleted = stringBuilder.charAt(charToBeDeletedIndex);
            stringBuilder.deleteCharAt(charToBeDeletedIndex);
            final Action oppositeAction = new Action(ActionType.INSERT, charToBeDeleted);
            destinationStack.push(oppositeAction);
        }
    }

    private static void insertCharacter(final Character item, final Stack<Action> destinationStack, final StringBuilder stringBuilder) {
        stringBuilder.append(item);
        final Action oppositeAction = new Action(ActionType.DELETE, item);
        destinationStack.push(oppositeAction);
    }

    private static class Action {
        private ActionType type;
        private Character item;

        public Action(final ActionType type, final Character item) {
            this.type = type;
            this.item = item;
        }
    }

    private enum ActionType {
        INSERT, DELETE, UNDO, REDO
    }
}
