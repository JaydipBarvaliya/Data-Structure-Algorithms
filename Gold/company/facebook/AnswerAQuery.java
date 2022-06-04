package com.company.facebook;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class AnswerAQuery {

    public static void main(String[] args) {
        test(new int[][]{{2, 3}, {1, 2}, {2, 1}, {2, 3}, {2, 2}}, 5);
    }

    public static void test(int [][] queries, int N) {
        final ArrayList<Query> queryList = new ArrayList<>();
        for(int []query: queries) {
            queryList.add(new Query(query[0], query[1]));
        }
        LogUtil.printArray(answerQueries(queryList, N));
    }

    static class Query {
        int type;
        int index;

        public Query(final int type, final int index) {
            this.type = type;
            this.index = index;
        }
    }
    static boolean[] state;
    public static int[] answerQueries(ArrayList<Query> queries, int N) {
        state = new boolean[N+1];

        // 2 - GET
        // 1 - SET
        final List<Integer> answer = new ArrayList<>();
        for(Query query:  queries) {
            if(query.type == 1) {
                state[query.index] = true;
            } else {
                answer.add(findIndex(query.index));
            }
        }
        int []result = new int[answer.size()];
        for(int i=0;i<answer.size();i++) {
            result[i] = answer.get(i);
        }
        return result;
    }


    public static int findIndex(int value) {
        int start = 1;
        int end = state.length - 1;
        int smallestIndex = -1;
        while(start <= end) {
            int mid = start+(end-start)/2;
            if(mid == value && state[mid]) {
                smallestIndex = mid;
                break;
            }
            if(mid > value) {
                if(state[mid] == true) {
                    smallestIndex = mid;
                }
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return smallestIndex;
    }
}
