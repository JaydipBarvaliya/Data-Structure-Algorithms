
package com.hackerearth.code_the_next_2k18;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BobAndEditor {

    private static List<String> editor = new ArrayList<>();
    private static Cursor cursor;
    private static String BUFFER = null;

    private static class Cursor {
        int line;
        int position; // 0 means beginning, 1 means end of line

        public Cursor(int line, int position) {
            this.line = line;
            this.position = position;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cursor = new Cursor(0, 1);
        // Find the No of lines of text i.e. N;
        int N = Integer.parseInt(br.readLine());
        int counter = 0;

        while (counter++ < N) {
            editor.add(br.readLine());
            cursor.line = counter - 1;
        }

        // Find No of Commands
        int Q = Integer.parseInt(br.readLine());
        counter = 0;

        while (counter++ < Q) {
            String command = br.readLine();
//            try {
            handleCommand(command, br);
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
        }

        // Finally Print the output
        for (String str : editor) {
            System.out.println(str);
        }
    }

    private static void handleCommand(String command, BufferedReader bufferedReader) throws Exception {
        // There are 2 special commands which actually takes Input in the command as well
        // :m [Jump to line number m]
        // i m [Insert following m lines of text into editor]
        int counter = 0;
        if (command.startsWith(":")) { // :m command
            int lineToJumpUpon = (int) (command.charAt(1) - '0');

            // Since cursor is 0 index based, So reduce 1 from it
            lineToJumpUpon--;
            if (lineToJumpUpon < 0) { // Safe check
                return;
            }
            if (lineToJumpUpon >= editor.size()) {
                cursor.line = 0;
                cursor.position = 1;
            } else {
                cursor.line = lineToJumpUpon;
                cursor.position = 1;
            }
            return;
        } else if (command.startsWith("i")) { // i m command
            String[] splitted = command.split("\\s+");
            int noOfLinesToBeInserted = Integer.parseInt(splitted[1]);
            int count = 0;
            while (count < noOfLinesToBeInserted) {
                String line = bufferedReader.readLine();

                if (count == 0) { // It might get appended into the system
                    if (editor.size() > 0 && cursor.line < editor.size()) { // Since there is some lines in the editor So let's append the 1st word
                        String lineWhereNewLineWillBeAppended = editor.remove(cursor.line);
                        editor.add(cursor.line, lineWhereNewLineWillBeAppended + line);
                        if (noOfLinesToBeInserted > 0) {
                            cursor.line = cursor.line + 1;
                            cursor.position = 1;
                        }
                    } else {
                        editor.add(line);
                        cursor.position = 1;
                        if (noOfLinesToBeInserted > 0) {
                            cursor.line = cursor.line + 1;
                        } else {
                            cursor.line = 0;
                        }
                    }
                } else {
                    editor.add(cursor.line, line);
                    cursor.position = 1;
                    if (count + 1 != noOfLinesToBeInserted) {
                        cursor.line = cursor.line + 1;
                    }
                }
                count++;
            }
            return;
        }

        switch (command) {
            case "dd": {
                if (cursor.line == 0 && editor.size() == 1) {
                    if (cursor.position == 0) { // i.e Beginning, So skip
                        break;
                    } else {
                        if (editor.size() > 0 && (cursor.line < editor.size() && cursor.line > -1)) {
                            editor.remove(cursor.line);
                            cursor.position = 0;
                        }
                    }
                } else {
                    int lineToBeRemoved = cursor.line;
                    if (editor.size() > 0 && cursor.line < editor.size()) {
                        if (cursor.line == editor.size() - 1) {
                            cursor.position = 1;
                            cursor.line = cursor.line - 1;
                        } else if (cursor.line < editor.size() - 1) {
                            cursor.position = 1;
                        }
                        editor.remove(lineToBeRemoved);
                    }
                }
                if (editor.size() <= 0) {
                    cursor.line = 0;
                    cursor.position = 0;
                }
                if (cursor.line < 0) {
                    cursor.line = 0;
                    cursor.position = 1;
                }
                break;
            }
            case "y": { // Let's copy
                if (editor.size() > 0) {
                    BUFFER = editor.get(cursor.line);
                    break;
                }
            }
            case "p": { // Let's paste
                if (cursor.position != 0 && BUFFER != null) { // i.e. there are some lines and something to paste upon
                    if (editor.size() > 0 && cursor.line < editor.size()) {
                        String lineWhereTextToBeAppended = editor.remove(cursor.line);
                        editor.add(cursor.line, lineWhereTextToBeAppended + BUFFER);
                    }
                } else if (cursor.position == 0 && BUFFER != null) {
                    editor.add(cursor.line, BUFFER);
                    cursor.position = 1;
                }
                break;
            }
            case "dy": {
                if (cursor.line == 0 && editor.size() == 0) {
                    if (cursor.position == 0) { // i.e Beginning, So skip
                        break;
                    } else {
                        if ((cursor.line < editor.size() && cursor.line > -1) && editor.size() > 0) {
                            BUFFER = editor.remove(cursor.line);
                            cursor.position = 0;
                        }
                    }
                }
                int lineToBeRemoved = cursor.line;
                if (editor.size() > 0 && cursor.line < editor.size()) {
                    if (cursor.line == editor.size() - 1) {
                        cursor.position = 1;
                        cursor.line = cursor.line - 1;
                    } else if (cursor.line < editor.size() - 1) {
                        cursor.position = 1;
                    }
                    BUFFER = editor.remove(lineToBeRemoved);
                }
                break;
            }
        }
    }
}
