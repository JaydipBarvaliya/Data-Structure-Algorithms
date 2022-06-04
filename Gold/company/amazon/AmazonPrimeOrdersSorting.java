package com.company.amazon;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author neeraj on 2019-07-28
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AmazonPrimeOrdersSorting {

    final static String SPACE_CONSTANT = " ";
    final static String REGEX_FOR_ALPHABETS = ".*[a-zA-Z]+.*";

    public static void main(String[] args) {
        List<String> orderList = new ArrayList<>();
        orderList.add("zld 9312");
        orderList.add("fp kindle book");
        orderList.add("10a echo show");
        orderList.add("17g 12 25 6");
        orderList.add("ab1 kindle book");
        orderList.add("125 echo dot second generation");

        LogUtil.logIt("After applying prioritized Sort " + prioritizedOrders(6, orderList));

    }


    /**
     * Util method which will apply custom build Prioritized sort on the list of order Provided from the {@param orderList}
     *
     * @param numOrders : No of Orders present in the {@param orderList}
     * @param orderList : {@link List<String>} containing order Id's of the orders
     * @return {@link List<String>} OrderId in the sorted order as requested by the API
     * A) Give Prime orders first, sorted in lexicographic order
     * B) Finally Give Non-Prime Orders in the order which they came into the system.
     */
    public static List<String> prioritizedOrders(int numOrders, List<String> orderList) {
        List<String> prioritizedOrders = new ArrayList<>();
        List<String> primeOrders = new ArrayList<>();
        List<String> nonPrimeOrders = new ArrayList<>();

        // First Identify the Non-Prime Orders and Prime Orders,
        // there is no need to sort them because Non-Prime customers don't get preference, you should pay for it :)
        for (String order : orderList) {
            if (order.substring(order.indexOf(SPACE_CONSTANT) + 1).matches(REGEX_FOR_ALPHABETS)) {
                primeOrders.add(order);
            } else {
                nonPrimeOrders.add(order);
            }
        }

        // Now Sorting Prime Orders on lexicographic order
        Collections.sort(primeOrders, new PrimeOrdersSorter());

        primeOrders.forEach(primeOrder -> prioritizedOrders.add(primeOrder));
        nonPrimeOrders.forEach(nonPrimeOrder -> prioritizedOrders.add(nonPrimeOrder));
        return prioritizedOrders;
    }

}

/**
 * PrimeOrdersSorter class which helps to sort the Prime orders according to the special meta-data string appended to prime orders
 * This class also takes care of the cases when the meta-data is same in that case we have to sort on alphaNumericIdOfOrder
 */
class PrimeOrdersSorter implements Comparator<String> {
    @Override
    public int compare(String order1, String order2) {
        String alphaNumericIdOfOrder1 = order1.substring(0, order1.indexOf(" "));
        String alphaNumericIdOfOrder2 = order2.substring(0, order2.indexOf(" "));

        order1 = order1.substring(order1.indexOf(" "));
        order2 = order2.substring(order2.indexOf(" "));


        // If Meta-data of prime customer is equal then we have to sort based on alphaNumericIdOfOrders;
        if (order1.compareTo(order2) == 0) {
            return alphaNumericIdOfOrder1.compareTo(alphaNumericIdOfOrder2);
        } else {
            return order1.compareTo(order2);
        }
    }
}
