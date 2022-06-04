package com.datastructures.mustDoInterviewQuestions.array;

/**
 * http://www.geeksforgeeks.org/find-a-tour-that-visits-all-stations/
 * Created by jaine03 on 27/07/17.
 */
public class PetrolStationCircularTour {

    public static class PetrolStation {
        int petrol;
        int distance;

        public PetrolStation(int petrol, int distance) {
            this.petrol = petrol;
            this.distance = distance;
        }
    }

    public static void main(String[] args) {
//        PetrolStation [] petrolPumps = new PetrolStation[]
//                {new PetrolStation(6,4),
//                new PetrolStation(3,6),
//                new PetrolStation(7,3)};

        PetrolStation[] petrolPumps = new PetrolStation[]
                {new PetrolStation(4, 6),
                        new PetrolStation(6, 5),
                        new PetrolStation(7, 3),
                        new PetrolStation(4, 5)};

        System.out.println("We should start at " + getStartingStation(petrolPumps));
    }

    public static int getStartingStation(PetrolStation[] petrolPumps) {
        int n = petrolPumps.length;
        int currPetrol = petrolPumps[0].petrol - petrolPumps[0].distance;
        int start = 0;
        int end = 1;

        while (start != end || currPetrol < 0) {

            while (currPetrol < 0 && start != end) {
                currPetrol -= petrolPumps[start].petrol - petrolPumps[start].distance;
                start = (start+1)%n;

                if(start == 0 ){
                    return -1;
                }
            }

            currPetrol += petrolPumps[end].petrol - petrolPumps[end].distance;
            end = (end+1)%n;
        }
        return start;
    }
}
