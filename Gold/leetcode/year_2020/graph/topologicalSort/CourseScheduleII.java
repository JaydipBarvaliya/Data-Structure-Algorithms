package com.leetcode.year_2020.graph.topologicalSort;

import com.leetcode.year_2020.graph.Graph;
import com.util.LogUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule-ii/
 *
 * @author neeraj on 02/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CourseScheduleII {

    public static void main(String[] args) {
        LogUtil.printArray(findOrder(2, new int[][]{
                {1, 0}
        }));
        LogUtil.printArray(findOrder(4, new int[][]{
                {1, 0}, {2, 0}, {3, 1}, {3, 2}
        }));
        LogUtil.printArray(findOrder(2, new int[][]{
                {1, 0}, {0, 1}
        }));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        /**
         * We need to find an order such that a user will be able to pursue all courses and also completing
         * all pre-requisite courses.
         *
         * Now we can simply use Topological Sort to find out this, but the catch is not always a user will be able to
         * complete all the course and that can happen in a dead-lock situation, i.e cycle.
         * How can we detect the cycle, if there is no vertex with indegree 0 at any point in time...
         * then there must be a cycle
         *
         * 1 ----> 2 -----> 3
         *         /\      ||
         *         ||      ||
         *         ||      \/
         *         5<------4
         *
         * Here 1 has indegree 0, but once it is finished we don't have any other vertex with indegree 0 and hence
         * Cycle exist.
         */
        Graph graph = new Graph(numCourses);

        /**
         * As per question
         * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
         * which is expressed as a pair: [0,1]
         *
         * So in this scenario the indegree of 0 is 1.
         *
         * For Pair [0,1]
         * 1 ----------> 0
         */
        for (int[] prerequisite : prerequisites) {
            graph.addEdge(prerequisite[1], prerequisite[0], true);
        }

        // Now since we have the indegree.
        // Let's try to find out the first edge with 0 indegree.
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.indegree.length; i++) {
            if (graph.indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> courseSchedule = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer courseGettingFinished = queue.poll();
            courseSchedule.add(courseGettingFinished);

            for (Graph.GraphVertex nextCourseLikelyToBeFinished : graph.adjacencyListArr[courseGettingFinished]) {
                graph.indegree[nextCourseLikelyToBeFinished.value] -= 1;
                if (graph.indegree[nextCourseLikelyToBeFinished.value] == 0)
                    queue.add(nextCourseLikelyToBeFinished.value);
            }

        }

        if (courseSchedule.size() == numCourses) {
            return courseSchedule.stream().mapToInt(i -> i).toArray();
        }
        return new int[]{};
    }
}
