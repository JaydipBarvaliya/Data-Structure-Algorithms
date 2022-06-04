package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.*;

/**
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AccountsMerge {

    public static void main(String[] args) {
        solveIt(new String[][]{
                {"John", "johnsmith@mail.com", "john00@mail.com"},
                {"John", "john_newyork@mail.com", "johnnybravo@mail.com"},
                {"John", "johnsmith@mail.com", "john_newyork@mail.com"},
                {"Mary", "mary@mail.com"}
        });
        solveIt(new String[][]{
                {"David", "0", "1"},
                {"David", "3", "4"},
                {"David", "4", "5"},
                {"David", "2", "3"},
                {"David", "1", "2"}
        });
    }

    public static void solveIt(String[][] accounts) {
        List<List<String>> accountsList = new ArrayList<>();
        for (String[] account : accounts) {
            accountsList.add(Arrays.asList(account));
        }
//        System.out.println(accountsMergeUsingDFS(accountsList));
        System.out.println(accountsMerge(accountsList));
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        /**
         * What we can do is to make first emailId as the leader of that whole group
         *
         * a b c  //  b,c have parent a
         * d e f  // e,f have parent d
         * g a d  // abc and def have parent g
         *
         * parents populated after parsing 1st account: a b c
         * a->a
         * b->a
         * c->a
         *
         * parents populated after parsing 2nd account: d e f
         * d->d
         * e->d
         * f->d
         *
         * parents populated after parsing 3rd account: g a d
         * g->g
         * a->g
         * d->g
         */
        Map<String, String> emailIdOwner = new HashMap<>();// <johnsmith@mail.com: John> , <mary@mail.com,Mary>
        Map<String, String> emailIdLeaders = new HashMap<>(); // <Key, Value> key as the emailId and Value is respective leader.
        Map<String, TreeSet<String>> leaderWithItsGroupEmails = new HashMap<>();

        // Initially all emailIds are their own owner
        for (List<String> account : accounts) {
            for (int i = 1; i < account.size(); i++) {
                emailIdLeaders.put(account.get(i), account.get(i));
                emailIdOwner.put(account.get(i), account.get(0));
            }
        }

        // Now we will change the leader to the first person in the group
        for (List<String> account : accounts) {
            String leaderOfAccount = findLeader(account.get(1), emailIdLeaders);

            // Now we will change all other emailId's owner to this first leader of account
            for (int i = 2; i < account.size(); i++) {
                emailIdLeaders.put(findLeader(account.get(i), emailIdLeaders), leaderOfAccount);
            }
        }

        // Now we just have to do union.
        for (List<String> account : accounts) {
            // This command of find Leader, will go all the way to top to find out the actual leader.
            String leaderOfAccount = findLeader(account.get(1), emailIdLeaders);

            if (!leaderWithItsGroupEmails.containsKey(leaderOfAccount)) {
                leaderWithItsGroupEmails.put(leaderOfAccount, new TreeSet<>());
            }

            for (int i = 1; i < account.size(); i++) {
                leaderWithItsGroupEmails.get(leaderOfAccount).add(account.get(i));
            }
        }

        // Now since output should be in format of
        // LeaderName ----> EmailId's
        List<List<String>> mergedAccounts = new ArrayList<>();
        for (String leaderEmail : leaderWithItsGroupEmails.keySet()) {
            String nameOfLeader = emailIdOwner.get(leaderEmail);
            List<String> account = new ArrayList<>(leaderWithItsGroupEmails.get(leaderEmail));
            account.add(0, nameOfLeader);
            mergedAccounts.add(account);
        }
        return mergedAccounts;
    }

    private static String findLeader(String email, Map<String, String> emailIdLeaders) {
        String leader = emailIdLeaders.get(email);

        if (leader != email) { // Path Compression.
            emailIdLeaders.put(email, findLeader(leader, emailIdLeaders));
            leader = emailIdLeaders.get(email);
        }
        return leader;
    }

    public static List<List<String>> accountsMergeUsingDFS(List<List<String>> accounts) {
        // Here we need 2 things
        // 1st edge between every two adjacent emailNodes.
        /**
         * Then we will do the DFS and visits all nodes
         * All related accounts will be visited in each iteration of DFS.
         */
        Map<String, List<String>> adjacentList = new HashMap<>();
        Map<String, String> emailIdUserNameMap = new HashMap<>();

        for (List<String> account : accounts) {
            String accountName = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                adjacentList.putIfAbsent(account.get(i), new ArrayList<>()); // For Each Vertex(emailId) their adjacent list is initialized.
                emailIdUserNameMap.put(account.get(i), accountName);

                // Now we will add undirected edge between adjacent email id's of the account
                // Such That
                // Account 1:  E11 E12 E13     ---->   E11----E12----E13
                // Account 2:  E21 E22 E21     ---->   E21----E22----E21
                // Note edge is bidirectional for undirected graph.

                if (i == 1)
                    continue; // Since on 0th(i-1) index Account Name is there and we can't add it to the adjacent list.
                adjacentList.get(account.get(i - 1)).add(account.get(i));
                adjacentList.get(account.get(i)).add(account.get(i - 1));
            }
        }

        List<List<String>> mergedAccounts = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        for (String email : emailIdUserNameMap.keySet()) {
            List<String> emailIdsInThisAccount = new ArrayList<>();
            if (seen.add(email)) { // If email already not visited.
                doDFS(adjacentList, email, seen, emailIdsInThisAccount);
                Collections.sort(emailIdsInThisAccount);
                emailIdsInThisAccount.add(0, emailIdUserNameMap.get(email));
                mergedAccounts.add(emailIdsInThisAccount);
            }
        }
        return mergedAccounts;
    }

    private static void doDFS(Map<String, List<String>> adjacentList, String email, Set<String> seen, List<String> emailIdsInThisAccount) {
        emailIdsInThisAccount.add(email);
        for (String adjacentEmail : adjacentList.get(email)) {
            if (seen.add(adjacentEmail)) { // On proceed if not already visited.
                doDFS(adjacentList, adjacentEmail, seen, emailIdsInThisAccount);
            }
        }
    }
}
