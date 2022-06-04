package com.geeksforgeeks.array;

/**
 *   * * * ^ * * *
     * * * | * * *
     * * * | * * *
     * * * | * * *
 *
 *  INTO
 *
 *
    * * * *
    * * * *
    * * * *
    -- - - >
    * * * *
    * * * *
    * * * *
 *
 *
 */
public class TurnImageBy90Degree {

    public static void main(String[] args) {
        char [][] image = {
                {'*','*','*','^','*','*','*'},
                {'*','*','*','|','*','*','*'},
                {'*','*','*','|','*','*','*'},
                {'*','*','*','|','*','*','*'}
        };
        turnImage(image);
    }

    public static void printImage(char [][] image) {
        for(int i=0;i<image.length;i++) {
            for(int j=0;j<image[i].length;j++) {
                System.out.print(image[i][j]);
            }
            System.out.println();
        }
    }

    public static void turnImage(char[][] image) {
        int M=image.length, N = image[0].length;

        for (int i = 0; i < N; i++) {
            for (int j = M - 1; j >= 0; j--) {
                if (image[j][i] == '*')
                    System.out.print(image[j][i]+" ");
                else if (image[j][i] == '|')
                    System.out.print("- ");
                else if (image[j][i] == '^')
                    System.out.print("> ");
            }
            System.out.println();
        }
    }
}
