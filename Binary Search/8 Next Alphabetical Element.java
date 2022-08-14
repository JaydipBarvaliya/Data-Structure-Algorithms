class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
      
        int len = letters.length;
        int start = 0;
        int end   = len-1;
        int result   = -1;
        
        while(start<=end){
           
           int mid = start + (end-start)/2;
           
           if(letters[mid] == target){
                result = (mid + 1) % len;
                start = mid + 1;
           }
           else if(target < letters[mid]){
               result = mid;
               end = mid - 1;
           }   
           else if(target > letters[mid])
               start = mid + 1;
       }
        
        if(result == -1 ) return letters[0];
        else return letters[result];
    }
}
