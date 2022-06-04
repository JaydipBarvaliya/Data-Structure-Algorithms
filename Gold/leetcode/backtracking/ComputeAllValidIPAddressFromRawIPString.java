package com.leetcode.backtracking;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/restore-ip-addresses/
 * <p>
 * The IP Address Decomposition Problem - Compute All Valid IP Addresses From Raw IP String
 * <p>
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * <p>
 * Example:
 * <p>
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 *
 * @author neeraj on 2019-05-18
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ComputeAllValidIPAddressFromRawIPString {

    public static void main(String[] args) {
        restoreIpAddress("25525511135");
    }


    public static void restoreIpAddress(String rawIpAddress) {
        List<String> validIpAddress = new ArrayList<>();
        int[] path = new int[4];
        int currentSegment = 0;
        int decisionPointer = 0;
        snapShotIp(rawIpAddress, currentSegment, path, validIpAddress, decisionPointer);
        LogUtil.logIt("Ip Address restored from " + rawIpAddress);
        LogUtil.printList(validIpAddress);
    }

    private static void snapShotIp(String rawIpAddress, int currentSegment, int[] path, List<String> validIpAddress, int decisionPointer) {
        // Base Cases
        // 1) If we have reached the last segment and also our decision pointer reached end of string
        if (currentSegment == 4 && decisionPointer == rawIpAddress.length()) {
            validIpAddress.add(path[0] + "." + path[1] + "." + path[2] + "." + path[3]);
            return;
        }

        // If we either of the above checks reached first without second one  being there, so just return
        if (currentSegment == 4 || decisionPointer == rawIpAddress.length()) {
            return;
        }

        // Now we know that 1 segment can have 1<=length<=3
        for (int len = 1; len <= 3 && decisionPointer + len <= rawIpAddress.length(); len++) {
            // len is starting from 1 because substring method used below excludes the last parameter
            String snapshotIpAddr = rawIpAddress.substring(decisionPointer, decisionPointer + len);

            // Let's validate this snapshot
            Integer snapshot = Integer.parseInt(snapshotIpAddr);
            if (snapshot > 255 || rawIpAddress.charAt(decisionPointer) == '0') {
                break;
            }
            path[currentSegment] = snapshot;   // Choose

            // Explore
            // Our Decision Pointer should jump, on the total length of characters we already picked in snapshotIpAddr
            snapShotIp(rawIpAddress, currentSegment + 1, path, validIpAddress, decisionPointer + len);


            path[currentSegment] = -1; // Unchoose
        }
    }


}
