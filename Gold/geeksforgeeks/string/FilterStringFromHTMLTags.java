package com.geeksforgeeks.string;

public class FilterStringFromHTMLTags {

    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html> <hi> <title> Palle </title> java and android </hi>");

        String filteredString = filterMyString(stringBuilder);
        System.out.println(filteredString);
    }

    public static String filterMyString(StringBuilder stringBuilder) {
        for (int i = 0; i < stringBuilder.length(); ) {
            if (stringBuilder.charAt(i) == '>') {
                for (int j = i; j >= 0; j--) {
                    if (stringBuilder.charAt(j) == '<') {
                        stringBuilder.deleteCharAt(j);

                        // Before breaking the loop just delete that extra space which is available between tags
                        if (j < stringBuilder.length() && stringBuilder.charAt(j) == ' ') {
                            stringBuilder.deleteCharAt(j);
                        }
                        break;
                    } else {
                        stringBuilder.deleteCharAt(j);
                    }
                }
                i = 0; // We have to set it to zero because we have deleted the characters and now where the i is pointing might be the incorrect location
            } else {
                i++;
            }
        }
        return stringBuilder.toString();
    }
}
