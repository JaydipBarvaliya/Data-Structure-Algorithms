package com.company.amazon;

public class SieveOfErastothenes {

    public static void main(String[] args) {
        Boolean[] primeStatus = getAllPrimeNumbersBelow(74);
        printAllPrimeNumbers(primeStatus);
    }

    public static void printAllPrimeNumbers(Boolean[] primeStatus) {
        for (int i = 2; i < primeStatus.length; i++) {
            if (primeStatus[i]) {
                System.out.print(i + ",");
            }
        }
    }

    public static Boolean[] getAllPrimeNumbersBelow(int number) {
        Boolean[] primeNumbers = new Boolean[number];
        //Assuming all numbers are prime numbers
        for (int i = 0; i < number; i++) {
            primeNumbers[i] = true;
        }

        for (int i = 2; i < Math.sqrt(number); i++) {
            if (primeNumbers[i]) {
                // Update all multiples of this prime number as non-prime
                for (int k = i * 2; k < number; k += i) {
                    primeNumbers[k] = false;
                }
            }
        }
        return primeNumbers;
    }
}
