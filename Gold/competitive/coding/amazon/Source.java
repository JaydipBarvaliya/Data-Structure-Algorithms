package com.competitive.coding.amazon;

import java.util.Scanner;

/**
 * Created by jaine03 on 22/04/17.
 */
public class Source {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            int noOfTestCases = in.nextInt();
            int testCasesCounter = 0;

            while (testCasesCounter++ < noOfTestCases) {

                int cities = in.nextInt();
                int roads = in.nextInt();

                int[][] roadMap = new int[roads][cities];

                for (int i = 0; i < roads; i++) {
                    roadMap[i][0] = in.nextInt();
                    roadMap[i][1] = in.nextInt();
                }
                //showMap(roadMap);

                int[][] connectedRoadMap = getConnectedRoadMap(roadMap, cities, roads);

                //showMap(connectedRoadMap);

                boolean resultFlag = false;

                for (int i = 1; i <= cities; i++) {
                    if (isTraversingAllRoadsPossible(roadMap[i - 1][0], connectedRoadMap, roads)) {
                        resultFlag = true;
                        break;
                    }
                }
                if (!resultFlag) {
                    System.out.println("NO");
                }

            }
        }catch (Exception e){
            System.out.println("NO");
            e.printStackTrace();
        }
   }

   public static boolean isTraversingAllRoadsPossible(int initialCity,int [][] connectedRoadMap,int totalRoads){

        int traversedRoad = 0;
        for(int i=initialCity;i<connectedRoadMap.length;){
            Boolean isConnectingRoad = false;
            for(int j=1;j<connectedRoadMap[i].length;j++){
                if(connectedRoadMap[i][j] == 1){
                    i = j;
                    isConnectingRoad = true;
                    traversedRoad++;
                    if(traversedRoad >= totalRoads){
                        if(j == initialCity){
                            System.out.println("YES");
                            return true;
                        }else{
                            return false;
                        }
                    }
                    if(i!=j){ // So we can not continue on the same road we have to jump on new road
                        break;
                    }
                }
            }
            if(!isConnectingRoad){
                i++;
            }

        }
        return false;
   }

   public static int[][] getConnectedRoadMap(int [][] roadMap,int cities,int roads){
        int [][] connectedRoadMap = new int[cities+1][cities+1];

        for(int i=0;i<roadMap.length;i++){
            connectedRoadMap[roadMap[i][0]][roadMap[i][1]] = 1;
            connectedRoadMap[roadMap[i][1]][roadMap[i][0]] = 1;
        }
        return connectedRoadMap;
    }

   public static void showMap(int [][] map){
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[i].length;j++){
                System.out.print(map[i][j]+"\t");
            }
            System.out.println();
        }
   }
}

