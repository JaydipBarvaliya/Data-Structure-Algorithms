package com.leetcode.year_2020.graph;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/course-schedule/
 *
 * @author neeraj on 26/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CourseSchedule {

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{
                {1, 0}
        }));

        System.out.println(canFinish(2, new int[][]{
                {1, 0}, {0, 1}
        }));

        System.out.println(canFinish(3, new int[][]{
                {1, 0}, {0, 2}, {1, 2}
        }));

        System.out.println(canFinish(3, new int[][]{
                {1, 0}, {2, 1}
        }));

        LogUtil.newLine();

        System.out.println(canFinishUsingTopologicalSort(2, new int[][]{
                {1, 0}
        }));

        System.out.println(canFinishUsingTopologicalSort(2, new int[][]{
                {1, 0}, {0, 1}
        }));

        System.out.println(canFinishUsingTopologicalSort(3, new int[][]{
                {1, 0}, {0, 2}, {1, 2}
        }));

        System.out.println(canFinishUsingTopologicalSort(3, new int[][]{
                {1, 0}, {2, 1}
        }));
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        /**
         *  So this problem is a classic example of finding a cycle in graph
         *   if pre-requisite has a cycle there is no way user can complete
         *.  all course.
         **/
        Graph graph = new Graph(numCourses);
        for (int[] requirement : prerequisites) {
            graph.addEdge(requirement[1], requirement[0], true);
        }
        LogUtil.logIt("Can finish " + numCourses + " courses with given prerequisite ?");
        LogUtil.printMultiDimensionArray(prerequisites);
        return !graph.ifGraphHasCycle(graph);
    }

    public static boolean canFinishUsingTopologicalSort(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) return true;

        // Represent Graph as 2D matrix(Rows and COls)
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }
        int[] indegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int currCourse = prerequisite[0];
            int preReqCourse = prerequisite[1];
            if (!graph[preReqCourse].contains(currCourse)) { // Only increasing the indegree, if this preReqCourse hasn't explored before for the same course
                // i.e to avoid duplicates in the input
                indegree[currCourse]++;
            }
            graph[preReqCourse].add(currCourse); // Adding the dependency, i.e currCourse has a dependency on preReqCourse to be completed
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int countOfCourse = 0;
        while (!queue.isEmpty()) {
            int course = queue.remove();
            countOfCourse++;
            for (int i = 0; i < numCourses; i++) {
                if (graph[course].contains(i)) { // Checking if we have any dependent course on this preRequisite.
                    if (--indegree[i] == 0) {
                        queue.add(i); // Adding only those courses to queue whose pre-requisite is completed.
                    }
                }
            }
        }
        return countOfCourse == numCourses;
    }
}
