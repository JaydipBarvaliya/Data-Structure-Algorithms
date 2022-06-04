package com.geeksforgeeks.array;

/**
 * https://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/?
 */
public class MaximumSumWithArrayRotation {

    public static void main(String[] args) {
        int[] sample = {8, 3, 1, 2};
//        int[] sample = {1, 2, 3, 4};
        System.out.println(getMaxSumUsingArrayRotation(sample));
//
        System.out.println(getNoOfRotationDoneToOriginalArray(sample));


        sample = new int[]{7,8,1,2,3,4,5,6};

        System.out.println("Min element in sorted rotAted array "+getMinimumElementInRotatedArray(sample, 0, 7));
    }

    /**
     * Best Optimized example
     * https://www.geeksforgeeks.org/maximum-sum-iarri-among-rotations-given-array/
     * Input : arr[] = {8, 3, 1, 2}
     * Output : 29
     * Explanation : Let us see all rotations
     * {8, 3, 1, 2} = 8*0 + 3*1 + 1*2 + 2*3 = 11
     * {3, 1, 2, 8} = 3*0 + 1*1 + 2*2 + 8*3 = 29
     * {1, 2, 8, 3} = 1*0 + 2*1 + 8*2 + 3*3 = 27
     * {2, 8, 3, 1} = 2*0 + 8*1 + 3*2 + 1*1 = 17
     * <p>
     * Actual Explanation :
     * https://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/
     * <p>
     * How to efficiently calculate Rj from Rj-1?
     * This can be done in O(1) time. Below are details.
     * <p>
     * Let us calculate initial value of i*arr[i] with no rotation
     * R0 = 0*arr[0] + 1*arr[1] +...+ (n-1)*arr[n-1]
     * <p>
     * After 1 rotation arr[n-1], becomes first element of array,
     * arr[0] becomes second element, arr[1] becomes third element
     * and so on.
     * R1 = 0*arr[n-1] + 1*arr[0] +...+ (n-1)*arr[n-2]
     * <p>
     * R1 - R0 = arr[0] + arr[1] + ... + arr[n-2] - (n-1)*arr[n-1]
     * <p>
     * After 2 rotations arr[n-2], becomes first element of array,
     * arr[n-1] becomes second element, arr[0] becomes third element
     * and so on.
     * R2 = 0*arr[n-2] + 1*arr[n-1] +...+ (n-1)*arr[n-3]
     * <p>
     * R2 - R1 = arr[0] + arr[1] + ... + arr[n-3] - (n-1)*arr[n-2] + arr[n-1]
     * <p>
     * If we take a closer look at above values, we can observe
     * below pattern
     * <p>
     * Rj - Rj-1 = arrSum - n * arr[n-j]
     * <p>
     * or
     * <p>
     * Rj = (arrSum - n * arr[n-j]) +  Rj-1
     * <p>
     * Where arrSum is sum of all array elements, i.e.,
     * <p>
     * arrSum = &Sum; arr[i]
     * 0<=i<=n-1
     * <p>
     * <p>
     * One more Explanation :
     * <p>
     * Simply put: Suppose you have 3 elements in the array:
     * <p>
     * base array sum is s= A+B+C
     * rs (rotatedSum) for initial array = 0xA+1xB+2xC
     * <p>
     * After any rotation, contribution of any elements at last position after rotation becomes zero.
     * whereas contribution all elements increases by 1.
     * So we technically want to do is:
     * (0xA+1xB+2xC) - after rotation -> ((0+1)xA+(1+1)xB+((2+1)xC) except we do not want C as C becomes zero.
     * We can further simplify the above formula as (0xA+1xB+2xC) +(A+B+C) ,
     * now from this we want to drop C which has occurred exactly number of times equal to size of array.
     * So we say RotationSum0((0xA+1xB+2xC)) +SUM((A+B+C)) - a[character at last position in before this rotation] * size of array.
     */
    public static int getMaxSumUsingArrayRotation(int[] arr) {
        int n = arr.length;
        int arrSum = 0, currentRotationSum = 0, maxSum = 0;

        for (int i = 0; i < n; i++) {
            arrSum += arr[i];
            currentRotationSum += i * arr[i];
        }
        System.out.println("Rotation (0) sum is: " + currentRotationSum);
        maxSum = currentRotationSum;

        // Now for next rotation
        for (int i = 1; i < n; i++) {
            // Similar to Ri = Ri-1 + (arrSum - (n * arr[n - i]);
            currentRotationSum = currentRotationSum + (arrSum - (n * arr[n - i]));
            System.out.println("Rotation (" + i + ") sum is: " + currentRotationSum);
            if (currentRotationSum > maxSum) {
                maxSum = currentRotationSum;
            }
        }

        return maxSum;
    }

    public static int getMaxElementIndex(int[] array) {
        int maxElem = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[maxElem] < array[i]) {
                maxElem = i;
            }
        }
        return maxElem;
    }

    /**
     * https://www.geeksforgeeks.org/find-rotation-count-rotated-sorted-array/
     * @param rotatedArray
     * @return
     */
    public static int getNoOfRotationDoneToOriginalArray(int[] rotatedArray) {
        int maxElementIndex = getMaxElementIndex(rotatedArray);

        if (maxElementIndex == rotatedArray.length - 1) {
            return 0;
        } else {
            return maxElementIndex + 1;
        }
    }


    public static int getMinimumElementInRotatedArray(int[] arr, int low, int high) {
        if (low > high)
            return arr[0];

        int mid = low + (high - low) / 2;

        if (mid > low && arr[mid] > arr[mid + 1])
            return arr[mid + 1];

        if (mid < high && arr[mid] < arr[mid - 1])
            return arr[mid];

        if (arr[mid] < arr[high])
            return getMinimumElementInRotatedArray(arr, low, mid - 1);
        else
            return getMinimumElementInRotatedArray(arr, mid + 1, high);
    }

}
