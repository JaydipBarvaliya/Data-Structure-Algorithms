package com.company.amazon;

import com.util.LogUtil;

import java.util.*;

/**
 * @author neeraj on 2019-07-28
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AmazonFreshDeliveryService {

    static int MINIMUM_DISTANCE_TO_BE_TRAVERSED = Integer.MAX_VALUE;
    public static int[] rowNumber = {-1, 0, 1, 0};
    public static int[] colNumber = {0, 1, 0, -1};

    public static void main(String[] args) {
        List<List<Integer>> area = new ArrayList<>();

        area.add(Arrays.asList(1, 0, 0));
        area.add(Arrays.asList(1, 0, 0));
        area.add(Arrays.asList(1, 9, 1));

        minimumDistance(3, 3, area);
        LogUtil.logIt("Finding Minimum Distance to be traversed by truck to reach the destination in Area " + area + " is " + MINIMUM_DISTANCE_TO_BE_TRAVERSED);

        area.clear();
        area.add(Arrays.asList(1, 1, 1, 1));
        area.add(Arrays.asList(9, 9, 1, 1));
        area.add(Arrays.asList(0, 1, 0, 1));
        area.add(Arrays.asList(1, 1, 1, 1));
        area.add(Arrays.asList(0, 1, 1, 9));

        minimumDistance(5, 4, area);
        LogUtil.logIt("Finding Minimum Distance to be traversed by truck to reach the destination in Area " + area + " is " + MINIMUM_DISTANCE_TO_BE_TRAVERSED);
    }

    static int minimumDistance(int numRows, int numColumns, List<List<Integer>> area) {
        MINIMUM_DISTANCE_TO_BE_TRAVERSED = Integer.MAX_VALUE;
        Set<String> visited = new HashSet<>();
        traverseArea51(0, 0, numRows, numColumns, area, 0, visited);
        return MINIMUM_DISTANCE_TO_BE_TRAVERSED;
    }

    /**
     * Util method which will recursively traverse all paths in this Area-51 :p to find out if we have a way to perform
     * the delivery by traversing minimum grids possible.
     *
     * @param x_Coordinate                         : x_Coordinate of the Grid where Truck is Standing
     * @param y_Coordinate                         : y_Coordinate of the Grid where Truck is Standing
     * @param numRows                              : Size of Area Vertically
     * @param numColumns                           : Size of Area Horizontally
     * @param area                                 : {@link List<List<Integer>>} defining Map of the Area telling which rows are good or which ones are not
     *                                             (1) represent, it's a good road for truck to go.
     *                                             (0) represent, road has potholes, or there is no road at all.
     *                                             (9) represent, the location at which delivery is to be done.
     * @param distanceTravelledByTruckInCurrentRun : Total distance traversed by truck in this run
     */
    static void traverseArea51(int x_Coordinate, int y_Coordinate, int numRows, int numColumns, List<List<Integer>> area, int distanceTravelledByTruckInCurrentRun, Set<String> visited) {
        visited.add(x_Coordinate + "," + y_Coordinate);
        // We reached our delivery location
        if (area.get(x_Coordinate).get(y_Coordinate) == 9) {
            if (MINIMUM_DISTANCE_TO_BE_TRAVERSED > distanceTravelledByTruckInCurrentRun) {
                MINIMUM_DISTANCE_TO_BE_TRAVERSED = distanceTravelledByTruckInCurrentRun;
            }
        }

        for (int i = 0; i < 4; i++) {
            if (isSafeToTraverseTheRoad(x_Coordinate + rowNumber[i], y_Coordinate + colNumber[i], numRows, numColumns, area, visited)) {
                traverseArea51(x_Coordinate + rowNumber[i], y_Coordinate + colNumber[i], numRows, numColumns, area, distanceTravelledByTruckInCurrentRun + 1, visited);
            }
        }
        visited.remove(x_Coordinate + "," + y_Coordinate);
    }

    /**
     * Util method just to check if it is safe for the truck to move to the next grid.
     *
     * @return True if it's safe else false
     */
    private static boolean isSafeToTraverseTheRoad(int x_Coordinate, int y_Coordinate, int numRows, int numColumns, List<List<Integer>> area, Set<String> visited) {
        return x_Coordinate >= 0 && y_Coordinate >= 0 && x_Coordinate < numRows && y_Coordinate < numColumns && area.get(x_Coordinate).get(y_Coordinate) != 0 && !visited.contains(x_Coordinate + "," + y_Coordinate);
    }
}
