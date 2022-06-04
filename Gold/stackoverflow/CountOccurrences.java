package com.stackoverflow;

public class CountOccurrences
{
  public static void main( String[] args ) throws java.lang.Exception
  {
    printOccurrences( "AAAABBCCCDAA" );
  }

  public static void printOccurrences( String str )
  {
    int len = str.length();
    char[] arr = str.toCharArray();
    char contChar = arr[0];
    int count = 0;
    StringBuffer sb = new StringBuffer();
    for( int i = 0; i < arr.length; i++ )
    {
      if( contChar == arr[i] )
      {
        count++;

        // this below condition helps you to add that last A missed frequency
        if( i + 1 >= arr.length )
        { // Since after this iteration loop will not enter the else block
          sb.append( count ).append( contChar );
        }
      }
      else
      {
        sb.append( count ).append( contChar );
        contChar = arr[i];
        count = 0;
        i--;
      }
    }
    System.out.println( sb.toString() );
  }

}
