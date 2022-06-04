package com.geeksforgeeks.stack;

public class TowerOfHanoi {

    public static void main(String[] args) {
        int disks = 1500;
        towerOfHanoi(disks, 'A', 'C', 'B');
    }

    public static void towerOfHanoi(int disk, char fromRod, char toRod, char usingRod) {
        if (disk > 0) {
            towerOfHanoi(disk - 1, fromRod, usingRod, toRod);
            System.out.println("Moving disk from :" + fromRod + " ====> " + toRod + " using " + usingRod);
            towerOfHanoi(disk - 1, usingRod, toRod, fromRod);
        }
    }
}
