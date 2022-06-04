package com.stackoverflow;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {

  public static void main(String[] args) throws Exception {
    File directory = new File("/Users/jaine03/Projects/github/neerajjain92/data-structures/src/com/stackoverflow/file_search_test/");
    File[] listOfFiles = directory.listFiles();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String inputQuery = null;
    List<String> result = new ArrayList<>();

    while (true) {
      System.out.println("Enter file name to be search.....or enter 'exit' to terminate the prograam");
      inputQuery = br.readLine();
      if (inputQuery.equalsIgnoreCase("exit")) {
        System.out.println("Bye thanks for visiting");
        break;
      }
      System.out.println("Searching files for ......." + inputQuery);
      for (File file : listOfFiles) {
        if (file.getName().toLowerCase().contains(inputQuery.toLowerCase())) {
          result.add(file.getName());
        }
      }
      if (result.size() > 0) {
        System.out.println("==============Files Matching the criteria==============");
        System.out.println(result);
        result = new ArrayList<>();
      } else {
        System.out.println(">>>>>>>>>No Files Matching this pattern<<<<<<<<<<<<<");
      }
    }


  }
}
