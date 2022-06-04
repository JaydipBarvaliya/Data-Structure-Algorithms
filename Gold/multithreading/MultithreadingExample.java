package com.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultithreadingExample {

    public static void main(String[] args) {
        runAllMethodsSimultaneously();
    }

    public static void runAllMethodsSimultaneously() {
        Callable<String> callable1 = HelperClass::methodA;
        Callable<String> callable2 = HelperClass::methodB;
        Callable<String> callable3 = HelperClass::methodC;
        Callable<String> callable4 = HelperClass::methodD;
        Callable<String> callable5 = HelperClass::methodE;

        final MultithreadingExample multithreadingExample = new MultithreadingExample();

        // Let me create a list of all tasks and invoke them at once
        List<Callable<String>> allTasks = new ArrayList<>();
        allTasks.add(callable1);
        allTasks.add(callable2);
        allTasks.add(callable3);
        allTasks.add(callable4);
        allTasks.add(callable5);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        try {
            // This list will get the result of all tasks
            List<Future<String>> futures = executorService.invokeAll(allTasks);

            for(Future future: futures) {
                System.out.println("Task Result is  = " + future.get());
            }
            executorService.shutdown();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    // Bhai these methods can be in your same class or can be a method of different class
    // Simplicity ke liye i kept them in inner class
    static class HelperClass {
        public static String methodA() {
            System.out.println("Method A");
            return "Method A";
        }

        public static String methodB() {
            System.out.println("Method B");
            return "Method B";
        }

        public static String methodC() {
            System.out.println("Method C");
            return "Method C";
        }

        public static String methodD() {
            System.out.println("Method D");
            return "Method D";
        }

        public static String methodE() {
            System.out.println("Method E");
            return "Method E";
        }
    }
}
