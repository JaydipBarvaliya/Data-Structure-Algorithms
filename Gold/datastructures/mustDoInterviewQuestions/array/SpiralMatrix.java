package com.datastructures.mustDoInterviewQuestions.array;

import com.datastructures.array.ArrayUtil;

/**
 * Created by jaine03 on 28/07/17.
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        int M = 4;
        int N = 4;
        int a[][] = {{1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        printInSpiralForm(a);

        ArrayUtil.print2DArray(a,M,N);

        inplaceRotateMatrix(a,4);
        ArrayUtil.print2DArray(a,M,N);

    }

    public static void inplaceRotateMatrix(int [][]arr,int N){

        for(int x=0;x<N/2;x++){
            for(int y=x;y<N-x-1;y++){
                int temp = arr[x][y];

                // Move values from Right to Top
                arr[x][y] = arr[y][N-1-x];

                // Move values from Bottom to Right
                arr[y][N-1-x] = arr[N-1-x][N-1-y];

                // Move values from Left to bottom
                arr[N-1-x][N-1-y] = arr[N-1-y][x];

                //Move top to left
                arr[N-1-y][x] = temp;
            }
        }
    }

    public static void printInSpiralForm(int [][]arr){

        int k = 0; // Start of the Row
        int l = 0; // Start of the column
        int m = 4; // End of the Row
        int n = 4; // End of the Column
        int i=0;

        while (k < m && l < n){


            // Print the First Row of the Grid
            for(i=l;i<n;i++){
                System.out.print(arr[k][i]+" ");
            }
            System.out.println();
            k++;

            // Last Column of the Grid
            for(i=k;i<m;i++){
                System.out.print(arr[i][n-1]+" ");
            }
            System.out.println();
            n--;

            // Print the Last Row
            if(k < m){
                for(i=n-1;i>=l;i--){
                    System.out.print(arr[m-1][i]+" ");
                }
                m--;
                System.out.println();
            }

            // Printing the first column
            if(l < n){
                for(i=m-1;i>=k;i--){
                    System.out.print(arr[i][l]+ " ");
                }
                l++;
                System.out.println();
            }

        }

    }
}
