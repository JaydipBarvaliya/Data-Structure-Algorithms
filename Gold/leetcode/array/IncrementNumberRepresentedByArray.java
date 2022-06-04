package com.leetcode.array;

import com.util.LogUtil;

/**
 * @author neeraj on 2019-05-19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class IncrementNumberRepresentedByArray {

    public static void main(String[] args) {
        incrementNumberInArray(new int[]{1, 2, 9});
        incrementNumberInArray(new int[]{9});
        incrementNumberInArray(new int[]{9, 9, 9, 9});
    }

    public static void incrementNumberInArray(int[] digit) {
        LogUtil.logIt("Incrementing 1 in digit");
        LogUtil.printArray(digit);

        int lastIndex = digit.length - 1;
        // Adding 1 to the last index
        digit[lastIndex] += 1;

        // Now we have to check that whether adding 1 to the index created
        // any carry, if yes then we have to propagate this carry to the end.

        // digit[i] == 10 check will let us know that  we have a carry to propagate
        for (int i = lastIndex; i > 0 && digit[i] == 10; i--) {
            digit[i] = 0;
            digit[i - 1] += 1;
        }

        // Till now everything is good and we have our answer as well, but we are missing 1 edge case
        //                _ _ _   _ _   _
        // For Example : |9|9|9|,|9|9|,|9| .... In all these cases the length of the array should be increased
        //                ----------------
        // How we know that this is the case, simple just check the item at 0th index
        // if it is 10 then we definitely have a carry coming from last index
        if (digit[0] == 10) {
            // We need a new array
            int[] revisedArr = new int[digit.length + 1];

            digit[0] = 0;
            revisedArr[0] = 1;

            // Let's copy the entire digits to newly created revisedArr
            System.arraycopy(digit, 0, revisedArr, 1, digit.length);

            digit = revisedArr;
        }
        LogUtil.logIt(" and the solution is ", true);
        LogUtil.printArray(digit);
    }
}
