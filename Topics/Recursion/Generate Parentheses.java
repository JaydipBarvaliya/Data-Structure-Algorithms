/*
Classic Backtracking Problem
For a Backtracking Problem : Remember the 3 Parts
1. Choices
2. Constraints
3. Goal Condition

1. The Choices in this case is to either put a "(" or ")"
2. The Constraints are
   (a) -> The number of openBrackets or ClosedBrackets cannot be more than n
   (b) -> A ClosedBracket cannot be added to an empty string, i.e. a corresponding open bracket is needed
   (c) -> The count of closedBracket cannot be more than that of OpenBracket at any given time
3. The Goal Condition is when the length of the string becomes 2*n

*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        backTrack("",res,0,0,n);
        return res;
    }
    
    public void backTrack(String currWord, List<String> res,int openCount, int closedCount, int n){
        //If the Goal Condition is reached
        if(currWord.length()==n*2){
            res.add(currWord);
            return;
        }
        
        //If the number of openBrackets are less than n, keeping recursively calling
        if(openCount<n){
            backTrack(currWord + "(", res, openCount+1, closedCount, n);
        }
        
        //If the count of closedBrackets is less than openCount -> It is valid to add
        if(closedCount<openCount){
            backTrack(currWord + ")", res, openCount, closedCount+1, n);
        }
        
    }
}
