package com.stackoverflow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionTest {

  public static void main(String[] args) {

    String sample = "<service elapsed=\"1,938.000\" elapsedSincePriorNode=\"0.000\" elapsedAfterLastNode=\"0.000\" start=\"4:13:00:845\" finish=\"4:13:02:783\" memoryDelta=\"-430455k\">";

//    Pattern pattern = Pattern.compile("elapsed=\".*?\"");
//    Matcher matcher = pattern.matcher(sample);
//    System.out.println(matcher.matches());
//    System.out.println(matcher.groupCount());

    Pattern p = Pattern.compile("elapsed=\\\"([^\\\"]*)\\\"");
    Matcher m = p.matcher(sample);
    while (m.find()) {
      System.out.println(m.group(1));
    }
  }
}
