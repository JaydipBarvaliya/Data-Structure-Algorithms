package com.company.google;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Google Technical Interview Question
 *
 * @author neeraj on 10/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RandomStateNameGeneratorBasedOnProportionateToProbabilityOfTotalPopulation {

    private static DecimalFormat df = new DecimalFormat("0.00");
    List<StateAndPopulation> stateAndPopulations = new ArrayList<>();
    double[] populationProbability;

    public void setStateAndPopulation(List<StateAndPopulation> stateAndPopulation) {
        this.stateAndPopulations = stateAndPopulation;
        Collections.sort(this.stateAndPopulations, (a, b) -> b.population - a.population);
        int totalPopulation = 0;
        for (int i = 0; i < stateAndPopulation.size(); i++)
            totalPopulation += stateAndPopulation.get(i).population;

        populationProbability = new double[stateAndPopulation.size()];
        double initialValue = 0.0d;
        for (int i = 0; i < stateAndPopulation.size(); i++) {
            populationProbability[i] = initialValue + stateAndPopulation.get(i).population / Double.valueOf(totalPopulation);
            initialValue = populationProbability[i];
        }
        for (int i = 0; i < stateAndPopulation.size(); i++) {
            System.out.println(stateAndPopulation.get(i).name + " -> " +
                    (Double.parseDouble(df.format(stateAndPopulation.get(i).population / Double.valueOf(totalPopulation))) * 100) + " %");
        }
        System.out.println("==========================OUTPUT=========================");
    }

    public StateAndPopulation random() {
        double random = Math.random();
        int indexOfRange = doBinarySearchAndFindRightRangeForThisRandom(populationProbability, random);
        return stateAndPopulations.get(indexOfRange);
    }

    public static int doBinarySearchAndFindRightRangeForThisRandom(final double[] populationProbability, final double random) {
        int start = 0;
        int end = populationProbability.length - 1;
        int index = 0;

        // Smallest index which can cover me.
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (populationProbability[mid] > random) {
                index = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        List<StateAndPopulation> stateAndPopulations = Arrays.asList(
                new StateAndPopulation("RAJASTHAN", 1),
                new StateAndPopulation("MP", 1),
                new StateAndPopulation("UP", 3),
                new StateAndPopulation("UK", 20),
                new StateAndPopulation("DEL", 10),
                new StateAndPopulation("MAHARASHTRA", 5),
                new StateAndPopulation("Telangana", 3)
        );
        RandomStateNameGeneratorBasedOnProportionateToProbabilityOfTotalPopulation obj =
                new RandomStateNameGeneratorBasedOnProportionateToProbabilityOfTotalPopulation();

        obj.setStateAndPopulation(stateAndPopulations);

        Map<String, Integer> stateFrequency = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
//            System.out.println(obj.random());
            StateAndPopulation stateAndPopulation = obj.random();
            stateFrequency.put(stateAndPopulation.name, stateFrequency.getOrDefault(stateAndPopulation.name, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : stateFrequency.entrySet()) {
            System.out.println(entry.getKey() + " -> " + (Double.parseDouble(df.format(entry.getValue() / 10000d)) * 100) + " %");
        }
        System.out.println(stateFrequency);
    }
}

class StateAndPopulation {
    String name;
    int population;

    public StateAndPopulation(String name, int population) {
        this.name = name;
        this.population = population;
    }

    @Override
    public String toString() {
        return "[Name : " + this.name + "," + "Population : " + this.population + "]";
    }
}
