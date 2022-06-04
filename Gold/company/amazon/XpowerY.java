package com.company.amazon;

import java.util.*;

public class XpowerY {
    private static Map<Integer, Integer> xPowerY = new HashMap<>();

    public static void main(String[] args) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 2; i <= 10000; i++) {
            if (canBeExpressedAsXPowerY(i)) {
                resultList.add(i);
            }
        }
        System.out.println(resultList);
    }

    public static boolean canBeExpressedAsXPowerY(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            int p = i;

            while (p <= num) {
                p = p * i;
                if (p == num) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canBeExpressedAsXPowerY1(int num) {
        int temp = num;
        Map<Integer, Integer> powerMap = new HashMap<>();
        Boolean result = false;
        for (int i = 2; i <= num; i++) {
            int power = 0;
            while (temp > 0) {
                if (temp % i == 0) {
                    temp = temp / i;
                    if (temp == 1) {
                        temp = 0;
                    }
                    power++;
                } else {
                    break;
                }
            }
            if (power > 0)
                powerMap.put(i, power);
            if (temp == 0) {
                if (powerMap.size() > 0 && hasSamePower(powerMap)) {
                    Map.Entry<Integer, Integer> entry = powerMap.entrySet().iterator().next();
                    if (entry.getValue() == 1) { // Power should not be only 1, if all power are same
                        result = false;
                    } else {
                        if (powerMap.size() == 1) {
                            if (Math.pow(entry.getKey(), entry.getValue()) == num) {
                                result = true;
//                                xPowerY.put(entry.getKey(), entry.getValue());
                            } else {
                                result = false;
                            }
                        } else {
//                        xPowerY.put(powerMap.key, entry.getValue());
                            result = true;
//                            return true;
                        }
                    }
                } else {
                    result = false;
                }
                break;
            }
        }
        return result;
    }

    public static boolean hasSamePower(Map<Integer, Integer> powerMap) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> keys = powerMap.keySet();
        for (Integer i : keys) {
            set.add(powerMap.get(i));
        }
        if (set.size() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
