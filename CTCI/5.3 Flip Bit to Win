public class Main {
    public static void main(String[] args) {

        System.out.println(approach1(1024));
    }
    
    static int approach1(int num){
    
        int zeroCount = 0;
        int preZero = 0;
        int longest = 0;
        int i = 0;
        int j = 0;
        
        while(num!=0){
            
            if((num & 1) == 0){
                zeroCount++;
                
                   if(zeroCount == 1){
                       preZero = j;
                   }
                  else{
                       if(j-i> longest){
                            longest  = j-i;
                        }
                       i = preZero+1;
                       zeroCount = 1;
                       preZero = j;
                   }
                
            }else{
                if(j-i> longest){
                    longest  = j-i;
                }
            }
            
            num = num >>>1;
            j++;
        }
        
        if(j-i > longest){
            longest = j-i;
        }
        if(zeroCount == 0){
                longest++;
          }
        
        return longest;
    }
}
